package kr.or.ddit.common.document.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.or.ddit.common.document.dao.OthersDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OthersRetrieveController {
	
	@Inject
	private OthersDAO dao; 
	
    @RequestMapping(value="/others/getDeptList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public List<Map<String,Object>> retrieveDeptList(  
			){
		 List<Map<String, Object>> resultList= dao.selectDeptList(); 
    	
		return resultList; 
	}
    
    @RequestMapping(value="/others/getMajorList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public  List<Map<String,Object>> retrieveMajorList( Model model 
			){
		
		 List<Map<String, Object>> resultList= dao.selectMajorList(); 

		return resultList; 
	}
    
    @RequestMapping(value="/others/getNameAndId.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public  List<Map<String,Object>> retrieveNameAndId( @ModelAttribute("member") MemberVO member 			
			){
    
    	if(!"US01".equals(member.getUserCode())) { //행정직원 아닐 경우 
    		 String mjCode = member.getDeptCode(); 
    		 member.setMemMajor(mjCode);
    		 member.setDeptCode(null);
    	}else { //행정직원일경우 
    		member.setMemMajor(null); 		
    	}
    	
        return 	dao.selectNameAndId(member); 
	}
    

}
