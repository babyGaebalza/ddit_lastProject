package kr.or.ddit.academic.common.lecturePage.qrCheck.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;

@Repository
public interface QRDao {

	//학생이 해당강의를 수강하는 학생이 맞는지 확인하는 부분
	public int checkStudent(Map<String, String> classMember);
	
	//출석처리
	public int attendStudent(Map<String, String> classMember);
	
	//강의정보 가져오기
	public ClassVO classInfo(String classNo);
	
	//수강신청정보 가져오기
	public ClassListVO classListInfo(Map<String, String> classAndmember);
}
