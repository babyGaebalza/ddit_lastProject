package kr.or.ddit.academic.professor.myStudent.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public interface ManageStudentService {

/*	public List<DocumentVO> retrieveStuTrackList(PagingVO<DocumentVO> pagingVO);
*/
	public ServiceResult adminTrack(String docuNo, String stuId, String trackNo)  ;

	public ServiceResult cancleTrack(String docuNo, String stuId);

/*	public List<DocumentVO> retrieveStuGraduList(PagingVO<DocumentVO> pagingVO);
*/
	public List<DocumentVO> retrieveMyStudentList(PagingVO<DocumentVO> pagingVO);

	public MemberVO getMyStudent(String stuId);

	public ServiceResult cancleGradu(String docuNo, String stuId);

	public ServiceResult adminGradu(String docuNo, String stuId);
}
