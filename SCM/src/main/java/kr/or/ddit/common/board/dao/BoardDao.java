package kr.or.ddit.common.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface BoardDao {
	
	//총 전체 수
	public int selectTotalCount(PagingVO<BoardVO> pagingVO);
	
	//과제게시글 추가
	public int insertNotice(BoardVO board);
	
	//게시글  리스트 조회
	public List<BoardVO> selectNoticeList(PagingVO<BoardVO> pagingVO);
	
	//게시글 상세조회
	public BoardVO selectNotice(String boardNo);
	
	//게시글 수정
	public int updateNotice(BoardVO board);
	
	//삭제
	public int deleteNotice(String boardNo);
	
	//조회수 증가 
	public int incrementCount(Map<String, Object> pMap);
	
	// 메인용 공지사항(학사) 조회
	public List<BoardVO> selectLatestNoticeList(Map<String, Object> noticeSearchMap);
}
