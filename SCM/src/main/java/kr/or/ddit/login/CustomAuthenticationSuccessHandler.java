package kr.or.ddit.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.or.ddit.common.member.dao.MemberDao;
import kr.or.ddit.common.schedule.dao.ScheduleDAO;
import kr.or.ddit.enumpkg.UserCode;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Inject
	private MemberDao memberDAO;
	@Inject
	private ScheduleDAO scheduleDAO;
	
	private String getUserMainPageMapping(String userCode) {
		
		String mapping = UserCode.valueOf(userCode).getViewURL();
		
		return mapping;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		MemberVO authMember = ((MemberVOWrapper) authentication.getPrincipal()).getAuthMember();
		
		// 회원아이디, 유저식별코드
		String memId = authMember.getMemId();
		String userCode = authMember.getUserCode();
		
		// 로그인 실패 횟수 초기화
		Map<String, Object> loginMap = new HashMap<>();
		loginMap.put("memId", memId);
		loginMap.put("loginFailCnt", new Integer(0));
		memberDAO.updateLoginfailcnt(loginMap);

		// 유저코드 기반 redirect
		response.sendRedirect(request.getContextPath() + getUserMainPageMapping(userCode));
	}

}
