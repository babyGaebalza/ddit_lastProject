package kr.or.ddit.academic.common.lecturePage.service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.common.lecturePage.dao.CommonLectureNoticeDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
@Service 
public class CommonLectureNoticeServiceImpl implements CommonLectureNoticeService{

	@Inject
	private CommonLectureNoticeDAO dao ;

	//상세조회
	@Override
	public BoardVO retrieveNotice(String boardNo) {
		BoardVO board = dao.selectNotice(boardNo);
		if(board==null)
			throw new PKNotFoundException(boardNo +"번 글이 없음.");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNo", boardNo);
		pMap.put("incType", "BOARD_HITS"); // replace text 활용
		dao.incrementCount(pMap);  
		return board;		
	}

	//리스트
	@Override
	public List<BoardVO> retrieveNoticeList(PagingVO<BoardVO> pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO); 
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectNoticeList(pagingVO);
	}

}
