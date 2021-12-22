package kr.or.ddit.academic.assistant.prepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@kr.or.ddit.annotations.ViewPreparer
public class AssistantViewPrepare implements ViewPreparer{
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);

		Map<String, Object> menuList = new LinkedHashMap<>();
		List<Map<String, String>> MajorPageList = new ArrayList<>();
		
		List<Map<String, String>> NoticeList = new ArrayList<>();
		menuList.put("far fa-clipboard/공지사항", NoticeList);
		Map<String, String> notice = new HashMap<>();
		notice.put("menuText", "공지사항");
		notice.put("menuURL", "/common/board/noticeList.do");
		NoticeList.add(notice);
		
		menuList.put("fas fa-landmark/학과페이지", MajorPageList);
		Map<String, String> major1 = new HashMap<>();
		major1.put("menuText", "학생정보 관리");
		major1.put("menuURL", "/advisorManage/advisorManageList.do");
		MajorPageList.add(major1);
		Map<String, String> major2 = new HashMap<>();
		major2.put("menuText", "학과 공지사항");
		major2.put("menuURL", "/departmentManage/departmentManageList.do");
		MajorPageList.add(major2);
		
		requestScope.put("menuList", menuList);
	}
}
