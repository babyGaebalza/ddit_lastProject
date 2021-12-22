package kr.or.ddit.administration.univ_aca.croommanager.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_aca.croommanager.dao.ClassRoomDao;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PushVO;
import kr.or.ddit.vo.ReservationVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClassRoomServiceImpl implements ClassRoomService {

	@Inject
	private ClassRoomDao dao;

	@Override
	public List<FacilityVO> retrieveCRoomList(PagingVO<FacilityVO> pagingVO) {
		List<FacilityVO> classroomList = dao.selectCRoomList(pagingVO);
		pagingVO.setTotalRecord(dao.selecttotalCRoom(pagingVO));
		pagingVO.setDataList(classroomList);
		return classroomList;
	}

	@Override
	public FacilityVO selectcroomInfo(String facNo) {
		return dao.selectcroomInfo(facNo);
	}

	@Override
	public List<ReservationVO> retrieveCRresList(PagingVO<ReservationVO> pagingVO) {
		List<ReservationVO> resList = dao.retrieveCRresList(pagingVO);
		pagingVO.setTotalRecord(10);
		pagingVO.setDataList(resList);
		return resList;
	}

	@Override
	public ServiceResult modifyCRoom(FacilityVO classRoom) {
		ServiceResult result = null;
		int res = dao.updateCRoom(classRoom);
		
		if(res > 0) {
			result = ServiceResult.OK;
		}
		else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public int createCRoom(FacilityVO classRoom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MajorVO> retrieveMajorCode() {
		List<MajorVO> majorList = dao.selectMajorCode();
		return majorList;
	}

	@Override
	public List<CategoryVO> retrieveCollegeCode() {
		List<CategoryVO> collegeList = dao.selectCollegeCode();
		return collegeList;
	}

	@Override
	public int insertPush(PushVO push) {
		String major = push.getMajor();
		log.info("전공 : {}", major);
		List<MemberVO> whoList = dao.selectWho(major);
		push.setWhoList(whoList);

		int res = dao.insertPush(push);
		
		return res;
	}

}
