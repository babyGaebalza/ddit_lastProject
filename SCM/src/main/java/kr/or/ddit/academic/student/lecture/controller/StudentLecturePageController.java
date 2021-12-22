package kr.or.ddit.academic.student.lecture.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.student.lecture.dao.StudentLecturePageDAO;
import kr.or.ddit.academic.student.lecture.service.StudentLecturePageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentLecturePageController {
	
	@Inject
	private StudentLecturePageService studentLecturePageService;
	
	
	// 강의페이지 리스트
	@GetMapping("/student/lecture/pageListForm")
	public String lecturePageListForm(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model
			) {
		
		Map<String, Object> resultMap = studentLecturePageService.retrieveLecturePageListInfo(authMember.getMemId());
		model.addAttribute("resultMap", resultMap);
		
		return "academic/studentMain/lecture/lecturePageListForm";
	}
	
	
	// 개별 강의페이지 로드
	@GetMapping("/student/lecturePage/main.do")
	public String lecturePage(
			@RequestParam(value="classNo", required=false) String classNo
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, Model model
			, HttpSession session
			) {
		Map<String, Object> studentLecturePageMap = new HashMap<>();
		
		if(classNo == null) {
			classNo = (String) session.getAttribute("classNo");
		}else {
			session.setAttribute("classNo", classNo);			
		}
		
		studentLecturePageMap.put("memId", authMember.getMemId());
		studentLecturePageMap.put("classNo", classNo);
		Map<String, Object> resultMap = studentLecturePageService.retrieveStudentLecturePage(studentLecturePageMap);
		model.addAttribute("resultMap", resultMap);
		
		return "academic/student/lecturePage/studentLectureMain";
	}
	
	//학생개인(나의 출석부) 출석조회 -> 해당강의만
	@RequestMapping(value="/student/lecturePage/myAtt")
	public String myAtt(
			HttpSession session
			,@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,Model model
			) {
		
		String memId = authMember.getMemId();
		String classNo = (String) session.getAttribute("classNo");
		AttendanceVO att = new AttendanceVO();
		att.setClassNo(classNo);
		att.setClassStudent(memId);
		
		List<AttendanceVO> myAtt = studentLecturePageService.retrieveMyAttend(att);
		
		model.addAttribute("myAtt", myAtt);
		return "academic/student/lecturePage/myAtt";
	}
	
	//화상강의
	@RequestMapping(value="/student/lecturePage/join.do")
	public String webRtc(
			HttpSession session
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,Model model
			) {
			String memId = authMember.getMemName();
			String memAtt = authMember.getMemId();
			String classNo = (String) session.getAttribute("classNo");
			
			model.addAttribute("memId", memId);
			model.addAttribute("memAtt", memAtt);
			model.addAttribute("classNo", classNo);
			log.info("1번 : {}", memAtt);
			
		return "academic/student/lecturePage/joinRtc";
	};
	
	
	//화상강의 참여한 학생 출석처리
	@RequestMapping(value="student/webRtc/attend.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String webAttend(
			@RequestParam("memAtt")String memId
			,@RequestParam("classNo")String classNo
			,@RequestParam("memAtt")String memAtt
			) {
		//출석에 필요한 데이터 세팅 부분
		AttendanceVO att = new AttendanceVO();
		att.setClassNo(classNo);
		att.setClassStudent(memAtt);
		
		ClassListVO classlist = studentLecturePageService.retrieveStudentClassList(att);
		String  classListno = classlist.getClasslistNo();
		att.setClasslistNo(classListno);
		//---------------------------------------------------------------------------------
		
		//준비된 데이터를 가지고 출석처리하는 부분		
		String res = "";
		ServiceResult result = studentLecturePageService.createStudentAttend(att);
		if(result==ServiceResult.OK) {
			res = "성공";
		}
		else {
			res = "실패";
		}

		return res;
	};
	
}
