package kr.or.ddit.administration.univ_man.classnotice.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface ClassNoticeService {
	
	//강의공지 리스트
	public List<BoardVO> retrieveCNoticeList(PagingVO<BoardVO> pagingVO);
	
	//강의공지 상세조회
	public BoardVO retrieveCNotice(String boardNo);
	
	//강의공지 수정
	public ServiceResult modifyCNotice(BoardVO board);
	
	//강의공지 삭제
	public ServiceResult removeCNotice(BoardVO board);
	
	//강의공지 추가
	public ServiceResult createCNotice(BoardVO board);
	
	// 첨부 다운로드
	public AttatchVO download(int attNo);
}
