package kr.or.ddit.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.member.service.MemberService;
import kr.or.ddit.enumpkg.KindOfSendCode;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.util.SMSServiceUtil;
import kr.or.ddit.validate.groups.FindIdGroup;
import kr.or.ddit.validate.groups.ResetPasswordAndUnlockAccountGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginPageProcessController {
	
	@Inject
	private SMSServiceUtil smsServiceUtil;
	@Inject
	private MemberService memberService;
	@Inject
	private PasswordEncoder passwordEncoder;
	
	
	//------------------------------------------------ 코드 발송 프로세스 ----------------------------------------------------------------
	// 인증코드 발송 처리
	private void sendCodeProcess(
			Map<String, Object> resultMap
			, MemberVO member
			, Errors errors
			, HttpSession session
			, KindOfSendCode kindOfSendCode) {
		
		ServiceResult result = null;
		
		// 검증 불통
		if(errors.hasErrors()) {
			result = ServiceResult.FAILED;
			StringBuffer errorMsg = new StringBuffer();
			for(ObjectError error : errors.getAllErrors()) {
				errorMsg.append(String.format("%s<br>", error.getDefaultMessage()));
			}
			resultMap.put("errorMsg", errorMsg);
			
		//검증 통과
		}else {
			// 입력된 조건으로 멤버 검색
			result = memberService.retrieveMember(member);
			log.info("컨트롤러 member 정보1 : {}", member);
			
			
			// 멤버가 있다면
			if(result == ServiceResult.OK) {
				// 보낼 코드 생성
				String code = smsServiceUtil.makeCode(6);
				// 보낼 번호
				String to = member.getMemTel();
				String pattern = null;
				switch (kindOfSendCode) {
				case FINDID:
					pattern = "**대학교 학번/사번 찾기 인증코드: %s";
					break;
				case RESETPASSWORD:
					pattern = "**대학교 비밀번호 초기화 인증코드: %s";
					break;
				case UNLOCKACCOUNT:
					boolean unlock = member.getMemLoginfailcnt() >= 3 ? true : false;
					
					if(unlock) {
						pattern = "**대학교 계정잠금해제 인증코드: %s";
						break;
					}else {
						result = ServiceResult.FAILED;
						resultMap.put("result", result);
						resultMap.put("errorMsg", String.format("%s님의 계정은 현재 잠금상태가 아닙니다.", member.getMemName()));
						return;
					}
				}
				
				// 있을시 sms서비스로 코드발송 / 인증 발송시 세션에 저장관리
				smsServiceUtil.sendMessage(to, String.format(pattern, code));
				session.setAttribute(kindOfSendCode.getCamelCase() + "Code", code);
				log.info("컨트롤러 member 정보2 : {}", member);
				session.setAttribute("findMember", member);
			}
		}
		
		resultMap.put("result", result);
	}
	
	// 아이디찾기 인증용 코드 발송
	@PostMapping(value="/login/findId/sendCode.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> sendCodeForFindId(
			@Validated(FindIdGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			, @RequestParam("kindOfSendCode") KindOfSendCode kindOfSendCode
			, HttpSession session) {
		
		Map<String, Object> resultMap = new HashMap<>();
		sendCodeProcess(resultMap, member, errors, session, kindOfSendCode);
		return resultMap;
	}
	// 비밀번호초기화 인증용 코드 발송
	@PostMapping(value="/login/resetPassword/sendCode.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> sendCodeForResetPassword(
			@Validated(ResetPasswordAndUnlockAccountGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			, @RequestParam("kindOfSendCode") KindOfSendCode kindOfSendCode
			, HttpSession session
			) {
		
		Map<String, Object> resultMap = new HashMap<>();
		sendCodeProcess(resultMap, member, errors, session, kindOfSendCode);
		return resultMap;
	}
	// 잠금계정해제 인증용 코드 발송
	@PostMapping(value="/login/unlockAccount/sendCode.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> sendCodeForUnlockAccount(
			@Validated(ResetPasswordAndUnlockAccountGroup.class) @ModelAttribute("member") MemberVO member
			, Errors errors
			, @RequestParam("kindOfSendCode") KindOfSendCode kindOfSendCode
			, HttpSession session
			) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		
		sendCodeProcess(resultMap, member, errors, session, kindOfSendCode);			
		return resultMap;
	}
	
	//------------------------------------------------ 코드 삭제 처리 프로세스 ----------------------------------------------------------------
	// 세션 데이터 삭제 처리
	private void removeSessionData(
			HttpSession session
			, String...sessionAttrName) {
		
		for(String attrName : sessionAttrName) {
			session.removeAttribute(attrName);
		}
	}
	
	// 인증용 코드 만료처리
	@GetMapping(value="/login/removeCode.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ServiceResult findPassCodeTimeout(
			@RequestParam("kindOfSendCode") KindOfSendCode kindOfSendCode
			, HttpSession session) {
		
		removeSessionData(session, kindOfSendCode.getCamelCase() + "Code", "findMember");
		
		return ServiceResult.OK;
	}
	
	//------------------------------------------------ 인증 처리 프로세스 ----------------------------------------------------------------
	
	public ServiceResult authCodeHandler(String authCode, KindOfSendCode kindOfSendCode, HttpSession session) {
		ServiceResult result = null;
		
		String sessionSavedCode = (String)session.getAttribute(kindOfSendCode.getCamelCase() + "Code");
		//인증코드 일치
		if(authCode.equals(sessionSavedCode)) {
			result = ServiceResult.OK;
			MemberVO findMember = (MemberVO) session.getAttribute("findMember");
			String memId = findMember.getMemId();
			String to = findMember.getMemTel();
			String memName = findMember.getMemName();
			String content = "서버오류, 잠시 후 다시 시도해주세요.";
			
			switch (kindOfSendCode) {
			case FINDID:
				content = String.format("** 대학교 %s님의 학번/사번은 %s 입니다.", memName, memId);
				break;
			case RESETPASSWORD:
				log.info("findMember 정보 : {}", findMember);
				String plane = findMember.getMemReg1();
				String encrypt = passwordEncoder.encode(plane);
				findMember.setMemPass(encrypt);
				ServiceResult resetPasswordResult = memberService.modifyResetPassword(findMember);
				if(resetPasswordResult == ServiceResult.OK) {
					content = String.format("** 대학교 %s님의 비밀번호가 초기화되었습니다.", memName);					
				}
				break;
			case UNLOCKACCOUNT:
				ServiceResult unlockAccountResult = memberService.modifyUnlockAccount(findMember);
				if(unlockAccountResult == ServiceResult.OK) {
					content = String.format("** 대학교 %s님의 계정이 잠금해제되었습니다.", memName);					
				}
				break;
			}
			
			smsServiceUtil.sendMessage(to, content);
		}else {
		//인증코드 불일치	
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
	// 인증 처리 
	@PostMapping(value="/login/auth.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ServiceResult authProcess(
			@RequestParam("authCode")String authCode
			,@RequestParam("kindOfSendCode") KindOfSendCode kindOfSendCode
			, HttpSession session) {
		
		ServiceResult result = authCodeHandler(authCode, kindOfSendCode, session);
		if(result == ServiceResult.OK) {
			removeSessionData(session, kindOfSendCode.getCamelCase() + "Code", "findMember");			
		}
		
		return result;
	}
	
	//------------------------------------------------ 폼 이동 ----------------------------------------------------------------
	// 폼 이동
	@GetMapping("/login/processForm/{formKind}")
	public String findIdForm(
			@PathVariable() String formKind                          
			) {
		return "login/" + formKind;
	}
}
