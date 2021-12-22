package kr.or.ddit.administration.off_acc.viewPrepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@kr.or.ddit.annotations.ViewPreparer
public class AdminOffAccViewPrepare implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		
		Map<String, Object> menuList = new HashMap<>();
		 
		List<Map<String, String>> manageMenu = new ArrayList<>();
		menuList.put("fa-coins/재무관리", manageMenu);
		
//			Map<String, String> enrollmentManage = new HashMap<>();
//			enrollmentManage.put("menuText", "등록금관리");
//			enrollmentManage.put("menuURL", "/enrollmentManage/enrollmentManageList.do");
//			manageMenu.add(enrollmentManage);
			
			Map<String, String> payManage = new HashMap<>();
			payManage.put("menuText", "급여관리");
			payManage.put("menuURL", "/pay/inputPayList.do");
			manageMenu.add(payManage);
			
			Map<String, String> tuitionManage = new HashMap<>();
			tuitionManage.put("menuText", "장학금관리");
			tuitionManage.put("menuURL", "/");
			manageMenu.add(tuitionManage);

			//------------------------------------------------------------------------
		 List<Map<String, String>> documentSignMenuList = new ArrayList<>();
		      //5. 
		      menuList.put("fa-file-import/결재관리", documentSignMenuList);
		         
		      //5-1. 
		      Map<String, String> myWriteDocument = new LinkedHashMap<>();
		      myWriteDocument.put("menuText", "내 작성 문서");
		      myWriteDocument.put("menuURL", "/common/document/pdfList.do");
		      documentSignMenuList.add(myWriteDocument);
		   
		      //5-2. 
		      Map<String, String> signNeedDocument = new LinkedHashMap<>();
		      signNeedDocument.put("menuText", "결재요청문서");
		      signNeedDocument.put("menuURL", "#");
		      documentSignMenuList.add(signNeedDocument);
		      
		      //5-3. 
		      Map<String, String> newDocumentWrite = new LinkedHashMap<>();
		      newDocumentWrite.put("menuText", "새결재문서작성");
		      newDocumentWrite.put("menuURL", "/common/document/documentManage.do");
		      documentSignMenuList.add(newDocumentWrite);
		    //------------------------------------------------------------------------			
			
		requestScope.put("menuList", menuList);
		
	}

}
