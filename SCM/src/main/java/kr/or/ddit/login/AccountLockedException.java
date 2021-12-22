package kr.or.ddit.login;

import org.springframework.security.core.AuthenticationException;

public class AccountLockedException extends AuthenticationException {

	public AccountLockedException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public AccountLockedException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}
}
