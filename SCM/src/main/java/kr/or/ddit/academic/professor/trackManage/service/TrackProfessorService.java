package kr.or.ddit.academic.professor.trackManage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TrackVO;

public interface TrackProfessorService {
	
	public ServiceResult createTrack(TrackVO track);
	public List<TrackVO> retrieveTrackList(PagingVO<TrackVO> pagingVO);
	public TrackVO retrieveTrack(String trackNo);
	
}
