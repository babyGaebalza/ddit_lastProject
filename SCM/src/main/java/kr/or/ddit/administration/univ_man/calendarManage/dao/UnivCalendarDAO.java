package kr.or.ddit.administration.univ_man.calendarManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ScheduleVO;

@Repository
public interface UnivCalendarDAO {

//전체리스트 
public List<ScheduleVO> selectCalendarList();

}
