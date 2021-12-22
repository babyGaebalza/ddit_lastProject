package kr.or.ddit.administration.univ_aca.majormanage.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.univ_aca.majormanage.service.MajorManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class MajorController {
	@Inject
	private MajorManageService service;
	
	/**
	 * 학과 리스트 출력
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_aca/majorList.do")
	public String majorList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<MajorVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveMajorList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/univ_aca/major/MajorList";
	}
	
	/**
	 * 선택 학과 조회
	 * @param majorName
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_aca/majorView.do")
	public String majorView(
		@RequestParam("majorName") String majorName,
		Model model
	) {
		MajorVO major = service.retrieveMajor(majorName);
		
		model.addAttribute("major", major);
		
		return "administration/univ_aca/major/MajorDetail";
	}
	
	
	
	/**
	 * 업데이트 요청
	 * @param majorName
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_aca/majorUpdate.do")
	public String updateForm(
			@RequestParam("majorName") String majorName,
			Model model
		) {
			MajorVO major = service.retrieveMajor(majorName);
			model.addAttribute("major", major);
		
		return "administration/univ_aca/major/MajorUpdateForm";
	}
	
	
	/**
	 * 업데이트 반영
	 * @param major
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/univ_aca/majorUpdate.do", method=RequestMethod.POST)
	public String majorUpdate(
		@ModelAttribute("major") MajorVO major,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyMajor(major);
			switch(result) {
				case OK:
					String urlEncodeMajorName;
					try {
						urlEncodeMajorName = URLEncoder.encode(major.getMajorName(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
					viewName = "redirect:/univ_aca/majorView.do?majorName="+ urlEncodeMajorName;
					break;
				default:
					viewName = "univ_aca/MajorList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/univ_aca/major/MajorUpdateForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	/**
	 * 삭제용
	 * @param majorName
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/univ_aca/majorDelete.do", method=RequestMethod.POST)
	public String majorDelete(
		@RequestParam("majorName") String majorName,
		RedirectAttributes redirectAttributes
	) {
		MajorVO major = new MajorVO();
		major.setMajorName(majorName);
		
		ServiceResult result = service.removeMajor(major);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK :
//			viewName = "administration/univ_aca/major/MajorList";
			viewName = "redirect:/univ_aca/majorList.do";
			break;
		default :
			viewName = "redirect:/univ_aca/majorView.do?majorName="+majorName;
			message = "서버오류";
			break;
		}
	redirectAttributes.addFlashAttribute("message",message);
	return viewName;
	}
	
	/**
	 * 입력 요청
	 * @param major
	 * @return
	 */
	@RequestMapping("/univ_aca/majorInsert.do")
	public String insertForm(
		@ModelAttribute("major") MajorVO major
	) {
		return "administration/univ_aca/major/MajorInsertForm";
	}
	
	/**
	 * 입력 반영
	 * @param major
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/univ_aca/majorInsert.do", method=RequestMethod.POST)
	public String majorInsert(
		@ModelAttribute("major") MajorVO major,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createMajor(major);
			switch(result) {
				case OK:
					String urlEncodeMajorName;
					try {
						urlEncodeMajorName = URLEncoder.encode(major.getMajorName(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException(e);
					}
					viewName = "redirect:/univ_aca/majorView.do?majorName="+ urlEncodeMajorName;
//					viewName = "redirect:/univ_aca/majorView.do?majorName="+ major.getMajorName();
					break;
				default:
					viewName = "univ_aca/MajorList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/univ_aca/major/MajorInsertForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
}
