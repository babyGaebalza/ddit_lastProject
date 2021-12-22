package kr.or.ddit.administration.univ_aca.trackmanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TrackVO;

public interface TrackManageService {
	//신청트랙 리스트
	public List<DocumentVO> retrieveDocumentList(PagingVO<DocumentVO> pagingVO);
	
	//신청트랙 상세페이지
	public DocumentVO retrieveDocument(String docuNo);
	
	//신청트랙 수정(최종결재자, 결재 상태 변경)
	public ServiceResult modifyDocument(DocumentVO docu);
	
	//트랙 리스트
	public List<TrackVO> retrieveTrackList(PagingVO<TrackVO> pagingVO);
	
	//트랙 상세페이지
	public TrackVO retrieveTrack(String trackNo);
		
	//트랙 등록
	public ServiceResult createTrack(TrackVO track);
	
	//트랙 수정
	public ServiceResult modifyTrack(TrackVO track);
}
