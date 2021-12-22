package kr.or.ddit.academic.student.graduation.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.academic.student.graduation.service.GraduaitonManageService;
import kr.or.ddit.common.member.dao.MemberDao;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

@Controller
public class GraduaitonManageController {
	
	@Inject
	private GraduaitonManageService gmService;
	@Inject
	private MemberDao memDao;
	
	//--------------------------------------------------------------------- 졸업관리 ----------------------------------------------------------------------------
	@GetMapping("/student/graduation/graduationManage")
	public String graduationManage(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model
			) {
		
		try {
			BeanUtils.copyProperties(authMember, memDao.selectMember(authMember));
		} catch (IllegalAccessException | InvocationTargetException e) {
		}
		
		if(StringUtils.isNotBlank(authMember.getMemTrack())) {
			model.addAttribute("resultMap", gmService.retrieveStudentGraduationPage(authMember));			
		}
		
		return "academic/studentMain/graduation/graduationManage";
	}
	
	
	@GetMapping("/student/graduation/graduationRegister.do")
	public String graduationRegister(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model
			, RedirectAttributes reAttr
			) {
		
		ServiceResult result = gmService.createGraduationRegister(authMember);
		
		String message = null;
		
		switch (result) {
		case OK:
			message = "졸업이 신청되셨습니다.";
			break;
		case FAILED:
			message = "서버 오류, 잠시 후 다시 시도해주세요.";
			break;	
		}
		reAttr.addFlashAttribute("message", message);
		
		return "redirect:/student/graduation/graduationManage";
	}
	
	//--------------------------------------------------------------------- 트랙관리 ----------------------------------------------------------------------------
	@GetMapping("/student/graduation/trackManage")
	public String trackManage(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model
			) {
		
		model.addAttribute("resultMap", gmService.retrieveStudentTrackPage(authMember));
		
		return "academic/studentMain/graduation/trackManage";
	}
	
	@GetMapping(value="/student/graduation/trackSearch.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> trackSearch(
			@RequestParam("trackNo") String trackNo
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			){
		
		Map<String, Object> trackDetailSearchMap = new HashMap<>();
		trackDetailSearchMap.put("trackNo", trackNo);
		trackDetailSearchMap.put("authMember", authMember);
		
		
		return gmService.retrieveTrackInfoAndSatisfied(trackDetailSearchMap);
	}
	
	@GetMapping(value="/student/graduation/trackRegister.do")
	public String trackRegister(
			@RequestParam("trackNo") String trackNo
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, RedirectAttributes reAttr
			){
		
		Map<String, Object> trackDetailSearchMap = new HashMap<>();
		trackDetailSearchMap.put("trackNo", trackNo);
		trackDetailSearchMap.put("authMember", authMember);
		
		ServiceResult result = gmService.createOrModifyTrackRegister(trackDetailSearchMap);
		
		String message = null;
		
		switch (result) {
		case CREATESUCCESS:
			message = "트랙 신청완료";
			break;
		case MODIFYSUCCESS:
			message = "트랙 변경 신청완료";
			break;
		default:
			message = "서버 오류, 잠시 후 다시 시도해주세요.";
			break;
		}
		
		reAttr.addFlashAttribute("message", message);
		
		return "redirect:/student/graduation/trackManage";
	}
}
