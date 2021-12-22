package kr.or.ddit.administration.off_gen.certificatemanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.administration.off_gen.certificatemanage.dao.CertificateManageDAO;
import kr.or.ddit.administration.vo.CertiVO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CertificateManageServiceImpl implements CertificateManageService {
	@Inject
	private CertificateManageDAO dao;

	@Override
	public List<CategoryVO> retrieveCategoryList(PagingVO<CategoryVO> pagingVO) {
		List<CategoryVO> cateList = dao.selectCategoryList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCount(pagingVO));
		pagingVO.setDataList(cateList);
		return cateList;
	}

	@Override
	public CategoryVO retrieveCategory(String cateCode) {
		CategoryVO cate = dao.selectCategory(cateCode);
		if(cate == null) throw new PKNotFoundException(cateCode + "번 증명서가 없습니다.");
		
		return cate;
	}

	@Override
	public ServiceResult modifyCategory(CategoryVO category) {
		ServiceResult result = null;
		int rowcnt = dao.updateCategory(category);
		
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		
		return result;
	}

	@Override
	public List<CertiVO> retrieveCertiList(PagingVO<CertiVO> pagingVO) {
		List<CertiVO> certiList = dao.selectCertiList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCerti(pagingVO));
		pagingVO.setDataList(certiList);
		
		return certiList;
	}

	@Override
	public CertiVO retrieveCerti(String certiNo) {
		CertiVO certi = dao.selectCerti(certiNo);
		if(certi == null) throw new PKNotFoundException(certiNo + "해당 증명서 발급현황을 찾을 수 없습니다.");
		return certi;
	}
	
	@Override
	public ServiceResult createCerti(CertiVO certi) {
		ServiceResult result = null;
				
		int rowcnt = dao.insertCerti(certi);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

//	@Transactional
	@Override
	public ServiceResult modifyCerti(CertiVO certi) {
		ServiceResult result = null;
		int rowcnt = dao.updateCerti(certi);
		
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		
		return result;
	}

	@Override
	public ServiceResult createCertificate(CertiVO certi) {
		ServiceResult result = null;
		
		int rowcnt = dao.insertCertificate(certi);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<CertiVO> retrieveCheckList(PagingVO<CertiVO> pagingVO) {
		List<CertiVO> certiList = dao.selectCheckList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCheck(pagingVO));
		pagingVO.setDataList(certiList);
		
		return certiList;
	}
}
