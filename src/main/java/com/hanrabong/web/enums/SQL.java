package com.hanrabong.web.enums;

public enum SQL {
	CREATE_HCUST, DROP_HCUST, CREATE_DB, CREATE_FAVORITE, DROP_FAVORITE, TRUNCATE_HCUST, CREATE_REVIEW;
	@Override
	public String toString() {
		String result = "";
		switch(this) {
		case CREATE_HCUST :
			result = "CREATE TABLE MYSQL.HCUST(\r\n" + 
					"   CNUM VARCHAR(2) PRIMARY KEY ,\r\n" + 
					"   CID VARCHAR(10),\r\n" + 
					"   CPW VARCHAR(10),\r\n" + 
					"  CNAME VARCHAR(12),\r\n" + 
					"  GEN VARCHAR(1),\r\n" + 
					"  BIRTH DATE,\r\n" + 
					"  SKIN_PROB VARCHAR(1),\r\n" + 
					"  SKIN_TYPE VARCHAR(1),\r\n" + 
					"  CHILD VARCHAR(1),\r\n" + 
					"  ADDR VARCHAR(50),\r\n" + 
					"  TEL VARCHAR(11),\r\n" + 
					"  CPOINT numeric\r\n" + 
					");";
			break;
		case DROP_FAVORITE :
			result = "DROP TABLE HANRABONG.FAVORITE";
			break;
		case CREATE_FAVORITE :
			result = "CREATE TABLE HANRABONG.FAVORITE(\n"
					+ "CNUM VARCHAR(4) PRIMARY KEY,\n"
					+ "MYBRAND VARCHAR(30),\n"
					+ "MYTHEME VARCHAR(30),\n"
					+ "MYTYPE VARCHAR(10),\n"
					+ "MYPRODUCT VARCHAR(5),\n"
					+ "MYPURCHARSE VARCHAR(10),\n"
					+ "FOREIGN KEY (CNUM) REFERENCES HCUST(CNUM))";
			break;
		case CREATE_REVIEW :
			result = "CREATE TABLE HANRABONG.REVIEW(\n"
					+ "REVNUM VARCHAR(4) PRIMARY KEY,\n"
					+ "TITLE VARCHAR(100),\n"
					+ "CONTENT VARCHAR(3000),\n"
					+ "RATING VARCHAR(1),\n"
					+ "REVDATE VARCHAR(8),\n"
					+ "CNUM VARCHAR(4),\n"
					+ "PNUM VARCHAR(6),\n"
					+ "FOREIGN KEY (CNUM) REFERENCES HCUST(CNUM))"
					+ "FOREIGN KEY (PNUM) REFERENCES PRODUCT(PNUM))";
			break;
		case DROP_HCUST :
			result = "";
			break;
		case CREATE_DB :
			result = "CREATE DATABASE HANRABONGDB";
			break;
		case TRUNCATE_HCUST :
			result = "TRUNCATE TABLE HCUST";//테이블 전체를 날림. 완전히. 되돌릴 수 없음. 일괄삭제.
			break;
		}
		return result;
	}
}
