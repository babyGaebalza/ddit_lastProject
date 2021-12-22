package kr.or.ddit.administration.univ_man.testmanage.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.univ_man.testmanage.dao.TestManageDAO;

@Service
public class TestManageServicveImpl implements TestManageService {
	@Inject
	private TestManageDAO dao;
}
