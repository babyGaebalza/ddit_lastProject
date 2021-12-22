package kr.or.ddit.academic.student.lecture.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.student.lecture.dao.StudentLecturePageDAO;
import kr.or.ddit.academic.vo.StuTimeTableVO;
import kr.or.ddit.common.schedule.dao.ScheduleDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttendanceVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.ScheduleVO;

@Service
public class StudentLecturePageServiceImpl implements StudentLecturePageService {
	
	@Inject
	private StudentLecturePageDAO studentLecturePageDAO;
	@Inject
	private ScheduleDAO scheduleDAO;
	
	@Override
	public Map<String, Object> retrieveLecturePageListInfo(String memId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		List<ClassListVO> classList = studentLecturePageDAO.selectClassListByAuth(memId);
		ScheduleVO currentSemester = scheduleDAO.selectCurrentSemester();
		
		resultMap.put("classList", classList);
		resultMap.put("currentSemester", currentSemester);
		
		return resultMap;
	}
	
	
	@Override
	public Map<String, Object> retrieveStudentLecturePage(Map<String, Object> studentLecturePageMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		int[] times = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		studentLecturePageMap.put("times", times);
		ClassVO lectureInfo = studentLecturePageDAO.selectLectureInfo(studentLecturePageMap);
		Map<String, Integer> lectureTaskInfo = studentLecturePageDAO.selectLectureTaskInfo(studentLecturePageMap);
		List<StuTimeTableVO> lectureTimeTableList = studentLecturePageDAO.selectLectureTimeTableList(studentLecturePageMap);
		List<BoardVO> ongoingTaskList = studentLecturePageDAO.selectOngoingTask(studentLecturePageMap);
		List<BoardVO> lectureNoticeList = studentLecturePageDAO.selectLectureNotice(studentLecturePageMap);
		
		resultMap.put("lectureInfo", lectureInfo);
		resultMap.put("lectureTaskInfo", lectureTaskInfo);
		resultMap.put("lectureTimeTableList", lectureTimeTableList);
		resultMap.put("ongoingTaskList", ongoingTaskList);
		resultMap.put("lectureNoticeList", lectureNoticeList);
		
		return resultMap;
	}

	
	
	@Override
	public ClassListVO retrieveStudentClassList(AttendanceVO att) {
		ClassListVO classList = studentLecturePageDAO.selectClassList(att);
		return classList;
	}



	@Override
	public ServiceResult createStudentAttend(AttendanceVO att) {
		ServiceResult result = null;
		int res = studentLecturePageDAO.inputStuAttend(att);
		if(res > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}



	@Override
	public List<AttendanceVO> retrieveMyAttend(AttendanceVO att) {
		List<AttendanceVO> myAtt =  studentLecturePageDAO.selectMyAttend(att);
		return myAtt;
	}



	
	
	
	
}
