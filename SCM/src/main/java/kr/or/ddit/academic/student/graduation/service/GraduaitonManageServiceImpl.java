package kr.or.ddit.academic.student.graduation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.academic.student.graduation.dao.GraduaitonManageDAO;
import kr.or.ddit.enumpkg.ClassKindCode;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GraduaitonManageServiceImpl implements GraduaitonManageService{
	
	@Inject
	private GraduaitonManageDAO gmDAO;
	
	//--------------------------------------------------------------------- 졸업관리 ----------------------------------------------------------------------------
	@Override
	public Map<String, Object> retrieveStudentGraduationPage(MemberVO authMember) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> trackDetailSearchMap = new HashMap<>();
		trackDetailSearchMap.put("trackNo", authMember.getMemTrack());
		log.info("확인" + authMember.getMemTrack());
		log.info("확인" + authMember.getMemTrack());
		log.info("확인" + authMember.getMemTrack());
		log.info("확인" + authMember.getMemTrack());
		log.info("확인" + authMember.getMemTrack());
		log.info("확인" + authMember.getMemTrack());

		trackDetailSearchMap.put("authMember", authMember);
		resultMap.put("trackDetail", gmDAO.selectTrackInfoAndSatisfied(trackDetailSearchMap));
		
		Map<String, Object> pointByCodeMap = new HashMap<>();
		// 엔트리 형태로 가공
		for(Map<String, Object> graudatePoint : gmDAO.selectGraduatePointList(authMember)) {
			pointByCodeMap.put((String)graudatePoint.get("classCode"), graudatePoint.get("pointByCode"));
		}
		List<Map<String, Object>> graduatePointList = new ArrayList<>();
		for(ClassKindCode code : ClassKindCode.values()) {
			Map<String, Object> graduatePointByCodeMap = new HashMap<>();
			graduatePointByCodeMap.put("classCodeName", code.getCategoryName());
			graduatePointByCodeMap.put("codePoint", code.getCodePoint());
			int getedPoint = 0;
			// 해당 이수구분 학점을 1학점 이상 이수했다면 
			if(pointByCodeMap.containsKey(code.name())) getedPoint = Integer.parseInt((String)pointByCodeMap.get(code.name()));
			graduatePointByCodeMap.put("getedPoint", getedPoint);
			graduatePointList.add(graduatePointByCodeMap);
		}
		
		String graduationState = null;
		if(StringUtils.contains(authMember.getMemSemester(), "RC07")) {
			graduationState = "graduate";
		}else if(gmDAO.selectGraduateRegister(authMember) > 0) {
			graduationState = "wait";
		}
		
		log.info("졸업 신청 상태  :{}", graduationState);
		
		resultMap.put("graduatePointList", graduatePointList);
		resultMap.put("classKindCodeList", ClassKindCode.values());
		resultMap.put("graduationState", graduationState);
		
		return resultMap;
	}
	
	@Override
	public ServiceResult createGraduationRegister(MemberVO authMember) {
		
		ServiceResult result = ServiceResult.FAILED;
		
		if(gmDAO.insertGraduationRegiser(authMember) > 0) result = ServiceResult.OK;
		
		return result;
	}
	
	//--------------------------------------------------------------------- 트랙관리 ----------------------------------------------------------------------------
	@Override
	public Map<String, Object> retrieveStudentTrackPage(MemberVO authMember) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("majorTrackList", gmDAO.selectMajorTrackList(authMember));
		resultMap.put("trackRegister", gmDAO.selectTrackRegister(authMember));
		
		
		return resultMap;
	}


	@Override
	public Map<String, Object> retrieveTrackInfoAndSatisfied(Map<String, Object> trackDetailSearchMap) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("trackDetail", gmDAO.selectTrackInfoAndSatisfied(trackDetailSearchMap));
		
		return resultMap;
	}


	@Override
	public ServiceResult createOrModifyTrackRegister(Map<String, Object> trackDetailSearchMap) {
		MemberVO authMember = (MemberVO)trackDetailSearchMap.get("authMember");
		DocumentVO already = gmDAO.selectTrackRegister(authMember);
		
		ServiceResult result = ServiceResult.FAILED;
		
		if(already == null) {
			int cnt = gmDAO.insertTrackRegister(trackDetailSearchMap);
			if(cnt > 0) result = ServiceResult.CREATESUCCESS;
		}else {
			int cnt = gmDAO.updateTrackRegister(trackDetailSearchMap);
			if(cnt > 0) result = result.MODIFYSUCCESS;
		}
		
		return result;
	}

}
