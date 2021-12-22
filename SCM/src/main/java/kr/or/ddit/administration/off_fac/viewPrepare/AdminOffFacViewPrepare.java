package kr.or.ddit.administration.off_fac.viewPrepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@kr.or.ddit.annotations.ViewPreparer
public class AdminOffFacViewPrepare implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		
		Map<String, Object> menuList = new HashMap<>();
		
		List<Map<String, String>> manageMenu = new ArrayList<>();
		menuList.put("fa-running/교내시설관리", manageMenu);
		
			Map<String, String> dormitoryManage = new HashMap<>();
			dormitoryManage.put("menuText", "생활관관리");
			dormitoryManage.put("menuURL", "/");
			manageMenu.add(dormitoryManage);
			
			Map<String, String> athleticManage = new HashMap<>();
			athleticManage.put("menuText", "체육시설관리");
			athleticManage.put("menuURL", "/off_fac/athleticList.do");
			manageMenu.add(athleticManage);
			
			Map<String, String> readingRoomManage = new HashMap<>();
			readingRoomManage.put("menuText", "열람실관리");
			readingRoomManage.put("menuURL", "/off_fac/readingRoomList.do");
			manageMenu.add(readingRoomManage);
			
			Map<String, String> classRoomManage = new HashMap<>();
			classRoomManage.put("menuText", "강의실관리");
			classRoomManage.put("menuURL", "/off_fac/classroomList.do");
			manageMenu.add(classRoomManage);

				
			
		List<Map<String, String>> complainMenu = new ArrayList<>();
		menuList.put("fa-building/시설민원", complainMenu);
		
			Map<String, String> facComplainManage = new HashMap<>();
			facComplainManage.put("menuText", "시설민원조회");
			facComplainManage.put("menuURL", "/board/facComList.do");
			complainMenu.add(facComplainManage);
			
			
			//------------------------------------------------------------------------
		      //5. 
			List<Map<String, String>> documentSignMenuList = new ArrayList<>();
		      menuList.put("fa-file/결재관리", documentSignMenuList);
		      
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
