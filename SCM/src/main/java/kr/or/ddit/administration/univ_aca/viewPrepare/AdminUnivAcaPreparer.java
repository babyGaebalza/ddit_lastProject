package kr.or.ddit.administration.univ_aca.viewPrepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

@kr.or.ddit.annotations.ViewPreparer
public class AdminUnivAcaPreparer implements ViewPreparer {
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		
		Map<String, Object> menuList = new HashMap<>();
		
		List<Map<String, String>> croomMenu = new ArrayList<>();
		menuList.put("fa-chalkboard-teacher/강의실", croomMenu);
			Map<String, String> croomManage = new HashMap<>();
			croomManage.put("menuText", "강의실관리");
			croomManage.put("menuURL", "/classroom/classroomList.do");
			croomMenu.add(croomManage);
		
		List<Map<String, String>> majorMenu = new ArrayList<>();
		menuList.put("fa-door-open/학과", majorMenu);
			Map<String, String> majorManage = new HashMap<>();
			majorManage.put("menuText", "학과관리");
			majorManage.put("menuURL", "/univ_aca/majorList.do");
			majorMenu.add(majorManage);

		List<Map<String, String>> payMenu = new ArrayList<>();
		menuList.put("fa-won-sign/급여", payMenu);
			Map<String, String> payManage = new HashMap<>();
			payManage.put("menuText", "급여관리");
			payManage.put("menuURL", "/pay2/payTableList.do");
			payMenu.add(payManage);

		List<Map<String, String>> trackMenu = new ArrayList<>();
		menuList.put("fa-road/트랙", trackMenu);
			Map<String, String> trackManage = new HashMap<>();
			trackManage.put("menuText", "신청트랙 관리");
			trackManage.put("menuURL", "/trackManage/trackManageList.do");
			trackMenu.add(trackManage);

		List<Map<String, String>> memberMenu = new ArrayList<>();
			menuList.put("fa-user-edit/사용자관리", memberMenu);
				Map<String, String> memberManage = new HashMap<>();
				memberManage.put("menuText", "사용자등록");
				memberManage.put("menuURL", "/member/memberform.do");
				memberMenu.add(memberManage);
				
				Map<String, String> memberListManage = new HashMap<>();
				memberListManage.put("menuText", "사용자조회");
				memberListManage.put("menuURL", "/member/memberList.do");
				memberMenu.add(memberListManage);
			
		requestScope.put("menuList", menuList);
	}
}
