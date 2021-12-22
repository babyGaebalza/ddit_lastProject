package kr.or.ddit.administration.univ_aca.pay.service;

import java.util.List;

import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PayVO;
import kr.or.ddit.vo.PushVO;

public interface payTableService {


	//급여 테이블 조회
	public List<PayTableVO> selectPayTableList(PagingVO pagingVO);
	
	//급여 테이블 상세 조회
	public PayTableVO selectPayTableDetail(String memId);
	
	
	//계좌정보 조회
	public AccountVO selectAccount(String memId);
	
	
	//급여 테이블 상세 리스트 조회
	public List<PayTableVO> selectPayTableDetailList(PagingVO pagingVO);
	
	//급여 정보 입력 처리
	public int insertPayTable(PayTableVO payTable);

	
	//계좌정보 입력처리
	public int createAccount(AccountVO account);
	
	//계좌정보 수정처리
	public int modifyAccount(AccountVO account);
	
	
	
	
	//급여 변동내역 푸시알림 처리
	public int insertPush(PushVO push);
	
	
	
	
	
	//급여 내역 조회
	public List<PayVO> selectPayList(String memNo);
	
	//급여 내역 상세 조회
	public PayVO selectPayListDetail(String memNo);
	
	

	
}
