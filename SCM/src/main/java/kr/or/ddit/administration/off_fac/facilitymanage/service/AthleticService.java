package kr.or.ddit.administration.off_fac.facilitymanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;

public interface AthleticService {
	
	//체육시설 전체 현황 조회
	public List<FacilityVO> retrieveAthleticList(PagingVO<FacilityVO> pagingVO);
		
	//체육시설 정보 조회
	public FacilityVO retrieveAthletic(String facNo);
		
	//체육시설 수정
	public ServiceResult modifyAthletic(FacilityVO facility);
		
	//체육시설 삭제
	public ServiceResult removeAthletic(FacilityVO facility);
		
	//체육시설 등록
	public ServiceResult createAthletic(FacilityVO facility);
	
}
