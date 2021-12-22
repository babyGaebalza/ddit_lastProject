package kr.or.ddit.common.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface BoardService {
	
	// 전체 공지 리스트
	public List<BoardVO> retrieveNoticeList(PagingVO<BoardVO> pagingVO);
	
	// 공지 상세
	public BoardVO retrieveNotice(String boardNo);
	
	// 공지 작성
	public ServiceResult createNotice(BoardVO board);
	
	// 공지 수정
	public ServiceResult modifyNotice(BoardVO board);
	
	// 공지 삭제
	public ServiceResult removeNotice(BoardVO board);
	
	// 첨부 다운로드
	public AttatchVO download(int attNo);


	
}
	
