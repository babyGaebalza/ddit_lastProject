package kr.or.ddit.common.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import kr.or.ddit.common.member.service.MemberService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes(names="savePage")
public class MemberController {

	@Inject
	MemberService service;

	@Inject
	private PasswordEncoder encoder;
	
	//사용자 등록
	@RequestMapping("/member/memberform.do")
	private String memberInsertFormSelect() {

		return "administration/univ_aca/member/MemberFormSelect";
	}
	
	@RequestMapping(value="/member/memberform.do", method=RequestMethod.POST)
	private String memberInsertForm(
			Model model,
			@RequestParam("who") String who
			) {
		log.info(who);
		String u1 = "행정직원등록";
		String u2 = "교수등록";
		String u3 = "조교등록";
		String u4 = "학생등록";
		String user = null;
		
		if(who.equals(u1)) {
			user = "u1";
			List<CategoryVO> rank = service.retrieveRankCode();
			List<CategoryVO> dept = service.retrieveDeptCode();
			
			model.addAttribute("ranks", rank);
			model.addAttribute("depts", dept);
		}
		else if (who.equals(u2)) {
			user = "u2";
			List<CategoryVO> rank = service.retrieveRankCode();
			List<MajorVO> major = service.retrieveMajorCode();
			
			model.addAttribute("ranks", rank);
			model.addAttribute("major", major);
		}
		else if (who.equals(u3)) {
			user = "u3";
			List<CategoryVO> rank = service.retrieveRankCode();
			List<MajorVO> major = service.retrieveMajorCode();
			
			model.addAttribute("ranks", rank);
			model.addAttribute("major", major);
		}
		else {
			user = "u4";
			List<CategoryVO> admission = service.retrieveAdmissionCode();
			List<MajorVO> major = service.retrieveMajorCode();
			
			model.addAttribute("admissions", admission);
			model.addAttribute("major", major);
		}
		
		String order = "insert";

		model.addAttribute("user", user);
		model.addAttribute("order", order);
		return "administration/univ_aca/member/MemberForm";
	}

	
	
	@RequestMapping(value="/member/memberInsert.do", method=RequestMethod.POST)
	public String memberInsertProcess(
			@RequestParam("memPass") String password,
			@Validated(InsertGroup.class) @ModelAttribute("member") MemberVO member
			,BindingResult errors
			,@RequestParam("user") String user
			,Model model
			,RedirectAttributes redirect
			,HttpSession session
			) throws IOException {
		
		String userCode = member.getUserCode();
		String who = null;
		if(userCode.equals("US01")) {
			who = "행정직원등록";
		};
		if(userCode.equals("US02")) {
			who = "교수등록";
		};
		if(userCode.equals("US03")) {
			who = "조교등록";
		};
		if(userCode.equals("US04")) {
			who = "학생등록";
		};
		
		StringBuffer buffer = new StringBuffer();
		String viewName = null;
		String message = null;
		log.info("등록");
		log.info("비밀번호 : {}", password);
		member.setMemPass(encoder.encode(member.getMemPass()));
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.createMember(member);
			String memId = member.getMemId();
			log.info("꺼낸ID : {}", memId);
			switch(result) {
			case OK:
				viewName ="redirect:/member/memberform.do";
				message= "등록된 학번/사번 : "+memId;
				session.setAttribute("message", message);
				break;
			default :
				viewName = "redirect:/member/memberform.do";
				message = "오류! 잠시 후 시도해주세요.";
			}
		}else {
			int cnt = 0;
			
			for(ObjectError error : errors.getAllErrors()) {
				log.info("{}번째 에러 메시지 : {}", cnt++, error.getDefaultMessage());
				buffer.append(error.getDefaultMessage() + "<br>");
			}
			model.addAttribute("user", user);
			viewName = "redirect:/member/memberform.do";
		}
		
