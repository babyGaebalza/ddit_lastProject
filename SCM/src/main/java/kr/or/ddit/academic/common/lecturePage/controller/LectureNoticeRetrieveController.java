package kr.or.ddit.academic.common.lecturePage.controller;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.common.lecturePage.service.CommonLectureNoticeService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class LectureNoticeRetrieveController {

	@Inject
	private CommonLectureNoticeService service;
	
	//데이터 구성 
		private void makeModel(int currentPage, SearchVO searchVO, Model model) {
			PagingVO<BoardVO> pagingVO = new PagingVO<>(10, 3);
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSearchVO(searchVO);
			
			List<BoardVO> noticeList = service.retrieveNoticeList(pagingVO);
			pagingVO.setDataList(noticeList);
			
			model.addAttribute("pagingVO", pagingVO);
		}
		
		//공지게시글 출력 
		@RequestMapping(value="/common/lecturePage/noticeList.do", method=RequestMethod.GET)
		public String showNoticetList(
				@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
				@ModelAttribute("searchVO") SearchVO searchVO,
				Model model,
				@AuthenticationPrincipal(expression="authMember") MemberVO member, 
				HttpServletRequest req
				) {
			HttpSession session = req.getSession(); 
			String classNo = (String) session.getAttribute("classNo");
			
			searchVO.setSearchWord2(member.getMemId()); 
			searchVO.setSearchWord(classNo);
			makeModel(currentPage, searchVO, model);
			return "academic/common/lecturePage/noticeBoardList"; 
		} 
		
		//공지게시글 상세조회 
		@RequestMapping(value="/common/lecturePage/noticeBoardView.do")
		public String showOneNotice(
				@RequestParam(value="what", required=true) String boardNo, 
				Model model
				) {				
			String viewName = null; 
			
		    BoardVO notice = service.retrieveNotice(boardNo);		
		    model.addAttribute("notice", notice);		

			viewName= "academic/common/lecturePage/noticeView";
			return viewName; 
		}

	
	
	
	
}
