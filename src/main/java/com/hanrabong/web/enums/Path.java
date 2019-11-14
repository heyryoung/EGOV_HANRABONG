package com.hanrabong.web.enums;

public enum Path {
	UPLOAD_PATH;
	public String toString() {
		String result ="";
		switch(this) {
		case UPLOAD_PATH:
			result="C:\\Users\\User\\git\\EGOV_HANRABONG\\src\\main\\webapp\\resources\\upload";
			
		}
		return result;
	}
	
}
