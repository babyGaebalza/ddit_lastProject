package kr.or.ddit.academic.professor.prepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;


@kr.or.ddit.annotations.ViewPreparer
public class ProfessorViewPrepare implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		//전체메뉴맵
		Map<String, List<Map<String, String>>> menuList = new LinkedHashMap<>();
		//전체
		
		//1.
		List<Map<String, String>> MajorHomeList = new ArrayList<>();
		menuList.put("fa-archway/학과페이지", MajorHomeList); 
	    //1-1.
		Map<String, String> trackManage = new LinkedHashMap<>();
		trackManage.put("menuText", "트랙관리");
		trackManage.put("menuURL", "/professor/trackList.do");
		MajorHomeList.add(trackManage);
			
		//1-2.
		Map<String, String> notice = new LinkedHashMap<>();
		notice.put("menuText", "공지사항");
		notice.put("menuURL", "/common/board/noticeList.do");
		MajorHomeList.add(notice);
		
		
		List<Map<String, String>> lectureManageMenuList = new ArrayList<>();
		//4. 
		menuList.put("fa-user-edit/강의관리", lectureManageMenuList);	 
		//4-1.  
			Map<String, String> lectureMake = new LinkedHashMap<>();
				lectureMake.put("menuText", "강의개설신청");
				lectureMake.put("menuURL", "/professor/makeLecture.do");
			lectureManageMenuList.add(lectureMake);
			//4-2. 
			Map<String, String> lecturePage = new LinkedHashMap<>();
				lecturePage.put("menuText", "강의페이지"); 
				lecturePage.put("menuURL", "/academic/professor/lectureMain.do");
			lectureManageMenuList.add(lecturePage);
		
		
/*		List<Map<String, String>> documentSignMenuList = new ArrayList<>();
		//5. 
		menuList.put("fa-file-export/결재관리", documentSignMenuList);

		//5-1. 
			Map<String, String> myWriteDocument = new LinkedHashMap<>();
				myWriteDocument.put("menuText", "내 작성 문서");
				myWriteDocument.put("menuURL", "/common/document/pdfList.do");
			documentSignMenuList.add(myWriteDocument);
		
			//5-2. 
			Map<String, String> signNeedDocument = new LinkedHashMap<>();
				signNeedDocument.put("menuText", "결재요청문서");
				signNeedDocument.put("menuURL", "/common/document/documentSign.do");
			documentSignMenuList.add(signNeedDocument);
			
			//5-3. 
			Map<String, String> newDocumentWrite = new LinkedHashMap<>();
				newDocumentWrite.put("menuText", "새결재문서작성");
				newDocumentWrite.put("menuURL", "/common/document/documentManage.do");
			documentSignMenuList.add(newDocumentWrite);*/
		
			
			//1.<i class="fas "></i>
			List<Map<String, String>> myStudentManageList = new ArrayList<>();
			menuList.put("fa-child/담당학생관리", myStudentManageList); 
		    //1-1.
			Map<String, String> sutdentTrack = new LinkedHashMap<>();
			sutdentTrack.put("menuText", "트랙신청승인");
			sutdentTrack.put("menuURL", "/professor/stuTrackManage.do");  //고치기 
			myStudentManageList.add(sutdentTrack);	
			//1-1.
			Map<String, String> sutdentGradu = new LinkedHashMap<>();
			sutdentGradu.put("menuText", "졸업신청승인");
			sutdentGradu.put("menuURL", "/professor/stuGraduManage.do");  //고치기 
			myStudentManageList.add(sutdentGradu);	

		//다 넣기 
		requestScope.put("menuList", menuList);
			
		
		
		
		
	}

}
