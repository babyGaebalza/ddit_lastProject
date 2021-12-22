package kr.or.ddit.academic.assistant.departmentmanage.controller;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.academic.assistant.departmentmanage.service.DepartmentManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DepartmentManageController {
	@Inject
	private DepartmentManageService service;
	
	//학과 공지리스트
	@RequestMapping("/departmentManage/departmentManageList.do")
	public String departmentManageList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
		, @AuthenticationPrincipal(expression="authMember") MemberVO member
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>();	
		pagingVO.setCurrentPage(currentPage);
		searchVO.setSearchWord2(member.getMemMajor());
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveDepartmentList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("member", member);
		
		log.info(member.toString());
		
		return "academic/assistant/departmentManage/DepartmentManageList";
	}
	
	//학과 공지 상세페이지
	@RequestMapping("/departmentManage/departmentManageDetail.do")
	public String departmentManageDetail(
		@RequestParam("noticeNo") String boardNo,
		Model model
	) {
		BoardVO board = service.retrieveDepartment(boardNo);
		model.addAttribute("notice", board);
		
		return "academic/assistant/departmentManage/DepartmentManageDetail";
	}
	
	//학과 공지 등록
	@RequestMapping("/departmentManage/departmentManageInsert.do")
	public String getDepartmentManageInsert(
		@ModelAttribute("notice") BoardVO notice
		, @AuthenticationPrincipal(expression="authMember") MemberVO member,
		Model model
	) {
		model.addAttribute("member", member);
		
		return "academic/assistant/departmentManage/DepartmentManageForm";
	}
	//학과 공지 등록
	@RequestMapping(value="/departmentManage/departmentManageInsert.do", method=RequestMethod.POST)
	public String postDepartmentManageInsert(
		@ModelAttribute("notice") BoardVO notice
		, Errors errors
		, Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createDepartment(notice);
			switch(result) {
			case OK:
				viewName = "redirect:/departmentManage/departmentManageDetail.do?noticeNo="+notice.getBoardNo();
				break;
			default:
				viewName = "academic/assistant/departmentManage/DepartmentManageList";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "assistant/departmentManage/DepartmentManageForm";
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	//학과 공지 수정
	@RequestMapping("/departmentManage/departmentManageUpdate.do")
	public String getDepartmentManageUpdate(
		@RequestParam("noticeNo") String noticeNo,
		Model model
	) {
		BoardVO board = service.retrieveDepartment(noticeNo);
		model.addAttribute("notice", board);
		
		return "academic/assistant/departmentManage/DepartmentManageForm";
	}
	//학과 공지 수정
	@RequestMapping(value="/departmentManage/departmentManageUpdate.do", method=RequestMethod.POST)
	public String postDepartmentManageUpdate(
		@ModelAttribute("notice") BoardVO notice,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyDepartment(notice);
			
			switch(result) {
			case OK:
				viewName = "redirect:/departmentManage/departmentManageDetail.do?noticeNo="+notice.getBoardNo();
				break;
			default:
				viewName = "academic/assistant/departmentManage/DepartmentManageList";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "assistant/departmentManage/DepartmentManageForm";
			
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	//학과 공지 삭제
	@RequestMapping(value="/departmentManage/departmentManageDelete.do", method=RequestMethod.POST)
	public String departmentManageDelete(
		@RequestParam("noticeNo") String boardNo,
		RedirectAttributes redirectAttributes
	) {
		BoardVO board = new BoardVO();
		board.setBoardNo(boardNo);
		
		ServiceResult result = service.removeDepartment(board);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
			case OK :
				viewName = "redirect:/departmentManage/departmentManageList.do";
				break;
			default :
				viewName = "redirect:/departmentManage/departmentManageDetail.do?noticeNo="+boardNo;
				message = "서버오류";
				break;
		}
		redirectAttributes.addFlashAttribute("message",message);
		return viewName;
	}
}
