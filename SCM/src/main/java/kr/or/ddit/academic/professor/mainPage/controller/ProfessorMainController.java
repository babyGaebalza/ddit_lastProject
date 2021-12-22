package kr.or.ddit.academic.professor.mainPage.controller;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.academic.professor.mainPage.service.ProfessorMainService;
import kr.or.ddit.academic.student.mainpage.service.StudentMainService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class ProfessorMainController {

	@Inject
	private  ProfessorMainService service;
   
   @RequestMapping(value="/professor/main.do", method=RequestMethod.GET)
   public String showMainProfessor(
		   @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model
		   ) {
      
		model.addAttribute("mainContent", service.retrieveProfMainContent(authMember));
      
      return "academic/professor/professorMain"; 
      
   } 
   
}

 