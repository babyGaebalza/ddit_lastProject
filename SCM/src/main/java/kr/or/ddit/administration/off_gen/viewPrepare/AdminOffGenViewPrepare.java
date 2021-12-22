package kr.or.ddit.administration.off_gen.viewPrepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@kr.or.ddit.annotations.ViewPreparer
public class AdminOffGenViewPrepare implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		
		Map<String, Object> menuList = new HashMap<>();
		 
			//------------------------------------------------------------------------
		      List<Map<String, String>> documentSignMenuList = new ArrayList<>();
		      //5. 
		      menuList.put("fas fa-file-upload/결재관리", documentSignMenuList);
		         
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
