package kr.or.ddit.academic.professor.lecturePage.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

public interface ProfessorLectureNoticeService {

	public ServiceResult createNotice(BoardVO notice);


	public ServiceResult modifyNotice(BoardVO notice);

	
	public ServiceResult removeNotice(String boardNo); 
}
