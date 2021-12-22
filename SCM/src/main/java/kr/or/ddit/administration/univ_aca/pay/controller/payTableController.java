package kr.or.ddit.administration.univ_aca.pay.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.administration.univ_aca.pay.service.payTableService;
import kr.or.ddit.common.member.service.MemberService;
import kr.or.ddit.vo.AccountVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PushVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class payTableController {

	@Inject
	private payTableService service;
	
	@Inject
	private MemberService memberService;
	
	//멤버 ID로 멤버정보(이름) 찾는 메서드
	public MemberVO changeMember(String memId) {
		
		return memberService.retrieveDetailMember(memId);
	};
	
	
	//급여 테이블 상세 조회
	@RequestMapping("pay/payTableDetail.do")
	public String retrievePayTableDetail(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			,@ModelAttribute("searchVO") SearchVO searchVO
			,@RequestParam("who") String memId
			,Model model
			) {
		PayTableVO tableDetail = service.selectPayTableDetail(memId);
		model.addAttribute("tableDetail", tableDetail);
		
		AccountVO accountDetail = service.selectAccount(memId);
		model.addAttribute("accountDetail", accountDetail);
		
		PagingVO<PayTableVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		searchVO.setSearchWord2(memId);
		pagingVO.setSearchVO(searchVO);
		List<PayTableVO> list = service.selectPayTableDetailList(pagingVO);

		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);

		MemberVO member = memberService.retrieveDetailMember(memId);
		model.addAttribute("member", member);
		
		
		return "administration/univ_aca/paytable/payTableDetail";
	}
	
	
	//급여 정보 입력	
	@RequestMapping("pay/payTableDetailInsert.do")
	public String insertPay(
		@ModelAttribute("insertForm") PayTableVO payTable
			) {
		log.info("1 : {}", payTable);
		int res = service.insertPayTable(payTable);
		if(res > 0) {
			String name = payTable.getPaytableMem();
			name = changeMember(name).getMemName();
			String reason = payTable.getPaytableReason();
			String pay = payTable.getPaytablePayDisplay();
			
			PushVO push = new PushVO();
			String pushTitle = name + "님의 임금 변동사항입니다.";
			String pushCont = name + "님의 임금이 " + reason + "을 근거로 하여" + pay +"로 변동되었습니다.";
			
			push.setPushMem(name);
			push.setPushTitle(pushTitle);
			push.setPushCont(pushCont);
			
			service.insertPush(push);
			
		}
		
		
		return "forward:/pay/payTableList.do";
	}
	
	
	
	//급여 내역 조회
	public String retrievepay(
			) {
		return null;
	}
	
	//급여 내역 상세 조회
	public String retrievepayDetail(
			) {
		return null;
	}
	
	//급여관리 폼
	public String payForm(
			) {
		return null;
	}
	

	
	//계좌정보 입력
	@RequestMapping(value="pay/AccountInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> insertAccount(
			@RequestParam("accountBank") String accountBank
			,@RequestParam("accountNumber") String accountNumber
			,@RequestParam("accountMem") String accountMem
			) {
		
		AccountVO account = new AccountVO();
		account.setAccountBank(accountBank);
		account.setAccountNumber(accountNumber);
		account.setAccountMem(accountMem);
		service.createAccount(account);
		
		String result="계좌입력 성공";
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	//계좌정보 수정
	@RequestMapping(value="pay/AccountModi.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> modiAccount(
			@RequestParam("accountBank") String accountBank
			,@RequestParam("accountNumber") String accountNumber
			,@RequestParam("accountMem") String accountMem
			) {
		
		AccountVO account = new AccountVO();
		account.setAccountBank(accountBank);
		account.setAccountNumber(accountNumber);
		account.setAccountMem(accountMem);
		service.modifyAccount(account);
		
		String result="계좌수정 성공";
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
}
