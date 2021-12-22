package kr.or.ddit.administration.univ_man.registermanage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.administration.univ_man.registermanage.service.registerService;
import kr.or.ddit.administration.vo.RegisterVO;
import kr.or.ddit.common.member.service.MemberService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class registerController {

	@Inject
	private registerService service;
	
	@Inject
	private MemberService memberService;
	
	
	
	//전체 회원 학적관리
	@RequestMapping("/register/regList.do")
	public String registerList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO
			,Model model
		) {
		
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<MemberVO> list = service.retrieveRegisterList(pagingVO);
		model.addAttribute("pagingVO", pagingVO);

		pagingVO = (PagingVO<MemberVO>)model.asMap().get("pagingVO");
		
		
		return "administration/univ_man/register/RegisterList";
	}
	
	//특정 학생 학적내역
	@RequestMapping("/register/stuRegList.do")
	public String stuRegisterList(
			@RequestParam("memId") String memId,
			Model model
		) {
		
		List<MemberVO> detailList = service.retrieveStuRegisterList(memId);
		model.addAttribute("member", detailList);

		return "administration/univ_man/register/RegisterDetail";
	}
	
	//학적 입력/수정 폼으로 이동
	@RequestMapping("/register/registerForm.do")
	public String registerForm(
			@RequestParam("order") String order
			,@RequestParam("memId") String memId
			,Model model
		) {
		log.info("사용자 번호 : {}",memId);
		String view = "";

		List<MemberVO> detailList = service.retrieveStuRegisterList(memId);
		model.addAttribute("member", detailList);
		
		List<CategoryVO> regList = service.retrieveRegCode();
		model.addAttribute("regList", regList);
		
				
		
		if(order.equals("등록")) {
			view="administration/univ_man/register/RegInputForm";
		}
		if(order.equals("수정")) {
			view="administration/univ_man/register/RegModiForm";
		}
		return view;
	}
	
	//학적 입력 처리
	@RequestMapping("/register/registerInsert.do")
	public String registerInsert(
			@Validated(InsertGroup.class) @ModelAttribute("regInputForm") RegisterVO register
			,@RequestParam("regLeavedate") int regLeavedate
			,@RequestParam("memMiLimit") int memMiLimit
			,Errors errors
			,RedirectAttributes attr
			,@AuthenticationPrincipal(expression="authMemeber") MemberVO loginMember
		) {
		String memId = loginMember.getMemId();
		register.setRegFwriter(memId);
		register.setRegWriter(memId);
		
		try {
			service.createRegister(register);	
		} catch (Exception e) {
			attr.addFlashAttribute("message", "학적변경에 실패하셨습니다.");
			return "redirect:/register/stuRegList.do?memId="+register.getRegStudent();
		}
		
		MemberVO member = new MemberVO();
		member.setMemId(register.getRegStudent());
		member.setMemAbsLimit(regLeavedate);
		member.setMemMiLimit(memMiLimit);
		
		
		int res = service.updateMemReg(member);
		
		return "forward:/register/regList.do";
	}
	

	
}
