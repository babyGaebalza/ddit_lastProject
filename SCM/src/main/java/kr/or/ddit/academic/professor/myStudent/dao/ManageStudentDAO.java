package kr.or.ddit.academic.professor.myStudent.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface ManageStudentDAO {

	public int selectTotalToDoRecord(PagingVO<DocumentVO> pagingVO);

	public List<DocumentVO> selectToDoList(PagingVO<DocumentVO> pagingVO);

	public MemberVO getMyStudent(String stuId);

	
	//문서승인
	public int updateMyStudentTrack(String docuNo);
	//문서취소 
	public int cancleMyStudentTrack(String docuNo);

	//멤버테이블 트랙변경하기 
	public int updateMemberTrackCol(Map<String, String> paramMap);
    
	//멤버테이블 트랙없애기 
	public int cancleMemberTrackCol(String stuId);
	
	//멤버테이블 졸업승인 변경하기 
	public int updateMemberGraduCol(String stuId);

}
