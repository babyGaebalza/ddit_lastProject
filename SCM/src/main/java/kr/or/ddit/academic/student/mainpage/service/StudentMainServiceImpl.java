package kr.or.ddit.academic.student.mainpage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.student.mainpage.dao.StudentMainDAO;
import kr.or.ddit.common.board.dao.BoardDao;
import kr.or.ddit.common.schedule.dao.ScheduleDAO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;

@Service
public class StudentMainServiceImpl implements StudentMainService {

	@Inject
	private StudentMainDAO studentMainDAO;
	@Inject
	private ScheduleDAO scheduleDAO;
	@Inject
	private BoardDao boardDAO;
	
	
	@Override
	public Map<String, Object> retrieveMainContent(MemberVO authMember) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("timeTable", studentMainDAO.selectTimeTable(authMember.getMemId()));
		resultMap.put("currentSemester", scheduleDAO.selectCurrentSemester());
		
		Map<String, Object> noticeSearchMap = new HashMap<>();
		noticeSearchMap.put("boardCount", 3);
		noticeSearchMap.put("boardCode", "BC_100");
		resultMap.put("collegeNoticeList", boardDAO.selectLatestNoticeList(noticeSearchMap));
		noticeSearchMap.put("boardCode", "BC_101");
		noticeSearchMap.put("memMajor", authMember.getMemMajor());
		resultMap.put("majorNoticeList", boardDAO.selectLatestNoticeList(noticeSearchMap));
		
		return resultMap;
	}
	
	private void makeAndSetScoreMap(Map<String, Object> scoreMap) {
		
		// 학기별 makeAndSet
		makeAndSetSemesterScoreList(scoreMap);
		// 이수구분별 makeAndSet
		makeAndSetPointByCodeMap(scoreMap);
		// 전체 makeAndSet
		makeAndSetTotalScoreMap(scoreMap);
		
	}
	
	private void makeAndSetSemesterScoreList(Map<String, Object> scoreMap) {
		// 학기별 점수 list makeAndSet
		List<ClassListVO> classList = (List<ClassListVO>) scoreMap.get("classList");
		List<Map<String, Object>> semesterScoreList = new ArrayList<>();
		Map<String, Object> semesterMap = null;
		double cnt = 0;
		int registerPoint = 0;	// 신청학점
		int gettedPoint = 0;	// 이수학점
		double sumGradePoint = 0;	// 평점 합
		int sumScore = 0;		// 백분위점수 합
		
		for(ClassListVO eachClass : classList) {
			String classYear = eachClass.getClassYear();
			String classSemetser = eachClass.getClassSemester();
			
			if(semesterMap == null) {
				semesterMap = new HashMap<>();
				semesterMap.put("year", classYear);
				semesterMap.put("semester", classSemetser);
			}
			
			String mapYear = (String)semesterMap.get("year");
			String mapSemester = (String)semesterMap.get("semester");
			
			if(!classYear.equals(mapYear) || !classSemetser.equals(mapSemester)) {
				//일치하지 않으면
				semesterMap.put("registerPoint", registerPoint);
				semesterMap.put("gettedPoint", gettedPoint);
				semesterMap.put("avgGradePoint", Math.round(sumGradePoint/cnt*100)/100.0);
				semesterMap.put("avgScore", Math.round(sumScore/cnt*100)/100.0);
				semesterScoreList.add(semesterMap);
				
				cnt = 0;
				registerPoint = 0;	
				gettedPoint = 0;	
				sumGradePoint = 0;	
				sumScore = 0;
				
				semesterMap = new HashMap<>();
				semesterMap.put("year", classYear);
				semesterMap.put("semester", classSemetser);
			}
			
			// 현재 eachClass의 (연도/학기)가 누적될 map과 (연도/학기)와 일치할 때 - 누적시켜야 함
			
			ClassVO classInfo = eachClass.getClassInfo();
			cnt++;
			int classPoint = classInfo.getClassPoint();
			// 신청학점 합
			registerPoint += classPoint;
			// F가 아니면 이수학점 합
			if(!eachClass.getClassGrade().equals("F")) gettedPoint += classPoint;
			// 평점/백분위 합
			sumGradePoint += eachClass.getClassGradePoint();
			sumScore += eachClass.getClassScore();
		}
		
		if(semesterMap != null) {
			semesterMap.put("registerPoint", registerPoint);
			semesterMap.put("gettedPoint", gettedPoint);
			semesterMap.put("avgGradePoint", Math.round(sumGradePoint/cnt*100)/100.0);
			semesterMap.put("avgScore", Math.round(sumScore/cnt*100)/100.0);
			semesterScoreList.add(semesterMap);
		}
		
		scoreMap.put("semesterScoreList", semesterScoreList);
	}
	
	public static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> map) {
	    List<Map.Entry<String, Integer>> entries = new LinkedList<>(map.entrySet());
	    Collections.sort(entries, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

	    LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
	    for (Map.Entry<String, Integer> entry : entries) {
	        result.put(entry.getKey(), entry.getValue());
	    }
	    return result;
	}
	
	private void makeAndSetPointByCodeMap(Map<String, Object> scoreMap) {
		// 이수구분별 학점 Map makeAndSet
		List<ClassListVO> classList = (List<ClassListVO>) scoreMap.get("classList");
		
		Map<String, Integer> pointByCodeMap = new LinkedHashMap<>();
		
		for(ClassListVO eachClass : classList) {
			String classCodeName = eachClass.getClassCodeName();
			int classPoint = eachClass.getClassInfo().getClassPoint();
			String classGrade = eachClass.getClassGrade();
			
			if(!classGrade.equals("F")) {
				if(!pointByCodeMap.containsKey(classCodeName)) { 
					pointByCodeMap.put(classCodeName, classPoint);
				}else{
					pointByCodeMap.put(classCodeName, pointByCodeMap.get(classCodeName) + classPoint);
				}				
			}
		}
		
		scoreMap.put("pointByCodeMap", sortMapByValue(pointByCodeMap));
		
	}
	
	private void makeAndSetTotalScoreMap(Map<String, Object> scoreMap) {
		// 총학점 list makeAndSet
		List<Map<String, Object>> semesterScoreList = (List)scoreMap.get("semesterScoreList");
		List<ClassListVO> classList = (List<ClassListVO>) scoreMap.get("classList");
		
		Map<String, Object> totalScoreMap = new HashMap<>();
		
		int cnt = 0;
		int totalPoint = 0;
		double sumAvgSemesterGradePoint = 0;
		double sumAvgSemesterScore = 0;
		
		for(ClassListVO eachClass : classList) {
			
			if(!eachClass.getClassGrade().equals("F")) {
				cnt++;
				totalPoint += eachClass.getClassInfo().getClassPoint();
				sumAvgSemesterGradePoint += eachClass.getClassGradePoint();
				sumAvgSemesterScore += eachClass.getClassScore();			
			}
		}
		
		totalScoreMap.put("totalPoint", totalPoint);
		totalScoreMap.put("totalAvgGradePoint", Math.round(sumAvgSemesterGradePoint/cnt*100)/100.0);
		totalScoreMap.put("totalAvgScore", Math.round(sumAvgSemesterScore/cnt*100)/100.0);
		
		scoreMap.put("totalScoreMap", totalScoreMap);
	}
	
	@Override
	public Map<String, Object> retrieveScoreMap(String memId) {
		
		Map<String, Object> scoreMap = new HashMap<>();
		
		List<ClassListVO> classList = studentMainDAO.selectStudentClassList(memId);
		scoreMap.put("classList", classList);
		makeAndSetScoreMap(scoreMap);
		
		return scoreMap;
	}

	

}
