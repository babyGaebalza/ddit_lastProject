package kr.or.ddit.academic.professor.lecturePage.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.common.lecturePage.service.CommonLectureNoticeService;
import kr.or.ddit.academic.professor.lecturePage.service.ProfessorLectureNoticeService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class LectureNoticeManageController {

	@Inject
	private ProfessorLectureNoticeService profService; 
	
	@Inject 
	private CommonLectureNoticeService commonService; 
	
	
	//삭제(사실 update) 
	@RequestMapping(value="/professor/lecturePage/noticeDelete.do", method=RequestMethod.GET)
	public String deleteNotice(
			@RequestParam(value ="boardNo", required=true) String boardNo, 
			HttpServletRequest req
			) {

		HttpSession session = req.getSession(); 
		ServiceResult result = profService.removeNotice(boardNo); 
		
		String viewName = null;
		String message = null;
		switch (result) {
		case OK:    
			viewName = "redirect:/common/lecturePage/noticeList.do";
			break;
		default:    
			viewName = "redirect:/common/lecturePage/noticeBoardView.do?what="+boardNo;
			message = "서버 오류";
			break;   
		}
		session.setAttribute("message", message);
		return viewName;
	}
		

		
	// 등록(폼띄우기)
		@RequestMapping(value="/professor/lecturePage/noticeBoardInsert.do", method=RequestMethod.GET)
		public String showNoticeBoardForm(
				@ModelAttribute("board") BoardVO notice,  
				@RequestParam(value ="classNo", required=true, defaultValue="2051700001") String classNo
				) {
			notice.setClassNo(classNo);

			return "academic/professor/lecturePage/noticeBoardForm"; 
		} 
		
		// 등록(폼에서 입력값 받기)
		@RequestMapping(value="/professor/lecturePage/noticeBoardInsert.do", method=RequestMethod.POST)
		public String insesrtNoticeBoardForm(
				@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO notice
				, Errors errors
				, Model model
				
			) {
				String viewName = null;
				String message = null;
								
				
				if(!errors.hasErrors()) {
					ServiceResult result = profService.createNotice(notice);
					switch(result) {
					case OK:     
						viewName = "redirect:/common/lecturePage/noticeList.do?classNo="+notice.getClassNo();
						break;
					default:  
						viewName = "redirect:/common/lecturePage/noticeList.do?classNo="+notice.getClassNo();
						message = "서버 오류, 잠시뒤 다시 해보셈.";
					}
				//에러	
				}else { 
					viewName = "academic/professor/lecturePage/noticeBoardForm";
				}
				
				model.addAttribute("message", message);
				
				return viewName;
			}
		
		// 수정(폼띄우기)
		@RequestMapping(value="/professor/lecturePage/noticeBoard/update.do")
		public String editReportBoard(	@RequestParam("what")  String boardNo, Model model
				) {
			BoardVO board = commonService.retrieveNotice(boardNo); 
			model.addAttribute("notice", board);
			return "academic/professor/lecturePage/noticeBoardEdit"; 
		}
		
		
		//과제게시글 수정(폼에서 입력값 받기)
		@RequestMapping(value="/professor/lecturePage/noticeBoard/update.do", method=RequestMethod.POST)
		public String updateReportBoard (
				@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO notice,
				Errors errors
				, Model model
				) {
			
			String viewName = null;
			String message = null;
			if(!errors.hasErrors()) {
				ServiceResult result = profService.modifyNotice(notice);
				switch(result) {
				case OK:
					viewName = "redirect:/common/lecturePage/noticeList.do?classNo="+notice.getClassNo();
					break;
				default:
					viewName = "redirect:/common/lecturePage/noticeList.do?classNo="+notice.getClassNo();
					message = "서버 오류, 잠시뒤 다시 해보셈.";
				}			
			}else {
				viewName = "academic/professor/lecturePage/noticeBoardEdit";
			}
			
			model.addAttribute("message", message);
			
			return viewName;
		}
	
}
