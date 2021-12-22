package kr.or.ddit.common.document.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.PagingVO;

public interface DocuService {

	/**
	 * 문서작성
	 * @param docu
	 * @return OK -성공, FAIL - 실패
	 */
	public ServiceResult createDocument(DocumentVO docu);
	
	public DocumentVO retrieveOneDocu(String docuReq); 
	
	/**자신이 올린문서(결재완료, 미완료. 단 취소된 문서는 볼 수 없다.) 출력
	 *@param 결재문서올린 사람 아이디   
	 * */
	public List<DocumentVO> retrieveMyDocu(PagingVO pagingVO); 

	/**자신이 결재 해야하는 문서 리스트 출력 
	 * */
	public List<DocumentVO> retrieveTodoDocu(PagingVO pagingVO); 
	
	/**자신이 결재완료한 문서리스트 출력  
	 */
	public List<DocumentVO> retrieveDoneDocu(DocumentVO docu);
	
	/**
	 * 문서수정(결재올린사람만이 && 문서가 최종결재가 되지 않았을때만 수정가능) 
	 */
	public ServiceResult modifyDocu(DocumentVO docu); 
	
	/**
	 * 문서 삭제(결재올린 사람만이 && 문서가 최종결재가 되지 않았을때만 삭제가능/ 데이터 삭제는 아니고 취소여부만 변경)
	 */
	public ServiceResult removeDocu(DocumentVO docu);

	//강의계획서 문서 작성 
	public ServiceResult createClassMakeDocument(DocumentVO docu);
}
