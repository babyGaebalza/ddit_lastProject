package kr.or.ddit.administration.univ_aca.majormanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;

public interface MajorManageService {
	
		//존재하는 학과 리스트 조회
		public List<MajorVO> retrieveMajorList(PagingVO<MajorVO> pagingVO);
		
		//해당 학과 조회
		public MajorVO retrieveMajor(String majorName);
		
		//학과 수정
		public ServiceResult modifyMajor(MajorVO major);
		
		//학과 삭제여부
		public ServiceResult removeMajor(MajorVO major);
		
		//학과 신설
		public ServiceResult createMajor(MajorVO major);
}
