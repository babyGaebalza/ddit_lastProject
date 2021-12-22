package kr.or.ddit.administration.off_fac.dormitorymanage.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.administration.off_fac.dormitorymanage.service.DormitoryManageService;

@Controller
public class DormitoryManageController {
	@Inject
	private DormitoryManageService service;
	
	//관리자
	
	//생활관 등록학생 리스트
	@RequestMapping("/dormitoryManage/dormitoryManageList")
	public String dormitoryManageList() {
		return "";
	}
	
	//생활관 등록학생(납부완료) 학생 등록
	@RequestMapping("/dormitoryManage/dormitoryManageInsert")
	public String getDormitoryManageInsert() {
		return "";
	}
	//생활관 등록학생(납부완료) 학생 등록
	@RequestMapping(value="/dormitoryManage/dormitoryManageInsert", method=RequestMethod.POST)
	public String postDormitoryManageInsert() {
		return "";
	}
	
	//생활관 등록학생(잔류) 학생 수정
	@RequestMapping("/dormitoryManage/dormitoryManageUpdate")
	public String getDormitoryManageUpdate() {
		return "";
	}
	//생활관 등록학생(납부완료) 학생 등록
	@RequestMapping(value="/dormitoryManage/dormitoryManageUpdate", method=RequestMethod.POST)
	public String postDormitoryManageUpdate() {
		return "";
	}
	
	//생활관 등록학생(납부완료) 학생 등록
	@RequestMapping("/dormitoryManage/dormitoryManageDelete")
	public String DormitoryManageDelete() {
		return "";
	}
}
