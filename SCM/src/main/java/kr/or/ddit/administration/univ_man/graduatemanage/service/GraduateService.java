package kr.or.ddit.administration.univ_man.graduatemanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface GraduateService {
	
	public List<MemberVO> retrieveGraduateList(PagingVO<MemberVO> pagingVO);
	
	public MemberVO retrieveGraduate(String memId);
	
	public ServiceResult modifyGraduate(MemberVO member);
	
	public ServiceResult removeGraduate(MemberVO member);
	
	public ServiceResult createGraduate(MemberVO member);
}
