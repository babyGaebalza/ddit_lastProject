package kr.or.ddit.common.facilities.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.facilities.dao.FacilityDAO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.LibraryVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReservationVO;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Inject
	private FacilityDAO facilityDAO;

	@Override
	public List<ReservationVO> reservationList(PagingVO<ReservationVO> pagingVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateReservation(ReservationVO reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteReservation(String resNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReservation(ReservationVO reservation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ReservationVO reservationDetail(String resNo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
