package kr.or.ddit.common.evaluation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.classfile.annotation.ElementValue;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.student.lecture.service.StudentLecturePageService;
import kr.or.ddit.administration.vo.EvalcontentVO;
import kr.or.ddit.administration.vo.EvaluationListVO;
import kr.or.ddit.administration.vo.EvaluationVO;
import kr.or.ddit.common.evaluation.dao.evaluationDao;
import kr.or.ddit.common.evaluation.service.evaluationService;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class evaluationController {

	
	@Inject
	private evaluationService service;
	
	@Inject
	private StudentLecturePageService studentLecturePageService;
	
	@Inject
	private evaluationDao dao;
	
	private String getClassName(String classNo) {
		return service.getClassName(classNo);
	};
	
	//강의평가폼 등록여부
	@RequestMapping(value="/common/evaluation/evaCheck.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String eveCheck(
			HttpSession session
			) {
		ClassListVO classinfo = new ClassListVO();
		String classNo = (String) session.getAttribute("classNo");
		classinfo.setClassNo(classNo);
		int res = service.retrieveEvaCheck(classinfo);
		String result = "";
		if(res >= 1) {
			result = "FAIL";
		}
		else {
			result="OK";
		}
		
		
		return result;
	}
	

	//강의평가 폼 접근(교수가 만드는 부분)
	@RequestMapping("/common/evaluation/evaInForm.do")
	public String evaForm(
			HttpSession session
			,Model model
			) {
		String classNo = (String) session.getAttribute("classNo");
		
		model.addAttribute("classNo", classNo);
		
		return "academic/professor/evaInForm";
	}
	
	//완성된 강의평가 폼 등록하는 부분(교수)
	@RequestMapping("/common/evaluation/evaInputForm.do")
	public String insertEVAForm(
			HttpSession session
			,@ModelAttribute(value="EvaluationListVO") EvaluationListVO evaluationList
			) {
		String classNo = (String) session.getAttribute("classNo");
		
		List<EvaluationVO> eva = evaluationList.getEvaluationList();
		for(EvaluationVO content : eva) {
			
			EvalcontentVO eval = new EvalcontentVO();
				eval.setClassNo(classNo);
			
			String cont = content.getEveCont();
			log.info("내용 : {}", cont);	
				eval.setEvaCont(cont);
				
			service.createEvaForm(eval);
		}
		
		return "redirect:/academic/professor/lecturePage/main.do?classNo="+classNo;
	}
	
	
	//학생 강의평가 여부 확인
	@RequestMapping(value="/common/evaluation/stuEvaCheck.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public String stuEvaCheck(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,HttpSession session
			) {
		log.info("체크 들어옴");
		String result = null;
		
		String memNo = authMember.getMemId();
		log.info("memId : {}", memNo);
		String classNo = (String) session.getAttribute("classNo");
		
		//이 사람이 이강의를 듣는지 체크 -> 들으면 수강번호를 리턴한다.
		ClassListVO classList = new ClassListVO();
		classList.setMemNo(memNo);
		classList.setClassNo(classNo);
		String classListNo = service.retrieveStuClassCheck(classList);
		
		if(classListNo.equals("No")) {//수강하지 않는 경우
		//수강하지않으면 여기 올 수가 없을텐데?
			log.info("수강하지 않는 사람으로 판명됨");
			return result = "수강하지 않는 사람입니다.";
		}
		else {
			log.info("수강하는 사람임");
			EvaluationVO eva = new EvaluationVO();
			eva.setClassNo(classNo);
			eva.setClasslistNo(classListNo);
			
			int res = service.retrieveStuEvaCheck(eva);
			if(res > 0) {	//여기 걸리면 이미 강의평가 한사람임
				log.info("이미 강의평가한 사람임");
				return result = "이미 강의평가를 하셨습니다.";
			}
			else {	//아직 강의평가 안했음
				log.info("아직 강의평가 안함");

				int formCheck = service.retrieveEvaCheck(classList);
				if(formCheck > 0) {//강의평가 폼이 작성되어있는지 판단
					log.info("강의평가 화면으로 넘겨야함");
					return result = "OK";
				}else {
					log.info("아직 양식이 준비되지않음");
					return result = "아직 강의평가 기간이 아니거나, 양식이 작성되지 않았습니다.";
				}
				
				
				
				
			}
		}

	}
	
	
	//학생 성적조회이전 강의평가 여부 확인하는 부분
	@RequestMapping(value="/common/evaluation/stuScoreEvaCheck.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String stuScoreEvaCheck(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,HttpSession session
			) {
		
		log.info("체크 들어옴");
		String result = null;
		
		String memNo = authMember.getMemId();
		log.info("memId : {}", memNo);
		String classNo = (String) session.getAttribute("classNo");
		
		//이 사람이 이강의를 듣는지 체크 -> 들으면 수강번호를 리턴한다.
		ClassListVO classList = new ClassListVO();
		classList.setMemNo(memNo);
		classList.setClassNo(classNo);
		String classListNo = service.retrieveStuClassCheck(classList);
		
		if(classListNo.equals("No")) {//수강하지 않는 경우
		//수강하지않으면 여기 올 수가 없을텐데?
			log.info("수강하지 않는 사람으로 판명됨");
			return result = "수강하지 않는 사람입니다.";
		}
		else {
			log.info("수강하는 사람임");
			EvaluationVO eva = new EvaluationVO();
			eva.setClassNo(classNo);
			eva.setClasslistNo(classListNo);
			
			int res = service.retrieveStuEvaCheck(eva);
			if(res > 0) {	//여기 걸리면 이미 강의평가 한사람임
				log.info("성적조회 화면으로 넘겨야함");
				return result = "OK";
			}
			else {	//아직 강의평가 안했음
				log.info("강의평가 화면으로 넘겨야함");
				return result = "강의평가를 하셔야합니다.";
			}
		}

	}
	
	
	
	//학생-강의평가하는 페이지로 연결
	@RequestMapping("/common/evaluation/stuEVA.do")
	public String stuEVA(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,HttpSession session
			,Model model
			) {
		String classNo = (String) session.getAttribute("classNo");
		String memId = authMember.getMemId();

		List<EvalcontentVO> evalContent = service.retrieveEvalContent(classNo);
		model.addAttribute("evalContent", evalContent);
		model.addAttribute("classNo", classNo);
		return "academic/student/lecturePage/doEvaluation";

	}
	
	
	//강의평가 결과처리
	@RequestMapping("/common/evaluation/stuInput.do")
	public String insertEvaluation(
			HttpSession session
			,@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,@ModelAttribute(value="EvaluationListVO") EvaluationListVO evaluationList
			,Model model
			) {
		log.info("강의평가 제출들어옴");
		log.info("데이터 : {}", evaluationList);
		List<EvaluationVO> evaList = evaluationList.getEvaluationList();
		String classNo = (String) session.getAttribute("classNo");
		String memId = authMember.getMemId();
		ClassListVO classList = new ClassListVO();
		
		classList.setMemNo(memId);	// 작성자(학생)
		classList.setClassNo(classNo);	//강의관리번호
		String classListNo = service.retrieveStuClassCheck(classList);	//수강관리번호
		
		String classCont = evaList.get(0).getClassCont();
		
		
		for(EvaluationVO eva : evaList) {	// 설문내용 저장하기
			String eveCont = eva.getEveCont();
			int evePoint = eva.getEvePoint();
			String eveconNo = eva.getEveconNo();
			
			EvaluationVO evaVO = new EvaluationVO();
			evaVO.setClasslistNo(classListNo);
			evaVO.setEveconNo(eveconNo);
			evaVO.setClassNo(classNo);
			evaVO.setEveCont(eveCont);
			evaVO.setEvePoint(evePoint);
			
			service.createEvaluation(evaVO);	//저장하는 부분
		}
	
		// 설문(문장형) 저장하기
		ClassListVO inevacheck = new ClassListVO();
		inevacheck.setClasslistNo(classListNo);
		inevacheck.setClassNo(classNo);
		inevacheck.setMemNo(memId);
		inevacheck.setClassCont(classCont);
		
		service.insertEvaCheck(inevacheck);
		
		//완료 후 다시 강의페이지로
		Map<String, Object> studentLecturePageMap = new HashMap<>();
		
		studentLecturePageMap.put("memId", authMember.getMemId());
		studentLecturePageMap.put("classNo", classNo);
		Map<String, Object> resultMap = studentLecturePageService.retrieveStudentLecturePage(studentLecturePageMap);
		model.addAttribute("resultMap", resultMap);
		
		return "redirect:/student/lecturePage/main.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//강의평가 결과 조회
	@RequestMapping("/common/evaluation/profEvaDetail.do")
	public String evaluationDetail(
			HttpSession session
			,@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,Model model
			) {
			log.info("평가조회 들어옴");
		String classNo = (String) session.getAttribute("classNo");
		String className = getClassName(classNo);
		
		//강의평가 참가학생수 가져오기
		List<String> EvaCount = service.getEvaCount(classNo);
		int eveTotalCount = EvaCount.size();
			log.info("강의평가 참가인원수 : {}", eveTotalCount);
		
		//강의평가 내용 전체 가져오기
		List<EvaluationVO> evaResultList = service.propEvaResult(classNo);
		
		//강의평가 질문갯수
		int QCount = service.getQCount(classNo);
		log.info("질문개수 : {}", QCount);
		
		int[] pointList = new int[QCount];	//점수 배열
		log.info("점수리스트 : {}",pointList );
		
			//강의평가 질문별 분류
			for(EvaluationVO eva : evaResultList) {
				log.info("처리할 설문결과 : {}", eva);
				int evaconNo = Integer.parseInt(eva.getEveconNo());	// 질문번호

				if(pointList[evaconNo-1] != 0) { // 리스트에 이미 값이 있다면?					
					int listPoint = pointList[evaconNo-1];	//저장되어있는 점수
					int newPoint = eva.getEvePoint()+listPoint;	//합쳐진 점수
					pointList[evaconNo-1] = newPoint / eveTotalCount;	//합쳐진 점수 저장
					log.info("점수저장리스트 : {}", pointList);
				}
				else {	// 리스트가 비어있다면?
					log.info("저장될 인덱스 : {}", evaconNo-1);
					pointList[evaconNo-1] = eva.getEvePoint() / eveTotalCount;
					log.info("점수저장리스트 : {}", pointList);
				}
			}

		
		//강의평가 내용(문장형) 전체 가져오기
		List<String> evaContList = service.propEvaContResult(classNo);
		
		model.addAttribute("classNo", classNo);
		model.addAttribute("className", className);
		model.addAttribute("eveTotalCount", eveTotalCount);
		model.addAttribute("pointList", pointList);
		model.addAttribute("evaResultList", evaResultList);
		model.addAttribute("evaContList", evaContList);
		return "academic/professor/lecturePage/eveResult";
	}
	

	
}
