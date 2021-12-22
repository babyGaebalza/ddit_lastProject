package kr.or.ddit.academic.professor.trackManage.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.professor.trackManage.service.TrackProfessorService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.TrackVO;

@Controller
public class TrackProfessorController {

	@Inject
	private TrackProfessorService service;
			
	/**
	 * 1. 접근전에 권한 여부 검사필요 
	 * 2. 자신이 속한 과의 트랙리스트만 출력하기 
	 * */
	
	//데이터 구성 
	private void makeModel(int currentPage, SearchVO searchVO, Model model) {
		PagingVO<TrackVO> pagingVO = new PagingVO<>(3, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<TrackVO> trackList = service.retrieveTrackList(pagingVO);
		pagingVO.setDataList(trackList);
		
		model.addAttribute("pagingVO", pagingVO);
	}
		
	//트랙리스트 출력하기 
	@RequestMapping(value="/professor/trackList.do", method=RequestMethod.GET)
	public String showTrackList(
			@AuthenticationPrincipal(expression="authMember") MemberVO member, 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO, Model model, HttpServletRequest req
			) {
		
		PagingVO<TrackVO> pagingVO = new PagingVO<>();
		pagingVO.setSearchVO(searchVO);		
		searchVO.setSearchWord(member.getMemId()); 
	
		makeModel(currentPage, searchVO, model);
			
		return "academic/professor/trackList" ; 
	} 
	
	
	/** 1. 트랙1건 상세정보*/
	@RequestMapping(value="/professor/trackView.do")
	public String showTrack(
			@RequestParam(value="what", required=true) String trackNo, 
			Model model, HttpServletRequest req
			) {				
		String viewName = null; 
	    TrackVO track = service.retrieveTrack(trackNo);		
	    model.addAttribute("track", track);		

		viewName= "academic/professor/trackView";
	
		return viewName; 
	}
	

	
	
}
