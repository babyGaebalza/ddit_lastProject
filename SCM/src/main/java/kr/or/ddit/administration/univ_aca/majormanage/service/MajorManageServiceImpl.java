package kr.or.ddit.administration.univ_aca.majormanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_aca.majormanage.dao.MajorManageDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class MajorManageServiceImpl implements MajorManageService {
	
	@Inject
	private MajorManageDao majorDao;
	
	@Override
	public List<MajorVO> retrieveMajorList(PagingVO<MajorVO> pagingVO) {
		List<MajorVO> majorList = majorDao.selectMajorList(pagingVO);
		pagingVO.setTotalRecord(majorDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(majorList);
		return majorList;
	}

	@Override
	public MajorVO retrieveMajor(String majorName) {
		MajorVO major = majorDao.selectMajor(majorName);
		if(major == null) 
			throw new PKNotFoundException("해당하는 학과는 없습니다.");
		return major;
	}

	@Override
	public ServiceResult modifyMajor(MajorVO major) {
		ServiceResult result = null;
		int rowcnt = majorDao.updateMajor(major);
		
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeMajor(MajorVO major) {
		ServiceResult result = null;
		int rowcnt = majorDao.deleteMajor(major);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createMajor(MajorVO major) {
		ServiceResult result = null;
		int rowcnt = majorDao.insertMajor(major);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}
	
}
