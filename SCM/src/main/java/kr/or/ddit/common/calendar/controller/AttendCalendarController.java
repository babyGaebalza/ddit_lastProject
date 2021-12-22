package kr.or.ddit.common.calendar.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.student.lecture.service.StudentLecturePageService;
import kr.or.ddit.common.calendar.service.AttendCalendarService;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.DATE;

@Controller
@Slf4j
public class AttendCalendarController {
	@Inject
	private AttendCalendarService service;
	
	@Inject
	private StudentLecturePageService attService;
	
	//월화수목---의 날짜숫자
	private int returnDay(String day) {
		int res = 0;
		switch (day) {
		case "월":
			res = 2;
			break;
		case "화":
			res = 3;
			break;
		case "수":
			res = 4;
			break;
		case "목":
			res = 5;
			break;
		case "금":
			res = 6;
			break;
		case "토":
			res = 7;
			break;
		case "일":
			res = 1;
			break;
		}
	return res;
	}
	
	//교시별 시작시간
	private String returnsTime(String time) {
		String classtime = null;
		switch (time) {
		case "1":
			classtime = "09:00:00";
			break;
		case "2":
			classtime = "10:00:00";
			break;
		case "3":
			classtime = "11:00:00";
			break;
		case "4":
			classtime = "12:00:00";
			break;
		case "5":
			classtime = "13:00:00";
			break;
		case "6":
			classtime = "14:00:00";
			break;
		case "7":
			classtime = "15:00:00";
			break;
		case "8":
			classtime = "16:00:00";
			break;
		case "9":
			classtime = "17:00:00";
			break;
		}
		return classtime;
	}
	
