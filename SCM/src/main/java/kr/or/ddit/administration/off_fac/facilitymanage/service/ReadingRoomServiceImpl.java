package kr.or.ddit.administration.off_fac.facilitymanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.off_fac.facilitymanage.dao.ReadingRoomDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class ReadingRoomServiceImpl implements ReadingRoomService {

	@Inject
	private ReadingRoomDao readingRoomDao;
	
	@Override
	public List<FacilityVO> retrieveReadingRoomList(PagingVO<FacilityVO> pagingVO) {
		List<FacilityVO> readingRoomList = readingRoomDao.selectReadingRoomList(pagingVO);
		pagingVO.setTotalRecord(readingRoomDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(readingRoomList);
		return readingRoomList;
	}

	@Override
	public FacilityVO retrieveReadingRoom(String facNo) {
		FacilityVO readingRoom = readingRoomDao.selectReadingRoom(facNo);
		if(readingRoom == null) throw new PKNotFoundException("해당하는 시설은 없습니다.");
		return readingRoom;
	}

	@Override
	public ServiceResult modifyReadingRoom(FacilityVO facility) {
		ServiceResult result = null;
		int rowcnt = readingRoomDao.updateReadingRoom(facility);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeReadingRoom(FacilityVO facility) {
		ServiceResult result = null;
		int rowcnt = readingRoomDao.deleteReadingRoom(facility);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createReadingRoom(FacilityVO facility) {
		ServiceResult result = null;
		int rowcnt = readingRoomDao.insertReadingRoom(facility);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

}
