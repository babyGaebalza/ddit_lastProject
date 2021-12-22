package kr.or.ddit.academic.assistant.studentmanage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.academic.assistant.studentmanage.dao.StudentManageDAO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/studentManage/studentAdviserList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentManageAdviserController {
	@Inject
	private StudentManageDAO dao;
	
	//지도교수 리스트
	@RequestMapping()
	public List<MemberVO> studemtManageAdviserList(
		@AuthenticationPrincipal(expression="authMember") MemberVO member		
	){
		log.info(member.getMemMajor());
		
		return dao.selectAdviserList(member);
	}
}
