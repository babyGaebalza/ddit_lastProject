package kr.or.ddit.administration.off_fac.facilitymanage.controller;

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

import kr.or.ddit.administration.off_fac.facilitymanage.service.AthleticService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AthleticController {
	
	@Inject
	private AthleticService service;
	
	/**
	 * 전체 리스트 조회
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/off_fac/athleticList.do")
	public String athleticList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<FacilityVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveAthleticList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/off_fac/athletic/AthleticList";
	}
	
	/**
	 * 선택 체육시설 조회
	 * @param facNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/off_fac/athleticView.do")
	public String athleticView(
		@RequestParam("facNo") String facNo,
		Model model
	) {
		FacilityVO athletic = service.retrieveAthletic(facNo);
		
		model.addAttribute("athletic", athletic);
		
		return "administration/off_fac/athletic/AthleticDetail";
	}
	
	/**
	 * 업데이트 입력
	 * @param facNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/off_fac/athleticUpdate.do")
	public String updateForm(
			@RequestParam("facNo") String facNo,
			Model model
		) {
			FacilityVO athletic = service.retrieveAthletic(facNo);
			model.addAttribute("athletic", athletic);
		
		return "administration/off_fac/athletic/AthleticUpdateForm";
	}
	
	/**
	 * 업데이트용
	 * @param facility
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/off_fac/athleticUpdate.do", method=RequestMethod.POST)
	public String athleticUpdate(
		@ModelAttribute("athletic") FacilityVO athletic,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyAthletic(athletic);
			switch(result) {
				case OK:
					viewName = "redirect:/off_fac/athleticView.do?facNo="+ athletic.getFacNo();
					break;
				default:
					viewName = "administration/off_fac/athletic/AthleticList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/off_fac/athletic/AthleticUpdateForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	
	@RequestMapping(value="/off_fac/athleticDelete.do", method=RequestMethod.POST)
	public String athleticDelete(
		@RequestParam("facNo") String facNo,
		RedirectAttributes redirectAttributes
	) {
		FacilityVO athletic = new FacilityVO();
		athletic.setFacNo(facNo);
		
		ServiceResult result = service.removeAthletic(athletic);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK :
			viewName = "redirect:/off_fac/athleticView.do?facNo="+athletic.getFacNo();
			break;
		default :
			viewName = "off_fac/athletic/athleticList";
			message = "서버오류";
			break;
		}
	redirectAttributes.addFlashAttribute("message",message);
	return viewName;
	}
	
	/**
	 * 입력
	 * @param facility
	 * @return
	 */
	@RequestMapping("/off_fac/athleticInsert.do")
	public String insertForm(
		  @ModelAttribute("athletic") FacilityVO athletic
		, Model model
	) {
		
		model.addAttribute("athletic", athletic);
		
		return "administration/off_fac/athletic/AthleticInsertForm";
		
		
	}
	
	
	@RequestMapping(value="/off_fac/athleticInsert.do", method=RequestMethod.POST)
	public String athleticInsert(
		@ModelAttribute("athletic") FacilityVO athletic,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		log.info("athletic : " + athletic);
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createAthletic(athletic);
			
			switch(result) {
				case OK:
					viewName = "redirect:/off_fac/athleticView.do?facNo="+ athletic.getFacNo();
					log.info("athleticFacNo : " + athletic.getFacNo());
					break;
				default:
					viewName = "off_fac/AthleticList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/off_fac/athletic/AthleticInsertForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
}