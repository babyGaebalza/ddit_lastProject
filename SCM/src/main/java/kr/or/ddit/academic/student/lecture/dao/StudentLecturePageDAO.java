package kr.or.ddit.academic.student.lecture.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.academic.vo.StuTimeTableVO;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;

@Repository
public interface StudentLecturePageDAO {
	
	// 수강중인 강의 리스트를 조회(로그인 기반)
	public List<ClassListVO> selectClassListByAuth(String memId);
	
	// 강의정보 조회
	public ClassVO selectLectureInfo(Map<String, Object> studentLecturePageMap);
	// 차트데이터 조회(과제)
	public Map<String, Integer> selectLectureTaskInfo(Map<String, Object> studentLecturePageMap);
	// 강의페이지 조회(시간표)
	public List<StuTimeTableVO> selectLectureTimeTableList(Map<String, Object> studentLecturePageMap);
	// 강의페이지 조회(공지)
	public List<BoardVO> selectLectureNotice(Map<String, Object> studentLecturePageMap);
	// 강의페이지 조회(진행중인 과제)
	public List<BoardVO> selectOngoingTask(Map<String, Object> studentLecturePageMap);
	
	// 특정수강정보 조회
	public ClassListVO selectClassList(AttendanceVO att);
	
	// 화상채팅 접속 학생 출석처리부분
	public int inputStuAttend(AttendanceVO att);
	
	// 학생개인의 특정강의 출석기록 가져오는 부분
	public List<AttendanceVO> selectMyAttend(AttendanceVO att);
}
