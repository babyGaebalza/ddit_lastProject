package kr.or.ddit.administration.off_acc.enrollmentmanage.service;

import java.util.List;

import kr.or.ddit.administration.vo.TuitionVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;

public interface EnrollmentManageService {
	//학교 학생 리스트
	public List<TuitionVO> retrieveStudentList(PagingVO<TuitionVO> pagingVO);

	//학생 등록금 확인 리스트
	public List<TuitionVO> retrieveStudentCheckList(PagingVO<TuitionVO> pagingVO);

	//등록금 insert
	public ServiceResult createTuition(TuitionVO tuition);
	
	//신입생 학생 리스트
//	public List<TuitionVO> retrieveNewStudentList(PagingVO<TuitionVO> pagingVO);
}
