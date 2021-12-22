package kr.or.ddit.administration.univ_man.stumajormanage.service;

import kr.or.ddit.vo.MemberVO;

public interface stuMajorService {

	//학생의 전공 조회
	public MemberVO stuDetail(String memNo);
	
	//학생 전공 변경
	public int updateStuMajor(String memNo);
	
	//학생 전공 변경 알림
	public int insertStuMajorPush(String pushMem);
	
}
