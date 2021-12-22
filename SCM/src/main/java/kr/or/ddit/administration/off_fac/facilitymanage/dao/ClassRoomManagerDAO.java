package kr.or.ddit.administration.off_fac.facilitymanage.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;

@Repository
public interface ClassRoomManagerDAO {

	//새 강의실 등록
	public int createClassRoom(FacilityVO classRoom);
}
