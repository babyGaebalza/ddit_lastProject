package kr.or.ddit.academic.professor.myStudent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.professor.myStudent.dao.ManageStudentDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ManageStudentServiceImpl implements ManageStudentService {


	@Inject
	private ManageStudentDAO dao ;
	
	@Override
	public List<DocumentVO> retrieveMyStudentList(PagingVO<DocumentVO> pagingVO) {
		int totalRecord = dao.selectTotalToDoRecord(pagingVO); 
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectToDoList(pagingVO);
	}
	
	//트랙승인 
	@Override
	public ServiceResult adminTrack(String docuNo, String stuId, String trackNo) {
		ServiceResult result = null; 
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("stuId", stuId);
		paramMap.put("trackNo", trackNo);

		int x  = dao.updateMyStudentTrack(docuNo); 
			x += dao.updateMemberTrackCol(paramMap); 
		
		if(x > 1 ) {
			result = ServiceResult.OK;		
			
		} 
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	//졸업승인
	//졸업승인
	@Override
	public ServiceResult adminGradu(String docuNo, String stuId) {
		ServiceResult result = null; 

		int x  = dao.updateMyStudentTrack(docuNo); 
			x += dao.updateMemberGraduCol(stuId); 
			if(x > 1 ) {
				result = ServiceResult.OK;		
			} 
			else {
				result = ServiceResult.FAILED;
			}
			return result;
	}
	
	@Override
	public ServiceResult cancleTrack(String docuNo, String stuId) {

		ServiceResult result = null; 
		int x  = dao.cancleMyStudentTrack(docuNo);
			x += dao.cancleMemberTrackCol(stuId); 

		if(x > 1 ) {
			result = ServiceResult.OK;		
		} 
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	
	}


	@Override
	public MemberVO getMyStudent(String stuId) {
		return dao.getMyStudent(stuId);
	}


	//졸업취소 
	@Override
	public ServiceResult cancleGradu(String docuNo, String stuId) {
		// TODO Auto-generated method stub
		return null;
	}





}
