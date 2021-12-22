package kr.or.ddit.administration.off_acc.enrollmentmanage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.off_acc.enrollmentmanage.dao.EnrollmentManageDAO;
import kr.or.ddit.administration.vo.TuitionVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class EnrollmentManageServiceImpl implements EnrollmentManageService {
	@Inject
	private EnrollmentManageDAO dao;

	@Override
	public List<TuitionVO> retrieveStudentList(PagingVO<TuitionVO> pagingVO) {
		List<TuitionVO> studentList = dao.selectStudentList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCount(pagingVO));
		pagingVO.setDataList(studentList);
		
		return studentList;
	}

	@Override
	public List<TuitionVO> retrieveStudentCheckList(PagingVO<TuitionVO> pagingVO) {
		List<TuitionVO> studentList = dao.selectStudentCheckList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCheck(pagingVO));
		pagingVO.setDataList(studentList);
		
		return studentList;
	}

	@Override
	public ServiceResult createTuition(TuitionVO tuition) {
		ServiceResult result = null;
		
		int rowcnt = dao.insertTuition(tuition);
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		
		return result;
	}

//	@Override
//	public List<TuitionVO> retrieveNewStudentList(PagingVO<TuitionVO> pagingVO) {
//		List<TuitionVO> studentList = dao.selectNewStudentList(pagingVO);
//		pagingVO.setTotalRecord(dao.selectNewStudentCount(pagingVO));
//		pagingVO.setDataList(studentList);
//		
//		return studentList;
//	}
}
