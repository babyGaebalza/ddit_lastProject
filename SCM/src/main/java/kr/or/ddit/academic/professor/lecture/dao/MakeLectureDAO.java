package kr.or.ddit.academic.professor.lecture.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface MakeLectureDAO {

	
	//
 	public List<Map<String, Object>> selectMajorList(String collegeCode);

 	//강의실
	public List<Map<String, Object>> selectFacList(String majorCode);

	//기존강의목록
	public List<Map<String, Object>> selectOldList(Map<String, String> classMap);

	//학과장 가져오기 
	public List<Map<String, Object>> selectMajorChef(String memMajor);
	
}
