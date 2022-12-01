package com.grupobbva.col.rest.conector.util;


public enum ResponseCode {
	OK(200), CREATED(201), ACEPTED(202), NO_CONTENT(204),NOT_FOUNT(404) ,BAT_REQUEST(400), UNAUTHORIZED(401), FORBIDDEN(403),
	CONFICT(409),SERVER_ERROR(500);

	private int code;

	private ResponseCode(int code) { 
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
	
	public static ResponseCode valueOfResponse(int status) {
	    for (ResponseCode r : values()) {
	        if (r.getCode()==status) {
	            return r;
	        }
	    }
	    return ResponseCode.SERVER_ERROR;
	}
}
