package kr.or.ddit.academic.assistant.main.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.board.dao.BoardDao;
import kr.or.ddit.vo.MemberVO;

@Service
public class AssistantMainServiceImpl implements AssistantMainService{
	@Inject
	private BoardDao dao;
	
	@Override
	public Map<String, Object> retrieveMainAssistant(MemberVO authMember) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> noticeSearchMap = new HashMap<>();
		noticeSearchMap.put("boardCount", 5);
		noticeSearchMap.put("boardCode", "BC_100");
		resultMap.put("collegeNoticeList", dao.selectLatestNoticeList(noticeSearchMap));
		noticeSearchMap.put("boardCode", "BC_101");
		noticeSearchMap.put("memMajor", authMember.getMemMajor());
		resultMap.put("majorNoticeList", dao.selectLatestNoticeList(noticeSearchMap));
		
		return resultMap;
	}

}
