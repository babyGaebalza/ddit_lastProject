package kr.or.ddit.academic.common.lecturePage.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.academic.vo.TaskVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface CommonLectureTaskService {

	// 전체 리스트
	public List<BoardVO> retrieveTaskList(PagingVO<BoardVO> pagingVO);
	
	// 상세
	public BoardVO retrieveTask(String boardNo);
	
	// 상세 제출물 조회
	public List<TaskVO> retrieveSubmitTasks(Map<String, Object> searchMap);
	
/*	// 공지 작성 교수만의 기능
	public ServiceResult createTask(BoardVO board);
	
	// 공지 수정
	public ServiceResult modifyTask(BoardVO board);
	
	// 공지 삭제
	public ServiceResult removeTask(BoardVO board);*/
	
	// 첨부 다운로드
	public AttatchVO download(int attNo);
	
	// 과제 제출
	public void createSubmitTask(TaskVO task) throws Exception;
	
	// 과제 수정
	public void modifySubmitTask(TaskVO task) throws Exception;
	
	//교수가 과제 점수 채점할때 
	public ServiceResult updateScore(TaskVO task);
}
