package com.hanrabong.web.tx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanrabong.web.brd.Review;
import com.hanrabong.web.brd.ReviewMapper;
import com.hanrabong.web.hcust.HCust;
import com.hanrabong.web.hcust.HCustMapper;
import com.hanrabong.web.pxy.Box;
import com.hanrabong.web.pxy.ProxyForCrawling;


@Service
@Lazy
public class TxService {
	@Autowired TxMapper txMapper;
	@Autowired HCustMapper mapper;
	@Autowired ReviewMapper revMapper;
	@Autowired ProxyForCrawling pxy;

	public Box<String> crawling(Map<?,?> paramMap){
		return pxy.choose(paramMap);
	}
	
	@Transactional
	public int registerHCusts(){
		List<HCust> list = new ArrayList<>();
		for(HCust c : list) {
			txMapper.insertHCusts(c);			
		}
		return mapper.countHCusts();
	}
	@Transactional
	public int registerReview(){
		List<Review> list = new ArrayList<>();
		for(Review c : list) {
			txMapper.insertReview(c);			
		}
		return revMapper.countAllReviews();
	}

	
	public List<?> oliveCategodycrawling() throws Exception {
		List<String> txServicelist = new ArrayList<>();
		txServicelist.clear();
		txServicelist = (List<String>) pxy.getCategoty();
		int cnt =1;
		Consumer<String> s = t-> txMapper.insertDumpArticle(t);
		for (String string : txServicelist) {
			s.accept(string);
			System.out.println(cnt+"-----------------");
			cnt++;
		}
		return txServicelist;
	}
	
}