package kr.or.ddit.academic.professor.lecturePage.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.academic.common.lecturePage.service.CommonLectureTaskService;
import kr.or.ddit.academic.professor.lecturePage.service.ProfessorLectureTaskService;
import kr.or.ddit.academic.vo.TaskVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LectureReportManageController {

	@Inject
	private CommonLectureTaskService commonService;
	
	@Inject
	private ProfessorLectureTaskService profService;
	
	//삭제(사실 update) 
	@RequestMapping(value="/professor/lecturePage/reportDelete.do", method=RequestMethod.GET)
	public String deleteNotice(
			@RequestParam(value ="boardNo", required=true) String boardNo, 
			HttpServletRequest req
			) {
		HttpSession session = req.getSession(); 
		ServiceResult result = profService.removeTask(boardNo); 
		
		String viewName = null;
		String message = null;
		switch (result) {
		case OK:    
			viewName = "redirect:/common/lecturePage/reportBoardList.do";
			break;
		default:    
			viewName = "redirect:/common/lecturePage/reportBoardView.do?what="+boardNo;
			message = "서버 오류";
			break;   
		}
		session.setAttribute("message", message);
		return viewName;
	}
	
	
	
	//과제게시글 등록(폼띄우기)
	@RequestMapping(value="/professor/lecturePage/reportBoardInsert.do", method=RequestMethod.GET)
	public String showReportBoardForm(
			@ModelAttribute("board") BoardVO taskNotice
			//,@RequestParam(value ="classNo", required=true) String classNo
			) {
		
		return "academic/professor/lecturePage/taskBoardForm"; 
	} 
	
	//과제게시글 등록(폼에서 입력값 받기)
	@RequestMapping(value="/professor/lecturePage/reportBoardInsert.do", method=RequestMethod.POST)
	public String insesrtReportBoardForm(
			@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO taskNotice
			, Errors errors
			, Model model
			, HttpSession session
		) {
		
			String classNo = (String) session.getAttribute("classNo");
			taskNotice.setClassNo(classNo);
		
			String viewName = null;
			String message = null;
			
			if(!errors.hasErrors()) {
				
				log.info("첨부파일 개수 : {}", taskNotice.getBoFiles().length);
				log.info("첨부파일 개수 : {}", taskNotice.getAttatchList().size());
				for(AttatchVO atch : taskNotice.getAttatchList()) {
					log.info("첨부파일명 : {}", atch.getAttOgfilename());
				}
				
				ServiceResult result = profService.createTask(taskNotice);
				switch(result) {
				case OK:     
					viewName = "redirect:/common/lecturePage/reportBoardList.do";
					break;
				default:
					viewName = "academic/common/lecturePage/taskBoardList";
					message = "서버 오류, 잠시뒤 다시 해보셈.";
				}
			//에러	
			}else { 
				viewName = "academic/professor/lecturePage/taskBoardForm";
			}
			
			model.addAttribute("message", message);
			
			return viewName;
		}
	
	//과제게시글 수정(폼띄우기)
	@RequestMapping(value="/professor/lecturePage/update.do")
	public String editReportBoard(	@RequestParam("what")  String boardNo, Model model
			) {
		BoardVO board = commonService.retrieveTask(boardNo); 
		model.addAttribute("board", board);
		return "academic/professor/lecturePage/taskBoardEdit"; 
	}
	
	//과제기간 계산하기 
	public long testDeadlineDate(String boardDeadLine) {
		Date inputBoardDeadlinedate = null; 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			 inputBoardDeadlinedate = format.parse(boardDeadLine);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date today = new Date();
		long duringReport = inputBoardDeadlinedate.getTime() -  today.getTime() ; 
		duringReport /= (1000*24*60*60);
		duringReport = (long) Math.ceil(duringReport) + 1;
		return duringReport; 
	} 
	
	//과제게시글 수정 시 마감 날짜 입력했을때 
	@RequestMapping(value="/professor/lecturePage/update.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String,String> checkDeadline( 
		@RequestParam(value="boardDeadLine", required=true)String boardDeadLine, 
		HttpServletRequest req)
	{	
		Map<String,String> result = new HashMap<>();
		String resultMsg = null; 
		
		long duringReport = testDeadlineDate(boardDeadLine);
	    
		log.info("과제기간" + duringReport );
	    if(duringReport<=0) {
	    	resultMsg = "과제마감기한 설정이 잘못되었습니다";	
	    }else {
	    	resultMsg ="과제기간은" + duringReport + "일로 설정되었습니다.";
	    }      
	    result.put("result", resultMsg);
		return result; 
	}
		
	//과제게시글 수정(폼에서 입력값 받기)
	@RequestMapping(value="/professor/lecturePage/update.do", method=RequestMethod.POST)
	public String updateReportBoard (
			@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board,
			Errors errors
			, Model model
			) {
		String boardDeadLine = board.getBoardDeadline();
		long duringReport = testDeadlineDate(boardDeadLine);
		boolean calDeadDate = true;
		if(duringReport<0) {
			calDeadDate = false; 
		}
		
		
		
		
		String viewName = null;
		String message = null;
		if(!errors.hasErrors()&& calDeadDate) {
			ServiceResult result = profService.modifyTask(board);
			switch(result) {
			case OK:
				viewName = "redirect:/common/lecturePage/reportBoardView.do?what="+board.getBoardNo();
				break;
			default:
				viewName = "academic/professor/lecturePage/taskBoardEdit";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}			
		}
		else if(!errors.hasErrors()&&!calDeadDate) {
			viewName = "academic/professor/lecturePage/taskBoardEdit";
			message = "과제마감기한 설정이 잘못되었습니다.";
		}
		else {
			viewName = "academic/professor/lecturePage/taskBoardEdit";
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
	
	// (학생)과제 제출
	@PostMapping("/student/lecturePage/submitTask.do")
	public String submitTask(
			@ModelAttribute("task") TaskVO task
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			, HttpSession session
			, RedirectAttributes reAttr
			) {
		// 제출자 set
		task.setMemId(authMember.getMemId());
		String message = null;
		try {
			String classNo = (String) session.getAttribute("classNo");
			task.setClassNo(classNo);
			commonService.createSubmitTask(task);
			message = "과제 제출 성공";
		} catch (Exception e) {
			e.printStackTrace();
			message = "서버 오류, 잠시 후 다시 시도해주세요.";
		}
		
		reAttr.addFlashAttribute("message", message);
		
		return "redirect:/common/lecturePage/reportBoardView.do?what=" + task.getBoardNo();
	}
	
	// (학생)과제 수정
	@PostMapping("/student/lecturePage/modifyTask.do")
	public String modifyTask(
			@ModelAttribute("task") TaskVO task
			, RedirectAttributes reAttr
			) {
		// 제출자 set
		String message = null;
		try {
			commonService.modifySubmitTask(task);
			message = "과제 수정 성공";
		} catch (Exception e) {
			e.printStackTrace();
			message = "서버 오류, 잠시 후 다시 시도해주세요.";
		}
		
		reAttr.addFlashAttribute("message", message);
		
		return "redirect:/common/lecturePage/reportBoardView.do?what=" + task.getBoardNo();
	}
}
