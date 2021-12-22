package kr.or.ddit.academic.professor.lecturePage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.professor.lecturePage.dao.LectureAttendanceDAO;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class LectureAttendanceServiceImpl implements LectureAttendanceService{

	@Inject
	private LectureAttendanceDAO dao;

	@Override
	public List<AttendanceVO> retrieveAttendanceList(PagingVO<AttendanceVO> pagingVO) {
		int totalRecord = dao.selectTotalRecord(pagingVO); 
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectAttendanceList(pagingVO);	
	}

	@Override
	public List<AttendanceVO> retrieveClassAtt(AttendanceVO Info) {
		return dao.selectClassAtt(Info);
	}

	@Override
	public int retrieveStuCount(String classNo) {
		return dao.selectCountStudent(classNo);
	}

	 
	
	
}
