package kr.or.ddit.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import kr.or.ddit.common.member.dao.MemberDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Inject
	private MemberDao memDao;
	
	private void setErrorMessageByException(Throwable exception, String memId, String memPass, HttpSession session) {
		
		// 하단 조건절의 두 exception은 userDetailServiceImple에서 메시지 구현했으므로 바로 받아옴
		String errorMessage = exception.getMessage();
		log.info("에러 메시지 : {}", errorMessage);
		log.info("에러 종류 : {}", exception.getClass().getSimpleName());
		// 비밀번호 불일치(= 조건절의 두 exception과 다른 spring-security 내부적으로 발생시키는 exception일 경우)
		if(StringUtils.isBlank(memPass)) {
			if(StringUtils.isBlank(memId)) {
				errorMessage += "<br>비밀번호를 입력해주세요.";
			}else {
				errorMessage = "비밀번호를 입력해주세요.";
			}
		}
		
		if(!((exception instanceof UsernameNotFoundException) || (exception.getCause() instanceof DisabledException))) {
			
			// loginMap 생성
			Map<String, Object> loginMap = new HashMap<>();
			loginMap.put("memId", memId);
			loginMap.put("loginFailCnt", new Integer(1));
			
			// 실패횟수 + 1
			int cnt = memDao.updateLoginfailcnt(loginMap);
			
			if(cnt > 0) { 
				errorMessage = String.format("로그인 %d회 실패<br>(3회 실패시 계정잠금입니다.)", loginMap.get("loginFailCnt")); 
			}else {
				errorMessage = "네트워크 오류, 잠시 후 다시 시도해주세요.";
			}
		}
		
		session.setAttribute("errorMessage", errorMessage);
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String memId = request.getParameter("memId");
		String memPass = request.getParameter("memPass");
		
		// exception 종류에 따른 메시지 세션 적재
		setErrorMessageByException(exception, memId, memPass, request.getSession());
		
		// 다시 로그인 페이지 redirect
		response.sendRedirect(request.getContextPath() + "/main");
	}

}
