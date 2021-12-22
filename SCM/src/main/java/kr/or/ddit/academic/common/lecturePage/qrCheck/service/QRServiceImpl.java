package kr.or.ddit.academic.common.lecturePage.qrCheck.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.common.lecturePage.qrCheck.dao.QRDao;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;

@Service
public class QRServiceImpl implements QRService{

	@Inject
	private QRDao dao;

	@Override
	public ServiceResult checkStuService(Map<String, String> classMember) {
		ServiceResult result;
		//수강하고있는 학생인지 확인
		int res = dao.checkStudent(classMember);
		
		if(res > 0 ) {
			//출석처리
			
			result = ServiceResult.OK;
		}
		else {
			//수강학생이 아님
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

	
	
	@Override
	public ServiceResult attendStudent(Map<String, String> classMember) {
		ServiceResult result = null;
		int res = dao.attendStudent(classMember);
		if(res > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;			
		}
		return result;
	}



	@Override
	public ClassVO getClassInfo(String classNo) {
		ClassVO classinfo = dao.classInfo(classNo);
		return classinfo;
	}



	@Override
	public ClassListVO getClassListInfo(Map<String, String> classAndmember) {
			ClassListVO classListInfo = dao.classListInfo(classAndmember);
		return classListInfo;
	}
}
