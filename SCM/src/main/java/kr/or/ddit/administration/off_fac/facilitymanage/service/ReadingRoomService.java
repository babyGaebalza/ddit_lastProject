package kr.or.ddit.administration.off_fac.facilitymanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;

public interface ReadingRoomService {

	//열람실 전체 현황 조회
	public List<FacilityVO> retrieveReadingRoomList(PagingVO<FacilityVO> pagingVO);

	//열람실 상세정보
	public FacilityVO retrieveReadingRoom(String facNo);
	
	//강의실 정보 수정
	public ServiceResult modifyReadingRoom(FacilityVO facility);
	
	//열람실 예약정보 수
	public ServiceResult removeReadingRoom(FacilityVO facility);
	
	//신규 강의실 등록
	public ServiceResult createReadingRoom(FacilityVO facility);
	
}
