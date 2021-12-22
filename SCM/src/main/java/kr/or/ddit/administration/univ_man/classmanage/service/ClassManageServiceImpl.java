package kr.or.ddit.administration.univ_man.classmanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_man.classmanage.dao.ClassManageDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class ClassManageServiceImpl implements ClassManageService {

	@Inject
	private ClassManageDao classcDao;
	
	@Override
	public List<ClassVO> retrieveClassList(PagingVO<ClassVO> pagingVO) {
		List<ClassVO> classcList = classcDao.selectClassList(pagingVO);
		pagingVO.setTotalRecord(classcDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(classcList);
		return classcList;
	}

	@Override
	public ClassVO retrieveClass(String classNo) {
		ClassVO uclass = classcDao.selectClass(classNo);
		if(uclass == null) throw new PKNotFoundException("해당하는 시설은 없습니다.");
		return uclass;
	}

	@Override
	public ServiceResult modifyClass(ClassVO uclass) {
		ServiceResult result = null;
		int rowcnt = classcDao.updateClass(uclass);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeClass(ClassVO uclass) {
		ServiceResult result = null;
		int rowcnt = classcDao.deleteClass(uclass);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createClass(ClassVO uclass) {
		ServiceResult result = null;
		int rowcnt = classcDao.insertClass(uclass);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

}
