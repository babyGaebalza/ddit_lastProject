package kr.or.ddit.common.board.service;


import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface FacComplaintService {
	
	//회원ID로 회원이름 변환하기
	public String getMemName(String memId);
	
	//민원 작성
	public ServiceResult createFC(BoardVO facComplaint);
	
	//민원리스트 조회
	public List<BoardVO> retrieveFCList(PagingVO pagingVO);
	
	//민원리스트 상세조회
	public BoardVO retrieveFCView(String fcNo);
	
	//민원글 수정
	public ServiceResult modifyFC(BoardVO board);
	
	//민원글 삭제
	public ServiceResult removeFC(String boardNo);
}
