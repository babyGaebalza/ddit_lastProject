package kr.or.ddit.administration.off_acc.pay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.off_acc.pay.dao.payDao;
import kr.or.ddit.administration.univ_aca.pay.dao.payTableDao;
import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PayVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class payServiceImpl implements payService {

	@Inject
	payDao dao;
	
	@Inject
	payTableDao payTabledao;

	@Override
	public List<PayTableVO> selectPayTableList(PagingVO pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		return dao.selectPayTableList(pagingVO);
	}
	
	@Override
	public List<PayVO> selectinputPayList(PagingVO pagingVO) {
		int totalRecord = dao.countSelectinputPayList(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		return dao.selectinputPayList(pagingVO);
	}
	
	
	@Override
	public Map<String, Integer> insertPay(List<String> memList, MemberVO authMember) {
		Map<String, Integer> resultMap = new HashMap<>();
		int ok = 0;	// 성공한 횟수
		int no = 0;	//	정보가 없는 횟수
		int fail = 0;	// 실패한 횟수
		
		for (String memId : memList) {
			int checkPaytable = dao.checkPaytable(memId);	//임금설정여부
			int checkAccount = dao.checkAccount(memId);	//계좌정보여부
			
			if(checkPaytable > 0 && checkAccount > 0) {	//	둘 다 있어야 임금처리가 가능
				log.info("둘다있음");
				try {
					//입금처리
					PayVO pay = new PayVO();
					
					//임금 받는사람
					pay.setPayMem(memId);
					log.info("임금받는사람 : {}", memId);
					
						//임금정보 가져오기
						PayTableVO paytable = payTabledao.selectPayTableDetail(memId);
							int Tpay = (paytable.getPaytablePay())/12;
					
					pay.setPayTpay(Tpay);
					log.info("임금 : {}", Tpay);
						
						//계좌정보 가져오기
						AccountVO account = dao.getAccount(memId);
							String payBank = account.getAccountBank();
							String payAccount = account.getAccountNumber();
						
					pay.setPayBank(payBank);
					log.info("은행 : {}", payBank);
					pay.setPayAccount(payAccount);
					log.info("계좌 : {}", payAccount);
					
						//세금계산하기
							//4대보험 10%
							int tax1 = (int) Math.round(Tpay * 0.1);
						pay.setPayInsurance(tax1);	
						
							//원천징수(4인기준 13,000원)
							int tax2 = 13000;
						pay.setPayTax(tax2);
							
							//최종임금
							int Fpay = Tpay - tax1 - tax2;
						pay.setPayFpay(Fpay);
						
						//수정자 정보 입력
						pay.setPayFwriter(authMember.getMemId());
					
					//입금!
					int res = dao.insertPay(pay);
					
					//반려가 있다면 반려 지워야함
					try {
						dao.deletePay(pay);
					} catch (Exception e) {
					}
					
					if(res > 0) {
						ok++;	//성공카운트
					}
						
				} catch (Exception e) {
					//입금처리실패
					fail++;	//실패 카운트
				}
			}else {
				// 정보없음
				no++;	//정보없음 카운트
			}

			
			
		}
		
		resultMap.put("ok", ok);
		resultMap.put("no", no);
		resultMap.put("fail", fail);
		
		return resultMap;
	}

	//임금 최종입금
	@Override
	public Map<String, Integer> insertFinalPay(List<String> payNo) {
		Map<String, Integer> resultMap = new HashMap<>();
		int ok = 0;	// 성공한 횟수
		int fail = 0;	// 실패한 횟수
		int no = 0; // 알림실패
		
		for (String pay : payNo) {
			try {				
				//입금처리
				int res = dao.insertFinalPay(pay);
				if(res > 0) {
					//알림처리
					int push = dao.pushPay(pay);
					if(push > 0) {						
						ok++;
					}
					else {
						no++;
					}
				}
			} catch (Exception e) {
				fail++;
			}
		}
		resultMap.put("ok", ok);
		resultMap.put("fail", fail);
		resultMap.put("no", no);
		
		return resultMap;

	}

	@Override
	public Map<String, Integer> cancleFinalPay(List<String> payNo, String reason) {
		
		
		Map<String, Integer> resultMap = new HashMap<>();
		int ok = 0;	// 성공한 횟수
		int fail = 0;	// 실패한 횟수
		
		for (String pay : payNo) {
			log.info("##:{}", pay);
			log.info("##:{}", reason);
			try {
				PayVO voPay = new PayVO();
				voPay.setPayNo(pay);
				voPay.setPayReason(reason);
				
				dao.canclePay(voPay);
				ok++;
				
			} catch (Exception e) {
				fail++;
			}
		}
		
		resultMap.put("ok", ok);
		resultMap.put("fail", fail);		
		return resultMap;
	}

		//재신청
		@Override
		public Map<String, Integer> reinsertPay(List<String> memList, MemberVO authMember) {
			Map<String, Integer> resultMap = new HashMap<>();
			int ok = 0;	// 성공한 횟수
			int no = 0;	//	정보가 없는 횟수
			int fail = 0;	// 실패한 횟수
			
			for (String memId : memList) {
				int checkPaytable = dao.checkPaytable(memId);	//임금설정여부
				int checkAccount = dao.checkAccount(memId);	//계좌정보여부
				
				if(checkPaytable > 0 && checkAccount > 0) {	//	둘 다 있어야 임금처리가 가능
					log.info("둘다있음");
					try {
						//입금처리
						PayVO pay = new PayVO();
						
						//임금 받는사람
						pay.setPayMem(memId);
						log.info("임금받는사람 : {}", memId);
						
							//임금정보 가져오기
							PayTableVO paytable = payTabledao.selectPayTableDetail(memId);
								int Tpay = (paytable.getPaytablePay())/12;
						
						pay.setPayTpay(Tpay);
						log.info("임금 : {}", Tpay);
							
							//계좌정보 가져오기
							AccountVO account = dao.getAccount(memId);
								String payBank = account.getAccountBank();
								String payAccount = account.getAccountNumber();
							
						pay.setPayBank(payBank);
						log.info("은행 : {}", payBank);
						pay.setPayAccount(payAccount);
						log.info("계좌 : {}", payAccount);
						
							//세금계산하기
								//4대보험 10%
								int tax1 = (int) Math.round(Tpay * 0.1);
							pay.setPayInsurance(tax1);	
							
								//원천징수(4인기준 13,000원)
								int tax2 = 13000;
							pay.setPayTax(tax2);
								
								//최종임금
								int Fpay = Tpay - tax1 - tax2;
							pay.setPayFpay(Fpay);
							
							//수정자 정보 입력
							pay.setPayFwriter(authMember.getMemId());
						
						//입금!
						int res = dao.insertPay(pay);
						int del = dao.deletePay(pay);
						
						if(res > 0 && del > 0) {
							ok++;	//성공카운트
						}
							
					} catch (Exception e) {
						//입금처리실패
						fail++;	//실패 카운트
					}
				}else {
					// 정보없음
					no++;	//정보없음 카운트
				}
	
				
				
			}
			
			resultMap.put("ok", ok);
			resultMap.put("no", no);
			resultMap.put("fail", fail);
			
			return resultMap;
		}
}