	//교시별 종료시간
	private String returneTime(String time) {
		String classtime = null;
		switch (time) {
		case "1":
			classtime = "09:50:00";
			break;
		case "2":
			classtime = "10:50:00";
			break;
		case "3":
			classtime = "11:50:00";
			break;
		case "4":
			classtime = "12:50:00";
			break;
		case "5":
			classtime = "13:50:00";
			break;
		case "6":
			classtime = "14:50:00";
			break;
		case "7":
			classtime = "15:50:00";
			break;
		case "8":
			classtime = "16:50:00";
			break;
		case "9":
			classtime = "17:50:00";
			break;
		}
		return classtime;
	}
	
	
	@ResponseBody
	@RequestMapping(value="attendcalendar.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Map<String, Object>> monthPlan(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,HttpSession session
			) throws ParseException {
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		
		
		



		
	//해당 학생이 수강하는 강의 정보 가져오기
		Map<String, String> input = new HashMap<>();
		
		String classnumber = (String) session.getAttribute("classNo");
		input.put("classNo", classnumber);
		
		String memId = authMember.getMemId();
		input.put("memNo", memId);
		
		List<Map<String, Object>> allClassList = service.selectAllClass(input);
		
	//해당 년도 학기 기간 정보 가져오기
		List<ScheduleVO> schedule = service.getSchedule();
		String startdate1 = null;	// 1학기 시작 날짜
		String enddate1 = null;	//1학기 종료 날짜
		String startdate2 = null;	//2학기 시작 날짜
		String enddate2 = null;	//2학기 종료 날짜
		for(ScheduleVO sch : schedule) {	//학기 시작/종료 날짜
			if(sch.getSchDecode().equals("SCED-SM1")) {
				startdate1 = sch.getSchSdate();
				enddate1 = sch.getSchEdate();
			}
			if(sch.getSchDecode().equals("SCED-SM2")) {
				startdate2 = sch.getSchSdate();
				enddate2 = sch.getSchEdate();
			}	// 학기 날짜 세팅 완료
		};
		
		for(Map<String, Object> classList : allClassList) {	// 모든 강의에서
				
			String startdate = null;	//강의 시작 날짜
			String enddate = null;	//강의 종료 날짜
			//해당 강의의 학기 뽑기
			String thisSemester = (String) classList.get("classSemester");
			if(thisSemester.equals("1")) {	//1학기 날짜 기준
				startdate = startdate1;	// 개강일과 수업시작일은 같다
				enddate = enddate1;	// 종강일과 수업종료일은 같다
			}
			if(thisSemester.equals("2")) {	//2학기 날짜 기준
				startdate = startdate2;	//개강일과 수업시작일은 같다
				enddate = enddate2;	//종강일과 수업종료일은 같다
			}
			
			//해당 강의의 강의요일과 시간 뽑기
			String classtime = (String)classList.get("classTime"); //강의시간
			Calendar cal = Calendar.getInstance();
			
			String[] classtimeDetail = classtime.split("/");
				String classtime1 = classtimeDetail[0];	//앞시간
				
				String classday1 = classtime1.substring(0,1);	//요일
					int day1 = returnDay(classday1);
				String classstart1 = classtime1.substring(1,2);
					String start1Time = returnsTime(classstart1);
				String classend1 = classtime1.substring(classtime1.length()-1);
					String end1Time = returneTime(classend1);
				
				String classtime2 = classtimeDetail[1];	//뒤시간
				
				String classday2 = classtime2.substring(0,1);	//요일
					int day2 = returnDay(classday2);
				String classstart2 = classtime2.substring(1,2);
				String start2Time = returnsTime(classstart2);
				String classend2 = classtime2.substring(classtime2.length()-1);
				String end2Time = returneTime(classend2);
			
			//해당 강의의 강의실, 이름 뽑기
					String classRoom = (String) classList.get("classRoom");
					String className = (String) classList.get("className");
					
					
		//시작날짜부터 시작해서 하루씩 추가해 가면서, 요일이 강의요일과 같다면? 일정에 추가하는데, 종강일까지 반복한다.
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date startSM = format.parse(startdate);
					Date endSM = format.parse(enddate);

			
			//강의별 출석 정보 가져오기
			AttendanceVO att = new AttendanceVO();
			att.setClassStudent(memId);
			
			String classNo = (String) classList.get("classNo");
			att.setClassNo(classNo);
			List<AttendanceVO> attList = attService.retrieveMyAttend(att);
			
			String nulldate = "1900-01-01 01:00";
			SimpleDateFormat nullparse = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			Date parsedate = nullparse.parse(nulldate);
			
			for(AttendanceVO attDetail: attList) {
				String title = (String) classList.get("className");
				String start = attDetail.getAtdcDate();
				SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				parsedate = parse.parse(start);
				
				
				SimpleDateFormat front = new SimpleDateFormat("yyyy-MM-dd");
				String frontDate = front.format(parsedate);
				
				SimpleDateFormat back = new SimpleDateFormat("HH:mm:ss");
				String backDate = back.format(parsedate);

			}
			log.info("파스데이트 : {}", parsedate);
			
			//시작날짜부터 종강일까지
			int index = 1;
			boolean flag = true;
			while(flag) {
				//개강일부터의 요일				
				Calendar c = Calendar.getInstance();
				c.setTime(startSM);
				int smDay = c.get(Calendar.DAY_OF_WEEK);	// 비교되는 날짜의 요일(int)

				if(smDay == day1) {	// 첫 수업요일과 같다면
					log.info("수업 1과 같음");
					SimpleDateFormat Frontdate = new SimpleDateFormat("yyyy-MM-dd");
					String frontdate = Frontdate.format(startSM);
					
					String start = frontdate +"T"+ start1Time;
					String end = frontdate +"T"+ end1Time;
					
					
					//출석 표현 부분
					String attCheck = "결석";
					//강의 날짜와 출석 날짜가 같다면?
				
					
					SimpleDateFormat attFormat = new SimpleDateFormat("yyyy-MM-dd");
					String classDate = attFormat.format(startSM);
					String attDate = attFormat.format(parsedate);
					
					log.info("강의날짜 : {}", classDate);
					log.info("출석날짜 : {}", attDate);
					
					if(classDate.equals(attDate)) {	
						log.info("강의날짜와 출석날짜가 같습니다!!");
						//강의 시작시간보다 출석시간이 빠르다면?
						SimpleDateFormat attTimeFormat = new SimpleDateFormat("HH:mm:ss");
						String attTime = attTimeFormat.format(parsedate);
						String[] classTimeArray = start.split("T");
						String classTime = classTimeArray[1];
						String[] classETimeArray = end.split("T");
						String classETime = classTimeArray[1];
												
						Date classTimeR = attTimeFormat.parse(classTime);
						Date classETimeR = attTimeFormat.parse(classETime);
						Date attTimeR = attTimeFormat.parse(attTime);
						log.info("강의시간 : {}", classTimeR);
						log.info("출석시간 : {}", attTimeR);
						if(classTimeR.after(attTimeR) || classTimeR.equals(attTimeR)) {
							log.info("강의 시작시간보다 출석시간이 빠르다");
							attCheck ="출석함";
						}
						if(attTimeR.after(classTimeR)) {
							log.info("강의 시작시간보다 출석시간이 느리다");
							//강의 시작보다 느리고, 종료보다 빠르다면?
							if(attTimeR.after(classETimeR)) {
								log.info("지각이다.");
								attCheck ="지각";
							//강의 종료보다 느리거나, 없다면?
							}else {
								attCheck ="결석";
							}
						}
					}
					
					//----------
					
					
					
					hash.put("title", classRoom+className+"출석 : "+attCheck);
					hash.put("no", index);
					hash.put("start", start);
					hash.put("end", end);
					index++;
					log.info(classRoom+className);
					log.info(start);
					log.info(end);
					jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
					jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
				};			
				
				if(smDay == day2) {	// 두번째 수업 요일과 같다면
					log.info("수업 2와 같음");
					SimpleDateFormat Frontdate = new SimpleDateFormat("yyyy-MM-dd");
					String frontdate = Frontdate.format(startSM);
					
					String start = frontdate +"T"+ start2Time;
					String end = frontdate +"T"+ end2Time;
					
					//출석 표현 부분
					String attCheck = "결석";
					//강의 날짜와 출석 날짜가 같다면?
					SimpleDateFormat attFormat = new SimpleDateFormat("yyyy-MM-dd");
					String classDate = attFormat.format(startSM);
					String attDate = attFormat.format(parsedate);
					
					log.info("강의날짜2 : {}", classDate);
					log.info("출석날짜2 : {}", attDate);
					
					if(classDate.equals(attDate)) {	
						log.info("강의날짜와 출석날짜가 같습니다!!2");
						//강의 시작시간보다 출석시간이 빠르다면?
						SimpleDateFormat attTimeFormat = new SimpleDateFormat("HH:mm:ss");
						String attTime = attTimeFormat.format(parsedate);
						String[] classTimeArray = start.split("T");
						String classTime = classTimeArray[1];
						String[] classETimeArray = end.split("T");
						String classETime = classTimeArray[1];
												
						Date classTimeR = attTimeFormat.parse(classTime);
						Date classETimeR = attTimeFormat.parse(classETime);
						Date attTimeR = attTimeFormat.parse(attTime);
						log.info("강의시간2 : {}", classTimeR);
						log.info("출석시간2 : {}", attTimeR);
						if(classTimeR.after(attTimeR) || classTimeR.equals(attTimeR)) {
							log.info("강의 시작시간보다 출석시간이 빠르다2");
							attCheck ="출석함";
						}
						if(attTimeR.after(classTimeR)) {
							log.info("강의 시작시간보다 출석시간이 느리다2");
							//강의 시작보다 느리고, 종료보다 빠르다면?
							if(attTimeR.after(classETimeR)) {
								log.info("지각이다.2");
								attCheck ="지각";
							//강의 종료보다 느리거나, 없다면?
							}else {
								attCheck ="결석";
							}
						}
					}
					
					//----------
					
					hash.put("title", classRoom+className+"출석 : "+attCheck);
					hash.put("no", index);
					hash.put("start", start);
					hash.put("end", end);
					index++;
					log.info(classRoom+className);
					log.info(start);
					log.info(end);
					jsonObj = new JSONObject(hash); //중괄호 {key:value , key:value, key:value}
					jsonArr.add(jsonObj); // 대괄호 안에 넣어주기[{key:value , key:value, key:value},{key:value , key:value, key:value}]
					log.info("jsonArrCheck: {}", jsonArr);
				};
				
				
				
				//------------------------------------------------------------------
				//하루 추가								
				c.add(Calendar.DATE, 1);
				startSM = c.getTime();
				log.info("하루추가 : {}", startSM);
				
				//하루 추가날짜가 종강일과 같다면 종료
				DateFormat dTos = new SimpleDateFormat("YY-MM-dd");
				
				String EstartSM = dTos.format(startSM);
				String EendSM = dTos.format(endSM);
				log.info("비교일 : {}", EstartSM);
				log.info("기준일 : {}", EendSM);
				
				if(EstartSM.equals(EendSM)) {
					flag=false;
				}
				
			};	
			
		};
		//종료	
		return jsonArr;
	}
	
	@RequestMapping("/calendar/attendCalendarList.do")
	public String calendarList() {
		return "/common/calendar/AttendCalendar";
	}
}
