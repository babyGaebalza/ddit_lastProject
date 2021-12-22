package kr.or.ddit.administration.univ_aca.trackmanage.dao;

import java.util.List;
import java.util.Map;

import javax.sound.midi.Track;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryVO;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MajorVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.TrackListVO;
import kr.or.ddit.vo.TrackVO;

@Repository
public interface TrackManageDAO {
	//상태변경
	public void updateDocu(String docuNo);
	
	//신청트랙 수
	public int selectTotalCount(PagingVO<DocumentVO> pagingVO);
	
	//신청트랙 리스트
	public List<DocumentVO> selectDocumentList(PagingVO<DocumentVO> pagingVO);
	
	//신청트랙 상세페이지
	public DocumentVO selectDocument(String docuNo);

	//pdf로 변환하도록...
	public DocumentVO selectDocumentPdf(String docuNo);
	
	//신청트랙 수정(최종결재자, 결재 상태 변경)
	public int updateDocument(DocumentVO docu);
	
	//트랙 수
	public int selectTotalTrack(PagingVO<TrackVO> paging);
	
	//트랙 리스트
	public List<TrackVO> selectTrackList(PagingVO<TrackVO> pagingVO);
	
	//트랙 상세페이지
	public TrackVO selectTrack(String trackNo);
	
	//
	public List<TrackVO> getClassList(String trackNo);
	
	//트랙 등록
	public int insertTrack(TrackVO track);
	
	//트랙 수정
	public int updateTrack(TrackVO track);
	
	//트랙 단과코드 select
	public List<MajorVO> selectCollegeCode(MajorVO major);
	
	//트랙 학과코드 select
	public List<MajorVO> selectMajorCode(MajorVO major);
	
	//트랙 강의 구분 select
	public List<ClassVO> selectClassCode(ClassVO clas);
	
	//트랙 강의(학년 학기) select
	public List<ClassVO> selectClassSemester(ClassVO clas);
	
	//트랙 강의 select
	public List<ClassVO> selectClassName(ClassVO clas);

	public List<ClassVO> selectClassName2(ClassVO clas);
	
	//신청트랙 상태변경
	public int update(String trackList);
	
	//트랙 상태변경
	public int updateState(String track);
	
	//트랙리스트 (과목 등록)
	public int insertTrackList(TrackListVO trackList);
	
	//트랙리스트 (과목등록 전필)
	public int insertTrackListCLO2(TrackListVO trackList);
	
}
