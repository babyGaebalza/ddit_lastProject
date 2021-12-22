package kr.or.ddit.administration.univ_man.graduatemanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_man.graduatemanage.dao.GraduateDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class GraduateServiceImpl implements GraduateService {
	
	@Inject
	private GraduateDao graduateDao;
	
	@Override
	public List<MemberVO> retrieveGraduateList(PagingVO<MemberVO> pagingVO) {
		List<MemberVO> graduate = graduateDao.selectGraduateList(pagingVO);
		pagingVO.setTotalRecord(graduateDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(graduate);
		return graduate;
	}

	@Override
	public MemberVO retrieveGraduate(String memId) {
		MemberVO graduate = graduateDao.selectGraduate(memId);
		if(graduate == null) throw new PKNotFoundException("해당하는 시설은 없습니다.");
		return graduate;
	}

	@Override
	public ServiceResult modifyGraduate(MemberVO member) {
		ServiceResult result = null;
		int rowcnt = graduateDao.updateGraduate(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeGraduate(MemberVO member) {
		ServiceResult result = null;
		int rowcnt = graduateDao.deleteGraduate(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createGraduate(MemberVO member) {
		ServiceResult result = null;
		int rowcnt = graduateDao.insertGraduate(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

}
