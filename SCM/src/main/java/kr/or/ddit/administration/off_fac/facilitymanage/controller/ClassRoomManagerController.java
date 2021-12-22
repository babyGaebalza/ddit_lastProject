package kr.or.ddit.administration.off_fac.facilitymanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.administration.off_fac.facilitymanage.service.ClassRoomManagerService;
import kr.or.ddit.administration.univ_aca.croommanager.service.ClassRoomService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class ClassRoomManagerController {

	@Inject
	private ClassRoomService crService;
	
	@Inject
	private ClassRoomManagerService service;
	
	//사무국 시설과 강의실시설관리
	
	//강의실 리스트
	@RequestMapping("/off_fac/classroomList.do")
	private String ClassRoomList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
			) {
		
		PagingVO<FacilityVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		crService.retrieveCRoomList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		
		return "administration/off_fac/classRoom/ClassRoomList";
	};
	
	
	
	//입력폼으로 이동
	@RequestMapping("/off_fac/classroomInsertForm.do")
	private String ClassRoomInsertForm(
			Model model
			) {		
		List<CategoryVO> collegeList = crService.retrieveCollegeCode();
		model.addAttribute("collegeList", collegeList);
		
		List<MajorVO> majorList = crService.retrieveMajorCode();
		model.addAttribute("majorList", majorList);
		
		return "administration/off_fac/classRoom/ClassRoomInsertForm";
	}
	
	
	
	//입력 프로세스
	@RequestMapping("/off_fac/classroomInsertProcess.do")
	private String ClassRoomInsertProcess(
			@Validated(InsertGroup.class) @ModelAttribute("classroomForm") FacilityVO classRoom
			,Errors errors
			) {
		
		ServiceResult res = service.createClassRoom(classRoom);
		
		return "forward:/off_fac/classroomList.do";
	}
	
}
