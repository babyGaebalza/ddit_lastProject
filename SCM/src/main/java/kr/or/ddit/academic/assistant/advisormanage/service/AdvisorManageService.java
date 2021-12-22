package kr.or.ddit.academic.assistant.advisormanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface AdvisorManageService {
	//학과 학생 리스트 출력
	public List<MemberVO> retreiveStudentList(PagingVO<MemberVO> pagingVO);
	
	//학과 교수 리스트 출력
	public List<MemberVO> retrieveAdviserList(PagingVO<MemberVO> pagingVO);
	
	//지도교수 상세페이지
	public MemberVO retrieveAdviser(String memId);
	
	//지도교수 정보 수정
	public ServiceResult modifyAdviser(MemberVO member);
	
}
