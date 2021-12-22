package kr.or.ddit.administration.off_fac.facilitymanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.off_fac.facilitymanage.dao.AthleticDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AthleticServiceImpl implements AthleticService {

	@Inject
	private AthleticDao athleticDao;
	
	@Override
	public List<FacilityVO> retrieveAthleticList(PagingVO<FacilityVO> pagingVO) {
		List<FacilityVO> athleticList = athleticDao.selectAthleticList(pagingVO);
		pagingVO.setTotalRecord(athleticDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(athleticList);
		return athleticList;
	} 

	@Override
	public FacilityVO retrieveAthletic(String facNo) {
		FacilityVO athletic = athleticDao.selectAthletic(facNo);
		if(athletic == null) throw new PKNotFoundException("해당하는 시설은 없습니다.");
		return athletic;
	}

	@Override
	public ServiceResult modifyAthletic(FacilityVO facility) {
		ServiceResult result = null;
		int rowcnt = athleticDao.updateAthletic(facility);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeAthletic(FacilityVO facility) {
		ServiceResult result = null;
		int rowcnt = athleticDao.deleteAthletic(facility);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createAthletic(FacilityVO facility) {
		ServiceResult result = null;
		int rowcnt = athleticDao.insertAthletic(facility);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

}
