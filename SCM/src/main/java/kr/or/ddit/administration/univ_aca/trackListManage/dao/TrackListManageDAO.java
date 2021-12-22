package kr.or.ddit.administration.univ_aca.trackListManage.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.TrackListVO;

@Repository
public interface TrackListManageDAO {
	public int insertClass(TrackListVO trackList);
}
