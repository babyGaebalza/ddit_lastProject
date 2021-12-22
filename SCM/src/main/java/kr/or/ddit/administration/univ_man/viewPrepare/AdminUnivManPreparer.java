package kr.or.ddit.administration.univ_man.viewPrepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@kr.or.ddit.annotations.ViewPreparer
public class AdminUnivManPreparer implements ViewPreparer{
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		
		Map<String, Object> menuList = new HashMap<>();

		List<Map<String, String>> registerMenu = new ArrayList<>();
		menuList.put("fa-book-reader/학적관리", registerMenu);
			Map<String, String> registerManage = new HashMap<>();
			registerManage.put("menuText", "학적관리");
			registerManage.put("menuURL", "/register/regList.do");
			registerMenu.add(registerManage);

		List<Map<String, String>> classAddMenu = new ArrayList<>();
		menuList.put("fa-chalkboard-teacher/교수강의관리", classAddMenu);
			Map<String, String> classManage = new HashMap<>();
			classManage.put("menuText", "교수강의신설관리");
			classManage.put("menuURL", "/univ_man/classList.do");
			classAddMenu.add(classManage);
			

		List<Map<String, String>> stuMajorMenu = new ArrayList<>();
		menuList.put("fa-pen-nib/학생전공관리", stuMajorMenu);
			Map<String, String> stuMajorManage = new HashMap<>();
			stuMajorManage.put("menuText", "학적관리");
			stuMajorManage.put("menuURL", "/register/regList.do");
			stuMajorMenu.add(stuMajorManage);

		List<Map<String, String>> graduateMenu = new ArrayList<>();
		menuList.put("fa-user-graduate/졸업생관리", graduateMenu);
			Map<String, String> graduateManage = new HashMap<>();
			graduateManage.put("menuText", "졸업생관리");
			graduateManage.put("menuURL", "/univ_man/graduateList.do");
			graduateMenu.add(graduateManage);

		List<Map<String, String>> cNoticeMenu = new ArrayList<>();
		menuList.put("fa-exclamation-circle/강의공지게시판", cNoticeMenu);
			Map<String, String> cNoticeManage = new HashMap<>();
			cNoticeManage.put("menuText", "강의공지게시판 관리");
			cNoticeManage.put("menuURL", "/univ_man/cNoticeList.do");
			cNoticeMenu.add(cNoticeManage);

		List<Map<String, String>> testMenu = new ArrayList<>();
		menuList.put("fa-calendar-alt/학사일정관리", testMenu);
			Map<String, String> testManage = new HashMap<>();
			testManage.put("menuText", "학사일정관리"); 
			testManage.put("menuURL", "/univ_man/univCalendarManage.do");
			testMenu.add(testManage);
		
		requestScope.put("menuList", menuList);
	}
}
