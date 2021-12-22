package kr.or.ddit.academic.professor.lecturePage.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
@Repository
public interface ProfessorLectureMaterialDAO {

	public int insertLectureMaterial(BoardVO board);

	public int updateMaterial(BoardVO board);
	
	public int deleteMaterial(String boardNo);


}
