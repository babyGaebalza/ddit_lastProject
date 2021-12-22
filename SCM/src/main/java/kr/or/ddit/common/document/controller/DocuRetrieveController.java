package kr.or.ddit.common.document.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.common.document.dao.DocuDAO;
import kr.or.ddit.common.document.service.DocuService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DocuRetrieveController {

	@Inject
	private DocuService service; 
	
	@Inject
	private DocuDAO dao;
	
	//데이터세팅
	private void makeModel(int currentPage, SearchVO searchVO, Model model, boolean flagTodo) {
		PagingVO<DocumentVO> pagingVO = new PagingVO<>(3, 3); 
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setSearchVO(searchVO);
		List<DocumentVO> docuList = new ArrayList<DocumentVO>(); 
		if(flagTodo) {
			docuList = service.retrieveTodoDocu(pagingVO);
		}
		else {
			docuList = service.retrieveMyDocu(pagingVO);
		}
		pagingVO.setDataList(docuList);		
		model.addAttribute("pagingVO", pagingVO);
	}
	
	//내가 올린 결재리스트 동기요청
	@RequestMapping(value="/common/document/pdfList.do")
	public String listView() {
		return "common/document/PDFList";
	}
	
	//비동기요청 : 내가 올린 결재리스트   
	@RequestMapping(value="/common/document/pdfList.do",  produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingVO<DocumentVO> showPDFList( 
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage, 
			Model model, @ModelAttribute("searchVO") SearchVO searchVO, 
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			) {

		boolean flagTodo = false; 
		PagingVO<DocumentVO> pagingVO = new PagingVO<>();
 		pagingVO.setSearchVO(searchVO);
        searchVO.setSearchWord(member.getMemId());
   
		makeModel(currentPage, searchVO, model, flagTodo);
	    pagingVO = (PagingVO<DocumentVO>) model.asMap().get("pagingVO");
		return pagingVO; 
	}
	//강의리스트 , 비동기 
	@RequestMapping(value="/common/pdf/showLectureList.do",  produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ClassVO> showLetureList(
			@RequestParam(value="memMajor", required=true) String memMajor ,
			Model model
			){
		
		List<ClassVO> myClassList = dao.selectMyMajorClassList(memMajor);
		//model.addAttribute("myClassList", myClassList);

		return myClassList;
	}
	
	
	//결재문서 한건 PDF로 출력을 위한 session에 넣어주기 
	@RequestMapping(value="/common/pdf/onePDF.do")
	public String showOneDocu(@RequestParam(value="docuNo", required=true) String docuNo , 
			@ModelAttribute("docuVO") DocumentVO docuVO, HttpServletRequest req 
	) {	
		docuVO = service.retrieveOneDocu(docuNo);
		log.info("PDF만드는 컨트롤러 보내기전 확인 " + docuNo);

		req.getSession().setAttribute("docuVO", docuVO);
		//return "common/pdf/showPDF"; JSP로 보내기 >>실패  
		return "redirect:/pdf.do"; //컨트롤러로 보내보기 	
	}
	
	//결재문서 입력폼 보여주기 
	//1.(track)List에서 추가 버튼 눌렀을때 
	//2.(track)View에서 수정, 삭제 버튼 눌렀을때 
	@RequestMapping(value="/common/document/documentManage.do")
	public String documentForm(Model model, HttpServletRequest req,
			@AuthenticationPrincipal(expression="authMember") MemberVO member
			) {

		return "common/document/documentManage"; 
	} 	
	//강의개설신청서 폼 입력값 받아오기 
	@RequestMapping(value="/common/document/classMakedocumentManage.do")
	public String classMakedocumentForm(
			 @Validated(InsertGroup.class) 
			 @ModelAttribute("myClass") ClassVO myClass, 
			 @RequestParam("docuApf") String docuApf,
			 Errors errors, 
			 Model model,
			@AuthenticationPrincipal(expression="authMember") MemberVO member

			) {	
		String viewName = null;
		String message = null;
		DocumentVO docu =  new DocumentVO(); 
		//강의개설신청서 
		docu.setDocuCode("DOC02");
		docu.setDocuReq(member.getMemId());	
		docu.setDocuApf(docuApf);
		docu.setDocuReqcnt(2);
		docu.setDocuFilename(myClass.getClassName() + "강의개설신청서" );
		docu.setDocuContent(myClass); 
		
	int attPoint = myClass.getClassAttpoint();
	int taskPoint = myClass.getClassTaskpoint();
	int midPoint = myClass.getClassMidpoint();
	int finPoint = myClass.getClassFinpoint();

	boolean flag = false; 
	if(attPoint + taskPoint + midPoint + finPoint == 100) {
		flag = true;
	}
	if(!errors.hasErrors() && flag) { //검증통과
			ServiceResult result = service.createClassMakeDocument(docu);
			switch(result) {
			case OK:
				viewName = "redirect:/common/document/pdfList.do";		
				break; 
			default:
				viewName = "academic/professor/lectureMakeForm";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
	}
	else if(flag ==false ){
		viewName = "academic/professor/lectureMakeForm";
		message = "점수비율 합이 100% 초과";
	}
	else {
		viewName = "academic/professor/lectureMakeForm";
		
	 }
		model.addAttribute("message", message);	
		return viewName;
	
	
	} 
	
	
	
	// 폼입력값 받아오기
	@RequestMapping(value="/common/document/documentManage.do", method=RequestMethod.POST)
	public String processDocumentForm(
			 @Validated(InsertGroup.class) 
			 @ModelAttribute("docu") DocumentVO docu, 
				Errors errors, 
				Model model
			) {
		
		String viewName = null;
		String message = null;
		String rawCont = docu.getDocuCont();
		String[] tracKName = rawCont.split("#");

		if(!errors.hasErrors()) { //검증통과
			
			
			ServiceResult result = service.createDocument(docu);
			switch(result) {
			case OK:
				viewName = "redirect:/common/document/pdfList.do";		
				break;
			default:
				viewName = "common/document/documentManage";
				message = "서버 오류, 잠시뒤 다시 해보셈.";
			}
		
		}else {
		viewName = "common/document/documentManage";
		
	     }
		model.addAttribute("message", message);	
		return viewName;
	} 
	
	//내가 결재할 리스트 불러오기 
	@RequestMapping(value="/common/document/documentSign.do")
	public String signTodoList(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage, 
			Model model,
			@ModelAttribute("searchVO") SearchVO searchVO, 
			@AuthenticationPrincipal(expression="authMember") MemberVO member		
			) {
		boolean flagTodo = true; 
		String viewName = "common/document/signTodoList" ; 
		
		PagingVO<DocumentVO> pagingVO = new PagingVO<>();
 		pagingVO.setSearchVO(searchVO);
        searchVO.setSearchWord(member.getMemId());
   
		makeModel(currentPage, searchVO, model, flagTodo);
	    //pagingVO = (PagingVO<DocumentVO>) model.asMap().get("pagingVO");
		//return pagingVO; 
		
		return viewName; 
	}
		
}

