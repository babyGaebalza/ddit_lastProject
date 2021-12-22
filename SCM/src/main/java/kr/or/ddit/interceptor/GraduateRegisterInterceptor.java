package kr.or.ddit.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

public class GraduateRegisterInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		MemberVO authMember = ((MemberVOWrapper)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthMember();
		
		if(authMember.getMemTrack() == null) {
			modelAndView.setViewName("redirect:/student/graduation/trackManage");
			request.getSession().setAttribute("message", "졸업은 트랙이 정해져야 가능합니다.");
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
