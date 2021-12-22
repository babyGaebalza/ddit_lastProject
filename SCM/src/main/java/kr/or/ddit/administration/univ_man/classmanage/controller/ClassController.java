package kr.or.ddit.administration.univ_man.classmanage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.univ_man.classmanage.service.ClassManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ClassController {
	
	@Inject
	private ClassManageService service;
	
	/**
	 * 전체 리스트 조회
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_man/classList.do")
	public String classList(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<ClassVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveClassList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "administration/univ_man/class/ClassList";
	}
	
	/**
	 * 선택 체육시설 조회
	 * @param facNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_man/classView.do")
	public String classView(
		@RequestParam("classNo") String classNo,
		Model model
	) {
		ClassVO uclass = service.retrieveClass(classNo);
		
		model.addAttribute("uclass", uclass);
		
		return "administration/univ_man/class/ClassDetail";
	}
	
	/**
	 * 업데이트 입력
	 * @param facNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/univ_man/classUpdate.do")
	public String updateForm(
			@RequestParam("classNo") String classNo,
			Model model
		) {
		ClassVO uclass = service.retrieveClass(classNo);
			model.addAttribute("uclass", uclass);
		
		return "administration/univ_man/class/ClassUpdateForm";
	}
	
	/**
	 * 업데이트용
	 * @param facility
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/univ_man/classUpdate.do", method=RequestMethod.POST)
	public String classUpdate(
		@ModelAttribute("uclass") ClassVO uclass,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyClass(uclass);
			switch(result) {
				case OK:
					viewName = "redirect:/univ_man/classView.do?classNo="+ uclass.getClassNo();
					break;
				default:
					viewName = "administration/univ_man/class/ClassList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/univ_man/class/ClassUpdateForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	
	@RequestMapping(value="/univ_man/classDelete.do", method=RequestMethod.POST)
	public String classDelete(
		@RequestParam("classNo") String classNo,
		RedirectAttributes redirectAttributes
	) {
		ClassVO uclass = new ClassVO();
		uclass.setClassNo(classNo);
		
		ServiceResult result = service.removeClass(uclass);
		
		String viewName = null;
		String message = null;
		
		switch(result) {
		case OK :
			viewName = "redirect:/univ_man/classView.do?classNo="+uclass.getClassNo();
			break;
		default :
			viewName = "univ_man/class/classList";
			message = "서버오류";
			break;
		}
	redirectAttributes.addFlashAttribute("message",message);
	return viewName;
	}
	
	/**
	 * 입력
	 * @param facility
	 * @return
	 */
	@RequestMapping("/univ_man/classInsert.do")
	public String insertForm(
		@ModelAttribute("uclass") ClassVO uclass
	) {
		return "administration/univ_man/class/ClassInsertForm";
	}
	
	
	@RequestMapping(value="/univ_man/classInsert.do", method=RequestMethod.POST)
	public String classInsert(
		@ModelAttribute("uclass") ClassVO uclass,
		Errors errors,
		Model model
	) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createClass(uclass);
			switch(result) {
				case OK:
					viewName = "redirect:/univ_man/classView.do?classNo="+ uclass.getClassNo();
					break;
				default:
					viewName = "univ_man/ClassList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		}else {
				viewName = "administration/univ_man/class/ClassInsertForm";
				
			}
			
			model.addAttribute("message", message);
			
			return viewName;
	}
	
	
	
}