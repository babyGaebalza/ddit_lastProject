package kr.or.ddit.common.document.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.DocumentVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Repository
public interface DocuDAO {
	
	/**강의리스트 출력*/
	public List<ClassVO> selectMyMajorClassList(String memMajor);
	
	/**결재자 리스트 출력*/
	public List<MemberVO> selectPeopleList(); 
	
	/**결재문서 생성 */
	public int insertDocu(DocumentVO docu); 
		
	/**전체 레코드 수 조회*/
	public int selectTotalRecord(PagingVO pagingVO); 
	
	/**자신이 올린문서(결재완료, 미완료. 단 취소된 문서는 볼 수 없다.) 출력  
	 * */
	public List<DocumentVO> selectMyDocuList(PagingVO pagingVO); 

	
	public DocumentVO selectOneDocu(String docuNo); 
	
	public int selectTodoDocuRecord(PagingVO pagingVO);
	
	
	/**자신이 결재 해야하는 문서 리스트 출력 */
	public List<DocumentVO> selectTodoDocuList(PagingVO pagingVO); 
	
	/**자신이 결재완료한 문서리스트 출력  
	 */
	public List<DocumentVO> selectDoneDocuList(DocumentVO docu);
	
	/**
	 * 문서수정(결재올린사람만이 && 문서가 최종결재가 되지 않았을때만 수정가능) 
	 */
	public int updateDocu(DocumentVO docu); 
	
	/**
	 * 문서 삭제(결재올린 사람만이 && 문서가 최종결재가 되지 않았을때만 삭제가능/ 데이터 삭제는 아니고 취소여부만 변경)
	 */
	public int updateCancleDocu(DocumentVO docu);
	
	
	
}
