package com.github.zs.rikit;

public class RikitException extends Exception 
{
	private static final long serialVersionUID = 4341571679290883433L;
	private RikitErrorCode code;
	private String message;
	private Throwable throwable;
	
	public RikitException(RikitErrorCode code, Throwable throwable)
	{
		this.code = code;
		this.throwable = throwable;
		this.message = throwable.getMessage();
	}	
	
	public RikitException(RikitErrorCode code, Throwable throwable, String message)
	{
		this.code = code;
		this.message = message;
		this.throwable = throwable;
	}

	public RikitErrorCode getCode() {
		return code;
	}

	public void setCode(RikitErrorCode code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
	
	
}
