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

import kr.or.ddit.academic.common.lecturePage.dao.CommonLectureMaterialsDAO;
import kr.or.ddit.academic.professor.lecturePage.service.ProfessorLectureMaterialService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class LectureMaterailManageController {

	@Inject
	private ProfessorLectureMaterialService profService; 
	
	@Inject 
	private CommonLectureMaterialsDAO commonService; 
	
	
	//
	
	//삭제(사실 update) 
		@RequestMapping(value="/professor/lecturePage/materialDelete.do", method=RequestMethod.GET)
		public String deleteNotice(
				@RequestParam(value ="boardNo", required=true) String boardNo, 
				HttpServletRequest req
				) {

			HttpSession session = req.getSession(); 
			ServiceResult result = profService.deleteMaterial(boardNo); 
			
			String viewName = null;
			String message = null;
			switch (result) {
			case OK:      
				viewName = "redirect:/common/lecturePage/materialList.do";
				break;
			default:    
				viewName = "redirect:/common/lecturePage/materialView.do?what="+boardNo;
				message = "서버 오류";
				break;   
			}
			session.setAttribute("message", message);
			return viewName;
		}
	

	// 등록(폼띄우기)  
		@RequestMapping(value="/professor/lecturePage/materialInsert.do", method=RequestMethod.GET)
		public String showNoticeBoardForm(
				@ModelAttribute("board") BoardVO board,  
				@RequestParam(value ="classNo", required=true, defaultValue="2051700001") String classNo
				) {
			
			board.setClassNo(classNo);

			return "academic/professor/lecturePage/materialBoardForm"; 
		} 
		
		// 등록(폼에서 입력값 받기)
		@RequestMapping(value="/professor/lecturePage/materialInsert.do", method=RequestMethod.POST)
		public String insesrtNoticeBoardForm(
				@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board
				, Errors errors
				, Model model
			) {
				String viewName = null;
				String message = null;
				
				if(!errors.hasErrors()) {
					ServiceResult result = profService.createMaterial(board);
					System.out.println("강의자료입력부분 등록 폼부분임. " + result);
					switch(result) {
					case OK:        
						viewName = "redirect:/common/lecturePage/materialList.do?classNo="+board.getClassNo();
						break;
					default:  
						viewName = "redirect:/common/lecturePage/materialList.do?classNo="+board.getClassNo();
						message = "서버 오류, 잠시뒤 다시 해보셈.";
					}
				//에러	
				}else { 
					viewName = "academic/professor/lecturePage/materialBoardForm";
				}
				
				model.addAttribute("message", message);
				
				return viewName;
			}
		
		// 수정(폼띄우기)
		@RequestMapping(value="/professor/lecturePage/materialBoard/update.do")
		public String editReportBoard(	@RequestParam("what")  String boardNo, Model model
				) {
			BoardVO board = commonService.selectMaterial(boardNo); 
			model.addAttribute("board", board);
			return "academic/professor/lecturePage/materialBoardEdit"; 
		}
		
		
		//과제게시글 수정(폼에서 입력값 받기)
		@RequestMapping(value="/professor/lecturePage/materialBoard/update.do", method=RequestMethod.POST)
		public String updateReportBoard (
				@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board,
				Errors errors
				, Model model
				) {
			
			String viewName = null;
			String message = null;
			if(!errors.hasErrors()) {
				ServiceResult result = profService.modifyBoard(board);
				switch(result) {
				case OK:
					viewName = "redirect:/common/lecturePage/materialList.do?classNo="+board.getClassNo();
					break;
				default:
					viewName = "redirect:/common/lecturePage/materialList.do?classNo="+board.getClassNo();
					message = "서버 오류, 잠시뒤 다시 해보셈.";
				}			
			}else {
				viewName = "academic/professor/lecturePage/materialBoardEdit";
			}
			
			model.addAttribute("message", message);
			
			return viewName;
		}
	
}
