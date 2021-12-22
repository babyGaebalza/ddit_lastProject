package kr.or.ddit.administration.mainpage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.mainpage.dao.AdminMainDao;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class AdminMainServiceImpl implements AdiminMainService {

	@Inject
	AdminMainDao dao;
	
	@Override
	public List<DocumentVO> retrieveDocumentList(PagingVO<DocumentVO> pagingVO) {
		List<DocumentVO> docList = dao.selectDocumentList(pagingVO);
		pagingVO.setTotalRecord(dao.selectTotalCount(pagingVO));
		pagingVO.setDataList(docList);
		return docList;
	}

}
