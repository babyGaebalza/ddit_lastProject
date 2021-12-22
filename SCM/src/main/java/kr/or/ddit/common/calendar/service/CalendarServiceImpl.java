package kr.or.ddit.common.calendar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.calendar.dao.CalendarDAO;
import kr.or.ddit.vo.CalendarVO;
import lombok.extern.slf4j.Slf4j;

@Service
public class CalendarServiceImpl implements CalendarService {
	@Inject
	private CalendarDAO dao;
	
	@Override
	public List<Map<String, Object>> calendarList() {
		return dao.calendarList();
	}
}
