package kr.or.ddit.common.reply.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface ReplyService {
	public ServiceResult createReply(BoardVO reply);
	public List<BoardVO> retrieveReplyList(PagingVO<BoardVO> pagingVO);
	public ServiceResult modifyReply(BoardVO reply);
	public ServiceResult removeReply(BoardVO reply);
}
