package kr.or.ddit.academic.professor.lecturePage.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;

public interface ProfessorLectureMaterialService {

	public ServiceResult createMaterial(BoardVO board);

	public ServiceResult modifyBoard(BoardVO board);
	
	public ServiceResult deleteMaterial(String boardNo); 

}
