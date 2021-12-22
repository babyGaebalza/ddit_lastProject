package kr.or.ddit.academic.assistant.advisormanage.controller;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.assistant.advisormanage.service.AdvisorManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdvisorManageController {
	@Inject
	private AdvisorManageService service;
	
	//학과 학생 리스트
	@RequestMapping("/advisorManage/advisorManageList.do")
	public String advisortManageList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
		, @AuthenticationPrincipal(expression="authMember") MemberVO member
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
		) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		searchVO.setSearchWord2(member.getMemMajor());
		pagingVO.setSearchVO(searchVO);
		
		service.retreiveStudentList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("member", member);
		
		return "academic/assistant/advisorManage/AdvisorManageList";
	}
	
	//지도교수 리스트
	@RequestMapping("/advisorManage/advisorList.do")
	public String advisorList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
		, @AuthenticationPrincipal(expression="authMember") MemberVO member
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
		) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		searchVO.setSearchWord2(member.getMemMajor());
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveAdviserList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("member", member);
		
		return "academic/assistant/advisorManage/AdvisorList";
	}
	
	//학생 상세정보
	@RequestMapping("/advisorManage/advisorDetail.do")
	public String studentManageDetail(
			@RequestParam("memId") String memId
			, Model model
		) {
		MemberVO member = service.retrieveAdviser(memId);
		model.addAttribute("member", member);
		
		return "academic/assistant/advisorManage/AdvisorManageDetail";
		
	}
	
	//교수 정보수정
	@RequestMapping("/advisorManage/advisorUpdate.do")
	public String advisorUpdate(
		@RequestParam("memId") String memId
		, Model model
		) {
		MemberVO member = service.retrieveAdviser(memId);
		model.addAttribute("member", member);
		
		return "academic/assistant/advisorManage/AdvisorManageEdit";
	}
	//교수 정보수정
	@RequestMapping(value="/advisorManage/advisorUpdate.do", method=RequestMethod.POST)
	public String advisorUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member,
			Errors errors,
			Model model
		) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyAdviser(member);
			
			switch (result) {
			case OK:
				viewName = "redirect:/advisorManage/advisorDetail.do?memId=" + member.getMemId();
				break;
			default:
				viewName = "academic/assistant/advisorManage/AdvisorManageList";
				message = "오류! 잠시 후 시도해주세요.";
			}
		} else {
			viewName = "assistant/advisorManage/AdvisorManageEdit";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
	
}