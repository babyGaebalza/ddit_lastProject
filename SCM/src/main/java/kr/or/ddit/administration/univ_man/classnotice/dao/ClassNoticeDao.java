package kr.or.ddit.administration.univ_man.classnotice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ClassNoticeDao {
	
	//강의공지 수
	public int selectTotalCount(PagingVO<BoardVO> pagingVO);
	
	//강의공지 리스트
	public List<BoardVO> selectCNoticeList(PagingVO<BoardVO> pagingVO);
	
	//강의공지 상세조회
	public BoardVO selectCNotice(String boardNo);
	
	//강의공지 수정
	public int updateCNotice(BoardVO board);
	
	//강의공지 삭제
	public int deleteCNotice(BoardVO board);
	
	//강의공지 추가
	public int insertCNotice(BoardVO board);
	
	//조회수
	public void incrementCount(Map<String, Object> pMap);
	
}
