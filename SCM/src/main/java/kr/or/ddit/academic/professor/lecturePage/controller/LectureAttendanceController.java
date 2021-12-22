package kr.or.ddit.academic.professor.lecturePage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.professor.lecturePage.dao.LectureAttendanceDAO;
import kr.or.ddit.academic.professor.lecturePage.service.LectureAttendanceService;
import kr.or.ddit.academic.professor.mainPage.service.ProfessorMainService;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureAttendanceController {
	
	@Inject
	private  ProfessorMainService profMainservice;
	
	
	@Inject
	private LectureAttendanceService service; 
	
	@Inject
	private LectureAttendanceDAO dao; 
	
	//데이터 구성 
	private void makeModel(int currentPage, SearchVO searchVO, Model model) {
		//screenSize를 학생 수만큼으로 하기 
	
	    int screenSize	= dao.selectCountStudent(searchVO.getSearchWord());
		
		PagingVO<AttendanceVO> pagingVO = new PagingVO<>(screenSize, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<AttendanceVO> attendanceList = service.retrieveAttendanceList(pagingVO);
		pagingVO.setDataList(attendanceList);
		model.addAttribute("pagingVO", pagingVO);
	}
	
	
	//전체 수강생 출결 리스트
	@RequestMapping(value="/common/lecturePage/attendanceList.do", method=RequestMethod.GET)
	public String showAttendanceList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
			@RequestParam(value="today", required=false, defaultValue="1900-01-01") String today,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model,
			HttpSession session,
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember

			) throws ParseException {
				
		String classNo = (String) session.getAttribute("classNo");
	
		searchVO.setSearchWord(classNo);
		makeModel(currentPage, searchVO, model);
		model.addAttribute("mainContent", profMainservice.retrieveProfMainContent(authMember));


		return "academic/common/lecturePage/attendanceList"; 
	} 
	
	
	//상세 출석조회
	@RequestMapping("/common/lecturePage/attDetail.do")
	public String attDetail(
			@RequestParam(value="classNo") String classNo
			,@RequestParam(value="start") String start
			,Model model
			) throws ParseException {
		AttendanceVO Info = new AttendanceVO();
		
		Info.setClassNo(classNo);
		
		String year = start.substring(2, 4);
		String month = start.substring(5,7);
		String day = start.substring(8,10);
		log.info("날짜 : {}", year+"/"+month+"/"+day);
		String classDate = year+"/"+month+"/"+day;
		Info.setAtdcDate(classDate);
		
		List<AttendanceVO> attList = service.retrieveClassAtt(Info);
		
		//강의의 현 수강인원 가져오기
		int count = service.retrieveStuCount(classNo);
		int attcount = attList.size();
		
		model.addAttribute("count", count);
		model.addAttribute("attcount", attcount);
		model.addAttribute("attList", attList);
		model.addAttribute("classNo", classNo);
		model.addAttribute("start", start);
		return "academic/common/lecturePage/attDetail";
	}
	

	

	
}
