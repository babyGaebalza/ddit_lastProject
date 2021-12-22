package kr.or.ddit.common.evaluation.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.vo.EvalcontentVO;
import kr.or.ddit.administration.vo.EvaluationVO;
import kr.or.ddit.common.evaluation.dao.evaluationDao;
import kr.or.ddit.vo.ClassListVO;

@Service
public class evaluationServiceImpl implements evaluationService {

	@Inject
	evaluationDao dao;

	@Override
	public String getClassName(String classNo) {
		return dao.getClassName(classNo);
	}
	
	
	
	@Override
	public int retrieveEvaCheck(ClassListVO classinfo) {
		return dao.evaCheck(classinfo);
	}

	@Override
	public int createEvaForm(EvalcontentVO eval) {		
		return dao.insertEvaForm(eval);
	}
	
	
	
	@Override
	public String retrieveStuClassCheck(ClassListVO classList) {
		String classListNo = "No";
		classListNo = dao.stuClassCheck(classList);
		return classListNo;
	}

	@Override
	public int retrieveStuEvaCheck(EvaluationVO eva) {
		return dao.stuEvaCheck(eva);
	}

	@Override
	public List<EvalcontentVO> retrieveEvalContent(String classNo) {
		return dao.getEvalContent(classNo);
	}
		


	
	
	
	
	
	@Override
	public int createEvaluation(EvaluationVO evaluation) {
		return dao.insertEvaluation(evaluation);
	}

	@Override
	public int insertEvaCheck(ClassListVO inevacheck) {
		return dao.insertEvaCheck(inevacheck);
	}
	
	@Override
	public List<String> getEvaCount(String classNo) {
		return dao.getEvaCount(classNo);
	}
	
	@Override
	public int getQCount(String classNo) {
		return dao.getQCount(classNo);
	}	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<EvaluationVO> propEvaResult(String classNo) {
		return dao.getpropEvaResult(classNo);
	}

	@Override
	public List<String> propEvaContResult(String classNo) {
		return dao.getpropEvaContResult(classNo);
	}







}
