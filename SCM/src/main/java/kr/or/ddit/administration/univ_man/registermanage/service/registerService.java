package kr.or.ddit.administration.univ_man.registermanage.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.administration.vo.RegisterVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PushVO;

public interface registerService {

	
		//학적 전체 조회
		public List<MemberVO> retrieveRegisterList(PagingVO pagingVO);
		
		//특정학생 학적 조회
		public List<MemberVO> retrieveStuRegisterList(String memId);
		
		//학적 등록
		public ServiceResult createRegister(RegisterVO register) throws Exception;

		
		//반려 알림처리
		public int createPush(PushVO push);
		
		//학적 전체 가져오기
		public List<CategoryVO> retrieveRegCode();
		
		//남은 휴학기간 업데이트
		public int updateMemReg(MemberVO member);

}
