package kr.or.ddit.academic.common.lecturePage.controller;

import java.util.List;

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

import kr.or.ddit.academic.common.lecturePage.service.CommonLectureMaterialsService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class LectureTeachingMaterials {
	
	@Inject
	private CommonLectureMaterialsService service; 
	
	//데이터 구성 
	private void makeModel(int currentPage, SearchVO searchVO, Model model) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<BoardVO> materialList = service.retrieveMaterialList(pagingVO);
		pagingVO.setDataList(materialList);

		model.addAttribute("pagingVO", pagingVO);
	}
	
	//리스트 출력 
	@RequestMapping(value="/common/lecturePage/materialList.do", method=RequestMethod.GET)
	public String showTeachinMaterialList(
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
		return "academic/common/lecturePage/teachingMaterialList"; 	
	} 
	
	// 상세조회 
	@RequestMapping(value="/common/lecturePage/materialView.do")
	public String showOneMaterialtNotice(
			@RequestParam(value="what", required=true) String boardNo, 
			Model model, HttpServletRequest req
			) {				
		String viewName = null; 
		
	    BoardVO material = service.retrieveMaterial(boardNo);		
	    model.addAttribute("material", material);		

		viewName= "academic/common/lecturePage/teachingMaterialView";
		return viewName; 
	}
	
	
}
