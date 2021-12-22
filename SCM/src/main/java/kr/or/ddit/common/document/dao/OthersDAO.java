package kr.or.ddit.common.document.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemberVO;

@Repository
public interface OthersDAO {
	
	//부서코드, 부서명
 	public List<Map<String, Object>> selectDeptList();
 	//학사코드, 학사명
 	public List<Map<String, Object>> selectMajorList();
 	//이름, 아이디
	public List<Map<String, Object>> selectNameAndId(MemberVO member);

	
}
