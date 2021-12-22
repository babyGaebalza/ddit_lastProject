package kr.or.ddit.academic.professor.lecturePage.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.PagingVO;

public interface LectureScoreManageService {

	//리스트 
	public List<ClassListVO> retrieveTotalScoreList(PagingVO<ClassListVO> pagingVO); 
	
	//구글 차트-성적평가비율 뽑아내기 
	public Map<String, Integer> retrieveScorePercentage(String classNo);

	//미입력 상태인 기말, 중간, 학생 리스트 뽑아내기 
	public List<ClassListVO> retrieveNullScoreList(String classNo);

	public int modifyScore(ClassListVO student); 

}
