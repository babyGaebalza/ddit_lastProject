package kr.or.ddit.common.reply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.reply.dao.ReplyDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;
	
	@Inject
	private PasswordEncoder encoder;
	
	private void encryptPassword(BoardVO reply) {
		reply.setRepPass(encoder.encode(reply.getRepPass()));
	}
	
	@Override
	public ServiceResult createReply(BoardVO reply) {
		encryptPassword(reply);
		int rowcnt = dao.insertReply(reply);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
	}

	@Override
	public List<BoardVO> retrieveReplyList(PagingVO<BoardVO> pagingVO) {
		pagingVO.setTotalRecord(dao.selectTotalRecord(pagingVO));
		List<BoardVO> replyList = dao.selectReplyList(pagingVO);
		pagingVO.setDataList(replyList);
		return replyList;
	}

	
	@Override
	public ServiceResult modifyReply(BoardVO reply) {
		ServiceResult result = null;
		
		if(encoder.matches(reply.getRepPass(), dao.selectRepPass(reply.getBoardNo()))) {
			int rowcnt = dao.updateReply(reply);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeReply(BoardVO reply) {
		ServiceResult result = null;
		
		if(encoder.matches(reply.getRepPass(), dao.selectRepPass(reply.getBoardNo()))) {
			int rowcnt = dao.deleteReply(reply);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAILED;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

}