		model.addAttribute("who", who);
		model.addAttribute("message", message);
		model.addAttribute("message", buffer.toString());
		return viewName;
	}

	
	
	@RequestMapping(value="/member/modifyForm.do", method=RequestMethod.POST)
	public String memberModifyForm(
			@RequestParam("who") String MemberId
			,@RequestParam(value="page", required=false , defaultValue="1" ) int page
			,@RequestParam(value="searchType2", required=false ) String searchType2
			,@RequestParam(value="searchWord2", required=false ) String searchWord2
			,Model model
			) {
		String order = "update";
		log.info("모디폼누구 : {}", MemberId);
		
		MemberVO member = service.retrieveDetailMember(MemberId);
		model.addAttribute("member", member);
		
		
		List<CategoryVO> rank = service.retrieveRankCode();
		List<CategoryVO> dept = service.retrieveDeptCode();
		
		model.addAttribute("ranks", rank);
		model.addAttribute("depts", dept);
		
		
		List<CategoryVO> admission = service.retrieveAdmissionCode();
		List<MajorVO> major = service.retrieveMajorCode();
		
		model.addAttribute("admissions", admission);
		model.addAttribute("major", major);

		model.addAttribute("user", MemberId);
		model.addAttribute("order", order);
		model.addAttribute("page2", page);
		model.addAttribute("searchType2", searchType2);
		model.addAttribute("searchWord2", searchWord2);
		return "administration/univ_aca/member/MemberModifyForm";
	}



	@RequestMapping("/member/memberView.do")
	public String memberView(
			Model model,
			@RequestParam(value="memId", required=false ) String memId
			,@RequestParam(value="page", required=false , defaultValue="1" ) int page
			,@RequestParam(value="searchType2", required=false ) String searchType2
			,@RequestParam(value="searchWord2", required=false ) String searchWord2
			) {
		
		log.info("상세 조회 들어옴");
		log.info("memId :{}", memId);
		if(model.asMap().containsKey("memId")) {			
			memId = (String) model.asMap().get("memId");
		}
		
		Map<String, Object> B =  (Map<String, Object>) model.asMap().get("savePage");
		if(B != null) {
			
			if(B.containsKey("page2")) {
				page = (int) B.get("page2");
			}
			if(B.containsKey("searchType2")) {
				searchType2 = (String) B.get("searchType2");
			}
			if(B.containsKey("searchWord2")) {
				searchWord2 = (String) B.get("searchWord2");
			}
			
		}
		
		
		MemberVO member = service.retrieveDetailMember(memId);
		model.addAttribute("member", member);
		model.addAttribute("page", page);
		model.addAttribute("searchType2", searchType2);
		model.addAttribute("searchWord2", searchWord2);
		
		return "administration/univ_aca/member/MemberDetail";
	}	
	
	
	@RequestMapping("/member/memberModify.do")
	public String memberModifyProcess(
			@Validated(UpdateGroup.class) @ModelAttribute("member") MemberVO member
			,@RequestParam(value="page", required=false, defaultValue="1") int page
			,@RequestParam(value="searchType2", required=false) String searchType2
			,@RequestParam(value="searchWord2", required=false) String searchWord2
			,Model model
			,RedirectAttributes modiWho
			) {	
		
		log.info("수정입니다.");
		String endWhere = member.getMemId();
		modiWho.addFlashAttribute("memId", endWhere);
		
		Map<String , Object> reMap = new HashMap<>();
		reMap.put("page2", page);
		reMap.put("searchType2", searchType2);
		reMap.put("searchWord2", searchWord2);

		model.addAttribute("savePage", reMap);
		
		ServiceResult result = service.modifyMember(member);
		
		return "redirect:/member/memberView.do";
	}
	
	//멤버 삭제
	@RequestMapping("/member/memberRemove.do")
	public String memberRemove(			
			@RequestParam("who") String MemberId
			,@RequestParam(value="page", required=false, defaultValue="1") int page
			,@RequestParam(value="searchType2", required=false) String searchType2
			,@RequestParam(value="searchWord2", required=false) String searchWord2
			,Model model
			) {
		
		log.info("삭제입니다.");
		log.info("page : {}", page);
		log.info("searchType2 : {}", searchType2);
		log.info("searchWord2 : {}", searchWord2);
		
		ServiceResult result = service.removeMember(MemberId);
		
		Map<String , Object> reMap = new HashMap<>();
		reMap.put("page2", page);
		reMap.put("searchType2", searchType2);
		reMap.put("searchWord2", searchWord2);

		model.addAttribute("savePage", reMap);
		
		return "redirect:/member/memberList.do";
	}
	
	//멤버 조회
	@RequestMapping("/member/memberList.do")
	public String memberList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, @ModelAttribute("searchVO") SearchVO searchVO
			,HttpServletRequest req
			, Model model
			, SessionStatus pagingStatus
			) {
		
/*		Object pagetest =  RequestContextUtils.getInputFlashMap(req).get("page2");
		Object pagetest2 =  RequestContextUtils.getInputFlashMap(req).get("page2");*/
		log.info("page : {}", currentPage);

		Map<String, Object> A =  model.asMap();
		Map<String, Object> B =  (Map<String, Object>) model.asMap().get("savePage");
		if(B != null) {
			
			if(B.containsKey("page2")) {
				if(B.get("page2") != null && B.get("page2") != "") {
					currentPage = (int) B.get("page2");					
				}
			}
			if(B.containsKey("searchType2")) {
				searchVO.setSearchType((String) B.get("searchType2"));
			}
			if(B.containsKey("searchWord2")) {
				searchVO.setSearchWord((String) B.get("searchWord2"));
			}
			
		}
			
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		
		service.retrieveMemberList(pagingVO);
		
		model.addAttribute("pagingVO", pagingVO);
				
		pagingStatus.setComplete();
		return "administration/univ_aca/member/MemberList";
		/*return "redirect:/member/memberList.do";*/
	}
	
	@RequestMapping(value="/common/member/stuMyPage.do")
	public String studentMyPage(
		  @AuthenticationPrincipal(expression="authMember") MemberVO member
		, Model model
		, Authentication authentication 
	) {
		
		MemberVO authMember = ((MemberVOWrapper) authentication.getPrincipal()).getAuthMember();
		
		log.debug("authMember : {} ",authMember);
		
		MemberVO student = service.retrieveStudentMyPage(member);
		
		model.addAttribute("student", student);
		
		return "academic/studentMain/main/stuMyPage";
	}
	
	@RequestMapping(value="/common/member/proMyPage.do")
	public String professorMyPage(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			, Model model
			) {
		MemberVO professor = service.retrieveProfessorMyPage(member);
		
		model.addAttribute("professor", professor);
		
		return "common/member/proMyPage";
	}
    
	
	@RequestMapping(value="/common/member/assiMyPage.do")
	public String assistantMyPage(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			, Model model
			) {
		MemberVO assistant = service.retrieveAssistantMyPage(member);
		
		model.addAttribute("assistant", assistant);
		
		return "common/member/assiMyPage";
	}
	
	
	@RequestMapping(value="/common/member/manageMyPage.do")
	public String manageMyPage(
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			, Model model
			) {
		MemberVO manage = service.retrieveManagerMyPage(member);
		
		model.addAttribute("manage", manage);
		
		return "common/member/manageMyPage";
	}
	
	
	@RequestMapping("/common/member/stuMyPageUpdate.do")
	public String stuUpdateForm(
		@ModelAttribute("member") MemberVO member
		, Model model
	) {
		MemberVO student = service.retrieveStudentMyPage(member);
		model.addAttribute("student", student);
		
		return "academic/studentMain/main/stuUpdateForm";
	}
	
	@RequestMapping(value="/common/member/stuMyPageUpdate.do", method=RequestMethod.POST)
	public String stuUpdate(
		  @ModelAttribute("member") MemberVO member
		, Errors errors
		, Model model
		, HttpServletRequest req
		, HttpSession session
		,Authentication authentication 
	) {
		MemberVO authMember = ((MemberVOWrapper) authentication.getPrincipal()).getAuthMember();
		
		log.debug("authMember : {} ",authMember);
		
		member.setMemId(authMember.getMemId());
		
		String viewName = null;
		String message = null;
		
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyMyPage(member);
			switch(result) {
				case OK :
					viewName = "redirect:/common/member/stuMyPage.do?memId="+member.getMemId();
					break;
				default :
					viewName = "academic/studentMain/main/stuMyPage";
			}
		} else {
			viewName = "academic/studentMain/main/stuMyPage";
		}
		model.addAttribute("message", message);
		
		return viewName;
	}
	
