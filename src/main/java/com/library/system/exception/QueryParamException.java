package com.library.system.exception;

public class QueryParamException extends RuntimeException {
	private static final long serialVersionUID = 9067244005757861679L;

	public QueryParamException() {
		super();
	}

	public QueryParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryParamException(String message) {
		super(message);
	}

	public QueryParamException(Throwable cause) {
		super(cause);
	}
}
