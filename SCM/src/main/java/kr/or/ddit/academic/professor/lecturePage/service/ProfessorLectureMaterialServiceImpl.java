package kr.or.ddit.academic.professor.lecturePage.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.academic.common.lecturePage.dao.CommonLectureMaterialsDAO;
import kr.or.ddit.academic.professor.lecturePage.dao.ProfessorLectureMaterialDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.common.attatch.dao.AttatchDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Service
public class ProfessorLectureMaterialServiceImpl implements ProfessorLectureMaterialService {

	@Inject
	private ProfessorLectureMaterialDAO dao;
	
	@Inject
	private CommonLectureMaterialsDAO commonDao ;
	
	@Inject
	private AttatchDAO attatchDAO; 
	@Value("#{appInfo.attatchPath}")  //root-context에 util로 등록되어 있음. 
	private File saveFolder;
	
	
	@Override
	public ServiceResult deleteMaterial(String boardNo) {
	
		ServiceResult result = ServiceResult.FAILED; 
		BoardVO board = commonDao.selectMaterial(boardNo);
		if(board==null)
			throw new PKNotFoundException(boardNo +"번 글이 없음.");

		List<AttatchVO> attatchList = board.getAttatchList();
		attatchDAO.deleteAttatches(board);
		int x = dao.deleteMaterial(board.getBoardNo());
		for(AttatchVO tmp : attatchList) {
			FileUtils.deleteQuietly(new File(saveFolder, tmp.getAttSavefilename()));
		}
		if(x == 1){result = ServiceResult.OK;}

		return result;
	}
	
	
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
	public ServiceResult createMaterial(BoardVO board) {
		ServiceResult result = null;
		int rowcnt = dao.insertLectureMaterial(board);
		if(rowcnt > 0) {
			rowcnt += processAttatches(board);
			result = ServiceResult.OK;		
		}else {
			result = ServiceResult.FAILED;
		}
		return result;   
		
	}

	@Override
	public ServiceResult modifyBoard(BoardVO board) {
		
		ServiceResult result = null;
		
		int rowcnt = dao.updateMaterial(board);
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


}
