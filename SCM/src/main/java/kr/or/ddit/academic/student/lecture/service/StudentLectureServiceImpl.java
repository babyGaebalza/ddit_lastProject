package kr.or.ddit.academic.student.lecture.service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.naming.LimitExceededException;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.student.lecture.dao.StudentLectureDAO;
import kr.or.ddit.academic.vo.StuTimeTableVO;
import kr.or.ddit.common.category.dao.CategoryDAO;
import kr.or.ddit.common.schedule.dao.ScheduleDAO;
import kr.or.ddit.enumpkg.ClassKindCode;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.ClassListVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentLectureServiceImpl implements StudentLectureService {
	
	@Inject
	private CategoryDAO categoryDAO;
	@Inject
	private StudentLectureDAO studentLectureDAO;
	@Inject
	private ScheduleDAO scheduleDAO;
	
	@Override
	public List<CategoryVO> retrieveCollegeList() {
		
		return categoryDAO.selectCategoryList("COLLEGE_CODE");
	}

	@Override
	public Map<String, ClassVO> retrieveSemesterClassMap(String semester) {
		List<ClassVO> semesterClassList = studentLectureDAO.selectSemesterClassList(semester);
		
		Map<String, ClassVO> semesterClassMap = new HashMap<>();
		
		for(ClassVO lecture : semesterClassList) {
			semesterClassMap.put(lecture.getClassNo(), lecture);
		}
		
		return semesterClassMap;
	}
	
	@Override
	public List<ClassVO> retrieveClassList(ClassVO searchOption) {
		
		return studentLectureDAO.selectClassList(searchOption);
	}
	
	@Override
	public List<ClassVO> retrieveCartList(String memId) {
		
		return studentLectureDAO.selectCartList(memId);
	}

	@Override
	public ServiceResult createCartRegister(Map<String, Object> cartRegisterMap) {
		
		ServiceResult result = null;
		// 이미 바구니에 들어있는 경우
		if(studentLectureDAO.selectCartInCheck(cartRegisterMap) > 0) {
			result = ServiceResult.PKDUPLICATED;
		
		// ServiceResult(ENUM).CARTMAX에 설정된 바구니 제한갯수 이상일경우
		}else if(studentLectureDAO.selectCountCart((String)cartRegisterMap.get("memId")) >= ServiceResult.CARTMAX.getValue()){
			result = ServiceResult.CARTMAX;
					
		// 수강신청 가능한 경우
		}else {
			int cnt = studentLectureDAO.insertCartRegister(cartRegisterMap);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}			
		}
		return result;
	}

	@Override
	public ServiceResult removeCartRegister(Map<String, Object> cartRegisterMap) {
		
		ServiceResult result = null;
		// 바구니에 들어있지 않은 경우
		if(studentLectureDAO.selectCartInCheck(cartRegisterMap) == 0) {
			result = ServiceResult.NOTEXIST;
		}else {
			// 바구니에 들어있는 경우
			int cnt = studentLectureDAO.deleteCartRegister(cartRegisterMap);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}	
		}
		
		return result;
	}
	
	@Override
	public Map<String, Object> retrieveClassRegisterAndStuTimeTableList(Map<String, Object> searchMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("classRegisterList", studentLectureDAO.selectRegisterClassList(searchMap));
		List<StuTimeTableVO> timeTableList = studentLectureDAO.selectSemesterTimeTableList(searchMap);
		Map<String, StuTimeTableVO> stuTimeTableMap = new HashMap<>();
		for(StuTimeTableVO dayTime : timeTableList) {
			stuTimeTableMap.put(dayTime.getStutimeDay(), dayTime);
		}
		resultMap.put("stuTimeTableMap", stuTimeTableMap);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> retrieveRegistarableCheck(Map<String, Object> classRegisterMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		MemberVO student = (MemberVO) classRegisterMap.get("student");
		ClassVO lecture = (ClassVO) classRegisterMap.get("class");
		
		ServiceResult result = null;
		
		// 3. 수강신청된 과목이 아니고
		if(studentLectureDAO.selectStudentRegisterCheck(classRegisterMap) <= 0) {
			int limitPoint = student.getMemRegisterlimit();					
			int allPoint = studentLectureDAO.selectStudentAllPoint(classRegisterMap);	// 현재 신청된 총 학점
			int lecturePoint = lecture.getClassPoint();									// 신청하려는 강의 학점
			// 4. 제한학점 내로 신청이 가능하면서
			if(allPoint + lecturePoint <= limitPoint) {
				List<StuTimeTableVO> lectureWeekdayTimeList = makeStuTimeTableVOList(lecture);
				classRegisterMap.put("lectureWeekdayTimeList", lectureWeekdayTimeList);
				int lectureCnt = lecture.getClassTime().split("/").length;				 // 강의의 요일수
				int cnt = studentLectureDAO.selectNotDuplicateWeekday(classRegisterMap); // 겹치지 않는 요일수
				// 5. 신청할 강의의 시간표가 겹치지 않으며
				if(cnt == lectureCnt) {
					String retakeClassNo = studentLectureDAO.selectRetakeClassNo(classRegisterMap);
					// 6. 재수강이 아니면
					if(retakeClassNo == null) {
						result = ServiceResult.OK;
					}else {
						// 6. 재수강이면
						result = ServiceResult.RETAKE;
						resultMap.put("retakeClassNo", retakeClassNo);
					}
				}else {
					// 5. 현재 시간표와 신청할 강의의 시간이 겹침
					result = ServiceResult.TIMEDUPLICATED;
				}
			}else {
				// 4. 제한학점을 초과하게 됨
				result = ServiceResult.LIMITEXCEED;
			}
		}else {
			// 3. 이미 수강신청된 과목
			result = ServiceResult.PKDUPLICATED;
		}
		resultMap.put("result", result);
		
		return resultMap;
	}
	
	// 강의 시간표를 각각의  StuTimeTableVO로 만들어 List를 반환하는 메서드
	private List<StuTimeTableVO> makeStuTimeTableVOList(ClassVO lecture){
		String classNo = lecture.getClassNo();							// 강의 수강번호
		String className = lecture.getClassName();						// 강의명
		String classRoom = lecture.getClassRoom();						// 강의실명
		String classTime = lecture.getClassTime();						// ex) "월12/화34"
		String[] classEachTimes = classTime.split("/");					// ex) {"월12", "화34"}
		List<StuTimeTableVO> lectureWeekdayTimeList = new ArrayList<>();// ex) 월12의 정보가 각각의 StuTimeTableVO가 되어 저장될 List 
		
		String timePlainName = null; // 시간표 컬럼의 기본이름을 저장하는 변수 (ex. stutime1 -> stutime)(DB상에서 기본이름 뒤에 숫자가 붙은 규칙은 유지된다)
		for(String classEachTimeByString : classEachTimes) {
			StuTimeTableVO classEachTime = new StuTimeTableVO();
			String day = String.valueOf(classEachTimeByString.charAt(0));// ex) 월(요일)
			classEachTime.setStutimeDay(day);							// 요일 set
			// reflection 
			Class targetVO = classEachTime.getClass();
			
			// 시간표 컬럼의 기본이름을 처음 한번만 정해주는 조건반복문
			if(timePlainName == null) {
				Field[] fields = targetVO.getDeclaredFields();
				for(Field field : fields) {
					String fieldName = field.getName();
					if(fieldName.contains("1")) {
						timePlainName = fieldName.replace("1", "");
						break;
					}
				}
			}
			
			for(int i = 1; i < classEachTimeByString.length(); i++) { // 12 -> 1, 2
				String timeDetail = String.valueOf(classEachTimeByString.charAt(i));
				try {
					PropertyDescriptor pd = new PropertyDescriptor(timePlainName + timeDetail, targetVO);
					Method setter = pd.getWriteMethod(); // 각 시간에 맞는 setter
					setter.invoke(classEachTime, String.format("(%s)<br>%s<br>/%s", classNo, className, classRoom));	 // 각 시간에 맞는 setter 호출해 set(강의명/강의실명)
				} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
			
			lectureWeekdayTimeList.add(classEachTime);
			
		}
		
		return lectureWeekdayTimeList;
	}

	@Override
	public ServiceResult createClassRegister(Map<String, Object> classRegisterMap) throws Exception{
		
		// 신청할 강의에 이수구분 적용(복수, 부전공 고려)
		setClassCode(classRegisterMap);
		
		// 수강신청 강의 수강현황리스트 입력
		int cnt = studentLectureDAO.insertClassRegister(classRegisterMap);
		// 수강신청 강의 시간표 변경
		ClassVO registerClassInfo = (ClassVO) classRegisterMap.get("registerClassInfo");
		List<StuTimeTableVO> lectureWeekdayTimeList = makeStuTimeTableVOList(registerClassInfo);
		for(StuTimeTableVO lectureWeekdayTime : lectureWeekdayTimeList) {
			classRegisterMap.put("lectureWeekdayTime", lectureWeekdayTime);			
			studentLectureDAO.updateStuTimeTableForNewRegister(classRegisterMap);
		}
		// 강의정보리스트 수강인원현황 + 1 변경
		String classNo = registerClassInfo.getClassNo();
		studentLectureDAO.updateClassPersonPlus(classNo);
		// 수강신청할 강의 현재 인원 조회
		ClassVO registerClass = studentLectureDAO.selectClass(classNo);
		if(registerClass.getClassPerson() > registerClass.getClassMax()) {
			throw new LimitExceededException();
		}
		
		return ServiceResult.OK;
	}
	
	// 수강신청 강의의 이수구분을 학생정보에 맞게 적용해주는 메서드
	private void setClassCode(Map<String, Object> classRegisterMap) {
		MemberVO member = (MemberVO) classRegisterMap.get("member");	// 로그인 기반 학생 정보
		String major = member.getMemMajor();							// 주전공
		String majorDouble = member.getMajorDouble();					// 복수전공
		String minor = member.getMajorMinor();							// 부전공
		ClassVO registerClassInfo = (ClassVO) classRegisterMap.get("registerClassInfo");
		String classCode = registerClassInfo.getClassCode();
		String classKind = ClassKindCode.valueOf(classCode).getCategoryName();
		String majorCode = registerClassInfo.getMajorCode();
		
		
		// 강의가 교양이나 주전공이 아닌경우
		if(!(classKind.equals("교양선택") || classKind.equals("교양필수") || majorCode.equals(major))){
			
			if(majorCode.equals(majorDouble)) {
				// 복수전공인 경우
				if(classCode.equals("CL01")) {
					//선택인 경우
					classCode = "CL05";
				}else if(classCode.equals("CL02")) {
					// 필수인 경우
					classCode = "CL06";
				}
			}else if(majorCode.equals(minor)) {
				// 부전공인 경우
				classCode = "CL08";
			}
		}
		// 신청할 강의VO에 이수구분 적용
		((ClassListVO)classRegisterMap.get("registerClass")).setClassCode(classCode);
	}

	@Override
	public ServiceResult deleteClassRegister(Map<String, Object> classRegisterMap) throws Exception {
		
		// 수강신청 취소(신청강의 삭제)
		studentLectureDAO.deleteClassRegister(classRegisterMap);
		
		// 수강신청 취소(학생시간표 변경)
		ClassVO registerClassInfo = (ClassVO) classRegisterMap.get("registerClassInfo");
		List<StuTimeTableVO> lectureWeekdayTimeList = makeStuTimeTableVOList(registerClassInfo);
		for(StuTimeTableVO lectureWeekdayTime : lectureWeekdayTimeList) {
			classRegisterMap.put("lectureWeekdayTime", lectureWeekdayTime);			
			studentLectureDAO.updateStuTimeTableForDelRegister(classRegisterMap);
		}
		
		// 강의정보리스트 수강인원현황- 1 변경
		studentLectureDAO.updateClassPersonMinus(registerClassInfo.getClassNo());
		
		return ServiceResult.OK;
	}

	@Override
	public void modifyCartChange() {
		scheduleDAO.updateCartChangeOne();
		scheduleDAO.updateCartChangeTwo();
		
	}

	@Override
	public void modifyRegiChange() {
		scheduleDAO.updateRegiChangeOne();
		scheduleDAO.updateRegiChangeTwo();
		
	}

}
