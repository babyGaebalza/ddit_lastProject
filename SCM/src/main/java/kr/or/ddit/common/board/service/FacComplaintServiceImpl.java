package kr.or.ddit.common.board.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.attatch.dao.AttatchDAO;
import kr.or.ddit.common.board.dao.FacComplaintDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FacComplaintServiceImpl implements FacComplaintService {

	
	@Inject
	private FacComplaintDAO dao;
	
	@Inject
	private AttatchDAO attatchDAO;
	
	@Value("#{appInfo.attatchPath}")
	private String saveFolderPath;
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	
	@PostConstruct
	public void init() throws IOException {
		saveFolder = new File(saveFolderPath);
		log.info("첨부파일 저장 위치 : {}", saveFolder.getCanonicalPath());
	}
	
	private int processAttatches(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			rowcnt = attatchDAO.insertAttatches(board);
			

			
			try {
				for(AttatchVO atch : attatchList) {
					atch.saveTo(saveFolder);
				}
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return rowcnt;
	}

	
	@Override
	public String getMemName(String memId) {
		return dao.getMemName(memId);
	}
	
	
	@Override
	public ServiceResult createFC(BoardVO facComplaint) {
		ServiceResult result = null;

		int rowcnt = dao.insertFC(facComplaint);
		if(rowcnt > 0) {
			rowcnt += processAttatches(facComplaint);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<BoardVO> retrieveFCList(PagingVO pagingVO) {
		List<BoardVO> boardList = dao.selectFCList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
		pagingVO.setDataList(boardList);
		return boardList;
	}

	@Override
	public BoardVO retrieveFCView(String fcNo) {
		BoardVO board = dao.selectFCView(fcNo);
		if(board==null)
			throw new PKNotFoundException(fcNo +"번 글이 없음.");
		
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boardNo", fcNo);
		pMap.put("incType", "BOARD_HITS"); // replace text 활용
		dao.incrementCount(pMap);
		
		return board;
	}

	@Override
	public ServiceResult modifyFC(BoardVO board) {
		ServiceResult result = null;

		int rowcnt = dao.UpdateFC(board);
		if(rowcnt > 0) {
			rowcnt += processAttatches(board);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeFC(String boardNo) {
		ServiceResult result = null;
		int res = dao.deleteFC(boardNo);
		if(res > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}


	
	
}
