package com.hanrabong.web.tx;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hanrabong.web.brd.Review;
import com.hanrabong.web.hcust.HCust;


@Repository
public interface TxMapper {
	@Insert(" INSERT INTO HCUST ( CNUM,CID, CPW, CNAME, GEN, BIRTH, SKIN_PROB, SKIN_TYPE, CHILD, ADDR, TEL, CPOINT)\n" + 
			"  	VALUES (#{cnum},#{cid},#{cpw},#{cname},#{gen},#{birth},#{skinProb},#{skinType},#{child},#{addr},#{tel},#{cpoint})")
	public void insertHCusts(HCust c);
	public void insertDumpArticle(String string);
	
	@Insert("insert into community (REVNUM,TITLE,CONTENT,RATING, REVDATE,CNUM,PNUM) values(\n" + 
			"        #{revnum},#{revtitle},#{revcontent},#{rating},#{cnum},#{pnum}\n" + 
			"    )")
	public void insertReview(Review param);
	
}