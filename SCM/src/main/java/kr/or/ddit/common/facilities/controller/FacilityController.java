package kr.or.ddit.common.facilities.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import kr.or.ddit.common.facilities.service.FacilityService;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ReservationVO;

@Controller
public class FacilityController {

	@Inject
	FacilityService service;
	
	//시설 예약 현황 조회
	public String listResrvation(
			) {
		return null;
	}
			
	//시설 예약 현황 수정
	public String updateReservation(
			) {
		return null;
	}
			
	//시설 예약 현황 삭제
	public String deleteReservation(
			) {
		return null;
	}
			
	//시설 예약
	public String insertReservation(
			) {
		return null;
	}
	
	//예약 상세 조회
	public String reservationDetail(
			) {
		return null;
	}
	
	
}
