package kr.or.ddit.administration.univ_man.calendarManage.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.common.lecturePage.service.CommonLectureNoticeService;
import kr.or.ddit.administration.univ_man.calendarManage.service.UnivCalendarService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ScheduleVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class UnivCalendarController {


	@Inject
	private UnivCalendarService service;
	
	//공지게시글 
	@RequestMapping(value="/univ_man/univCalendarManage.do", method=RequestMethod.GET)
	public String goToNoticetList( 	Model model
			) {	
		List<ScheduleVO> scheduleList = service.retrieveCalendarList();  

		model.addAttribute("scheduleList",scheduleList);
		return "administration/univ_man/calendar/univcalendarList"; 
	} 

	
}
