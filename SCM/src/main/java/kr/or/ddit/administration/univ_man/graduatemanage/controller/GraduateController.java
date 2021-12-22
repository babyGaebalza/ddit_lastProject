package kr.or.ddit.administration.univ_man.graduatemanage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.univ_man.graduatemanage.service.GraduateService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class GraduateController {
	
	@Inject
	private GraduateService service;
	
	@RequestMapping("/univ_man/graduateList.do")
	public String graduateList(
		  @RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);;
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveGraduateList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/univ_man/graduate/GraduateList";
	}
	
	@RequestMapping("/univ_man/graduateView.do")
	public String graduateView(
		  @RequestParam("memId") String memId
		, Model model
	) {
		MemberVO member = service.retrieveGraduate(memId);
		model.addAttribute("member", member);
		
		return "administration/univ_man/graduate/GraduateDetail";
	}
	
	@RequestMapping("/univ_man/graduateUpdate.do")
	public String updateForm(
		  @RequestParam("memId") String memId
		, Model model
	) {
		MemberVO member = service.retrieveGraduate(memId);
		model.addAttribute("member", member);
		
		return "administration/univ_man/graduate/GraduateUpdateForm";
	}
	
	@RequestMapping(value="/univ_man/graduateUpdate.do", method=RequestMethod.POST)
	public String graduateUpdate(
		  @ModelAttribute("member") MemberVO member
		, Errors errors
		, Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyGraduate(member);
			switch(result) {
				case OK:
					viewName = "redirect:/univ_man/graduateView.do?memId="+member.getMemId();
					break;
				default:
					viewName = "administration/univ_man/graduate/GraduateList";
					message = "서버오류, 다시 시도 하세요";
			}
		} else {
			viewName = "administration/univ_man/graduate/GraduateUpdateForm";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	@RequestMapping(value="/univ_man/graduateDelete.do", method=RequestMethod.POST)
	public String graduateDelete(
		@RequestParam("memId") String memId
		, RedirectAttributes redirectAttributes
	) {
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		
		ServiceResult result = service.removeGraduate(member);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK :
			viewName = "redirect:/univ_man/graduateList.do";
			break;
		default :
			viewName = "administration/univ_man/graduate/GraduateList";
			message = "서버 오류";
			break;
		}
		redirectAttributes.addFlashAttribute("message", message);
		return viewName;
	}
	
	@RequestMapping("/univ_man/graduateInsert.do")
	public String insertForm(
		@ModelAttribute("member") MemberVO member
	) {
		return "administration/univ_man/graduate/GraduateInsertForm";
	}
	
	
	@RequestMapping(value="/univ_man/graduateInsert.do", method=RequestMethod.POST)
	public String graduateInsert(
		  @ModelAttribute("member") MemberVO member
		, Errors errors
		, Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createGraduate(member);
			switch(result) {
				case OK :
					viewName = "redirect:/univ_man/graduateView.do?memId="+member.getMemId();
					break;
				default :
					viewName = "administration/univ_man/graduate/GraduateList";
					message = "서버오류";
			}
		} else {
			viewName = "administration/univ_man/graduate/GraduateInsertForm";
		}
		model.addAttribute("message", message);
		return viewName;
	}
}