//	@RequestMapping("/common/member/proMyPageUpdate.do")
//	public String proUpdateForm(
//			@RequestParam("member") MemberVO member
//			, Model model
//			) {
//		MemberVO professor = service.retrieveProfessorMyPage(member);
//		
//		model.addAttribute("professor", professor);
//		
//		return "common/member/proUpdateForm";
//	}
//	
//	@RequestMapping(value="/common/member/proMyPageUpdate.do", method=RequestMethod.POST)
//	public String proUpdate(
//		  @ModelAttribute("member") MemberVO member
//		, Errors errors
//		, Model model
//		, HttpServletRequest req
//		, HttpSession session
//		,Authentication authentication 
//	) {
//		MemberVO authMember = ((MemberVOWrapper) authentication.getPrincipal()).getAuthMember();
//		
//		log.debug("authMember : {} ",authMember);
//		log.debug("authMember : {} ",authMember);
//		log.debug("authMember : {} ",authMember);
//		
//		member.setMemId(authMember.getMemId());
//		
//		String viewName = null;
//		String message = null;
//		
//		if(!errors.hasErrors()) {
//			ServiceResult result = service.modifyMyPage(member);
//			switch(result) {
//				case OK :
//					viewName = "redirect:/common/member/proMyPage.do?memId="+member.getMemId();
//					break;
//				default :
//					viewName = "common/member/proMyPageUpdate";
//			}
//		} else {
//			viewName = "common/member/proMyPageUpdate";
//		}
//		model.addAttribute("message", message);
//		
//		return viewName;
//	}
//	
//	@RequestMapping("common/member/manageMyPageUpdate.do")
//	public String manageUpdateForm(
//			@RequestParam("member") MemberVO member
//			, Model model
//			) {
//		MemberVO manage = service.retrieveManagerMyPage(member);
//		model.addAttribute("manage", manage);
//		
//		return "common/member/manageUpdateForm";
//	}
	
}       