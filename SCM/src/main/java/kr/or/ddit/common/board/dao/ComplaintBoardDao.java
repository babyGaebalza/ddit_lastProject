package kr.or.ddit.common.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ComplaintBoardDao {
		// 총 문의 게시글 수
		public int selectTotalCount(PagingVO<BoardVO> pagingVO);
		
		// 전체 문의게시글 리스트 조회
		public List<BoardVO> selectComplaintList(PagingVO<BoardVO> pagingVO);
		
		// 전체공지 상세조회
		public BoardVO selectComplaint(String boardTitle);
		
		// 문의글 작성
		public int insertComplaint(BoardVO board);
		
		// 문의글 수정
		public int updateComplaint(BoardVO board);
		
		// 문의글 삭제
		public int deleteComplaint(BoardVO board);
		
		// 문의글 조회수 증가 부분
		public int incrementCount(Map<String, Object> pMap);
}
