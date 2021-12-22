package kr.or.ddit.academic.professor.lecturePage.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
@Repository
public interface ProvessorLectureNoticeDAO {


	public int insertLectureNotice(BoardVO notice);

	public int updateNotice(BoardVO notice);
	
	public int deleteNotice(String boardNo);

}
