package kr.or.ddit.common.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.board.service.BoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Inject
	private BoardService service;
	
	@RequestMapping("/board/noticeInsert.do")
	public String getController(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			,@ModelAttribute("notice") BoardVO notice
			,Model model
			) {
		String memId = member.getMemId();
		String order = "insert";
		model.addAttribute("order", order);
		model.addAttribute("memId", memId);
		return "common/board/noticeUpdateForm";
	}
	
	
	@RequestMapping(value="/board/noticeInsert.do", method=RequestMethod.POST)
	public String postController(
		@Validated(InsertGroup.class) @ModelAttribute("notice") BoardVO notice
		, Errors errors
		, Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createNotice(notice);
			switch(result) {
			case OK:
				viewName = "redirect:/board/noticeView.do?noticeNo="+notice.getBoardNo();
				break;
			default:
				viewName = "board/noticeList";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "common/board/noticeBoard";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}	
	
	
	@RequestMapping(value="/board/noticeUpdate.do", method=RequestMethod.GET)
	public String editFrom(
			@RequestParam("noticeNo") String noticeNo,
			Model model
		) {
			log.info("수정할 글번호 : {}",noticeNo );
			BoardVO board = service.retrieveNotice(noticeNo);
			model.addAttribute("notice", board);
			String order = "update";
			model.addAttribute("order", order);
		return "common/board/noticeUpdateForm";
	}
	
	
	
	
	@RequestMapping(value="/board/noticeUpdate.do", method=RequestMethod.POST)
	public String noticeUpdate(
			@Validated(InsertGroup.class) @ModelAttribute("notice") BoardVO notice,
			Errors errors,
			Model model
		) {
			String viewName = null;
			String message = null;
			
			if(!errors.hasErrors()) {
				ServiceResult result = service.modifyNotice(notice);
				
				switch(result) {
				case OK:
					viewName = "redirect:/board/noticeView.do?noticeNo="+notice.getBoardNo();
					break;
				default:
					viewName = "notice/noticeList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
				}
				
			}else {
				viewName = "common/board/noticeUpdateForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;

	}
	
	
	@RequestMapping(value = "/board/noticeDelete.do", method=RequestMethod.POST)
	public String noticeDelete(
		@RequestParam("noticeNo") String boardNo,
		RedirectAttributes redirectAttributes
		) {
		
		BoardVO board = new BoardVO();
		board.setBoardNo(boardNo);
		
		ServiceResult result = service.removeNotice(board);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
			case OK :
				viewName = "redirect:/common/board/noticeList.do";
				redirectAttributes.addFlashAttribute("message",message);

				break;
			default :
				viewName = "redirect:/board/noticeView.do?noticeNo="+boardNo;
				message = "서버오류";
				redirectAttributes.addFlashAttribute("message",message);
				break;		
		}
		return viewName;
	}
	
	
	@RequestMapping("/board/noticeView.do")
	public String noticeView(
			@RequestParam("noticeNo") String boardNo,
			Model model
		) {
			BoardVO board = service.retrieveNotice(boardNo);
			log.info("보드넘버 : {}", boardNo);
			
			model.addAttribute("notice", board);
			model.addAttribute("boardNo", boardNo);
		
		return "common/board/noticeDetail";
	}
	
	@GetMapping("/common/board/noticeList.do")
	public String noticeList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model,
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember

		) {
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();	
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveNoticeList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		if("US02".equals(authMember.getUserCode())) {
			return "academic/professor/noticeBoard";	
		}
		return "common/board/noticeBoard";
	}
	
}
