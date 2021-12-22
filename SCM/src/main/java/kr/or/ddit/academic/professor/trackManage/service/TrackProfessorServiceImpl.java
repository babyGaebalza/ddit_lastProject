package kr.or.ddit.academic.professor.trackManage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.academic.professor.trackManage.dao.TrackProfessorDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TrackVO;
@Service
public class TrackProfessorServiceImpl implements  TrackProfessorService{

	@Inject
	private TrackProfessorDAO trackDAO;
	
	//트랙생성
	@Override
	public ServiceResult createTrack(TrackVO track) {
		// TODO Auto-generated method stub
		return null;
	}

	//트랙리스트 조회
	@Override
	public List<TrackVO> retrieveTrackList(PagingVO<TrackVO> pagingVO) {
		int totalRecord = trackDAO.selectTotalRecord(pagingVO); 
		pagingVO.setTotalRecord(totalRecord);
		return trackDAO.selectTrackList(pagingVO);
	}

	//트랙상세조회 
	@Override
	public TrackVO retrieveTrack(String trackNo) {
		TrackVO track = trackDAO.selectTrackOne(trackNo); 
		if(track==null)
			throw new PKNotFoundException(trackNo+"에 해당하는 트랙이없음.");
		return track;
	}

}
