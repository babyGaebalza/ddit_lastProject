package kr.or.ddit.common.document.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.document.dao.DocuDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class DocuServiceImpl implements DocuService{

	@Inject
	private DocuDAO dao ; 
	
	@Override
	public ServiceResult createDocument(DocumentVO docu) {
		
		int peopleCnt = 2; 
		if(!(StringUtils.isBlank(docu.getDocuAp1())) ) {
			++peopleCnt; 
			if(!(StringUtils.isBlank(docu.getDocuAp2())) ) {
			++peopleCnt; 
			}
		}	
		//필요결재횟수 
		docu.setDocuReqcnt(peopleCnt);
		
		
		int cnt = dao.insertDocu(docu);
		ServiceResult result = null; 
		if(cnt > 0) {		
			result = ServiceResult.OK; 
		}else {
			result = ServiceResult.FAILED;
		}
		return result; 
	}
	
	@Override
	public ServiceResult createClassMakeDocument(DocumentVO docu) {
				
		int cnt = dao.insertDocu(docu);
		ServiceResult result = null; 
		if(cnt > 0) {		
			result = ServiceResult.OK; 
		}else {
			result = ServiceResult.FAILED;
		}
		return result; 
	}
	
	
	

	@Override
	public List<DocumentVO> retrieveMyDocu(PagingVO pagingVO) {
	
		int totalRecord = dao.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectMyDocuList(pagingVO);

	}

    //문서 하나만 출력 
	@Override
	public DocumentVO retrieveOneDocu(String docuNo) {
		// TODO Auto-generated method stub
		return dao.selectOneDocu(docuNo);
	}
	
	//결재해야 하는 문서 출력 
	@Override
	public List<DocumentVO> retrieveTodoDocu(PagingVO pagingVO) {
		
		int totalRecord = dao.selectTodoDocuRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		return dao.selectTodoDocuList(pagingVO);
		
		
	}

	@Override
	public List<DocumentVO> retrieveDoneDocu(DocumentVO docu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult modifyDocu(DocumentVO docu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeDocu(DocumentVO docu) {
		// TODO Auto-generated method stub
		return null;
	}



}
