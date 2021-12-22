package kr.or.ddit.academic.student.graduation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrackVO;

@Repository
public interface GraduaitonManageDAO {
	
	// 졸업요건 학점조회(이수구분별)
	public List<Map<String, Object>> selectGraduatePointList(MemberVO authMember);
	// 졸업 신청 상태 조회
	public int selectGraduateRegister(MemberVO authMember);
	// 졸업 신청
	public int insertGraduationRegiser(MemberVO authMember);
	
	// 학과 트랙 조회
	public List<TrackVO> selectMajorTrackList(MemberVO authMember);
	// 트랙 신청 조회
	public DocumentVO selectTrackRegister(MemberVO authMember);
	// 트랙 상세및 조건만족 조회 
	public TrackVO selectTrackInfoAndSatisfied(Map<String, Object> trackDetailSearchMap);
	// 트랙 신청
	public int insertTrackRegister(Map<String, Object> trackDetailSearchMap);
	// 트랙 신청 변경
	public int updateTrackRegister(Map<String, Object> trackDetailSearchMap);
}
