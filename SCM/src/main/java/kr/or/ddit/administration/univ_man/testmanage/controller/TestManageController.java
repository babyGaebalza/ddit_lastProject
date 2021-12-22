package kr.or.ddit.administration.univ_man.testmanage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.administration.univ_man.testmanage.service.TestManageService;

@Controller
public class TestManageController {
	@Inject
	private TestManageService service;
	
	//시험 리스트(교수 신청)
	@RequestMapping("/testManage/testManageList.do")
	public String testManageList() {
		return "/administration/univ_man/testManage/TestManageList";
	}
	
	//시험 등록
	@RequestMapping("/testManage/testManageInsert.do")
	public String getTestManageInsert() {
		return "/administration/univ_man/testManage/TestManageForm";
	}
	//시험 등록
	@RequestMapping(value="/testManage/testManageInsert.do", method=RequestMethod.POST)
	public String postTestManageInsert() {
		return "/administration/univ_man/testManage/TestManageList";
	}
	
	//시험 수정
	@RequestMapping("/testManage/testManageUpdate.do")
	public String getTestManageUpdate() {
		return "/administration/univ_man/testManage/TestManageEdit";
	}
	//시험 수정
	@RequestMapping(value="/testManage/testManageUpdate.do", method=RequestMethod.POST)
	public String postTestManageUpdate() {
		return "/administration/univ_man/testManage/TestManageList";
	}
}
