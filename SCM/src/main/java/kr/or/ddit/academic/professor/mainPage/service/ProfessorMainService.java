package kr.or.ddit.academic.professor.mainPage.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface ProfessorMainService {

	//시간표조회
	public Map<String, Object> retrieveProfMainContent(MemberVO authMember);
		
}
