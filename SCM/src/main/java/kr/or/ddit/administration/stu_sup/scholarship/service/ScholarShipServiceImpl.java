package kr.or.ddit.administration.stu_sup.scholarship.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.stu_sup.scholarship.dao.ScholarShipDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarVO;

@Service
public class ScholarShipServiceImpl implements ScholarShipService {
	
	@Inject
	private ScholarShipDao scholarShipDao;

	@Override
	public List<ScholarVO> retrieveScholarShipList(PagingVO<ScholarVO> pagingVO) {
		List<ScholarVO> scholarList = scholarShipDao.selectScholarShipList(pagingVO);
		pagingVO.setTotalRecord(scholarShipDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(scholarList);
		return scholarList;
	}

	@Override
	public ScholarVO retrieveScholarShip(String schCode) {
		ScholarVO scholar = scholarShipDao.selectScholarShip(schCode);
		if(scholar == null) throw new PKNotFoundException("해당하는 시설은 없습니다.");
		return scholar;
	}

	@Override
	public ServiceResult modifyScholarShip(ScholarVO scholar) {
		ServiceResult result = null;
		int rowcnt = scholarShipDao.updateScholarShip(scholar);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeScholarShip(ScholarVO scholar) {
		ServiceResult result = null;
		int rowcnt = scholarShipDao.deleteScholarShip(scholar);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createScholarShip(ScholarVO scholar) {
		ServiceResult result = null;
		int rowcnt = scholarShipDao.insertScholarShip(scholar);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}
	
	
}
