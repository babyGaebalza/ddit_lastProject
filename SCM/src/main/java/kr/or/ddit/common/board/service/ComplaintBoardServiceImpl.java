package kr.or.ddit.common.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.board.dao.ComplaintBoardDao;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComplaintBoardServiceImpl implements ComplaintBoardService {
	
	@Inject
	private ComplaintBoardDao complaintDao;
	
	@Override
	public List<BoardVO> retrieveComplaintList(PagingVO<BoardVO> pagingVO) {
		List<BoardVO> complaintList = complaintDao.selectComplaintList(pagingVO);
		pagingVO.setTotalRecord(complaintDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(complaintList);
		return complaintList;
	}

	@Override
	public BoardVO retrieveComplaint(String boardNo) {
		BoardVO complaint = complaintDao.selectComplaint(boardNo);
		if(complaint == null) throw new PKNotFoundException(boardNo + "번 공지글 없음");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boardNo", boardNo);
		pMap.put("incType", "BOARD_HITS");
		log.info("pMap : {}", pMap.entrySet());
		complaintDao.incrementCount(pMap);
		return complaint;
	}

	
	@Override
	public ServiceResult modifyComplaint(BoardVO board) {
		ServiceResult result = null;
		int rowcnt = complaintDao.updateComplaint(board);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}
	
	
	@Override
	public ServiceResult removeComplaint(BoardVO board) {
		ServiceResult result = null;
		int rowcnt = complaintDao.deleteComplaint(board);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createComplaint(BoardVO board) {
		ServiceResult result = null;
		int rowcnt = complaintDao.insertComplaint(board);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	
}
