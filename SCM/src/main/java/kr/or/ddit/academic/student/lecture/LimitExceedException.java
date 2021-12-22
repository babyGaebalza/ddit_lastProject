package kr.or.ddit.academic.student.lecture;

public class LimitExceedException extends RuntimeException {
	
	public LimitExceedException() {
		super();
		
	}

	public LimitExceedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public LimitExceedException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LimitExceedException(String message) {
		super(message);
		
	}

	public LimitExceedException(Throwable cause) {
		super(cause);
		
	}
}
