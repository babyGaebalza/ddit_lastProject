package kr.or.ddit.academic.student.mainpage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.academic.vo.StuTimeTableVO;
import kr.or.ddit.vo.ClassListVO;

@Repository
public interface StudentMainDAO {
	
	// 시간표 조회
	public List<StuTimeTableVO> selectTimeTable(String memId);
	
	// 학생 성적정보 조회
	public List<ClassListVO> selectStudentClassList(String memId);
}
