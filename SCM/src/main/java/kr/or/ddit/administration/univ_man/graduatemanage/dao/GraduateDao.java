package kr.or.ddit.administration.univ_man.graduatemanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface GraduateDao {
	
	public int selectTotalCount(PagingVO<MemberVO> pagingVO);
	
	public List<MemberVO> selectGraduateList(PagingVO<MemberVO> pagingVO);
	
	public MemberVO selectGraduate(String memId);
	
	public int updateGraduate(MemberVO member);
	
	public int deleteGraduate(MemberVO member);
	
	public int insertGraduate(MemberVO member);
	
	public List<MemberVO> selectGraduatedList(PagingVO<MemberVO> pagingVO); 
	
	public List<MemberVO> selectGraduatingList(PagingVO<MemberVO> pagingVO); 
	
}
