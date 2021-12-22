package kr.or.ddit.academic.student.enrollment.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.administration.off_acc.enrollmentmanage.dao.EnrollmentManageDAO;
import kr.or.ddit.administration.off_acc.enrollmentmanage.service.EnrollmentManageService;
import kr.or.ddit.administration.vo.TuitionVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class Enrollment {
	@Inject
	private EnrollmentManageService service;
	@Inject
	private EnrollmentManageDAO dao;
	
	@RequestMapping("/student/enrollment/enrollmentList.do")
	public String enrollmentList(
		@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage
		, @AuthenticationPrincipal(expression="authMember") MemberVO member
		, @ModelAttribute("searchVO") SearchVO searchVO
		, Model model
	) {
		PagingVO<TuitionVO> pagingVO = new PagingVO<>();	
		pagingVO.setCurrentPage(currentPage);
		searchVO.setSearchWord2(member.getMemId());
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveStudentCheckList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("member", member);
		
		return "academic/student/enrollment/EnrollmentList";
	}
	
	@ResponseBody
	@RequestMapping(value="/student/enrollment/enrollmentInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public int getEnrollmentInsert(
		@RequestParam(value="merchant_uid") String merchant_uid,
		@RequestParam(value="vbank_num") String vbank_num,
		@RequestParam(value="vbank_name") String vbank_name,
		@AuthenticationPrincipal(expression="authMember") MemberVO member,
		Model model
	){
		System.out.println(merchant_uid);
		System.out.println(vbank_num);
		System.out.println(vbank_name);
		
		TuitionVO tuition = new TuitionVO();
		tuition.setTuiNo(merchant_uid);
		tuition.setTuiMem(member.getMemId());
		tuition.setTuiBank(vbank_name);
		tuition.setTuiAccount(vbank_num);
		
		return dao.insertTuition(tuition);
	}
}
