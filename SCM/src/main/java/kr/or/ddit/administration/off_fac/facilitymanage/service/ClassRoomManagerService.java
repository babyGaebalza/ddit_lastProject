package kr.or.ddit.administration.off_fac.facilitymanage.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;

public interface ClassRoomManagerService {

	//새 강의실 등록
	public ServiceResult createClassRoom(FacilityVO classRoom);
}
