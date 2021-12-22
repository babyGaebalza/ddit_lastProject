package kr.or.ddit.academic.common.lecturePage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.common.lecturePage.dao.CommonLectureMaterialsDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
@Service
public class CommonLectureMaterialsServiceImpl implements CommonLectureMaterialsService {

	@Inject
	private CommonLectureMaterialsDAO dao;
	
	@Override
	public List<BoardVO> retrieveMaterialList(PagingVO<BoardVO> pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO); 
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectMaterialList(pagingVO);
	}

	@Override
	public BoardVO retrieveMaterial(String boardNo) {
		BoardVO board = dao.selectMaterial(boardNo);
		if(board==null)
			throw new PKNotFoundException(boardNo +"번 글이 없음.");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNo", boardNo);
		pMap.put("incType", "BOARD_HITS"); // replace text 활용
		dao.incrementCount(pMap);
		return board;
	}


}
