package kr.or.ddit.academic.professor.lecturePage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ProfessorLectureTaskDAO {

/*	//과제 게시글 
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	// 과제 게시글  리스트 조회
	public List<BoardVO> selectTaskList(PagingVO<BoardVO> pagingVO);*/
	
	//과제 게시글  추가
	public int insertLectureTask(BoardVO board);
	
/*	// 과제 게시글 상세조회
	public BoardVO selectTask(String boardNo);*/
	
	//과제 게시글  수정
	public int updateTask(BoardVO board);
	
	// 과제 게시글  삭제
	public int deleteTask(String boardNo);
	
/*	// 과제 게시글  조회수 증가 부분
	public int incrementCount(Map<String, Object> pMap);*/
	
	
}
