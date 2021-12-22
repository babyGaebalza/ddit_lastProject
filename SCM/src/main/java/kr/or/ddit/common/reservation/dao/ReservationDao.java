package kr.or.ddit.common.reservation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReservationVO;

@Repository
public interface ReservationDao {

	//총 일반시설예약 수
	public int selectTotalResCount(PagingVO<ReservationVO> pagingVO);
	
	//총 강의실사용 수
	public int selectTotalclassCount(PagingVO<ReservationVO> pagingVO);
	
	//총 열람실
	public int selectTotalLibraryCount(PagingVO<ReservationVO> pagingVO);
	
	//에약 현황 리스트
	public List<ReservationVO> selectResList(PagingVO<ReservationVO> pagingVO);
	
	//예약 정보 조회(상세)
	public ReservationVO selectReservation (String resNo);
	
	//예약 추가
	public int insertReservation (ReservationVO reservation);
	
	//예약 변경
	public int updateReservation (ReservationVO reservation);
	
	//예약 삭제
	public int deleteReservation (ReservationVO reservation);
	
	
}
