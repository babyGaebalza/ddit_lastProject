package kr.or.ddit.administration.stu_sup.support.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BusinessVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface StuSupportDao {
		
	//협업 업체 수
	public int selectTotalCount(PagingVO<BusinessVO> pagingVO);
	
	//협업 업체 리스트
	public List<BusinessVO> selectStuSupportList(PagingVO<BusinessVO> pagingVO);
	
	//업체 조회
	public BusinessVO selectStuSupport(String bussNo);
	
	//업체 수정
	public int updateStuSupport(BusinessVO business);
	
	//업체 삭제
	public int deleteStuSupport(BusinessVO business);
	
	//업체 등록
	public int insertStuSupport(BusinessVO business);
}
