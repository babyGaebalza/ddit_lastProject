package kr.or.ddit.administration.off_gen.certificatemanage.service;

import java.util.List;

import kr.or.ddit.administration.vo.CertiVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.PagingVO;

public interface CertificateManageService {
	//증명서 리스트 
	public List<CategoryVO> retrieveCategoryList(PagingVO<CategoryVO> pagingVO);
	
	//증명서 상세페이지
	public CategoryVO retrieveCategory(String cateCode);
	
	//증명서 수정
	public ServiceResult modifyCategory(CategoryVO category);
	
	//기록 리스트
	public List<CertiVO> retrieveCertiList(PagingVO<CertiVO> pagingVO);
	
	//기록 상세페이지
	public CertiVO retrieveCerti(String certiNo);
	
	//기록 등록
	public ServiceResult createCerti(CertiVO certi);
	
	//기록 수정
	public ServiceResult modifyCerti(CertiVO certi);

	//발급 증명서 리스트
	public List<CertiVO> retrieveCheckList(PagingVO<CertiVO> pagingVO);
	
	//학생 직원 증명서 등록
	public ServiceResult createCertificate(CertiVO certi);
}
