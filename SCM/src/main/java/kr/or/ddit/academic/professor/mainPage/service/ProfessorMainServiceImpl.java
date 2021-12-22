package kr.or.ddit.academic.professor.mainPage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.professor.mainPage.dao.ProftMainDAO;
import kr.or.ddit.academic.student.mainpage.dao.StudentMainDAO;
import kr.or.ddit.common.board.dao.BoardDao;
import kr.or.ddit.common.schedule.dao.ScheduleDAO;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
@Service
public class ProfessorMainServiceImpl implements ProfessorMainService{

	@Inject
	private ProftMainDAO profMainDAO;
	@Inject
	private ScheduleDAO scheduleDAO;
	@Inject
	private BoardDao boardDAO;
	
	
	@Override
	public Map<String, Object> retrieveProfMainContent(MemberVO authMember) {
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("timeTable", profMainDAO.selectTimeTable(authMember.getMemId()));
		resultMap.put("currentSemester", scheduleDAO.selectCurrentSemester());

		
		Map<String, Object> noticeSearchMap = new HashMap<>();
		noticeSearchMap.put("boardCount", 3);
		noticeSearchMap.put("boardCode", "BC_100");
		
		resultMap.put("collegeNoticeList", boardDAO.selectLatestNoticeList(noticeSearchMap));
		
		noticeSearchMap.put("boardCode", "BC_101");
		noticeSearchMap.put("memMajor", authMember.getMemMajor());
		
		resultMap.put("majorNoticeList", boardDAO.selectLatestNoticeList(noticeSearchMap));

		return resultMap;
	
	}

}
