package br.com.sbMail.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;


public class CustomGenericException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String errMsg;
	private HttpStatus statusCode;
	private Exception ex;

	public CustomGenericException(HttpStatus statusCode, Exception ex) {
		this.statusCode = statusCode;
		this.ex = ex;
	}

	public CustomGenericException(String errMsg, HttpStatus statusCode) {
		this(statusCode, null);
		this.errMsg = errMsg;
	}

	public CustomGenericException(String errMsg) {
		this(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public String getErrMsg() {

		if(StringUtils.isEmpty(errMsg)) {
			errMsg = ExceptionUtils.getRootCauseMessage(ex);
		}
		return errMsg;
	}

	public String getEx() {
		if(ex != null) {
			return ex.getClass().getSimpleName();
		} else {
			return "CustomGenericException";
		}
	}

}
