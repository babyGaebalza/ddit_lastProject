package kr.or.ddit.academic.assistant.advisormanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.academic.assistant.advisormanage.dao.AdvisorManageDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
public class AdvisorManageServiceImpl implements AdvisorManageService {
	@Inject
	private AdvisorManageDAO dao;

	@Override
	public List<MemberVO> retreiveStudentList(PagingVO<MemberVO> pagingVO) {
		List<MemberVO> studentList = dao.selectMemberList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCount(pagingVO));
		pagingVO.setDataList(studentList);
		return studentList;
	}

	@Override
	public List<MemberVO> retrieveAdviserList(PagingVO<MemberVO> pagingVO) {
		List<MemberVO> advisorList = dao.selectAdviserList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCountAdviser(pagingVO));
		pagingVO.setDataList(advisorList);
		return advisorList;
	}

	@Override
	public MemberVO retrieveAdviser(String memId) {
		MemberVO member = dao.selectAdviser(memId);
		
		if(member == null) throw new PKNotFoundException(memId + "해당 교수를 찾을 수 없습니다.");
		return member;
	}
	
//	@Transactional
	@Override
	public ServiceResult modifyAdviser(MemberVO member) {
		ServiceResult result = null;
		int rowcnt = dao.updateAdviser(member);
		
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		
		return result;
	}

}
