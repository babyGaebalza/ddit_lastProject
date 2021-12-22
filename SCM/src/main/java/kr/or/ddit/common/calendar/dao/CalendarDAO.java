package kr.or.ddit.common.calendar.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CalendarVO;

@Repository
public interface CalendarDAO {
	public List<Map<String, Object>> calendarList();
}
