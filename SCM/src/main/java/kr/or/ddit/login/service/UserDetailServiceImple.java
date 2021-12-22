package kr.or.ddit.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.member.dao.MemberDao;
import kr.or.ddit.enumpkg.UserCode;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailService")
public class UserDetailServiceImple implements UserDetailsService {
	
	@Inject
	private MemberDao memberDAO;

	@Override
	public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException, DisabledException {
		MemberVO loginMember = new MemberVO();
		loginMember.setMemId(memId);
		if(StringUtils.isBlank(memId)){
			throw new UsernameNotFoundException("아이디를 입력해주세요.");
		}
		MemberVO member = memberDAO.selectMember(loginMember);
		if(member==null) {
			// 존재하지 않는 아이디
			throw new UsernameNotFoundException(String.format("%s(학번/사번) 은 존재하지 않습니다.", memId));
		}else if(member.getMemLoginfailcnt().intValue() >= 3) {
			// 계정 잠금상태
			throw new DisabledException(String.format("%s(%s) 님은 비밀번호 3회 연속불일치로 인해 계정잠금 해제가 필요합니다.", memId, member.getUserCate()));
		}else {
			String authUserCode = member.getUserCode();
			UserCode userCode = UserCode.valueOf(authUserCode);
			
			// 학생/교수인 경우 강의페이지 접근권한 세팅
			if(userCode == UserCode.US02 || userCode == UserCode.US04) {
				Map<String, Object> mapForLectureAuth = new HashMap<>();
				mapForLectureAuth.put("memId", memId);
				mapForLectureAuth.put("authUserCode", authUserCode);
				List<String> lectureAuthList = memberDAO.selectLecturePageAuthorityList(mapForLectureAuth);
				List<String> memRoles = member.getMemRoles();
				memRoles.addAll(lectureAuthList);
				for(String auth : memRoles) {
					log.info("로그인 성공시 auth = {}", auth);
				}
				member.setMemRoles(memRoles);
			}
		}
		return new MemberVOWrapper(member);
	}

}
