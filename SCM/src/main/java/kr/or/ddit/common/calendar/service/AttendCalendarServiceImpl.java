package kr.or.ddit.common.calendar.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.calendar.dao.AttendCalendarDAO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.ScheduleVO;

@Service
public class AttendCalendarServiceImpl implements AttendCalendarService {
	@Inject
	private AttendCalendarDAO dao;
	
	@Override
	public List<Map<String, Object>> calendarList() {
		return dao.calendarList();
	}

	
	
	@Override
	public List<Map<String, Object>> selectAllClass(Map<String, String> input) {		
		return dao.selectAllClassList(input);
	}



	@Override
	public List<ScheduleVO> getSchedule() {
		return dao.getSchedule();	
	}



	@Override
	public ClassVO selectClassInfo(String classNo) {
		return dao.selectClassInfo(classNo);
	}
	
	
}
