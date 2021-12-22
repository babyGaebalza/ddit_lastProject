package kr.or.ddit.academic.student.mainpage.controller;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.academic.student.mainpage.service.StudentMainService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentMainController {
	
	@Inject
	private StudentMainService studentMainService;

	
	@RequestMapping(value="/student/main.do", method=RequestMethod.GET)
	public String studentMain(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model
			) {
		
		model.addAttribute("mainContent", studentMainService.retrieveMainContent(authMember));
		
		return "academic/studentMain/main/studentMain"; 
		
	} 
	
	@GetMapping("/student/score/{requestKind}")
	public String scoreView(
			@AuthenticationPrincipal(expression="authMember") MemberVO student
			, Model model
			, @PathVariable("requestKind") String requestKind
			) {
		
		String viewName = null;
		model.addAttribute("scoreMap", studentMainService.retrieveScoreMap(student.getMemId()));
		
		// 성적 페이지 요청일 경우
		if(requestKind.equals("scoreView")) {
			viewName = "academic/studentMain/score/scoreView";			
		// 성적 엑셀 다운로드 요청일 경우
		}else if(requestKind.equals("excelDownload")) {
			viewName = "scoreExcelDownload";
		}
		
		return viewName;
	}
	
	
}
