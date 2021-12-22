package kr.or.ddit.academic.professor.scholarship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.MemberVO;

@Controller
public class RecommendScholarshipController {

	//장학생 추천 폼으로 이동  
	@RequestMapping(value="/professor/scholarshipRecommendForm.do", method=RequestMethod.GET)
	public String recommendScholarship(MemberVO MemberVO) {
		String viewName= null; 
		
		viewName = "academic/professor/scholarshipRecommendForm" ; 
						
		return viewName; 
		
	} 
	
}
