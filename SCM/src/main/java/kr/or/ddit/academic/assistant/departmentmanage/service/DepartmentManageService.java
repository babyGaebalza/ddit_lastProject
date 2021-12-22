package kr.or.ddit.academic.assistant.departmentmanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface DepartmentManageService {
	// 전체 공지 리스트
	public List<BoardVO> retrieveDepartmentList(PagingVO<BoardVO> pagingVO);
	
	// 공지 상세
	public BoardVO retrieveDepartment(String boardNo);
	
	// 공지 작성
	public ServiceResult createDepartment(BoardVO board);
	
	// 공지 수정
	public ServiceResult modifyDepartment(BoardVO board);
	
	// 공지 삭제
	public ServiceResult removeDepartment(BoardVO board);
	
	// 첨부 다운로드
	public AttatchVO download(int attNo);
}
