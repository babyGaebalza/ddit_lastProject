package kr.or.ddit.common.reply.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * FreeReply 테이블을 대상으로 한 CRUD
 *
 */
@Repository
public interface ReplyDAO {
	
	public int insertReply(BoardVO reply);
	
	public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
	
	public List<BoardVO> selectReplyList(PagingVO<BoardVO> pagingVO);
	public String selectRepPass(String repNo);
	public int updateReply(BoardVO reply);
	public int deleteReply(BoardVO reply);
}









