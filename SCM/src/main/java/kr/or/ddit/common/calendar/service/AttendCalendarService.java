package kr.or.ddit.common.calendar.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.ScheduleVO;


public interface AttendCalendarService {
	public List<Map<String, Object>> calendarList();
	
	public List<Map<String, Object>> selectAllClass(Map<String, String> input);
	
	public List<ScheduleVO> getSchedule();

	public ClassVO selectClassInfo(String classNo);
}
