package kr.or.ddit.academic.professor.lecturePage.service;

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

import kr.or.ddit.academic.common.lecturePage.dao.CommonLectureNoticeDAO;
import kr.or.ddit.academic.common.lecturePage.dao.CommonLectureTaskDAO;
import kr.or.ddit.academic.professor.lecturePage.dao.ProfessorLectureTaskDAO;
import kr.or.ddit.academic.professor.trackManage.dao.TrackProfessorDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.attatch.dao.AttatchDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ProfessorLectureTaskServiceImpl implements ProfessorLectureTaskService{

	@Inject
	private ProfessorLectureTaskDAO dao;	
	
	@Inject
	private CommonLectureTaskDAO commonDao ;
	
    @Inject
	private AttatchDAO attatchDAO; 
	@Value("#{appInfo.attatchPath}")  //root-context에 util로 등록되어 있음. 
	private File saveFolder;
	
	private int processAttatches(BoardVO board) {
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList!=null && !attatchList.isEmpty()) {
			rowcnt = attatchDAO.insertAttatches(board);
			try {
				for(AttatchVO atch : attatchList) {
					atch.saveTo(saveFolder);  //실제 파일 저장됨. 
				}
			}catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return rowcnt;
	}  //확인하기 

    @Override
	public ServiceResult createTask(BoardVO board) {	
		ServiceResult result = null;
		int rowcnt = dao.insertLectureTask(board);
		if(rowcnt > 0) {
			rowcnt += processAttatches(board);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;    	
	}

	@Override
	public ServiceResult modifyTask(BoardVO board) {
		ServiceResult result = null;
		log.info("과제 업데이트 서비스");
		int rowcnt = dao.updateTask(board);
		if(rowcnt > 0) {
			// 올릴 파일 처리
			processAttatches(board);
			// 지울 파일 처리 
			int[] delAttNos = board.getDelAttNos();
			if(delAttNos!=null && delAttNos.length > 0) {
				List<AttatchVO> attatchList = board.getAttatchList();
				attatchDAO.deleteAttatches(board);
				Arrays.sort(delAttNos);
				for(AttatchVO tmp : attatchList) {
					if(tmp.getAttNo() ==null) continue;
					if(Arrays.binarySearch(delAttNos, Integer.parseInt(tmp.getAttNo())) >= 0) {
						log.info("삭제할 파일명 : {}", tmp.getAttOgfilename());
						FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavefilename()));
					}
				}
			}
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeTask(String boardNo) {
	
		ServiceResult result = ServiceResult.FAILED; 
		BoardVO board = commonDao.selectTask(boardNo);
		if(board==null)
			throw new PKNotFoundException(boardNo +"번 글이 없음.");

		List<AttatchVO> attatchList = board.getAttatchList();
		attatchDAO.deleteAttatches(board);
		int x = dao.deleteTask(board.getBoardNo());
		for(AttatchVO tmp : attatchList) {
			FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavefilename()));
		}
		if(x == 1){result = ServiceResult.OK;}

		return result;
		
		
	}



}
