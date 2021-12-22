package kr.or.ddit.academic.professor.lecturePage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.professor.lecturePage.service.LectureScoreManageService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LectureScoreManageController {
	
	@Inject
	private LectureScoreManageService service; 
	
	//데이터 구성 
	private void makeModel(int currentPage, SearchVO searchVO, Model model) {
		PagingVO<ClassListVO> pagingVO = new PagingVO<>(10, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		List<ClassListVO> totalScoreList = service.retrieveTotalScoreList(pagingVO);
		pagingVO.setDataList(totalScoreList);
		
		model.addAttribute("pagingVO", pagingVO);
	}
	
	 
	//점수 리스트 출력        
	@RequestMapping(value="/academic/professor/lecturePage/retrieveScore.do", method=RequestMethod.GET)
	public String showScoreList(
			@RequestParam(value="page", required=false, defaultValue="1" ) int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO,
			Model model,
			HttpServletRequest req
			) {
		HttpSession session = req.getSession(); 
		String classNo = (String) session.getAttribute("classNo");
		
		searchVO.setSearchWord(classNo);
		makeModel(currentPage, searchVO, model);
		
		Map<String, Integer> scorePercentage = service.retrieveScorePercentage(classNo);
		model.addAttribute("scorePercentage", scorePercentage);

		return "academic/prof/lecturePage/totalScoreList"; 
	}
	
	
	//점수신규 입력폼으로 이동 
	@RequestMapping(value="/academic/professor/lecturePage/writeScore.do", method=RequestMethod.GET)
	public String writeScoreForm(
			HttpServletRequest req, Model model
			) {
		HttpSession session = req.getSession(); 
		String classNo = (String) session.getAttribute("classNo");
		
		List<ClassListVO> studentList = service.retrieveNullScoreList(classNo);   
		model.addAttribute("studentList" ,studentList );
						
		return "academic/prof/lecturePage/writeScore"; 	
	} 
	
	//점수입력 받아오기 
	@RequestMapping(value="/academic/professor/lecturePage/writeScore.do", method=RequestMethod.POST)
	public String updateScoreForm(
		/*	//@Validated(UpdateGroup.class) @ModelAttribute("studentList") ArrayList<ClassListVO> studentList,
			Errors errors,,*/
			@RequestParam(value ="classMid", required=true) ArrayList<Integer> classMid,
			@RequestParam(value ="classFin", required=true) ArrayList<Integer> classFin,
			@RequestParam(value ="memNo", required=true) ArrayList<String> memNo,
			 Model model,
			HttpServletRequest req
			) {
		String viewName = null;
		String message = null;
		HttpSession session = req.getSession(); 
		String classNo = (String)session.getAttribute("classNo");
		
		ClassListVO student =  new ClassListVO();
		student.setClassNo(classNo);
		int result = 0; 
		for(int i = 0 ; i < classMid.size(); i++) {
			student.setClassMid(classMid.get(i));
			student.setClassFin(classFin.get(i));
			student.setMemNo(memNo.get(i));
			result +=service.modifyScore(student);		
		}
		
		log.info("*********************************************");
		log.info("result ; " + result);
		log.info("result : " + result);
		log.info("result ; " + result);
		log.info("result : " + result);
		log.info("classMid.size() : " + classMid.size());
		log.info("classMid.size() : " + classMid.size());
		log.info("classMid.size() : " + classMid.size());
		log.info("classMid.size() : " + classMid.size());
		log.info("*********************************************");


		if(result >= classMid.size()) {
			viewName = "redirect:/academic/professor/lecturePage/retrieveScore.do";
					log.info("성공으로 들어옴");
		}else if(result < classMid.size()){
			log.info("실패 들어옴");
			viewName = "academic/prof/lecturePage/writeScore";
			message = "미입력된 정보가 있습니다.";
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
		
	
} 	
