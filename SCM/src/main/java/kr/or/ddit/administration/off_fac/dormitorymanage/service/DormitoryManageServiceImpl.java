package kr.or.ddit.administration.off_fac.dormitorymanage.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.administration.off_fac.dormitorymanage.dao.DormitoryManageDAO;

@Service
public class DormitoryManageServiceImpl implements DormitoryManageService {
	@Inject
	private DormitoryManageDAO dao;
}
