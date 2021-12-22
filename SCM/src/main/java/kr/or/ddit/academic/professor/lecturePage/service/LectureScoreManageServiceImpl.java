package kr.or.ddit.academic.professor.lecturePage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.professor.lecturePage.dao.LectureScoreManageDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class LectureScoreManageServiceImpl  implements LectureScoreManageService{

	@Inject
	private LectureScoreManageDAO dao; 
	
	@Override
	public List<ClassListVO> retrieveTotalScoreList(PagingVO<ClassListVO> pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO); 
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectScoreList(pagingVO);	
	}

	@Override
	public Map<String, Integer> retrieveScorePercentage(String classNo) {

		return dao.selectScorePercentage(classNo);
	}

	@Override
	public List<ClassListVO> retrieveNullScoreList(String classNo) {
		return dao.selectNullScoreList(classNo);
	}

	//점수 수정하기 
	@Override
	public int modifyScore(ClassListVO student) {
		 int result = dao.updateScore(student);
		 //프로시저
		 Map<String, Object> pMap = new HashMap<>();
		 log.info("서비스단에서 student 의 강의번호/학생id/중간/기말 : " + student.getClassNo() 
		 +" / "+student.getMemNo() +" / "+ student.getClassMid()  +" / "+ student.getClassFin()
		 );		 
		 pMap.put("classNo", student.getClassNo() );

		 pMap.put("memNo", student.getMemNo());
		 pMap.put("classMid", student.getClassMid());
		 pMap.put("classFin", student.getClassFin());

		 dao.totalScoreProc(pMap);
		
		 
		 return result ; 
	}


	
}
