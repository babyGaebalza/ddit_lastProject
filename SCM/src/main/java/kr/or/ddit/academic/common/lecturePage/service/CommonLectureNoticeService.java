package kr.or.ddit.academic.common.lecturePage.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface CommonLectureNoticeService {

	// 전체 리스트
	public List<BoardVO> retrieveNoticeList(PagingVO<BoardVO> pagingVO);
	
	// 상세
	public BoardVO retrieveNotice(String boardNo);
	
	


}
