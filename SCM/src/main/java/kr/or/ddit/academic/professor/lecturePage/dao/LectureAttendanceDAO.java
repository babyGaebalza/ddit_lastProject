package kr.or.ddit.academic.professor.lecturePage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface LectureAttendanceDAO {
	
	// 게시글 수 
	public int selectTotalRecord(PagingVO<AttendanceVO> pagingVO);
	
	// 게시글  리스트 조회
	public List<AttendanceVO> selectAttendanceList(PagingVO<AttendanceVO> pagingVO);
	
	
	//이 강의의 수강생 수 
	public int selectCountStudent(String classNo);  
	
	
	public List<AttendanceVO> selectClassAtt(AttendanceVO Info);
}
