package com.hanrabong.web.enums;

public enum Path {
	UPLOAD_PATH, CLAWLING_TARGET;
	@Override
	public String toString() {
		String result = "";
		switch (this) {
		case UPLOAD_PATH:
			result = "C:\\Users\\User\\git\\EGOV_HANRABONG\\src\\main\\webapp\\resources\\upload";
			break;
		case CLAWLING_TARGET:
			result = "https://search.shopping.naver.com/detail/detail.nhn?nv_mid=12480326413&cat_id=50000444&frm=&query=%EA%B8%B0%EC%B4%88%ED%99%94%EC%9E%A5%ED%92%88&&section=review&NaPm=ct%3Dk2wvcfa8%7Cci%3Dcafb8ca9983a03278d15d4d31976f8fae36637ad%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3Daa73238109e3e809435fc980f82f7e9237a6f385";
		}
		return result;
	}
}
