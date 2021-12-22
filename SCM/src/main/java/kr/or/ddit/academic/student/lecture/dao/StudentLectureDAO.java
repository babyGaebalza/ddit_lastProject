package kr.or.ddit.academic.student.lecture.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.academic.vo.StuTimeTableVO;
import kr.or.ddit.vo.ClassVO;

@Repository
public interface StudentLectureDAO {
	
	// 현재 학기 모든 강의 리스트
	public List<ClassVO> selectSemesterClassList(String semester);
	// 강의 검색
	public List<ClassVO> selectClassList(@Param("searchOption") ClassVO searchOption);
	// 바구니 중복 체크
	public int selectCartInCheck(Map<String, Object> cartRegisterMap);
	// 바구니 강의 갯수
	public int selectCountCart(String memId);
	// 바구니 조회
	public List<ClassVO> selectCartList(String memId);
	// 바구니 담기
	public int insertCartRegister(Map<String, Object> cartRegisterMap);
	// 담기 취소
	public int deleteCartRegister(Map<String, Object> cartRegisterMap);
	
	// 학생(로그인 기반)의 현재학기 수강신청 리스트 조회
	public List<ClassVO> selectRegisterClassList(Map<String, Object> searchMap);
 	// 학생(로그인 기반)의 현재학기 시간표 리스트 조회
	public List<StuTimeTableVO> selectSemesterTimeTableList(Map<String, Object> searchMap); 
	
	// 수강신청 여부 확인
	public int selectStudentRegisterCheck(Map<String, Object> classRegisterMap);
	// 현재 학기 수강신청한 총 학점
	public int selectStudentAllPoint(Map<String, Object> classRegisterMap);
	// 현재 학생시간표 기준으로 신청할 강의와 비교해 겹치지 않는 요일의 수를 검색
	public int selectNotDuplicateWeekday(Map<String, Object> classRegisterMap);
	// 재수강 여부 체크
	public String selectRetakeClassNo(Map<String, Object> classRegisterMap);
	// 재수강 강의 정보
	public ClassVO selectRetakeClassInfo(String retakeClassNo);
	
	// 수강신청 강의 수강현황리스트 입력
	public int insertClassRegister(Map<String, Object> classRegisterMap);
	// 수강신청 강의 시간표 변경
	public int updateStuTimeTableForNewRegister(Map<String, Object> classRegisterMap);
	// 강의정보리스트 수강인원현황 + 1 변경
	public int updateClassPersonPlus(String classNo);
	// 수강신청할 강의 현재 인원 조회
	public ClassVO selectClass(String classNo);
	
	// 수강신청 취소(신청강의 삭제)
	public int deleteClassRegister(Map<String, Object> classRegisterMap);
	// 수강신청 취소(학생시간표 변경)
	public int updateStuTimeTableForDelRegister(Map<String, Object> classRegisterMap);
	// 강의정보리스트 수강인원현황 - 1 변경
	public int updateClassPersonMinus(String classNo);
}
