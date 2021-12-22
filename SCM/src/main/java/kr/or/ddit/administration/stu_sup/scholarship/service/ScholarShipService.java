package kr.or.ddit.administration.stu_sup.scholarship.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarVO;

public interface ScholarShipService {
	
	//장학생 추천 리스트
	public List<ScholarVO> retrieveScholarShipList(PagingVO<ScholarVO> pagingVO);
	
	//장학생 조회
	public ScholarVO retrieveScholarShip(String schCode);
	
	//장학생 수정
	public ServiceResult modifyScholarShip(ScholarVO scholar);
	
	//장학생 삭제
	public ServiceResult removeScholarShip(ScholarVO scholar);
	
	//장학생 등록
	public ServiceResult createScholarShip(ScholarVO scholar);
}
