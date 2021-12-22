package kr.or.ddit.administration.off_acc.pay.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.administration.off_acc.pay.service.payService;
import kr.or.ddit.common.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PayTableVO;
import kr.or.ddit.vo.PayVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class payController {

	@Inject
	private payService payService;
	
	@Inject
	private MemberService memberService;
		
	//멤버 ID로 멤버정보(이름) 찾는 메서드
		public MemberVO changeMember(String memId) {
			
			return memberService.retrieveDetailMember(memId);
		};
		
		
		//급여 테이블 조회(재무과)
		@RequestMapping("pay/payTableList.do")
		public String retrievepayList(
				@RequestParam(value="page", required=false, defaultValue="1") int currentPage
				,@ModelAttribute("searchVO") SearchVO searchVO
				,Model model
				) {
			PagingVO<PayTableVO> pagingVO = new PagingVO<>();
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSearchVO(searchVO);
			
			List<PayTableVO> list = payService.selectPayTableList(pagingVO);
			pagingVO.setDataList(list);
			
			
			
			model.addAttribute("pagingVO", pagingVO);
			
			return "administration/off_acc/pay/payList";
		};
		
		
		//급여 테이블 조회(교무과)
		@RequestMapping("pay2/payTableList.do")
		public String retrievepayListACA(
				@RequestParam(value="page", required=false, defaultValue="1") int currentPage
				,@ModelAttribute("searchVO") SearchVO searchVO
				,Model model
				) {
			PagingVO<PayTableVO> pagingVO = new PagingVO<>();
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSearchVO(searchVO);
			
			List<PayTableVO> list = payService.selectPayTableList(pagingVO);
			pagingVO.setDataList(list);
			
			
			
			model.addAttribute("pagingVO", pagingVO);
			
			return "administration/univ_aca/pay/payList";
		};
			
		
		
		//선택한 사람들 급여 입금신청 처리
		@RequestMapping(value="pay/payInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Integer> createPay(
				@RequestParam(value="checkArr[]") List<String> memList
				,@AuthenticationPrincipal(expression="authMember") MemberVO authMember
				) {
			log.info("입금신청들어옴");
			
			Map<String, Integer> resultMap = payService.insertPay(memList, authMember);
			
			return resultMap;
		};
		
		
		
		//입금신청조회리스트
		@RequestMapping("pay/inputPayList.do")
		public String inputPayList(
				@RequestParam(value="page", required=false, defaultValue="1") int currentPage
				,@ModelAttribute("searchVO") SearchVO searchVO
				,Model model
				) {
			
			PagingVO<PayVO> pagingVO = new PagingVO<>();
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSearchVO(searchVO);
			
			List<PayVO> list = payService.selectinputPayList(pagingVO);
			pagingVO.setDataList(list);
			
			
			
			model.addAttribute("pagingVO", pagingVO);
			
			return "administration/off_acc/pay/inputPayList";
		}
		
		//최종입금처리
		@RequestMapping(value="pay/finalPay.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Integer> finalPay(
				@RequestParam("checkArr[]") List<String> payNo
				,Model model
				) {
			log.info("최종입금 들어옴");

			Map<String, Integer> resultMap = payService.insertFinalPay(payNo);
			return resultMap;
		}
		
		//반려처리
		@RequestMapping(value="pay/canclePay.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Integer> canclePay(
				@RequestParam("checkArr[]") List<String> payNo
				,@RequestParam("reason") String reason
				,Model model
				) {
			log.info("반려처리 들어옴");

			Map<String, Integer> resultMap = payService.cancleFinalPay(payNo, reason);
			return resultMap;
		}
		

		@RequestMapping(value="/pay/payReInsert.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
		@ResponseBody
		public Map<String, Integer> createRePay(
				@RequestParam(value="checkArr[]") List<String> memList
	/*			, @AuthenticationPrincipal(expression="authMember") MemberVO authMember*/
				) {
			log.info("입금재신청들어옴");
			MemberVO authMember = new MemberVO();
			String memId = "D012105";	//	임시
			authMember.setMemId(memId);
			
			Map<String, Integer> resultMap = payService.reinsertPay(memList, authMember);
			
			return resultMap;
		};
		
		
		
		
}
