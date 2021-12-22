package kr.or.ddit.common.reservation.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.reservation.dao.ReservationDao;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReservationVO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Inject
	ReservationDao reservationDAO;
	
	@Override
	public int selectTotalResCount(PagingVO<ReservationVO> pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectTotalclassCount(PagingVO<ReservationVO> pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectTotalLibraryCount(PagingVO<ReservationVO> pagingVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReservationVO> selectResList(PagingVO<ReservationVO> pagingVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservationVO selectReservation(String resNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertReservation(ReservationVO reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReservation(ReservationVO reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReservation(ReservationVO reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

}
