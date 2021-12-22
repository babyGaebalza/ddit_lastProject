package kr.or.ddit.academic.professor.lecture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.academic.professor.lecture.dao.LectureMainDAO;
import kr.or.ddit.academic.student.lecture.dao.StudentLecturePageDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;
@Service
public class LectureMainServiceImpl implements LectureMainService{

	@Inject
	private LectureMainDAO dao; 
	@Inject 
	private StudentLecturePageDAO studentLecturePageDAO;
	
	@Override
	public Map<String, Object> retrieveProfessorLecturePage(Map<String, Object> professorLecturePageMap) {

		Map<String, Object> resultMap = new HashMap<>();
		
		List<BoardVO> lectureNoticeList = studentLecturePageDAO.selectLectureNotice(professorLecturePageMap);
		List<BoardVO> ongoingTaskList = dao.selectOngoingTaskSubmit(professorLecturePageMap);
		
		resultMap.put("lectureNoticeList", lectureNoticeList);
		resultMap.put("ongoingTaskList", ongoingTaskList);
		
		return resultMap;
	}

	@Override
	public List<ClassVO> retrieveMyLecture(ClassVO myClass) {
		return  dao.selectMyClassList(myClass);
	}
	
	
	@Override
	public List<ClassListVO> retrieveClassList(String classNo) {
		return dao.selectClassList(classNo);
	}

	
	@Override
	public Map<String, Integer> stuAttend(List<ClassListVO> classList) {
		Map<String, Integer> attendRes = new HashMap<String, Integer>();
		int ok = 0;
		int fail = 0;
		
			for(ClassListVO listMember : classList) {
				try {
					int result = dao.inputAttend(listMember);
					if(result > 0) {
						ok++;
					}
					else {
						fail++;
					}						
				} catch (Exception e) {
					fail++;
				}
			}
			attendRes.put("ok", ok);
			attendRes.put("fail", fail);
			
		return attendRes;
	}

	//교수 전공과목
	@Override
	public String getMajorName(String memId) {
		String majorName = dao.selectProfMajorName(memId);
		
		if(StringUtils.isNotBlank(majorName)) {
			return majorName; 
		}else {
			majorName = "해당전공없음"; 
		}
		return majorName;
	}


	



}
