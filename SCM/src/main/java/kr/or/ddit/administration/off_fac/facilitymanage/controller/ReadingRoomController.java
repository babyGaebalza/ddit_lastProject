package kr.or.ddit.administration.off_fac.facilitymanage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.off_fac.facilitymanage.service.ReadingRoomService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReadingRoomController {
	
	@Inject
	private ReadingRoomService service;
	
	/**
	 * 전체 리스트 조회
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/off_fac/readingRoomList.do")
	public String readingRoomList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<FacilityVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveReadingRoomList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/off_fac/readingRoom/ReadingRoomList";
	}
	
	/**
	 * 선택 체육시설 조회
	 * @param facNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/off_fac/readingRoomView.do")
	public String readingRoomView(
		@RequestParam("facNo") String facNo,
		Model model
	) {
		FacilityVO readingRoom = service.retrieveReadingRoom(facNo);
		
		model.addAttribute("readingRoom", readingRoom);
		log.info(facNo);
		
		return "administration/off_fac/readingRoom/ReadingRoomDetail";
	}
	
	/**
	 * 업데이트 입력
	 * @param facNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/off_fac/readingRoomUpdate.do")
	public String updateForm(
			@RequestParam("facNo") String facNo,
			Model model
		) {
			FacilityVO readingRoom = service.retrieveReadingRoom(facNo);
			model.addAttribute("readingRoom", readingRoom);
		
		return "administration/off_fac/readingRoom/ReadingRoomUpdate";
	}
	
	/**
	 * 업데이트용
	 * @param facility
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/off_fac/readingRoomUpdate.do", method=RequestMethod.POST)
	public String readingRoomUpdate(
		@ModelAttribute("readingRoom") FacilityVO readingRoom,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyReadingRoom(readingRoom);
			switch(result) {
				case OK:
					viewName = "redirect:/off_fac/readingRoomView.do?facNo="+ readingRoom.getFacNo();
					break;
				default:
					viewName = "administration/off_fac/readingRoom/ReadingRoomList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/off_fac/readingRoom/ReadingRoomUpdate";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	
	@RequestMapping(value="/off_fac/readingRoomDelete.do", method=RequestMethod.POST)
	public String readingRoomDelete(
		@RequestParam("facNo") String facNo,
		RedirectAttributes redirectAttributes
	) {
		FacilityVO readingRoom = new FacilityVO();
		readingRoom.setFacNo(facNo);
		
		ServiceResult result = service.removeReadingRoom(readingRoom);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK :
			viewName = "redirect:/off_fac/readingRoomView.do?facNo="+readingRoom.getFacNo();
			break;
		default :
			viewName = "off_fac/readingRoom/ReadingRoomList";
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
	@RequestMapping("/off_fac/readingRoomInsert.do")
	public String insertForm(
		@ModelAttribute("readingRoom") FacilityVO readingRoom
	) {
		return "administration/off_fac/readingRoom/ReadingRoomInsert";
	}
	
	
	@RequestMapping(value="/off_fac/readingRoomInsert.do", method=RequestMethod.POST)
	public String readingRoomInsert(
		@ModelAttribute("readingRoom") FacilityVO readingRoom,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createReadingRoom(readingRoom);
			switch(result) {
				case OK:
					viewName = "redirect:/off_fac/readingRoomView.do?facNo="+ readingRoom.getFacNo();
					
					break;
				default:
					viewName = "off_fac/ReadingRoomList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/off_fac/readingRoom/ReadingRoomInsert";
				
			}
			
		model.addAttribute("message", message);
		
		log.info(readingRoom.getFacNo());
		return viewName;
	}
	
}