package kr.or.ddit.common.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface MemberService {

	//회원 추가
	public ServiceResult createMember(MemberVO member);
	
	//회원 수정
	public ServiceResult modifyMember(MemberVO member);
	
	//회원 삭제
	public ServiceResult removeMember(String memId);
	
	//회원 전체조회
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO);
	
	//회원 상세조회
	public ServiceResult retrieveMember(MemberVO member);
	
	//직급 가져오기
	public List<CategoryVO> retrieveRankCode();
	
	//부서코드 가져오기
	public List<CategoryVO> retrieveDeptCode();
	
	//전공 가져오기
	public List<MajorVO> retrieveMajorCode();
	
	//입학전형 가져오기
	public List<CategoryVO> retrieveAdmissionCode();
	
	//회원 상세정보
	public MemberVO retrieveDetailMember(String memId);
	
	//학생 마이 페이지
	public MemberVO retrieveStudentMyPage(MemberVO member);
	
	//교수 마이 페이지
	public MemberVO retrieveProfessorMyPage(MemberVO member);
	
	//조교 마이 페이지
	public MemberVO retrieveAssistantMyPage(MemberVO member);
	
	//행정직원 마이 페이지
	public MemberVO retrieveManagerMyPage(MemberVO member);
	
	//마이페이지 수정
	public ServiceResult modifyMyPage(MemberVO member);
	
	
	/**
	 * 비밀번호 초기화
	 * @param member
	 * @return
	 */
	public ServiceResult modifyResetPassword(MemberVO member); 
	
	/**
	 * 계정잠금해제
	 * @param member
	 * @return
	 */
	public ServiceResult modifyUnlockAccount(MemberVO member);
	/**
	 * 로그인 성공시 처리(로그인 실패 횟수 초기화)/(학생/교수일 경우 진행중인강의에 대한 정보를 강의페이지 접근인가에 사용하기 위해 memRoles에 더해주는 역할)
	 * @param authMember
	 */
}
