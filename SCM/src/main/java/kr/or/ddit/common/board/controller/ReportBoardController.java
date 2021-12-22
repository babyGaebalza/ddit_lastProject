package kr.or.ddit.common.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.BoardVO;

@Controller
public class ReportBoardController {
	
	@GetMapping("/lecturePage/reportBoardForm")
	public String reportBoardForm() {
		
		return "common/board/reportBoard";
	}
	
	@GetMapping(value="/lecturePage/reportBoardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<BoardVO> reportBoardList(){
		
		return null;
	}
	
	@GetMapping(value="/lecturePage/reportBoardView.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public BoardVO reportBoardView() {
		
		return null;
	}
	
	@PostMapping(value="/lecturePage/reportBoard/insertReport.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> insertReport(){
		
		return null;
	}
	
	@PostMapping(value="/lecturePage/reportBoard/updateReport.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> updateReport(){
		
		return null;
	}

}
