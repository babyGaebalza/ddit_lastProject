package kr.or.ddit.common.board.controller;

import java.io.File;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.board.service.BoardService;
import kr.or.ddit.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class DownloadController {
	
	@Inject
	private BoardService service;
	
	@RequestMapping("/board/download.do")
	public String download(@RequestParam("what") int attNo, Model model) {
		log.info("다운로드 들어옴");
		AttatchVO atch = service.download(attNo);
		model.addAttribute("atch", atch);
		return "downloadView";
	}
}










