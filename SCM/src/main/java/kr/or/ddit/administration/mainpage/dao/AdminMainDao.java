package kr.or.ddit.administration.mainpage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface AdminMainDao {
	
	//신청트랙 수
	public int selectTotalCount(PagingVO<DocumentVO> pagingVO);
	
	//신청트랙 리스트
	public List<DocumentVO> selectDocumentList(PagingVO<DocumentVO> pagingVO);
		
}
