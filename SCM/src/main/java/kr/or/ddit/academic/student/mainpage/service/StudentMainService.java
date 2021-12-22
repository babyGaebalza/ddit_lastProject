package kr.or.ddit.academic.student.mainpage.service;

import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface StudentMainService {
	// 메인 콘텐츠 조회(시간표)
	public Map<String, Object> retrieveMainContent(MemberVO authMember);
	
	// (성적-성적조회)
	public Map<String, Object> retrieveScoreMap(String memId);
	
}
