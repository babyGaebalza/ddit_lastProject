package kr.or.ddit.common.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface MemberDao {
	
	//총 회원수
	public int selectTotalCount(PagingVO<MemberVO> pagingVO);
	
	//회원 추가
	public int insertMember(MemberVO member);
	
	//회원 수정
	public int updateMember(MemberVO member);
	
	//회원 삭제
	public int deleteMember(String memNo);
	
	//회원 전체조회
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);
	
	//회원 상세조회
	public MemberVO selectMember(@Param("member") MemberVO member);
	
	//직급 가져오기
	public List<CategoryVO> selectRankCode();
	
	//부서코드 가져오기
	public List<CategoryVO> selectDeptCode();
	
	//전공 가져오기
	public List<MajorVO> selectMajorCode();
	
	//입학전형 가져오기
	public List<CategoryVO> selectAdmissionCode();
	
	// 로그인 성공/실패시 실패횟수 처리
	public int updateLoginfailcnt(Map<String , Object> loginMap);
	
	// 로그인 성공 && (교수 || 학생) 인 경우 강의페이지 접근권한 가져오기
	public List<String> selectLecturePageAuthorityList(Map<String, Object> mapForLectureAuth);
	
	//회원 상세정보
	public MemberVO selectDetailMember(String memId);
	
	//비밀번호 초기화
	public int updateResetPassword(MemberVO member);
	
	//계정잠금 해제
	public int updateUnlockAccount(MemberVO member);
	
	//학생 마이 페이지
	public MemberVO selectStudentMyPage(MemberVO member);
	
	//교수 마이 페이지
	public MemberVO selectProfessorMyPage(MemberVO member);
	
	//조교 마이 페이지
	public MemberVO selectAssistantMyPage(MemberVO member);
	
	//행정직원 마이 페이지
	public MemberVO selectManagerMyPage(MemberVO member);
	
	//마이페이지 수정
	public int updateMyPage(MemberVO member);
	
}
