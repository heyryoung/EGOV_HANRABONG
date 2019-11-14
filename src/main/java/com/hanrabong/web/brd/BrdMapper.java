package com.hanrabong.web.brd;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;

import com.hanrabong.web.pxy.PageProxy;

@Repository
public interface BrdMapper {

	public ArrayList<Brd> selectBrdArticles(PageProxy pxy);
	public Brd selectArticle(String brdseq);
	public void insertArticle(Brd param);
	public void modify(Brd paeam);
	public void delete(Brd paeam);
	public int  lastArticle();
	public int  countAllArticle();
	
}