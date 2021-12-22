package kr.or.ddit.administration.mainpage.service;

import java.util.List;

import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.PagingVO;

public interface AdiminMainService {
	//신청트랙 리스트
		public List<DocumentVO> retrieveDocumentList(PagingVO<DocumentVO> pagingVO);
		
}
