package kr.or.ddit.academic.professor.lecture.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;

public interface LectureMainService {
	
	// 교수 강의메인 콘텐츠
	public Map<String, Object> retrieveProfessorLecturePage(Map<String, Object> professorLecturePageMap);
	
	//나의 강의리스트
	public List<ClassVO> retrieveMyLecture(ClassVO myClass ); 
	
	//전체수강생 수강리스트 정보
	public List<ClassListVO> retrieveClassList(String classNo);
	
	//수강생 출석처리
	public Map<String, Integer> stuAttend(List<ClassListVO> classList);

	//교수 전공과목 
	public String getMajorName(String memId);
	
}
