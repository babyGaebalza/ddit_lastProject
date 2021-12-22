package kr.or.ddit.academic.student.lecture.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.ClassListVO;

public interface StudentLecturePageService {
	
	
	/**
	 * 강의페이지리스에 필요한 정보를 조회하는 메서드(수강중인 강의, 학기)
	 * @param memId
	 * @return
	 */
	public Map<String, Object> retrieveLecturePageListInfo(String memId);
	/**
	 * 강의페이지(학생)에 필요한 정보를 조회하는 메서드
	 * @return
	 */
	public Map<String, Object> retrieveStudentLecturePage(Map<String, Object> studentLecturePageMap);
	
	
	/**
	 * 학생의 수강신청 정보를 가져오는 메서드
	 * @param att
	 * @return
	 */
	public ClassListVO retrieveStudentClassList(AttendanceVO att);
	
	/**
	 * 화상채팅에 접속한 학생의 해당강의 출석체크하는 메서드
	 * @param att
	 * @return
	 */
	public ServiceResult createStudentAttend(AttendanceVO att);
	
	
	/**
	 * 학생 개인의 특정강의에 관한 출석기록 리스트를 가져오는 메서드
	 * @param att
	 * @return
	 */
	public List<AttendanceVO> retrieveMyAttend(AttendanceVO att);
}
