package kr.or.ddit.administration.stu_sup.viewPrepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@kr.or.ddit.annotations.ViewPreparer
public class AdminStuSupViewPrepare implements ViewPreparer {
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		
		Map<String, Object> menuList = new HashMap<>();

		List<Map<String, String>> scholarMenu = new ArrayList<>();
		menuList.put("fa-award/장학금", scholarMenu);
			Map<String, String> scholarManage = new HashMap<>();
			scholarManage.put("menuText", "장학금관리");
			scholarManage.put("menuURL", "/stu_sup/scholarShipList.do");
			scholarMenu.add(scholarManage);

		List<Map<String, String>> complaintMenu = new ArrayList<>();
		menuList.put("fa-question-circle/문의게시판관리", complaintMenu);
			Map<String, String> complaintManage = new HashMap<>();
			complaintManage.put("menuText", "문의게시판관리");
			complaintManage.put("menuURL", "/stu_sup/complaintList.do");
			complaintMenu.add(complaintManage);

		List<Map<String, String>> stuSupportMenu = new ArrayList<>();
		menuList.put("fa-info-circle/학생지원관리", stuSupportMenu);
			Map<String, String> stuSupportManage = new HashMap<>();
			stuSupportManage.put("menuText", "학생지원관리");
			stuSupportManage.put("menuURL", "/stu_sup/stuSupportList.do");
			stuSupportMenu.add(stuSupportManage);

		List<Map<String, String>> noticeManageMenu = new ArrayList<>();
		menuList.put("fa-flag/공지사항관리", noticeManageMenu);
		Map<String, String> noticeManage = new HashMap<>();
		noticeManage.put("menuText", "공지사항");
		noticeManage.put("menuURL", "/common/board/noticeList.do");
		noticeManageMenu.add(noticeManage);
		
		requestScope.put("menuList", menuList);
	}
}
