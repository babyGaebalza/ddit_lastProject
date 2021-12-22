package kr.or.ddit.administration.univ_aca.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PayVO;
import kr.or.ddit.vo.PushVO;

@Repository
public interface payTableDao {

	//급여테이블 레코드수
	public int selectTotalRecord(PagingVO pagingVO);
	
	//급여 테이블 조회
	public List<PayTableVO> selectPayTableList(PagingVO pagingVO);
	
	
	
	
	//급여 테이블 상세조회
	public PayTableVO selectPayTableDetail(String memId);
	
	//계좌정보 상세조회
	public AccountVO selectAccountDetail(String memId);
	
	
	
	
	
	
	//급여 테이블상세리스트  레코드 수
	public int selectTotalTableDetailList(PagingVO pagingVO);
	
	//급여 테이블상세리스트 조회
	public List<PayTableVO> selectPayTableDetailList(PagingVO pagingVO);
	
		
	//급여 정보 입력 처리
	public int insertPayTable(PayTableVO payTable);
	

	//계좌정보 입력처리
	public int insertAccount(AccountVO account);
	
	//계좌정보 수정처리
	public int updateAccount(AccountVO account);
	
	
	
	
	
	
	//급여 등록/수정시 알림처리
	public int inputPush(PushVO push);
	
	//푸시알림 대상 선정
	public List<MemberVO> selectWho(String who);
	
	
	
	//급여 수불 내역 조회
	public List<PayVO> selectPayList(String memNo);
	
	//급여 수불 내역 상세 조회
	public PayVO selectPayDetail(String memNo);
	
	

	
}
