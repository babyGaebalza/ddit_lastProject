package kr.or.ddit.administration.univ_man.classnotice.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.univ_man.classnotice.service.ClassNoticeService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ClassNoticeController {
	
	@Inject
	private ClassNoticeService service;
	
	/**
	 * 리스트
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_man/cNoticeList.do")
	public String cNoticeList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveCNoticeList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/univ_man/cNotice/CNoticeList";
	}
	
	/**
	 * 강의 공지 상세조회
	 * @param classNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_man/cNoticeView.do")
	public String cNoticeView(
		@RequestParam("cNoticeNo") String boardNo,
		Model model
	) {
		BoardVO board = service.retrieveCNotice(boardNo);
		
		model.addAttribute("cNotice", board);
		
		return "administration/univ_man/cNotice/CNoticeDetail";
	}
	
	
	@RequestMapping("/univ_man/cNoticeUpdate.do")
	public String updateForm(
			@RequestParam("boardNo") String boardNo,
			Model model
		) {
		BoardVO board = service.retrieveCNotice(boardNo);
			model.addAttribute("board", board);
		
		return "administration/univ_man/cNotice/CNoticeUpdateForm";
	}
	
	
	@RequestMapping(value="/univ_man/cNoticeUpdate.do", method=RequestMethod.POST)
	public String cNoticeUpdate(
		@ModelAttribute("board") BoardVO board,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyCNotice(board);
			switch(result) {
				case OK:
					viewName = "redirect:/univ_man/cNoticeView.do?cNoticeNo="+ board.getBoardNo();
					break;
				default:
					viewName = "administration/univ_man/cNotice/CNoticeUpdateForm";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/univ_man/cNotice/CNoticeUpdateForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	@RequestMapping(value="/univ_man/cNoticeDelete.do", method=RequestMethod.POST)
	public String cNoticeDelete(
		@RequestParam("boardNo") String boardNo,
		RedirectAttributes redirectAttributes
	) {
		BoardVO board = new BoardVO();
		board.setBoardNo(boardNo);
		
		ServiceResult result = service.removeCNotice(board);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
			case OK :
				viewName = "redirect:/univ_man/cNoticeList.do";
				break;
			default :
				viewName = "redirect:/univ_man/cNoticeView.do?cNoticeNo="+board.getBoardNo();
				message = "서버오류";
				break;
		}
		redirectAttributes.addFlashAttribute("message",message);
		return viewName;
	}
	
	@RequestMapping("/univ_man/cNoticeInsert.do")
	public String insertForm(
		@ModelAttribute("board") BoardVO board
	) {
		return "administration/univ_man/cNotice/CNoticeInsertForm";
	}
	
	/**
	 * 입력
	 * @param board
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/univ_man/cNoticeInsert.do", method=RequestMethod.POST)
	public String cNoticeInsert(
		@ModelAttribute("board") BoardVO board,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		log.info("board : " + board);
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createCNotice(board);
			switch(result) {
				case OK:
					viewName = "redirect:/univ_man/cNoticeView.do?cNoticeNo="+ board.getBoardNo();
					break;
				default:
					viewName = "administration/univ_man/cNotice/CNoticeList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		} else {
				viewName = "administration/univ_man/cNotice/CNoticeInsertForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
}
