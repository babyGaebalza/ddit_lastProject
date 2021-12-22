package kr.or.ddit.academic.assistant.departmentmanage.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.academic.assistant.departmentmanage.dao.DepartmentManageDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.attatch.dao.AttatchDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepartmentManageServiceImpl implements DepartmentManageService {
	@Inject
	private DepartmentManageDAO dao;
	@Inject
	private AttatchDAO attatchDAO;

	@Value("#{appInfo.attatchPath}")
	private String saveFolderPath;
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
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
	public List<BoardVO> retrieveDepartmentList(PagingVO<BoardVO> pagingVO) {
		List<BoardVO> departmentList = dao.selectDepartmentList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCount(pagingVO));
		pagingVO.setDataList(departmentList);
		
		return departmentList;
	}

	@Override
	public BoardVO retrieveDepartment(String boardNo) {
		BoardVO notice = dao.selectDepartment(boardNo);
		if(notice == null) throw new PKNotFoundException(boardNo + "번 공지글 없음");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boardNo", boardNo);
		pMap.put("incType", "BOARD_HITS");
		log.info("pMap : {}", pMap);
		dao.incrementCount(pMap);
		return notice;
	}

	//@Transactional
	@Override
	public ServiceResult createDepartment(BoardVO board) {
		ServiceResult result = null;
		
		int rowcnt = dao.insertDepartment(board);
		if(rowcnt > 0) {
			rowcnt += processAttatches(board);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	//@Transactional
	@Override
	public ServiceResult modifyDepartment(BoardVO board) {
		ServiceResult result = null;
		BoardVO saved = retrieveDepartment(board.getBoardNo());
		
		int rowcnt = dao.updateDepartment(board);
			if(rowcnt > 0) {
				// 올릴 파일 처리
				processAttatches(board);
				// 지울 파일 처리
				int[] delAttNos = board.getDelAttNos();
				if(delAttNos!=null && delAttNos.length > 0) {
					List<AttatchVO> attatchList = saved.getAttatchList();
					attatchDAO.deleteAttatches(board);
					Arrays.sort(delAttNos);
					for(AttatchVO tmp : attatchList) {
						if(Arrays.binarySearch(delAttNos, Integer.parseInt(tmp.getAttNo())) >= 0)
							FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavefilename()));
					}
				}
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult removeDepartment(BoardVO board) {
		ServiceResult result = null;
		BoardVO saved = board;
		List<AttatchVO> attatchList = saved.getAttatchList();
		
		if(attatchList != null) {
			attatchDAO.deleteAttatches(board);
			for(AttatchVO tmp : attatchList) {
				FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavefilename()));
			}
		}
		dao.deleteDepartment(board.getBoardNo());
		result = ServiceResult.OK;

		return result;
	}

	@Override
	public AttatchVO download(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null) throw new PKNotFoundException(attNo+"파일의 메타데이터가 없음.");
		return atch;
	}
}
