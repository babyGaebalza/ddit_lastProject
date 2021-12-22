package kr.or.ddit.common.schedule.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ScheduleVO;

@Repository
public interface ScheduleDAO {
	//
	public ScheduleVO selectLectureRegisterByNow();
	// 현재학기 정보 불러오기
	public ScheduleVO selectCurrentSemester();
	
	// 강의바구니 기간 변경
	public int updateCartChangeOne();
	public int updateCartChangeTwo();
	// 수강신청 기간 변경
	public int updateRegiChangeOne();
	public int updateRegiChangeTwo();
}
