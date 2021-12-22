package kr.or.ddit.academic.assistant.studentmanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface StudentManageDAO {
	//학과 학생 총 수
	public int selectTotalCount(PagingVO<MemberVO> pagingVO);
	
	//학과 학생 리스트
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);
	
	//학생 상세페이지
	public MemberVO selectMember(String memId);
	
	//학생 정보 수정
	public int updateMember(MemberVO member);
	
	//지도교수 리스트
	public List<MemberVO> selectAdviserList(MemberVO member);
}
