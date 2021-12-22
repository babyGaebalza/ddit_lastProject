package kr.or.ddit.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.ddit.academic.student.lecture.dao.StudentLecturePageDAO;
import kr.or.ddit.vo.ClassVO;

public class LecturePageInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	private StudentLecturePageDAO studentLecturePageDAO;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		String classNo = (String)session.getAttribute("classNo");
		Map<String, Object> studentLecturePageMap = new HashMap<>();
		studentLecturePageMap.put("classNo", classNo);
		ClassVO currentLecturePageInfo = studentLecturePageDAO.selectLectureInfo(studentLecturePageMap);
		request.setAttribute("currentLecturePageInfo", currentLecturePageInfo);
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
