package kr.or.ddit.administration.univ_aca.croommanager.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PushVO;
import kr.or.ddit.vo.ReservationVO;

public interface ClassRoomService {

	
	//강의실 전체 현황 조회
	public List<FacilityVO> retrieveCRoomList(PagingVO<FacilityVO> pagingVO);
	

	//특정강의실정보
	public FacilityVO selectcroomInfo(String facNo);
	
	//특정강의실예약정보
	public List<ReservationVO> retrieveCRresList(PagingVO<ReservationVO> pagingVO);
	
	
	//강의실 정보 수정
	public ServiceResult modifyCRoom(FacilityVO classRoom);
	
	//신규 강의실 등록
	public int createCRoom(FacilityVO classRoom);
	
	//전공목록
	public List<MajorVO> retrieveMajorCode();
	
	//단과목록
	public List<CategoryVO> retrieveCollegeCode();
	
	//알림보내기
	public int insertPush(PushVO push);
	
}
