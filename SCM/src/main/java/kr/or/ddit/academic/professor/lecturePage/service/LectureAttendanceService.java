package kr.or.ddit.academic.professor.lecturePage.service;

import java.util.List;

import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.PagingVO;

public interface LectureAttendanceService {

	public List<AttendanceVO> retrieveAttendanceList(PagingVO<AttendanceVO> pagingVO);
	
	public List<AttendanceVO> retrieveClassAtt(AttendanceVO Info);

	public int retrieveStuCount(String classNo);
	
}

