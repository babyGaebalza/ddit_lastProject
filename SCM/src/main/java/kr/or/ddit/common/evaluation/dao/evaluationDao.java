package kr.or.ddit.common.evaluation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.administration.vo.EvalcontentVO;
import kr.or.ddit.administration.vo.EvaluationVO;
import kr.or.ddit.vo.ClassListVO;

@Repository
public interface evaluationDao {

	//강의번호로 강의이름 가져오기
	public String getClassName(String classNo);
	
	
	//강의평가 여부체크
	public int evaCheck(ClassListVO classinfo);
	
	
	//강의평가 설문지폼 DB입력
	public int insertEvaForm(EvalcontentVO eval);
	
	//학생수강여부체크 -> 수강번호
	public String stuClassCheck(ClassListVO classList);
	
	//학생 강의평가 체크 여부
	public int stuEvaCheck(EvaluationVO eva);
	
	//강의평가 폼 가져오는 부분
	public List<EvalcontentVO> getEvalContent(String classNo);
	
	
	
	
	//강의평가 내용 입력
	public int insertEvaluation(EvaluationVO evaluation);
	
	//강의평가 여부 처리
	public int insertEvaCheck(ClassListVO inevacheck);
	
	//강의평가한 인원수
	public List<String> getEvaCount(String classNo);
	
	//질문수
	public Integer getQCount(String classNo);
	
	
	//교수 강의평가 결과 조회 (강의평가 내용 전체 가져오기)
	public List<EvaluationVO> getpropEvaResult(String classNo);
	
	//교수 강의평가 결과 조회 (강의평가 내용-문장형 가져오기)
	public List<String> getpropEvaContResult(String classNo);
}
