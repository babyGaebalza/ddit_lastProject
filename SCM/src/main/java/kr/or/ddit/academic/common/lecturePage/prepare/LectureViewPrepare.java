package kr.or.ddit.academic.common.lecturePage.prepare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.security.core.context.SecurityContextHolder;

import kr.or.ddit.enumpkg.UserCode;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;


@kr.or.ddit.annotations.ViewPreparer
public class LectureViewPrepare implements ViewPreparer{

	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		Map<String, Object> requestScope = tilesContext.getContext(Request.REQUEST_SCOPE);
		UserCode userCode = UserCode.valueOf(((MemberVOWrapper)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthMember().getUserCode());

		//전체메뉴맵
		Map<String, List<Map<String, String>>> menuList = new LinkedHashMap<>();
		
		//1.
		List<Map<String, String>> noticeList = new ArrayList<>();
		menuList.put("fa-list-alt/강의게시판", noticeList);
		//1-1.
		Map<String, String> lectureNotice = new LinkedHashMap<>();
		lectureNotice.put("menuText", "공지사항");
		lectureNotice.put("menuURL", "/common/lecturePage/noticeList.do");
		noticeList.add(lectureNotice);
		
		Map<String, String> lectureTask = new LinkedHashMap<>();
		lectureTask.put("menuText", "과제게시판");
		lectureTask.put("menuURL", "/common/lecturePage/reportBoardList.do");
		noticeList.add(lectureTask);

		Map<String, String> lectureMaterial = new LinkedHashMap<>();
		lectureMaterial.put("menuText", "강의자료실");
		lectureMaterial.put("menuURL", "/common/lecturePage/materialList.do");
		noticeList.add(lectureMaterial);

		//2
		List<Map<String, String>> attendanceList = new ArrayList<>(); 
		menuList.put("fa-user-edit/출결관리", attendanceList);	
		//2-1.
		Map<String, String> lectureMyAttendance = new LinkedHashMap<>();
		lectureMyAttendance.put("menuText", "나의 출석부");
		lectureMyAttendance.put("menuURL", "/student/lecturePage/myAtt");

		attendanceList.add(lectureMyAttendance);
	
		Map<String, String> lectureAttendance = new LinkedHashMap<>();
			lectureAttendance.put("menuText", "출결현황");
			lectureAttendance.put("menuURL", "/common/lecturePage/attendanceList.do");

		attendanceList.add(lectureAttendance);

			

		//4. 
		List<Map<String, String>> lectureManageMenuList = new ArrayList<>();
		menuList.put("fa-list-alt/화상강의", lectureManageMenuList);

		//4-1. 
		Map<String, String> joinRTC = new LinkedHashMap<>();	
			joinRTC.put("menuText", "화상강의참여");
			joinRTC.put("menuURL", "/student/lecturePage/join.do");
		lectureManageMenuList.add(joinRTC);
			
		//4-2.
		Map<String, String> makeRTC = new LinkedHashMap<>();	
			makeRTC.put("menuText", "화상강의개설");
			makeRTC.put("menuURL", "/academic/professor/lecturePage/RTC.do");
		lectureManageMenuList.add(makeRTC);

		//5.
		List<Map<String, String>> scoreManageMenuList = new ArrayList<>();
		menuList.put("fa-list-alt/성적관리", scoreManageMenuList);
		
		//5-1. 전체성적조회 
		Map<String, String> retrieveScorePage = new LinkedHashMap<>();
		retrieveScorePage.put("menuText", "성적조회"); 
		retrieveScorePage.put("menuURL", "/academic/professor/lecturePage/retrieveScore.do");
		scoreManageMenuList.add(retrieveScorePage);
		
		//5-2. 성적입력  
		Map<String, String> insertScorePage = new LinkedHashMap<>();
		insertScorePage.put("menuText", "성적입력(중간/기말)"); 
		insertScorePage.put("menuURL", "/academic/professor/lecturePage/writeScore.do");
		scoreManageMenuList.add(insertScorePage);
				
		//끝
		requestScope.put("menuList", menuList);
	}

}
