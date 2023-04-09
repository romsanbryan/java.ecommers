package romsanbryan.ecommers.exceptions;

import romsanbryan.ecommers.annotations.Generated;

@Generated
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	private String code;

	public ServiceException() {

	}

	public ServiceException(String message, String code) {
		this.message = message;
		this.code = code;
	}

	public ServiceException(String message, String code, Exception e) {
		super(e);
		this.message = message;
		this.code = code;
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
