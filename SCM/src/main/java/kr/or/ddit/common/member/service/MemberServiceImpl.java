package kr.or.ddit.common.member.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.board.dao.BoardDao;
import kr.or.ddit.common.member.dao.MemberDao;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.enumpkg.UserCode;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	private MemberDao memberDAO;

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		
		int rowcnt = memberDAO.insertMember(member);
		String memId = member.getMemId();
		log.info("들어간ID : {}", memId);
		if(rowcnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result;
		int rowcnt = memberDAO.updateMember(member);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(String memId) {
		ServiceResult result;
		int rowcnt = memberDAO.deleteMember(memId);

		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		List<MemberVO> memberList = memberDAO.selectMemberList(pagingVO);
		pagingVO.setTotalRecord(memberDAO.selectTotalCount(pagingVO));
		pagingVO.setDataList(memberList);
		return memberList;
	}

	@Override
	public ServiceResult retrieveMember(MemberVO member) {
		
		ServiceResult result = ServiceResult.FAILED;
		
		MemberVO findMember = memberDAO.selectMember(member);
		
		if(findMember != null) {
			try {
				BeanUtils.copyProperties(member, findMember);
			} catch (IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
			result = ServiceResult.OK;
			log.info("service member 정보  :{}", member);
			member.setMemAdd1("주소1");
		}else {
			result = ServiceResult.NOTEXIST;
		}
		
		return result;
	}


	
	
	@Override
	public List<CategoryVO> retrieveRankCode() {
		List<CategoryVO> category = memberDAO.selectRankCode();
		return category;
	}

	@Override
	public List<CategoryVO> retrieveDeptCode() {
		List<CategoryVO> category = memberDAO.selectDeptCode();
		return category;
	}

	@Override
	public List<MajorVO> retrieveMajorCode() {
		List<MajorVO> major = memberDAO.selectMajorCode();
		return major;
	}

	@Override
	public List<CategoryVO> retrieveAdmissionCode() {
		List<CategoryVO> category = memberDAO.selectAdmissionCode();
		return category;
	}

	@Override
	public MemberVO retrieveDetailMember(String memId) {
		MemberVO member = memberDAO.selectDetailMember(memId);
		return member;
	}

	@Override
	public ServiceResult modifyResetPassword(MemberVO member) {
		
		ServiceResult result = null;
		int cnt = memberDAO.updateResetPassword(member);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyUnlockAccount(MemberVO member) {
		ServiceResult result = null;
		int cnt = memberDAO.updateUnlockAccount(member);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public MemberVO retrieveStudentMyPage(MemberVO member) {
		MemberVO student = memberDAO.selectStudentMyPage(member);
		return student;
	}

	@Override
	public MemberVO retrieveProfessorMyPage(MemberVO member) {
		MemberVO professor = memberDAO.selectProfessorMyPage(member);
		return professor;
	}

	@Override
	public MemberVO retrieveAssistantMyPage(MemberVO member) {
		MemberVO assistant = memberDAO.selectAssistantMyPage(member);
		return assistant;
	}
	
	@Override
	public MemberVO retrieveManagerMyPage(MemberVO member) {
		MemberVO manager = memberDAO.selectManagerMyPage(member);
		return manager;
	}

	@Override
	public ServiceResult modifyMyPage(MemberVO member) {
		ServiceResult result = null;
		int rowcnt = memberDAO.updateMyPage(member);
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
}
