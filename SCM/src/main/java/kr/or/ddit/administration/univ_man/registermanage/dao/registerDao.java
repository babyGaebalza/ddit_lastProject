package kr.or.ddit.administration.univ_man.registermanage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.administration.vo.RegisterVO;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PushVO;

@Repository
public interface registerDao {
	
	//학적 전체 수
	public int selectRegCount(PagingVO pagingVO);
	
	//학적 전체 조회
	public List<MemberVO> selectRegisterList(PagingVO<MemberVO> pagingVO);
	
	//특정학생 학적 조회
	public List<MemberVO> selectStuRegisterList(String memId);
	
	//학적 등록
	public int insertRegister(RegisterVO register);
	
	
	
	//반려 알림처리
	public int insertPush(PushVO push);
	
	//학적 전체 가져오기
	public List<CategoryVO> selectRegCode();
	
	//이전 학적 상태 변경 (N처리)
	public int updateRegState(RegisterVO register);
	
	
	public int updateMemReg (MemberVO member);
}
