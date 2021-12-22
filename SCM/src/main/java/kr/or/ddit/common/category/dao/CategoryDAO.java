package kr.or.ddit.common.category.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CategoryVO;

@Repository
public interface CategoryDAO {

	public List<CategoryVO> selectCategoryList(String codeGroup);
	
}
