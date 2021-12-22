package kr.or.ddit.common.evaluation.service;

import java.util.List;

import kr.or.ddit.administration.vo.EvalcontentVO;
import kr.or.ddit.administration.vo.EvaluationVO;
import kr.or.ddit.vo.ClassListVO;

public interface evaluationService {

	//강의번호로 강의이름 가져오기
	public String getClassName(String classNo);
	
	//강의평가 폼 등록여부 체크
	public int retrieveEvaCheck(ClassListVO classinfo);
	
	//강의평가 설문지폼 DB입력
	public int createEvaForm(EvalcontentVO eval);
	
	
	//학생이 이 강의를 듣는지 체크하는 부분 -> 리턴은 수강번호
	public String retrieveStuClassCheck(ClassListVO classList);
	
	//학생 강의평가여부 체크하는 부분
	public int retrieveStuEvaCheck(EvaluationVO eva);
	
	
	//저장된 강의평가 폼 가져오는 부분
	public List<EvalcontentVO> retrieveEvalContent(String classNo);
	
	
	
	
	//강의평가 입력(제출된것)
	public int createEvaluation(EvaluationVO evaluation);
	
	//강의평가(문장형)등록 -> 강의평가 완료처리
	public int insertEvaCheck(ClassListVO inevacheck);
	
	//강의평가한 인원수
	public List<String> getEvaCount(String classNo);
	
	//질문수
	public int getQCount(String classNo);
	

	//교수 강의평가 결과 조회 (강의평가 내용 전체 가져오기)
	public List<EvaluationVO> propEvaResult(String classNo);
	
	//교수 강의평가 결과 조회 (강의평가 내용-문장형 가져오기)
	public List<String> propEvaContResult(String classNo);

}
