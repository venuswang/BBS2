package cn.scau.edu.util;

/**
 * 构建自己的Exception类 
 */
public class MyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyException() {
		
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(Throwable cause) {
		super(cause);
	}

	public MyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
