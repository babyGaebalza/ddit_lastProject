package kr.or.ddit.academic.assistant.studentmanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface StudentManageService {
	//학과 학생 리스트
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO);
	
	//학생 상세페이지
	public MemberVO retrieveMember(String memId);
	
	//학생 정보 수정
	public ServiceResult modifyMember(MemberVO member);
	
}
