package kr.or.ddit.administration.off_fac.facilitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ReadingRoomDao {
	//열람실 전체 수
	public int selectTotalCount(PagingVO<FacilityVO> pagingVO);
	
	//열람실 전체 현황 조회
	public List<FacilityVO> selectReadingRoomList(PagingVO<FacilityVO> pagingVO);

	//열람실 상세정보
	public FacilityVO selectReadingRoom(String facNo);
	
	//강의실 정보 수정
	public int updateReadingRoom(FacilityVO facility);
	
	//열람실 예약정보 수
	public int deleteReadingRoom(FacilityVO facility);
	
	//신규 강의실 등록
	public int insertReadingRoom(FacilityVO facility);
	
}
