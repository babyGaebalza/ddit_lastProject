package kr.or.ddit.administration.stu_sup.scholarship.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.stu_sup.scholarship.service.ScholarShipService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ScholarShipController {
	
	@Inject
	private ScholarShipService service;
	
	/**
	 * 전체 리스트
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/stu_sup/scholarShipList.do")
	private String selectScholarShip(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<ScholarVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveScholarShipList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/stu_sup/scholarShip/ScholarShipList";
	}
	/**
	 * 상세조회
	 * @param schCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/stu_sup/scholarShipView.do")
	public String scholarShipView(
			@RequestParam("schCode") String schCode,
			Model model
	) {
		ScholarVO scholar = service.retrieveScholarShip(schCode);
		
		model.addAttribute("scholar", scholar);
		
		return "administration/stu_sup/scholarShip/ScholarShipDetail";
	}
	
	@RequestMapping("/stu_sup/scholarShipUpdate.do")
	public String updateForm(
			@RequestParam("schCode") String schCode,
			Model model
		) {
		ScholarVO scholar = service.retrieveScholarShip(schCode);
			model.addAttribute("scholar", scholar);
		
		return "administration/stu_sup/scholarShip/ScholarShipUpdateForm";
	}
	
	@RequestMapping(value="/stu_sup/scholarShipUpdate.do", method=RequestMethod.POST)
	public String scholarShipUpdate(
			@ModelAttribute("scholar") ScholarVO scholar,
			Errors errors,
			Model model
		) {
			String viewName = null;
			String message = null;
			
			if(!errors.hasErrors()) {
				ServiceResult result = service.modifyScholarShip(scholar);
				switch(result) {
				case OK:
					viewName = "redirect:/stu_sup/scholarShipView.do?schCode="+scholar.getSchCode();
					break;
				default:
					viewName = "administration/stu_sup/scholarShip/ScholarShipList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
				}
				
			}else {
				viewName = "administration/stu_sup/scholarShip/ScholarShipUpdateForm";
			}
			
			model.addAttribute("message", message);
			return viewName;
	}
	
	
	@RequestMapping(value = "/stu_sup/scholarShipDelete.do", method=RequestMethod.POST)
	public String scholarShipDelete(
		@RequestParam("schCode") String schCode,
		RedirectAttributes redirectAttributes
		) {
		
		ScholarVO scholar = new ScholarVO();
		scholar.setSchCode(schCode);
		
		ServiceResult result = service.removeScholarShip(scholar);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
			case OK :
				viewName = "redirect:/stu_sup/scholarShipList.do";
				break;
			default :
				viewName = "redirect:/stu_sup/scholarShipView.do?schCode="+schCode;
				message = "서버오류";
				break;
		}
		redirectAttributes.addFlashAttribute("message",message);
		return viewName;
	}

	
	@RequestMapping("/stu_sup/scholarShipInsert.do")
	public String insertForm(
		@ModelAttribute("scholar") ScholarVO scholar
	) {
		return "administration/stu_sup/scholarShip/ScholarShipInsertForm";
	}
	
	
	@RequestMapping(value = "/stu_sup/scholarShipInsert.do", method=RequestMethod.POST)
	public String scholarShipInsert(
		@ModelAttribute("scholar") ScholarVO scholar,
		Errors errors,
		Model model
		) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createScholarShip(scholar);
			switch(result) {
				case OK:
//					String urlEncodeScholarCode;
//					try {
//						urlEncodeScholarCode = URLEncoder.encode(scholar.getSchCode(), "UTF-8");
//					} catch (UnsupportedEncodingException e) {
//						throw new RuntimeException(e);
//					}
//					viewName = "redirect:/stu_sup/scholarShipView.do?schCode="+ urlEncodeScholarCode;
					viewName = "redirect:/stu_sup/scholarShipView.do?schCode="+ scholar.getSchCode();
					break;
				default:
					viewName = "administration/stu_sup/scholarShip/ScholarShipList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/stu_sup/scholarShip/ScholarShipInsertForm";
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	
}
