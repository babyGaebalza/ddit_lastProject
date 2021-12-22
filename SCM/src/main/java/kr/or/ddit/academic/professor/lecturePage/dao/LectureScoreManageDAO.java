package kr.or.ddit.academic.professor.lecturePage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface LectureScoreManageDAO {
	//전체 학생 점수 출력 
	public List<ClassListVO> selectScoreList(PagingVO<ClassListVO> pagingVO);  
	
	//전체 레코드 수 	
	public int selectTotalRecord(PagingVO<ClassListVO> pagingVO);

	//평가비율 뽑아내기
	public Map<String, Integer> selectScorePercentage(String classNo);

	//미입력상태인 학생, 중간, 기말 리스트 뽑아내기 
	public List<ClassListVO> selectNullScoreList(String classNo);

	//학생들 성적 일괄변경 여러번 업뎃할꺼임
	public int updateScore(ClassListVO studentList);

	//프로시저 호출
	public void totalScoreProc(Map<String, Object> pMap);

	
}
