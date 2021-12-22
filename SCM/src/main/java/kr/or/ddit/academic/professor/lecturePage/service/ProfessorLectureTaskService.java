package kr.or.ddit.academic.professor.lecturePage.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

public interface ProfessorLectureTaskService {


	
	// 공지 작성
	public ServiceResult createTask(BoardVO board);
	
	// 공지 수정
	public ServiceResult modifyTask(BoardVO board);
	
	// 공지 삭제
	public ServiceResult removeTask(String boardNo);
	
	
}
