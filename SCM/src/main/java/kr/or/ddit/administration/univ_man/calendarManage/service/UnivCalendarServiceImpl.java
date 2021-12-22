package kr.or.ddit.administration.univ_man.calendarManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_man.calendarManage.dao.UnivCalendarDAO;
import kr.or.ddit.vo.ScheduleVO;

@Service
public class UnivCalendarServiceImpl implements  UnivCalendarService{

	@Inject
	private UnivCalendarDAO dao; 
	
	
	//전체 일정 리스트 출력하기 
	@Override
	public List<ScheduleVO> retrieveCalendarList() {
		return dao.selectCalendarList(); 
	}

}
