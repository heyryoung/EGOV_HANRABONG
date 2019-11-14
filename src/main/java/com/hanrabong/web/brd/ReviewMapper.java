package com.hanrabong.web.brd;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.hanrabong.web.pxy.PageProxy;
@Repository
public interface ReviewMapper {

	public ArrayList<Review> selectReviews(PageProxy pxy);
//	public Review selectReview(String brdseq);
	public void insertReview(Review param);
	/*public void modify(Brd paeam);
	public void delete(Brd paeam);
	public int  lastArticle();*/
	public int  countAllReviews();
	public void createReview(HashMap<String, String> t);
}
