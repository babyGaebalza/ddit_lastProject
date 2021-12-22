package kr.or.ddit.administration.off_gen.certificatemanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.administration.vo.CertiVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface CertificateManageDAO {
	//증명서 수
	public int selectTotalCount(PagingVO<CategoryVO> pagingVO);
	
	//증명서 리스트
	public List<CategoryVO> selectCategoryList(PagingVO<CategoryVO> pagingVO);
	
	//증명서 상세페이지
	public CategoryVO selectCategory(String cateCode);
	
	//증명서 수정(발급 가능 증명서 또는 수수료 변경)
	public int updateCategory(CategoryVO category);
	
	//기록 수
	public int selectTotalCerti(PagingVO<CertiVO> pagingVO);
	
	//기록 리스트
	public List<CertiVO> selectCertiList(PagingVO<CertiVO> pagingVO);
	
	//기록 상세페이지
	public CertiVO selectCerti(String certiNo);
	
	//기록 등록
	public int insertCerti(CertiVO certi);
	
	//기록 수정
	public int updateCerti(CertiVO certi);
	
	//발급 증명서 확인 수 
	public int selectTotalCheck(PagingVO<CertiVO> pagingVO);
	
	//발급 증명서 리스트
	public List<CertiVO> selectCheckList(PagingVO<CertiVO> pagingVO);
	
	//증명서 리스트(select)
	public List<CategoryVO> selectCertificate(CategoryVO cate);
	
	//수수료
	public CategoryVO selectCertificateValue(CategoryVO cate);
	
	//학생 직원 증명서 등록
	public int insertCertificate(CertiVO crerti);
	
	//신청트랙 상태변경
	public int update(String certiList);
	
	//카테고리 state 변경
	public int updateCateState(String cate);
	
	public List<CategoryVO> selectCateList(CategoryVO cate);
}
