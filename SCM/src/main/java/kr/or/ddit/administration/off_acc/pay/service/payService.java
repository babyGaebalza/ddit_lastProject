package kr.or.ddit.administration.off_acc.pay.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PayVO;

public interface payService {

	//급여 테이블 조회
	public List<PayTableVO> selectPayTableList(PagingVO pagingVO);

	//급여 일괄 입금신청처리
	public Map<String, Integer> insertPay(List<String> memList, MemberVO authMember);
	
	//입금신청조회
	public List<PayVO> selectinputPayList(PagingVO pagingVO);
	
	
	//급여 일괄 최종입금
	public Map<String, Integer> insertFinalPay(List<String> payNo);
	
	//급여 신청 반려(취소)
	public Map<String, Integer> cancleFinalPay(List<String> payNo, String reason);
	
	//급여 일괄 입금신청처리
	public Map<String, Integer> reinsertPay(List<String> memList, MemberVO authMember);
}
