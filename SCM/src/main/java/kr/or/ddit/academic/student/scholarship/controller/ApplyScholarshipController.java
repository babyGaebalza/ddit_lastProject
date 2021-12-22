package kr.or.ddit.academic.student.scholarship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.MemberVO;

@Controller
public class ApplyScholarshipController {

	//장학생 신청 폼으로 이동  
	@RequestMapping(value="/student/scholarshipApplyForm.do", method=RequestMethod.GET)
	public String applyScholarship(MemberVO MemberVO) {
		String viewName= null; 
		
		viewName = "academic/student/scholarshipApplyForm" ; 
						
		return viewName; 
		
	} 
	
}
