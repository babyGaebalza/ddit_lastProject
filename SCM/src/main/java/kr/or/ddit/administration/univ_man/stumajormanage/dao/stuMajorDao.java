package kr.or.ddit.administration.univ_man.stumajormanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public interface stuMajorDao {

	//학생의 전공 조회
	public MemberVO stuDetail(String memNo);
	
	//학생 전공 변경
	public int updateStuMajor(String memNo);
	
	//학생 전공 변경 알림
	public int insertStuMajorPush(String pushMem);
}
