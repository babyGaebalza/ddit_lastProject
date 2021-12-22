package kr.or.ddit.administration.off_fac.facilitymanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface AthleticDao {
	
	//체육시설 수
	public int selectTotalCount(PagingVO<FacilityVO> pagingVO);
	
	//체육시설 전체 현황 조회
	public List<FacilityVO> selectAthleticList(PagingVO<FacilityVO> pagingVO);
	
	//체육시설 정보 조회
	public FacilityVO selectAthletic(String facNo);
	
	//체육시설 수정
	public int updateAthletic(FacilityVO facility);
	
	//체육시설 삭제
	public int deleteAthletic(FacilityVO facility);
	
	//체육시설 등록
	public int insertAthletic(FacilityVO facility);
	
}
