package kr.or.ddit.academic.student.lecture.service;

import java.util.List;
import java.util.Map;

import javax.naming.LimitExceededException;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.ClassVO;

public interface StudentLectureService {
	/**
	 * 단과대학(공통코드)의 리스트를 가져오는 메서드 
	 * @return 단과대학(공통코드) 리스트
	 */
	public List<CategoryVO> retrieveCollegeList();
	
	/**
	 * 해당 학기에 개설된 모든 강의를 map 형태로 가져오는 메서드
	 * @return key : classNo(property) / value : classVO
	 */
	public Map<String, ClassVO> retrieveSemesterClassMap(String semester);
	
	/**
	 * 수강신청 폼에서 검색조건을 받아 강의리스트를 검색하는 메서드
	 * @param searchOption 검색조건 
	 * @return 강의리스트
	 */
	public List<ClassVO> retrieveClassList(ClassVO searchOption);
	
	/**
	 * 강의바구니를 조회하는 메서드
	 * @param memId
	 * @return
	 */
	public List<ClassVO> retrieveCartList(String memId);
	
	/**
	 * authMember의 memId와 폼에서 받은 classNo를 map에 담아 찜 기능을 구현하는 메서드
	 * @param cartRegisterMap 찜하는 학생/찜하는 강의 가 담겨있는 map
	 * @return OK - 성공/ Fail - 실패
	 */
	public ServiceResult createCartRegister(Map<String, Object> cartRegisterMap);
	
	/**
	 * 강의 바구니에 담긴 강의를 삭제하는 메서드
	 * @param cartRegisterMap
	 * @return
	 */
	public ServiceResult removeCartRegister(Map<String, Object> cartRegisterMap);
	
	/**
	 * 학생(로그인 기반 정보)이 현재 학기에 수강신청한 강의 리스트/시간표를 조회하는 메서드
	 * @param searchMap
	 * @return 
	 */
	public Map<String, Object> retrieveClassRegisterAndStuTimeTableList(Map<String, Object> searchMap);
	
	/**
	 * 수강신청 가능여부를 체크해주는 메서드
	 * 3. 이미 수강신청이 되었는지
	 * 4. 신청가능한 학점을 초과하는지
	 * 5. 시간표가 겹치지 않는지
	 * 6. 재수강이 적용되는 강의인지
	 * @param classRegisterMap
	 * @return key-result(3-PKDUPLICATED, 4-LIMITEXCEED, 5-TIMEDUPLICATED, 6-RETAKE(포함 key-classListNo) / 모두 통과-OK)
	 */
	public Map<String, Object> retrieveRegistarableCheck(Map<String, Object> classRegisterMap);
	/**
	 * 수강신청하는 메서드
	 * @param classRegisterMap
	 * @return OK-성공 / {@link Exception} DB CUD 실패 / {@link LimitExceededException} 수강신청 인원제한으로 실패
	 */
	public ServiceResult createClassRegister(Map<String, Object> classRegisterMap) throws Exception;
	/**
	 * 수강신청을 취소하는 메서드
	 * @param classRegisterMap
	 * @return OK-성공 / {@link Exception} DB CUD 실패
	 */
	public ServiceResult deleteClassRegister(Map<String, Object> classRegisterMap) throws Exception;
	/**
	 * 강의바구니 기간 변경
	 */
	public void modifyCartChange();
	/**
	 * 수강신청 기간 변경
	 */
	public void modifyRegiChange();
}
