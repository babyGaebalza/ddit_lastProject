package kr.or.ddit.academic.assistant.studentmanage.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.assistant.studentmanage.service.StudentManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentManageController {
	@Inject
	private StudentManageService service;
	
	//학과 학생리스트
	@RequestMapping("/studentManage/studentManageList.do")
	public String studentManageList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
			, @AuthenticationPrincipal(expression="authMember") MemberVO member
			, @ModelAttribute("searchVO") SearchVO searchVO
			, Model model
		) {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();	
		pagingVO.setCurrentPage(currentPage);
		searchVO.setSearchWord2(member.getMemMajor());
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveMemberList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("member", member);
		
		return "academic/assistant/studentManage/StudentManageList";
	}
	
	//학생 상세정보
	@RequestMapping("/studentManage/studentManageDetail.do")
	public String studentManageDetail(
			@RequestParam("memId") String memId
			, Model model
		) {
		MemberVO member = service.retrieveMember(memId);
		model.addAttribute("member", member);
		
		return "academic/assistant/studentManage/StudentManageDetail";
		
	}
	 
	//학과 학생 정보 수정
	@RequestMapping("/studentManage/studentManageUpdate.do")
	public String getStudentManageUpdate(
			@RequestParam("memId") String memId
			, Model model
		) {
		MemberVO member = service.retrieveMember(memId);
		model.addAttribute("member", member);
		
		return "academic/assistant/studentManage/StudentManageEdit";
	}
	//학과 학생 정보 수정
	@RequestMapping(value="/studentManage/studentManageUpdate.do", method=RequestMethod.POST)
	public String postStudentManageUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member,
			Errors errors,
			Model model
		) {
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyMember(member);
			
			switch (result) {
			case OK:
				viewName = "redirect:/studentManage/studentManageDetail.do?memId=" + member.getMemId();
				break;
			default:
				viewName = "academic/assistant/studentManage/StudentManageList";
				message = "오류! 잠시 후 시도해주세요.";
			}
		} else {
			viewName = "academic/assistant/studentManage/StudentManageEdit";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
}
