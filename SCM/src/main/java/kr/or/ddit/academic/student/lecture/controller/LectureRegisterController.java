package kr.or.ddit.academic.student.lecture.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.LimitExceededException;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.student.lecture.dao.StudentLectureDAO;
import kr.or.ddit.academic.student.lecture.service.StudentLectureService;
import kr.or.ddit.administration.univ_aca.majormanage.dao.MajorManageDao;
import kr.or.ddit.common.schedule.dao.ScheduleDAO;
import kr.or.ddit.enumpkg.ClassKindCode;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LectureRegisterController {
	
	@Inject
	private StudentLectureService studentLectureService;
	@Inject
	private StudentLectureDAO studentLectureDAO;
	@Inject
	private MajorManageDao majorManageDAO;
	@Inject
	private ScheduleDAO scheduleDAO;
	
	
	// 현재 수강신청 기간 스케줄
	private ScheduleVO registerSchedule;
	// 현재 스케줄의 디테일 코드(학기/기간) (ex. 1학기 강의바구니 기간 -> 1/1)
	private String registerDetailCode;
	// 현재 학기 (1, 2)
	private String semester;
	// 현재 기간 종류(강의바구니, 수강신청, 수강변경) (1, 2, 3)
	private String registerKind;
	
	// 현재 학기의 강의 맵
	private Map<String, ClassVO> semesterClassMap;
	
	// 수강신청 기간 여부를 알려주는 요청
	@GetMapping(value="/student/lecture/registerYN.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> registerYN() {
		
		registerSchedule = scheduleDAO.selectLectureRegisterByNow();
		
		
		Map<String, Object> resultMap = new HashMap<>();
		
		String result = null;
		
		if(registerSchedule == null) {
			result = "N";
		}else {
			result = "Y";
		}
		
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	// 수강신청 폼
	@GetMapping("/student/lecture/registerForm")
	public String registerForm(
			Model model) {
		registerSchedule = scheduleDAO.selectLectureRegisterByNow();
		registerDetailCode = registerSchedule.getSchDecode();
		semester = registerDetailCode.substring(registerDetailCode.length() - 2, registerDetailCode.length() - 1); 
		registerKind = registerDetailCode.substring(registerDetailCode.length() - 1);
		semesterClassMap = studentLectureService.retrieveSemesterClassMap(semester);
		
		String viewName = null;
		// 수강신청 기간이 아니면
		if(registerSchedule == null) {
			viewName = "redirect:/student/main.do";
			
		// 수강신청 기간이면
		}else {
			viewName = "academic/studentMain/lecture/registerForm";
			
			// 학기 (1, 2)
			model.addAttribute("semester", semester);
			// 수강신청 종류(담기/수강신청/변경) (1, 2, 3)
			model.addAttribute("registerKind", registerKind);
			model.addAttribute("collegeList", studentLectureService.retrieveCollegeList());
		}
		
		return viewName;
	}
	
	// 단과대 코드로 학부(과) 리스트 조회
	@GetMapping(value="/student/lecture/collegeMajorList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<MajorVO> collegeMajorList(String collegeCode){
		
		return majorManageDAO.selectCollegeMajorList(collegeCode);
	}
	
	
	// 강의 검색
	@PostMapping(value="/student/lecture/search.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> search(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			, @ModelAttribute ClassVO searchOption
			) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> searchMap = new HashMap<>();
		
		searchMap.put("memNo", member.getMemId());
		searchMap.put("semester", semester);
		
		resultMap.put("registerList", studentLectureDAO.selectRegisterClassList(searchMap));
		resultMap.put("lectureList", studentLectureService.retrieveClassList(searchOption));
		
		return resultMap;
	}
	
	// 강의장바구니 조회
	@GetMapping(value="/student/lecture/cartList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> cartList(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			){
		
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> searchMap = new HashMap<>();
		
		searchMap.put("memNo", member.getMemId());
		searchMap.put("semester", semester);
		
		resultMap.put("registerList", studentLectureDAO.selectRegisterClassList(searchMap));
		resultMap.put("lectureList", studentLectureService.retrieveCartList(member.getMemId()));
		
		return resultMap;
	}
	
	// 강의장바구니 신청
	@GetMapping(value="/student/lecture/cartRegister.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> cartRegister(
			@RequestParam("classNo") String classNo
			, @AuthenticationPrincipal(expression="authMember") MemberVO member
			){
		
		
		Map<String, Object> resultMap = new HashMap<>();
		ServiceResult result = null;
		
		// 담기 기간일 경우
		if(registerKind.equals("1")) {
			Map<String, Object> cartRegisterMap = new HashMap<>();
			String memId = member.getMemId();
			cartRegisterMap.put("memId", memId);
			cartRegisterMap.put("classNo", classNo);
			
			result = studentLectureService.createCartRegister(cartRegisterMap);
			
			if(result == ServiceResult.OK || result == ServiceResult.CARTMAX) {
				resultMap.put("cartClassCount", studentLectureDAO.selectCountCart(memId));
			}
		}else {
			// 담기 기간이 아닐 경우
			result = ServiceResult.NOTPERIOD;
		}
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	// 강의장바구니 삭제
	@GetMapping(value="/student/lecture/cartRemove.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> cartRemove(
			@RequestParam("classNo") String classNo
			, @AuthenticationPrincipal(expression="authMember") MemberVO member
			){
		
		Map<String, Object> resultMap = new HashMap<>();
		ServiceResult result = null;
		
		// 담기 기간일 경우
		if(registerKind.equals("1")) {
			Map<String, Object> cartRegisterMap = new HashMap<>();
			String memId = member.getMemId();
			cartRegisterMap.put("memId", memId);
			cartRegisterMap.put("classNo", classNo);
			
			result = studentLectureService.removeCartRegister(cartRegisterMap); 	
			
			if(result == ServiceResult.OK || result == ServiceResult.CARTMAX) {
				resultMap.put("cartClassCount", studentLectureDAO.selectCountCart(memId));
			}
		}else {
			// 담기 기간이 아닐 경우
			result = ServiceResult.NOTPERIOD;
		}
		resultMap.put("result", result);
		
		return resultMap;
	}
		
	
	// 이번 학기 수강신청 리스트 조회(+시간표)
	@GetMapping(value="/student/lecture/registerList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> registerList(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			){
		
		Map<String, Object> searchMap = new HashMap<>();
		
		searchMap.put("memNo", member.getMemId());
		searchMap.put("semester", semester);
		
		return studentLectureService.retrieveClassRegisterAndStuTimeTableList(searchMap);
	}
	
	// 수강신청 가능 확인
	@GetMapping(value="/student/lecture/registerCheck.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> registerCheck(
			@RequestParam("classNo") String classNo
			, @AuthenticationPrincipal(expression="authMember") MemberVO member
			) {
		
		Map<String, Object> resultMap = new HashMap<>();
		ServiceResult result = null;
		
		// 1. 수강신청 기간인지 검증
		if(registerKind.equals("2")) {
			ClassVO lecture = semesterClassMap.get(classNo); // 강의
			String classKind = ClassKindCode.valueOf(lecture.getClassCode()).getCategoryName(); // 강의 이수구분
			String majorCode = lecture.getMajorCode();	// 강의 전공
			
			String memMajor = member.getMemMajor(); // 주전공
			String majorDouble = member.getMajorDouble(); // 복수전공
			String minor = member.getMajorMinor(); // 부전공
			
			
			log.info("강의 전공 : {}", majorCode);
			log.info("내 전공 : {}", memMajor);
			log.info("결과 : {}", majorCode.equals(memMajor));
			// 2. 교양임 || 전공인데 (주전공||복수전공||부전공)에 해당함
			if( (classKind.equals("교양선택") || classKind.equals("교양필수")) || (majorCode.equals(memMajor) || majorCode.equals(majorDouble) || majorCode.equals(minor)) ) {
				Map<String, Object> classRegisterMap = new HashMap<>();
				classRegisterMap.put("semester", semester);
				classRegisterMap.put("student", member);
				classRegisterMap.put("class", semesterClassMap.get(classNo));
				
				resultMap = studentLectureService.retrieveRegistarableCheck(classRegisterMap);
				// 6. 재수강이면 강의 정보 검색해 resultMap에 put
				if((ServiceResult)resultMap.get("result") == ServiceResult.RETAKE) {
					ClassVO retakeClass = studentLectureDAO.selectRetakeClassInfo((String)resultMap.get("retakeClassNo"));
					resultMap.put("retakeClass", retakeClass);
				}
			}else {
				// 2. 전공인데 (주전공||복수전공||부전공)에 해당하지 않음
				result = ServiceResult.NOTMAJOR;
				resultMap.put("result", result);
			}
		}else {
			// 1. 수강기간이 아님
			result = ServiceResult.NOTPERIOD;
			resultMap.put("result", result);
		}
		
		
		return resultMap;
	}
	
	// 수강신청 요청
	@GetMapping(value="/student/lecture/register.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ServiceResult register(
			@ModelAttribute ClassListVO registerClass
			, @AuthenticationPrincipal(expression="authMember") MemberVO member
			) {
		
		Map<String, Object> classRegisterMap = new HashMap<>();
		classRegisterMap.put("registerClass", registerClass);
		classRegisterMap.put("registerClassInfo", semesterClassMap.get(registerClass.getClassNo()));
		classRegisterMap.put("member", member);
		classRegisterMap.put("semester", semester);
		
		ServiceResult result = null;
		
		try {
			result = studentLectureService.createClassRegister(classRegisterMap);
		} catch (Exception e) {
			if(e instanceof LimitExceededException) {
				result = ServiceResult.LIMITEXCEED;
			}else {
				result = ServiceResult.FAILED;
			}
		}
		
		return result;
	}
	
	// 수강신청 삭제
	@GetMapping(value="/student/lecture/registerRemove.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ServiceResult registerRemove(
			@RequestParam("classNo") String classNo
			, @AuthenticationPrincipal(expression="authMember") MemberVO member
			){
		
		ServiceResult result = null;
		
		Map<String, Object> classRegisterMap = new HashMap<>();
		
		classRegisterMap.put("registerClassInfo", semesterClassMap.get(classNo));
		classRegisterMap.put("member", member);
		classRegisterMap.put("semester", semester);
		
		try {
			result = studentLectureService.deleteClassRegister(classRegisterMap);
		} catch (Exception e) {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
	// 강의바구니기간 변경
	@GetMapping("/student/lecture/cartChange.do")
	public String cartChange() {
		
		studentLectureService.modifyCartChange();
		
		return "redirect:/student/lecture/registerForm";
	}
	
	// 수강신청기간 변경
	@GetMapping("/student/lecture/regiChange.do")
	public String regiChange() {
		
		studentLectureService.modifyRegiChange();
		
		return "redirect:/student/lecture/registerForm";
	}
}
