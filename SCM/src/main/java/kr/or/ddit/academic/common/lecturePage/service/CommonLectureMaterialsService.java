package kr.or.ddit.academic.common.lecturePage.service;

import java.util.List;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface CommonLectureMaterialsService {

	// 전체 리스트
		public List<BoardVO> retrieveMaterialList(PagingVO<BoardVO> pagingVO);
		
	// 상세
		public BoardVO retrieveMaterial(String boardNo);
	
}
