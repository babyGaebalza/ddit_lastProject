package kr.or.ddit.common.board.controller;


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

import kr.or.ddit.common.board.service.ComplaintBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ComplaintBoardController {

	@Inject
	private ComplaintBoardService service;
	
	/**
	 * 문의게시판 리스트 출력
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/stu_sup/complaintList.do")
	public String complaintList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model
			) {
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();	
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveComplaintList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		
		return "common/board/ComplaintList";
	}
	
	/**
	 * 문의게시판 상세
	 * @param boardNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/stu_sup/complaintView.do")
	public String complaintView(
			@RequestParam("boardNo") String boardNo,
			Model model
	) {
		BoardVO board = service.retrieveComplaint(boardNo);
		
		model.addAttribute("complaint", board);
		log.info(boardNo);
		
		return "common/board/ComplaintDetail";
	}
	
	
	/**
	 * 문의게시판 수정 입력 폼
	 * @param complaintTitle
	 * @param model
	 * @return
	 */
	@RequestMapping("/stu_sup/complaintUpdate.do")
	public String updateForm(
			@RequestParam("boardNo") String boardNo,
			Model model
		) {
			BoardVO board = service.retrieveComplaint(boardNo);
			model.addAttribute("complaint", board);
		return "common/board/ComplaintUpdateForm";
	}
	
	/**
	 * 문의게시판 수정
	 * @param complaint
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/stu_sup/complaintUpdate.do", method=RequestMethod.POST)
	public String complaintUpdate(
			@ModelAttribute("complaint") BoardVO complaint,
			Errors errors,
			Model model
		) {
			String viewName = null;
			String message = null;
			
			if(!errors.hasErrors()) {
				ServiceResult result = service.modifyComplaint(complaint);
				switch(result) {
				case OK:
					viewName = "redirect:/stu_sup/complaintView.do?boardNo="+complaint.getBoardNo();
					break;
				default:
					viewName = "common/board/ComplaintList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
				}
				
			}else {
				viewName = "common/board/ComplaintUpdateForm";
			}
			
			model.addAttribute("message", message);
			return viewName;
	}
	
	
	@RequestMapping(value = "/stu_sup/complaintDelete.do", method=RequestMethod.POST)
	public String complaintDelete(
		@RequestParam("boardNo") String boardNo,
		RedirectAttributes redirectAttributes
		) {
		
		BoardVO board = new BoardVO();
		board.setBoardNo(boardNo);
		
		ServiceResult result = service.removeComplaint(board);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
			case OK :
				viewName = "redirect:/stu_sup/complaintList.do";
				break;
			default :
				viewName = "redirect:/stu_sup/complaintView.do?boardNo="+boardNo;
				message = "서버오류";
				break;
		}
		redirectAttributes.addFlashAttribute("message",message);
		return viewName;
	}

	
	@RequestMapping("/stu_sup/complaintInsert.do")
	public String insertForm(
		@ModelAttribute("boardNo") MajorVO board
	) {
		return "common/board/ComplaintInsertForm";
	}
	
	
	@RequestMapping(value = "/stu_sup/complaintInsert.do", method=RequestMethod.POST)
	public String complaintInsert(
		@ModelAttribute("board") BoardVO board,
		Errors errors,
		Model model
		) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createComplaint(board);
			switch(result) {
				case OK:
					String urlEncodeMajorName;
					try {
						urlEncodeMajorName = URLEncoder.encode(board.getBoardNo(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
					viewName = "redirect:/stu_sup/complaintView.do?boardNo="+ urlEncodeMajorName;
					break;
				default:
					viewName = "common/board/ComplaintList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "common/board/ComplaintInsert";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	
}
