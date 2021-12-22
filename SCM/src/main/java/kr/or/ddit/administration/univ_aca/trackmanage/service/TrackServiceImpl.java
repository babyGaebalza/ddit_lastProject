package kr.or.ddit.administration.univ_aca.trackmanage.service;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.administration.univ_aca.trackListManage.dao.TrackListManageDAO;
import kr.or.ddit.administration.univ_aca.trackmanage.controller.TrackManageController;
import kr.or.ddit.administration.univ_aca.trackmanage.dao.TrackManageDAO;
import kr.or.ddit.common.PKNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TrackListVO;
import kr.or.ddit.vo.TrackVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrackServiceImpl implements TrackManageService {
	@Inject
	private TrackManageDAO dao;
	
	@Override
	public List<DocumentVO> retrieveDocumentList(PagingVO<DocumentVO> pagingVO) {
		List<DocumentVO> docList = dao.selectDocumentList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCount(pagingVO));
		pagingVO.setDataList(docList);
		return docList;
	}

	@Override
	public DocumentVO retrieveDocument(String docuNo) {
		DocumentVO doc = dao.selectDocument(docuNo);
		if(doc == null) throw new PKNotFoundException(docuNo + "해당 게시글을 찾을 수 없습니다.");
		return doc;
	}

	//@Transactional
	@Override
	public ServiceResult modifyDocument(DocumentVO docu) {
		ServiceResult result = null;
		int rowcnt = dao.updateDocument(docu);
		
		log.info("service rowcnt : {}", rowcnt);
		
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		
		log.info("service result : {}", result);
		
		return result;
	}

	@Override
	public List<TrackVO> retrieveTrackList(PagingVO<TrackVO> pagingVO) {
		List<TrackVO> trackList = dao.selectTrackList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalTrack(pagingVO));
		pagingVO.setDataList(trackList);
		return trackList;
	}

	@Override
	public TrackVO retrieveTrack(String trackNo) {
		TrackVO track = dao.selectTrack(trackNo);
		if(track == null) throw new PKNotFoundException(trackNo + "해당 게시글을 찾을 수 없습니다.");
		return track;
	}

	@Override
	public ServiceResult createTrack(TrackVO track) {
		ServiceResult result = null;
		int rowcnt = dao.insertTrack(track);
		
		if(rowcnt > 0) {
			result = ServiceResult.OK;
		}
		else result = ServiceResult.FAILED;
		
		return result;
	}

	//@Transactional
	@Override
	public ServiceResult modifyTrack(TrackVO track) {
		ServiceResult result = null;
		int rowcnt = dao.updateTrack(track);
		
		if(rowcnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		
		return result;
	}
}
