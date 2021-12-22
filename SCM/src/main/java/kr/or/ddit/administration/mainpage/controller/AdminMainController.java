package kr.or.ddit.administration.mainpage.controller;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.academic.professor.mainPage.service.ProfessorMainService;
import kr.or.ddit.administration.mainpage.service.AdiminMainService;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminMainController {

	@Inject
	private  ProfessorMainService service;
	
	@Inject
	private AdiminMainService Adminservice;
	
	@RequestMapping("/admin/main.do")
	public String AdminMain(
			   @AuthenticationPrincipal(expression="authMember") MemberVO authMember
				, Model model
			) {
		String dept = authMember.getDeptCode();
		log.info("부서코드 : {}", dept);
		String viewName = null;
		switch (dept) {
		case "D1C1":
			viewName = "administration/univ_man/main/univmanMain";			
			break;
		case "D1C2":
			viewName = "administration/univ_aca/main/univacaMain";
			
			int currentPage = 1;
			SearchVO searchVO = new SearchVO();
			PagingVO<DocumentVO> pagingVO = new PagingVO<>();
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSearchVO(searchVO);
			
			Adminservice.retrieveDocumentList(pagingVO);
			
			model.addAttribute("pagingVO", pagingVO);
						
			break;
		case "D2C1":
			viewName = "administration/stu_sup/main/stusupMain";
			break;
		case "D2C2":
			viewName = "administration/stu_emp/main/stuempMain";
			break;
		case "D3C1":
			viewName = "administration/ent_man/main/entmanMain";
			break;
		case "D4C1" :
			viewName = "administration/off_gen/main/offgenMain";
			break;
		case "D4C2":
			viewName = "administration/off_acc/main/offaccMain";
			break;
		case "D4C3":
			viewName = "administration/off_fac/main/offfacMain";
			break;
		default:
			viewName = "administration/main/adminMain";
			break;
		}
		
		model.addAttribute("mainContent", service.retrieveProfMainContent(authMember));
		log.info("뷰네임 : {}", viewName);
		return viewName;
	}
}
