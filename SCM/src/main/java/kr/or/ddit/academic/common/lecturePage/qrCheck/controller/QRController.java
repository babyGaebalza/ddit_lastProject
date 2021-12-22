package kr.or.ddit.academic.common.lecturePage.qrCheck.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.academic.common.lecturePage.qrCheck.service.QRService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class QRController {
	
	@Inject
	private QRService service;
	
	//QR 생성할 강의실 정보 넣으면 QR 생성 화면으로 이동함
	@RequestMapping("/common/lecturePage/QR.do")
	public String QRtest(
			HttpSession session
			,Model model
			) {
		String classNo = (String) session.getAttribute("classNo");
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		log.info("강의번호 : {}", classNo);
		model.addAttribute("classNo", classNo);
		return "academic/common/lecturePage/QRtest";
	}
	
	
	@RequestMapping("/QR/QRform.do")
	public String QRForm(
			@RequestParam("classNo") String classNo
			,Model model
			) {
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		log.info("클래스번호 : {}", classNo);
		
		//강의번호를 가지고 강의 정보를 가져오는 부분
		ClassVO classInfo = service.getClassInfo(classNo);
		
		model.addAttribute("classInfo", classInfo);
		model.addAttribute("classNo", classNo);
		return "common/QRInputForm";
	}
	
	
	@RequestMapping("/QR/QRsubmit.do")
	public String QRsubmit(
			@RequestParam("classNo") String classNo
			,@RequestParam("stuNo") String stuNo
			,@RequestParam("stuName") String stuName
			,Model model
			) {
				log.info("강의번호 : {}", classNo);
				log.info("학생번호 : {}", stuNo);
				log.info("학생이름 : {}", stuName);
		Map<String, String> classMember = new HashMap<>();
		classMember.put("classNo", classNo);
		classMember.put("stuNo", stuNo);
		classMember.put("stuName", stuName);
		classMember.put("atdcPoint", "2");
		
		//수강확인
		ServiceResult result = service.checkStuService(classMember);
		if(result.equals(ServiceResult.OK)) {
			
			
			//출석처리
			ClassVO classinfo = service.getClassInfo(classNo);
			classinfo.getClassTime();
			//월12/화34
			
			//classListNo 가져와서 넣기
			Map<String, String> classAndmember = new HashMap<>();
			classAndmember.put("classNo", classNo);
			classAndmember.put("stuNo", stuNo);
			
			ClassListVO classListInfo = service.getClassListInfo(classAndmember);
			
			String classListNo = classListInfo.getClasslistNo();
			classMember.put("classListNo", classListNo);
			
			ServiceResult finres = service.attendStudent(classMember);
			
			if(finres.equals(ServiceResult.OK)) {
				model.addAttribute("classNo", classNo);
				model.addAttribute("stuNo", stuNo);
				model.addAttribute("stuName", stuName);
				return "common/QRResult";
				
			}
		}
		else {
			ClassVO classInfo = service.getClassInfo(classNo);
			
			model.addAttribute("classInfo", classInfo);
			model.addAttribute("classNo", classNo);
			String message = "존재하지 않는 수강생 정보입니다.";
			model.addAttribute("message", message);
			return "common/QRInputForm";
		}
		
		
		model.addAttribute("classNo", classNo);
		model.addAttribute("stuNo", stuNo);
		model.addAttribute("stuName", stuName);
		return "common/QRResult";
	}
	
	
}
