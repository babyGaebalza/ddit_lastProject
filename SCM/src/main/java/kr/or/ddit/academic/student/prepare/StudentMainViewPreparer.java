package kr.or.ddit.academic.student.prepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;


@kr.or.ddit.annotations.ViewPreparer
public class StudentMainViewPreparer implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		
		Map<String, Object> menuList = new LinkedHashMap<>();
		 
		List<Map<String, String>> scoreMenu = new ArrayList<>();
		menuList.put("fa-id-card/성적", scoreMenu);
		Map<String, String> retrieveScore = new HashMap<>();
		retrieveScore.put("menuText", "성적조회");
		retrieveScore.put("menuURL", "/student/score/scoreView");
		scoreMenu.add(retrieveScore);
		
		 
		
		List<Map<String, String>> lectureMenu = new ArrayList<>();
		menuList.put("fa-book/수강관리", lectureMenu);
		Map<String, String> lectureReister = new HashMap<>();
		lectureReister.put("menuText", "수강신청");
		lectureReister.put("menuURL", "/student/lecture/registerForm");
		lectureMenu.add(lectureReister);
		Map<String, String> lecturePage = new HashMap<>();
		lecturePage.put("menuText", "강의페이지");
		lecturePage.put("menuURL", "/student/lecture/pageListForm");
		lectureMenu.add(lecturePage);
		
		List<Map<String, String>> certiMenu = new ArrayList<>();
		menuList.put("fa-file-video/증명서", certiMenu);
		Map<String, String> retrieveCerti = new HashMap<>();
		retrieveCerti.put("menuText", "증명서 발급");
		retrieveCerti.put("menuURL", "/certificateManage/certificateManageList.do");
		certiMenu.add(retrieveCerti);
		
//		List<Map<String, String>> enrollmentMenu = new ArrayList<>();
//		menuList.put("fas fa-money-check-alt/등록", enrollmentMenu);
//		Map<String, String> retrieveEnrollment = new HashMap<>();
//		retrieveEnrollment.put("menuText", "등록금 납부");
//		retrieveEnrollment.put("menuURL", "/student/enrollment/enrollmentList.do");
//		enrollmentMenu.add(retrieveEnrollment);
		
		List<Map<String, String>> graduateMenu = new ArrayList<>();
		menuList.put("fa-graduation-cap/졸업", graduateMenu);
		Map<String, String> graduationManage = new HashMap<>();
		graduationManage.put("menuText", "졸업관리");
		graduationManage.put("menuURL", "/student/graduation/graduationManage"); 
		graduateMenu.add(graduationManage);
		Map<String, String> trackManage = new HashMap<>();
		trackManage.put("menuText", "트랙관리");
		trackManage.put("menuURL", "/student/graduation/trackManage");
		graduateMenu.add(trackManage);
		
		requestScope.put("menuList", menuList);
	}

}
