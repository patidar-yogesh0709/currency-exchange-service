package com.in28minutes.microservices.currencyexchangeservice;

public class APIResponse {
	
	private String message;
	private String status;
	private Object data;
	private boolean isError;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	@Override
	public String toString() {
		return "APIResponse [message=" + message + ", status=" + status + ", data=" + data + ", isError=" + isError
				+ "]";
	}
	

}
