package kr.or.ddit.administration.stu_sup.support.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BusinessVO;
import kr.or.ddit.vo.PagingVO;

public interface StuSupportService {
		
		//협업 업체 리스트
		public List<BusinessVO> retrieveStuSupportList(PagingVO<BusinessVO> pagingVO);
		
		//업체 조회
		public BusinessVO retrieveStuSupport(String bussNo);
		
		//업체 수정
		public ServiceResult modifyStuSupport(BusinessVO business);
		
		//업체 삭제
		public ServiceResult removeStuSupport(BusinessVO business);
		
		//업체 등록
		public ServiceResult createStuSupport(BusinessVO business);
}
