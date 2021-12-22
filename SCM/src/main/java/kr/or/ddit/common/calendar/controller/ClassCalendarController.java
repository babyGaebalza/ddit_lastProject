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

import org.apache.commons.digester.Substitutor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.academic.professor.lecturePage.service.LectureAttendanceService;
import kr.or.ddit.academic.student.lecture.service.StudentLecturePageService;
import kr.or.ddit.common.calendar.service.AttendCalendarService;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ScheduleVO;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.DATE;

@Controller
@Slf4j
public class ClassCalendarController {
	
	@Inject
	private AttendCalendarService service;
	
	@Inject
	private StudentLecturePageService attService;
	
	@Inject
	private LectureAttendanceService LecService;
	
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
	@RequestMapping(value="classcalendar.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Map<String, Object>> monthPlan(
			@AuthenticationPrincipal(expression="authMember") MemberVO authMember
			,HttpSession session
			) throws ParseException {
		
	log.info("교수용 출석부 출력을 시작합니다.");
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		
	//해당 수업의 정보 가져오기
		String classNo = (String) session.getAttribute("classNo");
	log.info("해당 수업의 정보를 가져옵니다. : {}", classNo);
		ClassVO classInfo = service.selectClassInfo(classNo);
	log.info("해당 수업의 정보입니다. : {}", classInfo);	
	
	
	//해당 수업의 총 수강생 가져오기
		int totalStuCount = LecService.retrieveStuCount(classNo);
	
	
	//해당 수업 해당일자의 출석인원 가져오기
	
	
	
	//해당 년도 학기 기간 정보 가져오기
		List<ScheduleVO> schedule = service.getSchedule();
	log.info("개강 종강 날짜를 가져옵니다.");
		String startdate1 = null;	// 1학기 시작 날짜
		String enddate1 = null;	//1학기 종료 날짜
		String startdate2 = null;	//2학기 시작 날짜
		String enddate2 = null;	//2학기 종료 날짜
		
		for(ScheduleVO sch : schedule) {	//학기 시작/종료 날짜
			if(sch.getSchDecode().equals("SCED-SM1")) {
				startdate1 = sch.getSchSdate();
				enddate1 = sch.getSchEdate();
		log.info("1학기 개강,종강일 입니다. : {}", startdate1+"/"+enddate1);
			}
			if(sch.getSchDecode().equals("SCED-SM2")) {
				startdate2 = sch.getSchSdate();
				enddate2 = sch.getSchEdate();
		log.info("2학기 개강,종강일 입니다. : {}", startdate2+"/"+enddate2);
			}	// 학기 날짜 세팅 완료
	
		};

		
		String startdate = null;	//강의 시작 날짜
		String enddate = null;	//강의 종료 날짜
	log.info("강의 자체의 시작일과 종료일을 생성합니다.");
		//해당 강의의 학기 뽑기
			String thisSemester = (String) classInfo.getClassSemester();
		log.info("강의의 학기정보 입니다. : {}", thisSemester);
		String[] Semester = thisSemester.split("/");
			if(Semester[1].equals("1")) {	//1학기 날짜 기준
				startdate = startdate1;	// 개강일과 수업시작일은 같다
				enddate = enddate1;	// 종강일과 수업종료일은 같다
			}
			if(Semester[1].equals("2")) {	//2학기 날짜 기준
				startdate = startdate2;	//개강일과 수업시작일은 같다
				enddate = enddate2;	//종강일과 수업종료일은 같다
			}
	log.info("생성된 개강,종강 시간입니다. : {}", startdate+"/"+enddate);	
	
		//해당 강의의 강의요일과 시간 뽑기
			String classtime = (String)classInfo.getClassTime();
		log.info("해당강의의 강의시간정보를 추출합니다. : {}", classtime);
			
			Calendar cal = Calendar.getInstance();
			
			String[] classtimeDetail = classtime.split("/");
		log.info("강의 시간정보를 가공합니다. : {}", classtimeDetail[0]+"/"+classtimeDetail[1]);
			//---------------------------------------------------------------------------------
				String classtime1 = classtimeDetail[0];	//앞시간				
					String classday1 = classtime1.substring(0,1);	//요일
						int day1 = returnDay(classday1);	// 요일 값 반환						
				String classstart1 = classtime1.substring(1,2);	// 수업시작시간
					String start1Time = returnsTime(classstart1);						
				String classend1 = classtime1.substring(classtime1.length()-1); // 수업 종료시간
					String end1Time = returneTime(classend1);				
			//---------------------------------------------------------------------------------							
				String classtime2 = classtimeDetail[1];	//뒤시간				
					String classday2 = classtime2.substring(0,1);	//요일
						int day2 = returnDay(classday2);
				String classstart2 = classtime2.substring(1,2);
					String start2Time = returnsTime(classstart2);
				String classend2 = classtime2.substring(classtime2.length()-1);
					String end2Time = returneTime(classend2);
			//---------------------------------------------------------------------------------
					
					
			//해당 강의의 강의실, 이름 뽑기
					String classRoom = (String) classInfo.getClassRoom();
					String className = (String) classInfo.getClassName();
			log.info("해당 강의의 강의실, 이름을 추출합니다. : {}",classRoom+"/"+className);		
					
		//시작날짜부터 시작해서 하루씩 추가해 가면서, 요일이 강의요일과 같다면? 일정에 추가하는데, 종강일까지 반복한다.
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date startSM = format.parse(startdate);	// 학기 시작부터
				Date endSM = format.parse(enddate);	// 학기 종료까지
			log.info("반복할 날짜를 설정합니다. : {}", startSM+"/"+endSM);
				
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
					SimpleDateFormat attFormat = new SimpleDateFormat("yyyy-MM-dd");
					String classDate = attFormat.format(startSM);					
					log.info("강의날짜 : {}", classDate);	
					
//						출석 정보 가져오기				
					AttendanceVO Info = new AttendanceVO();
					
					Info.setClassNo(classNo);
					String year = start.substring(2, 4);
					String month = start.substring(5,7);
					String day = start.substring(8,10);
					log.info("날짜 : {}", year+"/"+month+"/"+day);
					String cDate = year+"/"+month+"/"+day;
					Info.setAtdcDate(cDate);
					List<AttendanceVO> attList = LecService.retrieveClassAtt(Info);
						int attcount = attList.size();
					String attCont = "("+attcount+"/"+totalStuCount+")";
					//----------
										
					hash.put("title", classRoom+className+attCont);
					hash.put("no", index);
					hash.put("url", "attDetail.do?classNo="+classNo+"&&start="+start);
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
					SimpleDateFormat attFormat = new SimpleDateFormat("yyyy-MM-dd");
					String classDate = attFormat.format(startSM);										
					log.info("강의날짜2 : {}", classDate);		
					
//						출석 정보 가져오기
					AttendanceVO Info = new AttendanceVO();
					
					Info.setClassNo(classNo);
					String year = start.substring(2, 4);
					String month = start.substring(5,7);
					String day = start.substring(8,10);
					log.info("날짜 : {}", year+"/"+month+"/"+day);
					String cDate = year+"/"+month+"/"+day;
					Info.setAtdcDate(cDate);
					List<AttendanceVO> attList = LecService.retrieveClassAtt(Info);
						int attcount = attList.size();
					String attCont = "("+attcount+"/"+totalStuCount+")";						
					//----------
					
					hash.put("title", classRoom+className+attCont);
					hash.put("no", index);
					hash.put("url", "attDetail.do?classNo="+classNo+"&&start="+start);
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
		//종료	
		return jsonArr;
	}
	
	@RequestMapping("/calendar/classCalendarList.do")
	public String calendarList() {
		return "/common/calendar/classCalendar";
	}
}
