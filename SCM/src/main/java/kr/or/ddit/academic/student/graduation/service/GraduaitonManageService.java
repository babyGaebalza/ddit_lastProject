package kr.or.ddit.academic.student.graduation.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrackVO;

public interface GraduaitonManageService {
	
	// 졸업 페이지 조회
	public Map<String, Object> retrieveStudentGraduationPage(MemberVO authMember);
	
	// 졸업 신청
	public ServiceResult createGraduationRegister(MemberVO authMember);
	
	// 트랙 페이지 조회
	public Map<String, Object> retrieveStudentTrackPage(MemberVO authMember);
	
	// 트랙 상세및 조건만족 조회
	public Map<String, Object> retrieveTrackInfoAndSatisfied(Map<String, Object> trackDetailSearchMap);
	
	// 트랙신청(신규, 변경)
	public ServiceResult createOrModifyTrackRegister(Map<String, Object> trackDetailSearchMap);
}
