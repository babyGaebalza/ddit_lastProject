package kr.or.ddit.administration.univ_man.classnotice.service;

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

import kr.or.ddit.administration.univ_man.classnotice.dao.ClassNoticeDao;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.attatch.dao.AttatchDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassNoticeServiceImpl implements ClassNoticeService {
	
	@Inject
	private ClassNoticeDao cNoticeDao;
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
	public List<BoardVO> retrieveCNoticeList(PagingVO<BoardVO> pagingVO) {
		List<BoardVO> cNoticeList = cNoticeDao.selectCNoticeList(pagingVO);
		pagingVO.setTotalRecord(cNoticeDao.selectTotalCount(pagingVO));
		pagingVO.setDataList(cNoticeList);
		return cNoticeList;
	}

	@Override
	public BoardVO retrieveCNotice(String boardNo) {
		BoardVO cNotice = cNoticeDao.selectCNotice(boardNo);
		if(cNotice == null) throw new PKNotFoundException(boardNo + "번 공지글 없음");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boardNo", boardNo);
		pMap.put("incType", "BOARD_HITS");
		log.info("pMap : {}", pMap.entrySet());
		cNoticeDao.incrementCount(pMap);
		return cNotice;
	}

	@Override
	public ServiceResult modifyCNotice(BoardVO board) {
		ServiceResult result = null;
		BoardVO saved = board;
		int rowcnt = cNoticeDao.updateCNotice(board);
			if(rowcnt > 0) {
				processAttatches(board);
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
	public ServiceResult removeCNotice(BoardVO board) {
		ServiceResult result = null;
		int rowcnt = cNoticeDao.deleteCNotice(board);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public ServiceResult createCNotice(BoardVO board) {
		ServiceResult result = null;
		int rowcnt = cNoticeDao.insertCNotice(board);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		return result;
	}

	@Override
	public AttatchVO download(int attNo) {
		AttatchVO atch = attatchDAO.selectAttatch(attNo);
		if(atch==null)
			throw new PKNotFoundException(attNo+"파일의 메타데이터가 없음.");
		return atch;
	} 
}
