package kr.or.ddit.academic.common.lecturePage.qrCheck.service;

import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;

public interface QRService {

	//학생이 해당강의를 수강하는 학생이 맞는지 확인하는 부분
	public ServiceResult checkStuService(Map<String, String> classMember);
	
	
	//출석처리
	public ServiceResult attendStudent(Map<String, String> classMember);
	
	//강의정보 가져오기
	public ClassVO getClassInfo(String classNo);
	
	//수강신청정보 가져오기
	public ClassListVO getClassListInfo(Map<String, String> classAndmember);
}
