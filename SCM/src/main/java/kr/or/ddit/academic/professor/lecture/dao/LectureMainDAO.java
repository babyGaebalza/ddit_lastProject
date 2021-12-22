package kr.or.ddit.academic.professor.lecture.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;

@Repository
public interface LectureMainDAO {
	
	// 강의페이지 조회(과제 제출 현황)
	public List<BoardVO> selectOngoingTaskSubmit(Map<String, Object> professorLecturePageMap);
	
	//내 강의리스트 
	public List<ClassVO> selectMyClassList(ClassVO myClass);

	//강의 한 건 정보 
	public ClassVO selectOneLecture(String classNo); 
	
	//전체수강생 수강리스트 정보
	public List<ClassListVO> selectClassList(String classNo);

	public int inputAttend(ClassListVO listMember);

	//전공이름
	public String selectProfMajorName(String memId);
}
