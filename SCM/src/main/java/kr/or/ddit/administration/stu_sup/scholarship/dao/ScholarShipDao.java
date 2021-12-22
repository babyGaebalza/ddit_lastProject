package kr.or.ddit.administration.stu_sup.scholarship.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ScholarVO;

@Repository
public interface ScholarShipDao {
	
	//장학 추천 수
	public int selectTotalCount(PagingVO<ScholarVO> pagingVO);
	
	//장학생 추천 리스트
	public List<ScholarVO> selectScholarShipList(PagingVO<ScholarVO> pagingVO);
	
	//장학생 조회
	public ScholarVO selectScholarShip(String schCode);
	
	//장학생 수정
	public int updateScholarShip(ScholarVO scholar);
	
	//장학생 삭제
	public int deleteScholarShip(ScholarVO scholar);
	
	//장학생 등록
	public int insertScholarShip(ScholarVO scholar);
}