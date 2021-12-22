package kr.or.ddit.academic.professor.lecture.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.professor.lecture.dao.MakeLectureDAO;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MakeLectureController {

	@Inject
	private MakeLectureDAO dao; 
	
	//강의계획서작성폼으로 이동 
	@RequestMapping(value="/professor/makeLecture.do", method=RequestMethod.GET)
	public String showMakeLectureForm(MemberVO MemberVO) {
		String viewName= null; 
		
		viewName = "academic/professor/lectureMakeForm" ; 				
		return viewName; 	
	} 
	
	
	//학과장 가져오기 
	@RequestMapping(value="/makeLecture/getMajorChef.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody	
	public List<Map<String,Object>> retrieveMajorList(  
		@RequestParam(value="memId", required=true)	String memId
			){
		 List<Map<String, Object>> resultList= dao.selectMajorChef(memId); 
		return resultList; 
	}
	
	//강의실 가져오기 
	@RequestMapping(value="/makeLecture/getFacCode.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody	
	public List<Map<String,Object>> retrieveFacList(  
		@RequestParam(value="majorCode", required=true)	String majorCode
			){
		 List<Map<String, Object>> resultList= dao.selectFacList(majorCode); 
    	
		return resultList; 
	}
	
	
	//기존강의리스트 가져오기 
	@RequestMapping(value="/makeLecture/getOldClassList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody	
	public List<Map<String,Object>> retrieveOldClassList(  
		@RequestParam(value="majorCode", required=true)	String majorCode,
		@RequestParam(value="classCode", required=true)	 String classCode
			){
		
		Map<String, String> myClassMap = new HashMap<>();
		myClassMap.put("majorCode", majorCode); 
		myClassMap.put("classCode", classCode); 
		
		 List<Map<String, Object>> resultList= dao.selectOldList(myClassMap); 
    	
		return resultList; 
	}
	
}
