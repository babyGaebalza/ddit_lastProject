package kr.or.ddit.academic.assistant.main.controller;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.academic.assistant.main.service.AssistantMainService;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
public class AssistantMainController {
	@Inject
	private AssistantMainService service;
	
	@RequestMapping("/main/assistantMain.do")
	public String assistantMain(
		@AuthenticationPrincipal(expression="authMember") MemberVO authMember
		, Model model
	) {
		model.addAttribute("mainAssistant", service.retrieveMainAssistant(authMember));
		
		return "academic/assistant/AssistantMain"; 
	}
}
