package com.ync365.px.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaErrorException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public CaptchaErrorException() {
        super();
    }

    public CaptchaErrorException(String message) {
        super(message);
    }

    public CaptchaErrorException(Throwable cause) {
        super(cause);
    }

    public CaptchaErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
