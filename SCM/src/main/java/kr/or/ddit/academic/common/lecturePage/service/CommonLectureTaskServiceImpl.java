package kr.or.ddit.academic.common.lecturePage.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.or.ddit.academic.common.lecturePage.dao.CommonLectureTaskDAO;
import kr.or.ddit.academic.professor.lecturePage.dao.ProfessorLectureTaskDAO;
import kr.or.ddit.academic.professor.trackManage.dao.TrackProfessorDAO;
import kr.or.ddit.academic.vo.TaskVO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CommonLectureTaskServiceImpl implements CommonLectureTaskService{

	@Inject
	private CommonLectureTaskDAO dao;
	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
		
	//교수가 과제 점수 채점할때 
	@Override
	public ServiceResult updateScore(TaskVO task) {
		ServiceResult result = null; 
		int x  = dao.updateScore(task);
		if(x > 0 ) {
			result = ServiceResult.OK; 
		}
		else {
			result = ServiceResult.FAILED; 
		}
		return  result; 
	}
	
	
	//리스트 
	@Override
	public List<BoardVO> retrieveTaskList(PagingVO<BoardVO> pagingVO) {	
		int totalRecord = dao.selectTotalRecord(pagingVO); 
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectTaskList(pagingVO);
		
	}

	@Override
	public BoardVO retrieveTask(String boardNo) {
		BoardVO board = dao.selectTask(boardNo);
		if(board==null)
			throw new PKNotFoundException(boardNo +"번 글이 없음.");
		Map<String, Object> pMap = new HashMap<>();
		pMap.put("boNo", boardNo);
		pMap.put("incType", "BOARD_HITS"); // replace text 활용
		dao.incrementCount(pMap);
		return board;		
	}
	
	@Override
	public List<TaskVO> retrieveSubmitTasks(Map<String, Object> searchMap) {
		
		for(Entry<String, Object> entry : searchMap.entrySet()) {
			log.info("key 값 : {} / value 값 : {}", entry.getKey(), entry.getValue());
		}
		
		return dao.selectSubmitTasks(searchMap);
	}

/*	@Override 교수만의 기능
	public ServiceResult createTask(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyTask(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeTask(BoardVO board) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public AttatchVO download(int attNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 과제 제출(첨부파일 저장)
	private void saveSubmitTaskAttatch(TaskVO task) {
		
		try {
			List<AttatchVO> atchList = task.getAttatchList();
			if(atchList != null && atchList.size() > 0) {
				dao.insertSubmitTaskAttatch(task);
				for(AttatchVO atch : atchList) {
					atch.saveTo(saveFolder);
				}				
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	
	}
	
	@Override
	public void createSubmitTask(TaskVO task) throws Exception{
		
		dao.insertSubmitTask(task);
		
		saveSubmitTaskAttatch(task);
	}
	
	// 과제 수정(첨부파일 삭제)
	private void deleteSubmitTaskAttatch(TaskVO task) {
		String[] delAttNos = task.getDelAttNos();
		String[] delAttOgFileNames = task.getDelAttOgFileNames();
		
		if((delAttNos != null && delAttNos.length > 0) && (delAttOgFileNames != null && delAttOgFileNames.length > 0)) {
			dao.deleteSubmitTaskAttatch(task);
			for(String ogName : delAttOgFileNames) {
				FileUtils.deleteQuietly(new File(saveFolder, ogName));				
			}
		}
	}

	@Override
	public void modifySubmitTask(TaskVO task) throws Exception {
		dao.updateSubmitTask(task);
		
		saveSubmitTaskAttatch(task);
		
		deleteSubmitTaskAttatch(task);
	}

	

	

}
