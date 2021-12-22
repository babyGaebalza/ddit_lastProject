package kr.or.ddit.academic.common.lecturePage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.academic.vo.TaskVO;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface CommonLectureTaskDAO {

	//과제 게시글 
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	// 과제 게시글  리스트 조회
	public List<BoardVO> selectTaskList(PagingVO<BoardVO> pagingVO);
	
/*	//과제 게시글  추가 교수만의 기능
	public int insertLectureTask(BoardVO board);*/
	
	// 과제 게시글 상세조회
	public BoardVO selectTask(String boardNo);
	
	public List<TaskVO> selectSubmitTasks(Map<String, Object> searchMap);
/*	
	//과제 게시글  수정  >>교수만의 기능
	public int updateTask(BoardVO board);
	
	// 과제 게시글  삭제
	public int deleteTask(String boardNo);*/
	
	// 과제 게시글  조회수 증가 부분
	public int incrementCount(Map<String, Object> pMap);
	
	// 과제 제출(글)
	public int insertSubmitTask(TaskVO task);
	// 과제 제출(첨부파일)
	public int insertSubmitTaskAttatch(TaskVO task); 
	
	// 과제 수정(글)
	public int updateSubmitTask(TaskVO task);
	// 과제 수정(점부파일 삭제)
	public int deleteSubmitTaskAttatch(TaskVO task);

	// 교수가 과제점수  채점할때 
	public int updateScore(TaskVO task);
}
