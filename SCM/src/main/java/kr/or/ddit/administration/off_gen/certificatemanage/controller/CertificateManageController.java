package kr.or.ddit.administration.off_gen.certificatemanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.administration.off_gen.certificatemanage.dao.CertificateManageDAO;
import kr.or.ddit.administration.off_gen.certificatemanage.service.CertificateManageService;
import kr.or.ddit.administration.vo.CertiVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CertificateManageController {
	@Inject
	private CertificateManageService service;
	@Inject
	private CertificateManageDAO dao;
	
	//증명서 리스트
	@RequestMapping("/certificateManage/certificateManageList.do")
	public String certificateManageList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<CategoryVO> pagingVO = new PagingVO<>();	
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveCategoryList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/off_gen/certi/CertiManageList";
	}
	
	//증명서 상세페이지
	@RequestMapping("/certificateManage/certificateManageDetail.do")
	public String certificateManageDetail(
		@RequestParam("cateCode") String cateCode,
		Model model
	) {
		CategoryVO cate = service.retrieveCategory(cateCode);
		model.addAttribute("cate", cate);
		
		return "administration/off_gen/certi/CertiManageDetail";
	}
	
	//증명서 수정
	@RequestMapping("/certificateManage/certificateManageUpdate.do")
	public String getCertificateManageUpdate(
		@RequestParam("cateCode") String cateCode
		, Model model
	) {
		CategoryVO cate = service.retrieveCategory(cateCode);
		model.addAttribute("cate", cate);
		
		return "administration/off_gen/certi/CertiManageForm";
	}
	//증명서 수정
	@RequestMapping(value="/certificateManage/certificateManageUpdate.do", method=RequestMethod.POST)
	public String postCertificateManageUpdate(
		@ModelAttribute("cate") CategoryVO cate,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyCategory(cate);
			
			switch (result) {
			case OK:
				viewName = "redirect:/certificateManage/certificateManageDetail.do?cateCode=" + cate.getCateCode();
				break;
			default:
				viewName = "administration/off_gen/certi/CertiManageList";
				message = "오류! 잠시 후 시도해주세요.";
				break;
			}
		} else {
			viewName = "administration/off_gen/certi/CertiManageEdit";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}	
	
	//증명서 발급 리스트
	@RequestMapping("/certificateManage/certiList.do")
	public String certiList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
		@ModelAttribute("searchVO") SearchVO searchVO,
		Model model
	) {
		PagingVO<CertiVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveCertiList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/off_gen/certi/CertiList";
	}
	
	//증명서 발급 상세리스트 
	@RequestMapping("/certificateManage/certiDetail.do")
	public String certiDetail(
		@RequestParam("certiNo") String certiNo,
		Model model
	) {
		CertiVO certi = service.retrieveCerti(certiNo);
		model.addAttribute("certi", certi);
		
		return "administration/off_gen/certi/CertiDetail";
	}
	
	//증명서 발급 등록
	@RequestMapping("/certificateManage/certiInsert.do")
	public String getCertiInsert(
		@ModelAttribute("certi") CertiVO certi
		, @AuthenticationPrincipal(expression="authMember") MemberVO member
		, Model model
	) {
		model.addAttribute("member", member);
		
		return "administration/off_gen/certi/CertiForm";
	}
	//증명서 발급 등록
	@RequestMapping(value="/certificateManage/certiInsert.do", method=RequestMethod.POST)
	public String postDepartmentManageInsert(
		@ModelAttribute("certi") CertiVO certi
		, Errors errors
		, @AuthenticationPrincipal(expression="authMember") MemberVO member
		, Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createCerti(certi);
			switch(result) {
			case OK:
				viewName = "redirect:/certificateManage/certiDetail.do?certiNo="+certi.getCertiNo();
				break;
			default:
				viewName = "administration/off_gen/certi/CertiList";
				message = "서버 오류, 잠시뒤 다시 시도하세요.";
			}
		}else {
			viewName = "administration/off_gen/certi/CertiForm";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("member", member);
		
		return viewName;
	}
	
	//증명서 발급 리스트 확인
	@RequestMapping("/certificateManage/certiListCheck.do")
	public String certiListCheck(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
		, @AuthenticationPrincipal(expression="authMember") MemberVO member
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<CertiVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		searchVO.setSearchWord2(member.getMemId());
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveCheckList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("member", member);
		
		return "administration/off_gen/certi/CertiListCheck";
	}
	
	//증명서 발급 수정
	@RequestMapping("/certificateManage/certiUpdate.do")
	public String getCertiUpdate(
		@RequestParam("certiNo") String certiNo,
		Model model
	) {
		CertiVO certi = service.retrieveCerti(certiNo);
		model.addAttribute("certi", certi);
		
		return "administration/off_gen/certi/CertiUpdateForm";
	}
	//증명서 발급 수정
	@RequestMapping(value="/certificateManage/certiUpdate.do", method=RequestMethod.POST)
	public String postCertiUpdate(
		@ModelAttribute("certi") CertiVO certi,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyCerti(certi);
			
			switch(result) {
			case OK:
				viewName = "redirect:/certificateManage/certiDetail.do?certiNo="+certi.getCertiNo();
				break;
			default:
				viewName = "administration/off_gen/certi/CertiList";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
			viewName = "administration/off_gen/certi/CertiUpdateForm";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	//증명서 리스트(select)
	@ResponseBody
	@RequestMapping(value="/certificateManage/certificateList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CategoryVO> certificateList(
			@ModelAttribute("cate") CategoryVO cate){
		
		return dao.selectCertificate(cate);
	}
	
	//증명서 수수료 select
	@ResponseBody
	@RequestMapping(value="/certificateManage/certificateListValue.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public CategoryVO certificateListValue(
			@ModelAttribute("cate") CategoryVO cate){
		
		return dao.selectCertificateValue(cate);
	}
	
	//상태코드 ajax
	@ResponseBody
	@RequestMapping(value="/certificateManage/certirficateStateList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String trackStateList(
		@RequestParam(value="checkArr[]") List<String> certiList
	){
		log.info(certiList.toString());
		
		for(int i=0;i<certiList.size();i++){
			dao.update(certiList.get(i));
		}
		log.info(certiList.toString());
		
		return String.valueOf(certiList);
	}
	
	//상태코드 ajax
	@ResponseBody
	@RequestMapping(value="/certificateManage/certirficateCateState.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String CateStateList(
		@RequestParam(value="checkArr[]") List<String> cate
	){
		log.info(cate.toString());
		
		for(int i=0;i<cate.size();i++){
			dao.updateCateState(cate.get(i));
		}
		log.info(cate.toString());
		
		return String.valueOf(cate);
	}
	
	//증명서 코드 select
	@ResponseBody
	@RequestMapping(value="/certificateManage/certiSelect.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<CategoryVO> trackCodeList(
		@ModelAttribute("cate") CategoryVO cate
	) {
		return dao.selectCateList(cate);
	}
	
	@RequestMapping("/certificateManage/certiPdf.do")
	public String certiPdf(
		@RequestParam("cateCode") String cateCode,
		@AuthenticationPrincipal(expression="authMember") MemberVO member, 
		Model model
	) {
		log.info(cateCode);
		
		String viewName = null;
		
		if (cateCode.equals("CED03")){
			viewName = "CED03";
		}
		if (cateCode.equals("CED04")){
			viewName = "CED04";
		}
		
		model.addAttribute("member", member);
		
		return viewName;
	}
}
