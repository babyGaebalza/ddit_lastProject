package kr.or.ddit.administration.stu_sup.support.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.stu_sup.support.dao.StuSupportDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BusinessVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class StuSupportServiceImpl implements StuSupportService{
	
	@Inject
	private StuSupportDao stuSupprotDao;
	
	@Override
	public List<BusinessVO> retrieveStuSupportList(PagingVO<BusinessVO> pagingVO) {
		List<BusinessVO> stusupport = stuSupprotDao.selectStuSupportList(pagingVO);
		pagingVO.setTotalRecord(stuSupprotDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(stusupport);
		return stusupport;
	}

	@Override
	public BusinessVO retrieveStuSupport(String bussNo) {
		BusinessVO stusupport = stuSupprotDao.selectStuSupport(bussNo);
		if(stusupport == null) throw new PKNotFoundException("해당하는 시설은 없습니다.");
		return stusupport;
	}

	@Override
	public ServiceResult modifyStuSupport(BusinessVO business) {
		ServiceResult result = null;
		int rowcnt = stuSupprotDao.updateStuSupport(business);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeStuSupport(BusinessVO business) {
		ServiceResult result = null;
		int rowcnt = stuSupprotDao.deleteStuSupport(business);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createStuSupport(BusinessVO business) {
		ServiceResult result = null;
		int rowcnt = stuSupprotDao.insertStuSupport(business);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

}
