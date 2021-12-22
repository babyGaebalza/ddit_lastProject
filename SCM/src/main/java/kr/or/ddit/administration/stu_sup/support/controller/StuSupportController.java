package kr.or.ddit.administration.stu_sup.support.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.stu_sup.support.service.StuSupportService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BusinessVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class StuSupportController {
	
	@Inject
	private StuSupportService service;
	
	
	@RequestMapping("/stu_sup/stuSupportList.do")
	public String selectStuSupport(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
	) {
		PagingVO<BusinessVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveStuSupportList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/stu_sup/stuSupport/StuSupportList";
	}
	
	
	@RequestMapping("/stu_sup/stuSupportView.do")
	public String stuSupportView(
			@RequestParam("bussNo") String bussNo,
			Model model
	) {
		BusinessVO business = service.retrieveStuSupport(bussNo);
		
		model.addAttribute("business", business);
		
		return "administration/stu_sup/stuSupport/StuSupportDetail";
	}
	
	
	@RequestMapping("/stu_sup/stuSupportUpdate.do")
	public String updateForm(
			@RequestParam("bussNo") String bussNo,
			Model model
		) {
		BusinessVO business = service.retrieveStuSupport(bussNo);
			model.addAttribute("business", business);
		
		return "administration/stu_sup/stuSupport/StuSupportUpdate";
	}
	
	
	@RequestMapping(value="/stu_sup/stuSupportUpdate.do", method=RequestMethod.POST)
	public String stuSupportUpdate(
		@ModelAttribute("business") BusinessVO business,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyStuSupport(business);
			switch(result) {
				case OK:
					viewName = "redirect:/stu_sup/stuSupportView.do?bussNo="+ business.getBussNo();
					break;
				default:
					viewName = "administration/stu_sup/stuSupport/StuSupportList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/stu_sup/stuSupport/StuSupportUpdateForm";
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	@RequestMapping(value="/stu_sup/stuSupportDelete.do", method=RequestMethod.POST)
	public String stuSupportDelete(
		@RequestParam("bussNo") String bussNo,
		RedirectAttributes redirectAttributes
	) {
		BusinessVO business = new BusinessVO();
		business.setBussNo(bussNo);
		
		ServiceResult result = service.removeStuSupport(business);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK :
			viewName = "redirect:/stu_sup/stuSupportList.do";
			break;
		default :
			viewName = "stu_sup/stuSupport/StuSupportList";
			message = "서버오류";
			break;
		}
	redirectAttributes.addFlashAttribute("message",message);
	return viewName;
	}
	
	
	@RequestMapping("/stu_sup/stuSupportInsert.do")
	public String insertForm(
		@ModelAttribute("business") BusinessVO business
	) {
		return "administration/stu_sup/stuSupport/StuSupportInsert";
	}
	
	
	@RequestMapping(value="/stu_sup/stuSupportInsert.do", method=RequestMethod.POST)
	public String athleticInsert(
		@ModelAttribute("business") BusinessVO business,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createStuSupport(business);
			switch(result) {
				case OK:
					viewName = "redirect:/stu_sup/stuSupportList.do?bussNo="+business.getBussNo();
					break;
				default:
					viewName = "stu_sup/stuSupport/StuSupportList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/stu_sup/stuSupport/StuSupportInsert";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
}
