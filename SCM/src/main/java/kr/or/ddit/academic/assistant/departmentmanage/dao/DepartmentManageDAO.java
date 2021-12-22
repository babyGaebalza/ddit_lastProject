package kr.or.ddit.academic.assistant.departmentmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface DepartmentManageDAO {
	//총 전체공지 수
	public int selectTotalCount(PagingVO<BoardVO> pagingVO);
	
	//전체공지 추가
	public int insertDepartment(BoardVO board);
	
	// 전체공지 리스트 조회
	public List<BoardVO> selectDepartmentList(PagingVO<BoardVO> pagingVO);
	
	// 전체공지 상세조회
	public BoardVO selectDepartment(String boardNo);
	
	// 전체공지 수정
	public int updateDepartment(BoardVO board);
	
	// 전체공지 삭제
	public int deleteDepartment(String boardVO);
	
	// 전체공지 조회수 증가 부분
	public int incrementCount(Map<String, Object> pMap);
}
