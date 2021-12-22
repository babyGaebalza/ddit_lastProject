package kr.or.ddit.academic.assistant.main.service;

import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface AssistantMainService {
	// 메인 콘텐츠 조회(시간표)
		public Map<String, Object> retrieveMainAssistant(MemberVO authMember);
}
