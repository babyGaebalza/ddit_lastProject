package kr.or.ddit.academic.assistant.advisormanage.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface AdvisorManageDAO {
	//학과 학생 총 수
	public int selectTotalCount(PagingVO<MemberVO> pagingVO);
	
	//학과 학생 리스트 출력
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);
	
	//지도교수 총 수 
	public int selectTotalCountAdviser(PagingVO<MemberVO> pagingVO);
	
	//지도교수 리스트
	public List<MemberVO> selectAdviserList(PagingVO<MemberVO> pagingVO);
	
	//지도교수 상세페이지
	public MemberVO selectAdviser(String memId);
	
	//지도교수 정보수정
	public int updateAdviser(MemberVO member);
	
	//해당 지도교수 학생 수
	public int studentCount(MemberVO member);
}
