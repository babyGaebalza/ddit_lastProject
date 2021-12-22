package kr.or.ddit.administration.univ_aca.croommanager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.FacilityVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.PushVO;
import kr.or.ddit.vo.ReservationVO;

@Repository
public interface ClassRoomDao {
	
	//강의실 전체 수
	public int selecttotalCRoom(PagingVO<FacilityVO> pagingVO);
	
	//강의실 전체 현황 조회
	public List<FacilityVO> selectCRoomList(PagingVO<FacilityVO> pagingVO);
	

	//특정강의실정보
	public FacilityVO selectcroomInfo(String facNo);
	//강의실예약정보 수
	public int selecttotalRes(PagingVO<ReservationVO> pagingVO);
	//특정강의실예약정보
	public List<ReservationVO> retrieveCRresList(PagingVO<ReservationVO> pagingVO);
	
	//강의실 정보 수정
	public int updateCRoom(FacilityVO classRoom);
	
	//신규 강의실 등록
	public int insertCRoom(FacilityVO classRoom);
	
	//전공목록
	public List<MajorVO> selectMajorCode();
	
	//단과대목록
	public List<CategoryVO> selectCollegeCode();
	
	
	//알림보내기
	public int insertPush(PushVO push);
	
	//알림대상
	public List<MemberVO> selectWho(String major);
}
