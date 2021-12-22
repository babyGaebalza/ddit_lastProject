package kr.or.ddit.common.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface FacComplaintDAO {

	public String getMemName(String memId);
	
	public int insertFC(BoardVO facComplaint);
	
	public List<BoardVO> selectFCList(PagingVO<BoardVO> pagingVO);
	
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	public BoardVO selectFCView(String fcNo);
	
	public int incrementCount(Map<String, Object> pMap);
	
	
	//민원글 수정
	public int UpdateFC(BoardVO board);
	
	//민원글 삭제
	public int deleteFC(String boardNo);
}
