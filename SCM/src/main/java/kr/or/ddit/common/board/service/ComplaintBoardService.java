package kr.or.ddit.common.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface ComplaintBoardService {
		// 전체 문의 리스트
		public List<BoardVO> retrieveComplaintList(PagingVO<BoardVO> pagingVO);
		
		// 문의 내역 상세
		public BoardVO retrieveComplaint(String boardTitle);
		
		// 문의글 생성
		public ServiceResult createComplaint(BoardVO board);
		
		// 문의글 수정
		public ServiceResult modifyComplaint(BoardVO board);
		
		// 문의글 삭제
		public ServiceResult removeComplaint(BoardVO board);
		
}
