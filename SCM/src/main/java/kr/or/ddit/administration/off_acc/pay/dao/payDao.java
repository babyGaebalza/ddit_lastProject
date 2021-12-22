package kr.or.ddit.administration.off_acc.pay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PayVO;

@Repository
public interface payDao {

	//급여테이블 레코드수
	public int selectTotalRecord(PagingVO pagingVO);
	
	//급여 테이블 조회
	public List<PayTableVO> selectPayTableList(PagingVO pagingVO);
	
	//입금신청조회
	public List<PayVO> selectinputPayList(PagingVO pagingVO);
	
	//입금신청조회 레코드수
	public int countSelectinputPayList(PagingVO pagingVO);
	
	
	//해당인원의 임금이 설정되어있는지 여부 파악
	public int checkPaytable(String memId);
		
	//해당인원의 계좌 정보가 설정되어있는지 여부 파악
	public int checkAccount(String memId);

	//해당인원의 임금정보 가져오기
	public PayTableVO getPayTable(String memId);
	
	//해당인원의 계좌정보 가져오기
	public AccountVO getAccount(String memId);
	
	
	//급여 일괄 입금신청처리
	public int insertPay(PayVO account);
	
	//급여 일괄 입금처리
	public int insertFinalPay(String pay);
	
	
	//급여입금 알림처리
	public int pushPay(String pay);
	
	//급여신청 취소(반려)처리
	public int canclePay(PayVO voPay);
	
	//반려신청 삭제
	public int deletePay(PayVO account);
	
	//재신청
	public int reinsertPay(PayVO account);
	
}
