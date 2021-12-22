package kr.or.ddit.academic.professor.lecture.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.professor.lecture.dao.LectureMainDAO;
import kr.or.ddit.academic.professor.lecture.service.LectureMainService;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureMainController {

	@Inject
	private LectureMainService service; 
	
	@Inject
	private LectureMainDAO dao; 
	
	//강의 전환할때 무조건 여기 태우기 
	//강의페이지선택하는 화면 
	@RequestMapping(value="/academic/professor/lectureMain.do", method=RequestMethod.GET)
	public String showLecturePage( 
			@AuthenticationPrincipal(expression="authMember") MemberVO member, 
			Model model
			) {
		String memId= member.getMemId();
		String majorName = service.getMajorName(memId);
		model.addAttribute("majorName", majorName);
		
		return "academic/professor/lectureMain"; 
	} 
	
	//강의리스트 출력 
	@RequestMapping(value="/academic/professor/lectureMain.do", method=RequestMethod.POST)
	public String MyLectureListPage( 
			@AuthenticationPrincipal(expression="authMember") MemberVO member, 
			@RequestParam(value="collegeCode", required=false) String collegeCode, 
			@RequestParam(value="classCode", required=true) String classCode, 
			@ModelAttribute("myClass") ClassVO myClass, 
			Model model, HttpServletRequest req			
			) {
		HttpSession session = req.getSession(); 

		String memId= member.getMemId();
		String majorName = service.getMajorName(memId);
		model.addAttribute("majorName", majorName);	
		//---/		
		PagingVO<ClassVO> pagingVO = new PagingVO<>(3, 3);
		String message = null; 
		
		myClass.setMemNo(memId);
		
		if(StringUtils.isNotBlank(myClass.getClassDate())
			&&StringUtils.isNotBlank(myClass.getClassSemester())	
		) {

		  List<ClassVO> myClassList = service.retrieveMyLecture(myClass); 
		  log.info("$$$$$$$$$$$$$$$  사이즈는 " + myClassList.size());
		  pagingVO.setDataList(myClassList);
	    }else {
				message= "학년/학기를 선택 하십시오.";
		}
			
			model.addAttribute("pagingVO", pagingVO);
			session.setAttribute("message", message);

			return "academic/professor/lectureMain"; 
		} 
	
	//강의하나 출력하기  
	@RequestMapping(value="/academic/professor/lecturePage/main.do", method=RequestMethod.GET)
	public String asdf( 
			@RequestParam(value="classNo", required=false) String classNo,
			Model model, HttpSession session
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			) {
		
		    
		    if(classNo == null) {
				classNo = (String) session.getAttribute("classNo");
			}else {
				session.setAttribute("classNo", classNo);			
			}
		    Map<String, Object> professorLecturePageMap = new HashMap<>();
		    professorLecturePageMap.put("memId", authMember.getMemId());
		    professorLecturePageMap.put("classNo", classNo);
			Map<String, Object> resultMap = service.retrieveProfessorLecturePage(professorLecturePageMap);
			model.addAttribute("resultMap", resultMap);
			
			return "academic/common/lecturePage/oneLecturePage"; 
		} 
	
	//강의평가폼 등록여부
	@RequestMapping(value="/academic/professor/lecturePage/checkEva.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String eveCheck(
			HttpSession session
			) {
		session.getAttribute("classNo");
		
		
		return null;
	}
	
	
	
	
	//화상강의개설
	@RequestMapping(value="academic/professor/lecturePage/RTC.do")
	public String webRtc(
			HttpServletRequest req
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,Model model
			) {
			HttpSession session = req.getSession();
			String memId = authMember.getMemName();
			String classNo = (String) session.getAttribute("classNo");
			
			model.addAttribute("memId", memId);
			model.addAttribute("classNo", classNo);
			
		return "academic/professor/lecturePage/openRtc";
	}
	
	//화상강의시작시 수강생들 전체 출석처리
	@RequestMapping(value="academic/professor/webRtc/Attend.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public Map<String, Integer> attend(
			@RequestParam("classNo") String classNo
			) {
		log.info("화상강의 전체출석처리 들어옴");
		
		//해당강의를 듣는 수강생 리스트
		List<ClassListVO> classList =  service.retrieveClassList(classNo);
		log.info("수강생들 정보 : {}", classList);
		
		//리스트내의 수강생들 출석 처리
		Map<String, Integer> res = service.stuAttend(classList);
		
		return res;
	}
	
	
}
