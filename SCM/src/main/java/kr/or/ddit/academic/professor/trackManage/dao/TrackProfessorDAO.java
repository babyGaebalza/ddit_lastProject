package kr.or.ddit.academic.professor.trackManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TrackVO;

@Repository
public interface TrackProfessorDAO {


	public List<TrackVO> selectTrackList(PagingVO<TrackVO> pagingVO);
	public int selectTotalRecord(PagingVO<TrackVO> pagingVO);
	
	public TrackVO selectTrackOne(String trackNo); 
	
	
}
