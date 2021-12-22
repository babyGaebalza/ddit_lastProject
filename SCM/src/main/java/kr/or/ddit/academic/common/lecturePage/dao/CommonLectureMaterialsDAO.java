package kr.or.ddit.academic.common.lecturePage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;
@Repository
public interface CommonLectureMaterialsDAO {

	//과제 게시글 
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	// 과제 게시글  리스트 조회
	public List<BoardVO> selectMaterialList(PagingVO<BoardVO> pagingVO);
	
	// 과제 게시글 상세조회
	public BoardVO selectMaterial(String boardNo);

		
	// 과제 게시글  조회수 증가 부분
	public int incrementCount(Map<String, Object> pMap);
}
