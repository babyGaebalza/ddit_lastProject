package kr.or.ddit.common.calendar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CalendarVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.ScheduleVO;

@Repository
public interface AttendCalendarDAO {
	public List<Map<String, Object>> calendarList();
	
	
	public List<Map<String, Object>> selectAllClassList(Map<String, String> input);
	
	
	public List<ScheduleVO> getSchedule();
	
	public ClassVO selectClassInfo(String classNo);
}
