package kr.or.ddit.administration.univ_man.classmanage.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.ClassVO;
import kr.or.ddit.vo.PagingVO;

public interface ClassManageService {

	//수업신청 리스트 조회
	public List<ClassVO> retrieveClassList(PagingVO<ClassVO> pagingVO);
	
	//수업신청 조회
	public ClassVO retrieveClass(String classNo);
	
	//수업신청 정보 수정
	public ServiceResult modifyClass(ClassVO uclass);
	
	//수업 삭제여부
	public ServiceResult removeClass(ClassVO uclass);
	
	//수업 신설
	public ServiceResult createClass(ClassVO uclass);
}
