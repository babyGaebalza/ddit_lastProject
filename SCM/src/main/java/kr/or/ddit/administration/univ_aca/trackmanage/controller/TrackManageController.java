package kr.or.ddit.administration.univ_aca.trackmanage.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.administration.univ_aca.trackmanage.dao.TrackManageDAO;
import kr.or.ddit.administration.univ_aca.trackmanage.service.TrackManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.TrackListVO;
import kr.or.ddit.vo.TrackVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TrackManageController {
	@Inject
	private TrackManageService service;
	@Inject
	private TrackManageDAO dao;
	
	//트랙신청 리스트
	@RequestMapping("/trackManage/trackManageList.do")
	public String trackManageList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
		@ModelAttribute("searchVO") SearchVO searchVO,
		Model model
	) {
		PagingVO<DocumentVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveDocumentList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/univ_aca/trackManage/TrackManageList";
	}
	
	//트랙신청 상세페이지
	@RequestMapping("/trackManage/trackManageDetail.do")
	public String trackManageDetail(
		@RequestParam("docuNo") String docuNo
		, Model model
	) {
		DocumentVO docu = service.retrieveDocument(docuNo);
		model.addAttribute("docu", docu);
		
		return "administration/univ_aca/trackManage/TrackManageDetail";
	}
	
	//신청트랙 등록(get)
	@RequestMapping("/trackManage/trackManageInsert.do")
	public String  getTrackManageInsert(
		@ModelAttribute("track") TrackVO track
		, @RequestParam("docuNo") String docuNo
		, Model model
	) {	
		log.info("=====================================");
		log.info("docuNo : {}", docuNo);
		
		model.addAttribute("docuNo", docuNo);
		
		return "administration/univ_aca/trackManage/TrackManageForm";
	}
	//신청트랙 등록(post)
	@RequestMapping(value="/trackManage/trackManageInsert.do", method=RequestMethod.POST)
	public String postTrackManageInsert(
		@ModelAttribute("track") TrackVO track
		, @RequestParam("classNo") String[] classNo
		, @RequestParam("docuNo") String docuNo
		, Errors errors
		, Model model
	) {
		String viewName = null;
		String message = null;
		
		log.info("=====================================");
		log.info("docuNo : {}", docuNo);
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createTrack(track);
			
			switch(result) {
			case OK:
				for(String clas : classNo) {
					TrackListVO trackList = new TrackListVO();
					trackList.setClassNo(clas);
					trackList.setTrackNo(track.getTrackNo());
					dao.insertTrackList(trackList);
					System.out.println(track.getTrackNo() + "----------------->>>>");
				}
				TrackListVO trackList = new TrackListVO();
				trackList.setTrackNo(track.getTrackNo());
//				dao.insertTrackListCLO2(trackList);
				dao.updateDocu(docuNo);
				
				viewName= "redirect:/trackManage/trackDetail.do?trackNo=" + track.getTrackNo();
				break;
			default:
				viewName= "administration/univ_aca/trackManage/TrackList";
				message = "서버 오류, 잠시 뒤 다시 시도하세요";
			}
		} else {
			viewName = "administration/univ_aca/trackManage/TrackManageForm";
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	//신청트랙 수정(get)
	@RequestMapping("/trackManage/trackManageUpdate.do")
	public String getTrackManageUpdate(
		@RequestParam("docuNo") String docuNo
		, Model model
	) {
		DocumentVO docu = service.retrieveDocument(docuNo);
		model.addAttribute("docu", docu);
		
		return "administration/univ_aca/trackManage/TrackManageEdit";
	}
	//신청트랙 수정(post)
	@RequestMapping(value="/trackManage/trackManageUpdate.do", method=RequestMethod.POST)
	public String postTrackManageUpdate(
		@Validated(InsertGroup.class) @ModelAttribute("docu") DocumentVO docu,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyDocument(docu);
			
			switch (result) {
			case OK:
				viewName = "redirect:/trackManage/trackManageDetail.do?docuNo=" + docu.getDocuNo();
				break;
			default:
				viewName = "administration/univ_aca/trackManage/TrackManageList";
				message = "오류! 잠시 후 시도해주세요.";
			}
		} else {
			viewName = "administration/univ_aca/trackManage/TrackManageEdit";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	//트랙 리스트
	@RequestMapping("/trackManage/trackList.do")
	public String trackList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("SearchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<TrackVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveTrackList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/univ_aca/trackManage/TrackList";
	}
	
	//트랙 상세페이지
	@RequestMapping("/trackManage/trackDetail.do")
	public String trackDetail(
		@RequestParam("trackNo") String trackNo
		, Model model
	) {
		TrackVO track = service.retrieveTrack(trackNo);
		model.addAttribute("track", track);
		
		List list = dao.getClassList(trackNo);
		model.addAttribute("list", list);
		
		return "administration/univ_aca/trackManage/TrackDetail";
	}
	
	//트랙 수정(get)
	@RequestMapping("/trackManage/trackUpdate.do")
	public String getTrackUpdate(
		@RequestParam("trackNo") String trackNo
		, Model model
	) {
		TrackVO track = service.retrieveTrack(trackNo);
		model.addAttribute("track", track);
		
		return "administration/univ_aca/trackManage/TrackEdit";
	}
	//트랙 수정(post)
	@RequestMapping(value="/trackManage/trackUpdate.do", method=RequestMethod.POST)
	public String postTrackUpdate(
		@Validated(InsertGroup.class) @ModelAttribute("track") TrackVO track,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyTrack(track);
			
			switch (result) {
			case OK:
				viewName = "redirect:/trackManage/trackDetail.do?trackNo=" + track.getTrackNo();
				break;
			default:
				viewName = "administration/univ_aca/trackManage/TrackList";
				message = "서버 오류, 잠시 후 시도해주세요.";
			}
		} else {
			viewName = "administration/univ_aca/trackManage/TrackEdit";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	//단과 코드 select
	@ResponseBody
	@RequestMapping(value="/trackManage/collegeCodeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MajorVO> trackCodeList(
		@ModelAttribute("major") MajorVO major
	) {
		return dao.selectCollegeCode(major);
	}
	//학과 이름 select
	@ResponseBody
	@RequestMapping(value="/trackManage/majorCodeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MajorVO> trackNameList(
		@ModelAttribute("major") MajorVO major
	) {
		return dao.selectMajorCode(major);
	}
	//강의 구분 select
//	@ResponseBody
//	@RequestMapping(value="/trackManage/classCodeList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public List<ClassVO> trackClassCode(
//		@ModelAttribute("clas") ClassVO clas
//	){
//		return dao.selectClassCode(clas);
//	}
	//강의 선택(학년 학기) select
	@ResponseBody
	@RequestMapping(value="/trackManage/classNameListCLO2.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ClassVO> trackClassSemester(
			@ModelAttribute("clas") ClassVO clas
			){
		return dao.selectClassSemester(clas);
	}
	//강의 선택 select
	@ResponseBody
	@RequestMapping(value="/trackManage/classNameList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ClassVO> trackClassName(
		@ModelAttribute("clas") ClassVO clas
	){
		return dao.selectClassName(clas);
	}

	@ResponseBody
	@RequestMapping(value="/trackManage/classNameList2.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<ClassVO> trackClassName2(
			@ModelAttribute("clas") ClassVO clas
			){
		return dao.selectClassName2(clas);
	}
	
	//상태코드 ajax
	@ResponseBody
	@RequestMapping(value="/trackManage/trackStateList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String trackStateList(
		@RequestParam(value="checkArr[]") List<String> trackList
	){
		log.info(trackList.toString());
		
		for(int i=0;i<trackList.size();i++){
			dao.update(trackList.get(i));
		}
		return String.valueOf(trackList);
	}
	
	@ResponseBody
	@RequestMapping(value="/trackManage/trackState.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String trackState(
		@RequestParam(value="checkArr[]") List<String> track
	){
		log.info(track.toString());
		
		for(int i=0;i<track.size();i++){
			dao.updateState(track.get(i));
		}
		return String.valueOf(track);
	}
}
