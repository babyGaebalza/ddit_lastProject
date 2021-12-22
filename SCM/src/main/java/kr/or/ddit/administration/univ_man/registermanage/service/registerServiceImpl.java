package kr.or.ddit.administration.univ_man.registermanage.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_man.registermanage.dao.registerDao;
import kr.or.ddit.administration.vo.RegisterVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PushVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class registerServiceImpl implements registerService {

	@Inject
	private registerDao registerDAO;

	
	
	@Override
	public List<MemberVO> retrieveRegisterList(PagingVO pagingVO) {
		List<MemberVO> regList = registerDAO.selectRegisterList(pagingVO);
		log.info("서비스단 : {}",regList);
		pagingVO.setTotalRecord(registerDAO.selectRegCount(pagingVO));
		pagingVO.setDataList(regList);
		return regList;
	}

	@Override
	public List<MemberVO> retrieveStuRegisterList(String memId) {
		List<MemberVO> stuReg = registerDAO.selectStuRegisterList(memId);
		return stuReg;
	}

	@Override
	public ServiceResult createRegister(RegisterVO register) throws Exception {
		ServiceResult result = null;
		
		int res2 = registerDAO.updateRegState(register);
		int res = registerDAO.insertRegister(register);
		if(res > 0 && res2 > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}


	@Override
	public int createPush(PushVO push) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CategoryVO> retrieveRegCode() {
		
		return registerDAO.selectRegCode();
	}

	@Override
	public int updateMemReg(MemberVO member) {
		
		return registerDAO.updateMemReg(member);
	}

}
