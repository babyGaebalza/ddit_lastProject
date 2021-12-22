package kr.or.ddit.academic.professor.myStudent.controller;

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

import kr.or.ddit.academic.professor.myStudent.service.ManageStudentService;
import kr.or.ddit.academic.student.graduation.service.GraduaitonManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ManageStudentController {
	@Inject
	private ManageStudentService service;
	@Inject
	private GraduaitonManageService gmService;
	
	//데이터 구성 
	private void makeModel(int currentPage, SearchVO searchVO, Model model) {
		PagingVO<DocumentVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<DocumentVO> docuList =  service.retrieveMyStudentList(pagingVO);		
		
		pagingVO.setDataList(docuList);
		
		model.addAttribute("pagingVO", pagingVO);
	}
	
	//학생졸업신청리스트 출력 
	@RequestMapping(value="/professor/stuGraduManage.do", method=RequestMethod.GET) 
	public String showMyStudentGarduList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
			@AuthenticationPrincipal(expression="authMember") MemberVO member, 
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model
			) {
		String memId = member.getMemId(); 
		searchVO.setSearchWord(memId);
		searchVO.setSearchWord2("DOC11");
		makeModel(currentPage, searchVO, model);
		return "academic/professor/myStudentManage"; 	
	}
	
	
	//학생트랙신청리스트 출력 
	//
	@RequestMapping(value="/professor/stuTrackManage.do", method=RequestMethod.GET)
	public String showMyStudentTrackList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
			@AuthenticationPrincipal(expression="authMember") MemberVO member, 
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model
			) {
	
		String memId = member.getMemId(); 
		searchVO.setSearchWord(memId);
		searchVO.setSearchWord2("DOC10");
		makeModel(currentPage, searchVO, model);
		return "academic/professor/myStudentManage"; 	
	} 
	
	
	// 승인 || 승인취소 
	@RequestMapping(value="/professor/stuTrackGraduAdmin.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void adminTrack(
			@RequestParam(value="docuNo", required=true)String docuNo, 
			@RequestParam(value="cancle", required=false)String cancle, 
			@RequestParam(value="flag", required=false)String flag, 
			@RequestParam(value="stuId", required=true)String stuId, 
			@RequestParam(value="trackNo", required=false)String trackNo, 
			HttpServletRequest req
			) {
		String message = null;
		HttpSession session = req.getSession();
		ServiceResult result = null;
		stuId = stuId.trim();
		
		log.info("학생아이디###########################" + stuId);
		log.info("어떤걸로 들어왔는지###########################" + flag);
		log.info("트랙번호 잘들어왔는지 "   + trackNo); 
		log.info("cancle 이  Y인지 null인지 ############ "   + cancle); 
		boolean trackfFlag = false; 
			
		if("DOC10".equals(flag)) {
			trackfFlag  = true; 
		} 
		
		
		//트랙인경우 
		if(trackfFlag) {
			log.info("$$$$$$$$$$트랙으로 들어옴");
			if("Y".equals(cancle)) { //승인취소하기
				log.info("$$$$$$$$$$트랙 승인취소로 들어옴");

			 result = service.cancleTrack(docuNo, stuId); 		
			} else { //승인하기 
				log.info("$$$$$$$$$$트랙 승인으로 들어옴");

			 result = service.adminTrack(docuNo, stuId,trackNo); 		
			}
		}
		
		//졸업인경우 
		else { 
			log.info("$$$$$$$$$$졸업으로 들어옴");

			if("Y".equals(cancle)) { //승인취소하기
			 result = service.cancleGradu(docuNo, stuId); 		
			} else { //승인하기 
				log.info("$$$$$$$$$$승인으로 들어옴");
			 result = service.adminGradu(docuNo, stuId); 		
			}
			
		}
		switch (result) {
		case OK:    
			message ="처리되었습니다.";
			break;
		default:    
			message = "서버 오류";
			break;   
		}
		session.setAttribute("message", message);	
	} 
	
	//담당학생 데이터 출력
	@RequestMapping(value="/professor/myStuGraduInfo.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public   Map<String, Object> adminTrack(
			@RequestParam(value="stuId", required=true)String stuId, Model model
			) {
		   log.info("########################컨트롤러 옴.파라미터"+stuId);

		   MemberVO myStudent = service.getMyStudent(stuId.trim());
		   Map<String, Object> resultMap = gmService.retrieveStudentGraduationPage(myStudent);
		   model.addAttribute("resultMap", resultMap);				
		   return resultMap;
	}

	
}
