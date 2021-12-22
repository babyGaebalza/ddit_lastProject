//package kr.or.ddit.common.pdf.controller;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import kr.or.ddit.common.pdf.service.PDFService;
//import kr.or.ddit.enumpkg.ServiceResult;
//import kr.or.ddit.validate.groups.InsertGroup;
//import kr.or.ddit.vo.DocumentVO;
//
//@Controller
//public class PDFInsertController {
//
//	@Inject
//	private PDFService service; 
//	
//	//입력폼 띄워주기 
//	@RequestMapping(value = "/pdf/input.do", method = RequestMethod.GET)
//	public String showPDF() throws Exception {
//
//		return "/common/pdf/PDFInputForm"; 
//	}
//	
//	//입력값 받아오기 
//	@RequestMapping(value = "/pdf/input.do", method = RequestMethod.POST)
//	public String process(@Validated(InsertGroup.class) @ModelAttribute("docu") DocumentVO docu, 
//			Errors errors, Model model) {		
//		
//		String viewName = null;
//		String message = null;
//		if(!errors.hasErrors()) { //검증통과
//			ServiceResult result = service.createPDF(docu);
//			
//			switch(result) {
//			case OK:
//				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
//				break;
//			default:
//				viewName = "prod/prodForm";
//				message = "서버 오류, 잠시뒤 다시 해보셈.";
//			}
//		
//		}else {
////	3-2. 불통
//		viewName = "prod/prodForm";
//		
//	}
//	
//	model.addAttribute("message", message);
//	
//	return viewName;
//	}
//}
