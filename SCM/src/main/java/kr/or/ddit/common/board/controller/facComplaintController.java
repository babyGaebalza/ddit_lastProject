package kr.or.ddit.common.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.validator.constraints.Range;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.board.service.FacComplaintService;
import kr.or.ddit.common.category.dao.CategoryDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class facComplaintController {

	@Inject
	private FacComplaintService service;
	
	@Inject
	private CategoryDAO dao;
	
	//회원ID로 회원이름 변환하기
	public String getMemName(String memId) {
		return service.getMemName(memId);
	}
	
	//민원 작성 폼으로
	@RequestMapping("/board/facComForm.do")
	public String facComForm(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			,Model model
			) {
		String memid = member.getMemId();
		String memName = getMemName(memid);
		
		List<CategoryVO> cateList = dao.selectCategoryList("FAC_CODE");
		model.addAttribute("facList", cateList);
		model.addAttribute("memid", memid);
		model.addAttribute("memName", memName);
		return "common/board/FacComForm";
	}
	
	
	//민원 작성 처리
	@RequestMapping("/board/facComInsert.do")
	public String insertfacCom(
			@Validated(InsertGroup.class) @ModelAttribute("facCom") BoardVO facCom,
			Errors errors,
			Model model
			) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createFC(facCom);
			switch(result) {
			case OK:
				viewName = "redirect:/board/facComList.do";
				break;
			default:
				viewName = "main";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "main";
			message = "DB오류, 관리자에게 문의하세요.";
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	//민원 리스트
	@RequestMapping(value="board/facComList.do")
	public String facComList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model
			) {
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);;
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveFCList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "common/board/FacComList";
	}
	
	//민원 상세조회
	@RequestMapping("board/facComView.do")
	public String facComView(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			,@RequestParam("fcNo") String fcNo
			, Model model
			) {
		log.info("들어왓다");
		String memid = member.getMemId();
		String memName = getMemName(memid);
		
		
		BoardVO board = service.retrieveFCView(fcNo);
		String boardWId = board.getMemNo();
		String boardWName = getMemName(boardWId);
		
		model.addAttribute("board", board);
		model.addAttribute("memid", memid);
		model.addAttribute("memName", memName);
		model.addAttribute("boardWName", boardWName);
		return "common/board/FacComView";
	}
	
	//민원삭제
	@RequestMapping("/board/facComDelete.do")
	public String facComDelete(
			@RequestParam("what") String boardNo,
			Model model
			) {
		
		ServiceResult result =  service.removeFC(boardNo);
		
		return "redirect:/board/facComList.do";
	}
	
	//민원 수정폼으로
	@RequestMapping("/board/facComUpdate.do")
	public String facComUpdateForm(
			@RequestParam("what") String boardNo,
			Model model
			) {
		
		BoardVO board = service.retrieveFCView(boardNo);
		String title = board.getBoardTitle();
		/*2021-11-14/강의실/301의 시설민원*/
		String[] splTitle = title.split("/");
		String date = splTitle[0];
		String cateName1 = splTitle[1];
		String facName = splTitle[2];
		String[] splfacName = facName.split("의");
		String facName2 = splfacName[0];
		
		board.setBoardDate(date);

		model.addAttribute("board", board);
		model.addAttribute("cateName1", cateName1);
		model.addAttribute("facName", facName2);
		List<CategoryVO> cateList = dao.selectCategoryList("FAC_CODE");
		model.addAttribute("facList", cateList);
		
		return "common/board/FacComUpdateForm";
	}
	
	//민원수정처리
	@RequestMapping("/board/facComUpdateprocess.do")
	public String facComUpdate(
			@Validated(InsertGroup.class) @ModelAttribute("facCom") BoardVO facCom,
			Errors errors,
			Model model
			) {
		
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyFC(facCom);
			switch(result) {
			case OK:
				viewName = "redirect:/board/facComList.do";
				break;
			default:
				viewName = "main";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
			
		}else {
			viewName = "main";
			message = "DB오류, 관리자에게 문의하세요.";
		}
		
		model.addAttribute("message", message);
		
		
		return "redirect:/board/facComList.do";
	}
	
	
}
