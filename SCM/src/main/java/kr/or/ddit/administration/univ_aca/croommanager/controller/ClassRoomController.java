package kr.or.ddit.administration.univ_aca.croommanager.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.administration.univ_aca.croommanager.service.ClassRoomService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PushVO;
import kr.or.ddit.vo.ReservationVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ClassRoomController {

	@Inject
	private ClassRoomService service;
	
	//교무처 교무과 강의실 배분기능
	
	@RequestMapping("/classroom/classroomList.do")
	private String selectCroom(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
			) {
		
		PagingVO<FacilityVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveCRoomList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/univ_aca/classroom/ClassRoomList";
	}
	
	@RequestMapping("/classroom/classroom.do")
	private String selectCroomDetail(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			,Model model
			,@RequestParam("facNo") String facNo
			) {
		log.info("상세 들어옴");
		log.info("검색 : {}", searchVO);
		FacilityVO croomInfo = service.selectcroomInfo(facNo);
		model.addAttribute("croom", croomInfo);
		
		
		
		PagingVO<ReservationVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		ReservationVO res = new ReservationVO();
		res.setFacNo(facNo);
		
		pagingVO.setOther(facNo);

		service.retrieveCRresList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);
		
		List<CategoryVO> collegeList = service.retrieveCollegeCode();
		model.addAttribute("collegeList", collegeList);
		
		List<MajorVO> majorList = service.retrieveMajorCode();
		model.addAttribute("majorList", majorList);
		
		return "administration/univ_aca/classroom/ClassRoomView";
	}
	
	@RequestMapping(value="/classroom/modiclassroom.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	private String modifyClassroomInfo (
			@RequestParam("facNo") String facNo,
			@RequestParam("reUse") String facResult,
			@RequestParam("reMaj") String majorCode,
			@RequestParam("reNum") int facNumber,
			@RequestParam("facName") String facName
			) {
		log.info("전공 : {}", majorCode);
		
		FacilityVO facility = new FacilityVO();
		facility.setFacNo(facNo);
		facility.setFacResult(facResult);
		facility.setMajorCode(majorCode);
		facility.setFacNumber(facNumber);
		
		String result = null;
		
		ServiceResult res = service.modifyCRoom(facility);
		
		if(res == ServiceResult.OK) {
			result = "OK";
		}
		else {
			result = "Fail";
		}
		
		return result;
	}
	
}
