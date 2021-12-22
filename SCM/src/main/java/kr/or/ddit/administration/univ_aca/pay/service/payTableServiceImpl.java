package kr.or.ddit.administration.univ_aca.pay.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_aca.pay.dao.payTableDao;
import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PayVO;
import kr.or.ddit.vo.PushVO;

@Service
public class payTableServiceImpl implements payTableService {

	@Inject
	private payTableDao dao;
	
	@Override
	public List<PayTableVO> selectPayTableList(PagingVO pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		return dao.selectPayTableList(pagingVO);
	}


	@Override
	public PayTableVO selectPayTableDetail(String memId) {
		return dao.selectPayTableDetail(memId);
	}
	
	

	@Override
	public AccountVO selectAccount(String memId) {
		return dao.selectAccountDetail(memId);
	}



	
	
	
	
	@Override
	public List<PayTableVO> selectPayTableDetailList(PagingVO pagingVO) {
		int totalRecord = dao.selectTotalTableDetailList(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		return dao.selectPayTableDetailList(pagingVO);
	}

	
	@Override
	public int insertPayTable(PayTableVO payTable) {
		return dao.insertPayTable(payTable);
	}

	
	@Override
	public int createAccount(AccountVO account) {
		return dao.insertAccount(account);
	}


	@Override
	public int modifyAccount(AccountVO account) {
		return dao.updateAccount(account);
	}

	
	
	
	
	
	
	

	@Override
	public int insertPush(PushVO push) {
		String who = push.getPushMem();
		List<MemberVO>whoList = dao.selectWho(who);
		
		push.setWhoList(whoList);
		
		int res = dao.inputPush(push);
		return res;
	}
	

	@Override
	public List<PayVO> selectPayList(String memNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayVO selectPayListDetail(String memNo) {
		// TODO Auto-generated method stub
		return null;
	}












}
