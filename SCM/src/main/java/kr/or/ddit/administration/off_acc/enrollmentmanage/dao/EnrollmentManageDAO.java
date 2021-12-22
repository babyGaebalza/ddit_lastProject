package kr.or.ddit.administration.off_acc.enrollmentmanage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.administration.vo.TuitionVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface EnrollmentManageDAO {
	//총 학교 학생 수
	public int selectTotalCount(PagingVO<TuitionVO> pagingVO);
	
	//전체 학생 리스트 수
	public List<TuitionVO> selectStudentList(PagingVO<TuitionVO> pagingVO);

	//학생 등록금 확인
	public int selectTotalCheck(PagingVO<TuitionVO> pagingVO);
	
	//학생 등록금 확인 리스트
	public List<TuitionVO> selectStudentCheckList(PagingVO<TuitionVO> pagingVO);
	
	//등록금 insert
	public int insertTuition(TuitionVO tuition);
	
	//신입생 학생 수
//	public int selectNewStudentCount(PagingVO<TuitionVO> pagingVO);
	
	//신입생 학생 리스트 수
//	public List<TuitionVO> selectNewStudentList(PagingVO<TuitionVO> pagingVO);
	
	//신입생 insert
//	public int insertNewStudent(MemberVO member);
}
