package kr.or.ddit.academic.assistant.studentmanage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.academic.assistant.studentmanage.dao.StudentManageDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.board.service.BoardServiceImpl;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentManageServiceImpl implements StudentManageService {
	@Inject
	private StudentManageDAO dao;

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		List<MemberVO> memberList = dao.selectMemberList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCount(pagingVO));
		pagingVO.setDataList(memberList);
		return memberList;
	}

//	@Transactional
	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		int rowcnt = dao.updateMember(member);
		
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		
		return result;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = dao.selectMember(memId);

		if(member == null) throw new PKNotFoundException(memId + "해당 학생을 찾을 수 없습니다.");
		return member;
	}
}
