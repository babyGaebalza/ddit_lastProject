package kr.or.ddit.academic.common.lecturePage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.common.lecturePage.service.CommonLectureTaskService;
import kr.or.ddit.academic.professor.lecturePage.service.ProfessorLectureTaskService;
import kr.or.ddit.academic.professor.trackManage.service.TrackProfessorService;
import kr.or.ddit.academic.vo.TaskVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.enumpkg.UserCode;
import kr.or.ddit.validate.groups.ReportScoreMakeGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import kr.or.ddit.vo.TrackVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureReportRetrieveController {

	@Inject
	private CommonLectureTaskService service;
	
	//데이터 구성 
	private void makeModel(int currentPage, SearchVO searchVO, Model model) {
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<BoardVO> taskList = service.retrieveTaskList(pagingVO);
		pagingVO.setDataList(taskList);
		
		model.addAttribute("pagingVO", pagingVO);
	}
	
	//과제게시글 출력 
	@RequestMapping(value="/common/lecturePage/reportBoardList.do", method=RequestMethod.GET)
	public String showReportList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model,
			@AuthenticationPrincipal(expression="authMember") MemberVO member, 
			//@RequestParam(value="classNo", required=true, defaultValue="2051700001") String classNo
			HttpServletRequest req
			) {
		HttpSession session = req.getSession(); 
		String classNo = (String) session.getAttribute("classNo"); 
		
		searchVO.setSearchWord2(member.getMemId()); 
		searchVO.setSearchWord(classNo);
		makeModel(currentPage, searchVO, model);
		return "academic/common/lecturePage/taskBoardList"; 
	} 
	
	//과제게시글 상세조회 
	@RequestMapping(value="/common/lecturePage/reportBoardView.do")
	public String showOneReportNotice(
			@RequestParam(value="what", required=true) String boardNo 
			, Model model
			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember
			) {				
		String viewName = null; 
		
	    BoardVO task = service.retrieveTask(boardNo);
	    model.addAttribute("task", task);		
	    
	    // 게시글 포함 제출리스트 retrieve & set
	    UserCode userCode = UserCode.valueOf(authMember.getUserCode());
	    Map<String, Object> searchMap = new HashMap<>();
	    searchMap.put("boardNo", boardNo);
	    if(userCode == UserCode.US04) {
	    	searchMap.put("memId", authMember.getMemId());
	    }
	    model.addAttribute("submitTasks", service.retrieveSubmitTasks(searchMap));

		viewName= "academic/common/lecturePage/taskView";
		return viewName; 
	}

	
	//과제점수 입력
	@RequestMapping("/common/lecturePage/reportScoreMake.do")
	public String makeReportScore(
			@Validated(ReportScoreMakeGroup.class) @ModelAttribute("taskScore") TaskVO taskScore, 
			Errors errors
			, Model model,
			HttpServletRequest req
	) {
		
		HttpSession  session = req.getSession(); 
		String viewName = null;
		String message = null;
	    boolean flag = true;
	    int score = 0;
		
	    try{
		  score = Integer.parseInt(taskScore.getTaskScore()); 
		  if (score < 0 || score > 10) {
			  message = "1부터 10사이의 값을 입력하시오.";
			  flag = false;  
		  }
	    }catch(Exception e) {
			message = "정확한 값을 재입력하십시오.";
			flag =false;  
		}

		
		if(!errors.hasErrors() && flag) {
			ServiceResult result = service.updateScore(taskScore);  
			switch (result) {
			case OK:     
				viewName = "redirect:/common/lecturePage/reportBoardView.do?what="+ taskScore.getBoardNo();
				message ="해당 과제의 점수가 입력(변경)되었습니다."; 
				break;
			default:    
				viewName = "redirect:/common/lecturePage/reportBoardView.do?what="+ taskScore.getBoardNo();
				message = "서버 오류..재시도 바랍니다.";
				break;   
			}
		}else {
			viewName = "academic/common/lecturePage/taskView";
		}
		session.setAttribute("message", message);
		return viewName;		
	}
		
}
