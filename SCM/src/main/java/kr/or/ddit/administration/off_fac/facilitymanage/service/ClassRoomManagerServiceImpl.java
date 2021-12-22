package kr.or.ddit.administration.off_fac.facilitymanage.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.off_fac.facilitymanage.dao.ClassRoomManagerDAO;
import kr.or.ddit.administration.univ_aca.croommanager.dao.ClassRoomDao;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.FacilityVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClassRoomManagerServiceImpl implements ClassRoomManagerService {
	
	
	@Inject
	private ClassRoomManagerDAO dao;

	@Override
	public ServiceResult createClassRoom(FacilityVO classRoom) {
		ServiceResult result = null;
		int res = dao.createClassRoom(classRoom);
		if(res > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

}
