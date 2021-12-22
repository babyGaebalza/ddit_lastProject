package kr.or.ddit.academic.common.lecturePage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface CommonLectureNoticeDAO {

	
	// 게시글 수 
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	// 게시글  리스트 조회
	public List<BoardVO> selectNoticeList(PagingVO<BoardVO> pagingVO);
	
	// 게시글 상세조회
	public BoardVO selectNotice(String boardNo);

	// 게시글  조회수 증가 부분
	public int incrementCount(Map<String, Object> pMap);

}
