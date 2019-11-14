package com.hanrabong.web.pxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hanrabong.web.brd.Review;
import com.hanrabong.web.tx.TxMapper;

@Component
public class ReviewProxy {
	@Autowired Trunk<String> trunk;
	@Autowired ProxyForCrawling crawler;
	@Autowired Review review;
	@Autowired TxMapper txMapper;
	
	public ArrayList<String> content() {
		trunk.put(Arrays.asList("targetSite","searchWrd"), Arrays.asList("직접입력","네이버리뷰"));
		return crawler.choose(trunk.get()).getList();
	}
	public String writer() {
		List<String> custs = Arrays.asList("1", "2", "3", "4", "5");
		Collections.shuffle(custs);
		return custs.get(0);
	}
	public String title(){
		List<String> titles = Arrays.asList("최고에요","좋아요","저렴해요","그저그래요");
		Collections.shuffle(titles);
		return titles.get(0);
	}
	public String rating(){
		List<String> rating = Arrays.asList("1","2","3","4","5");
		Collections.shuffle(rating);
		return rating.get(0);
	}
	public String revDate(){
		Random r = new Random();
	      int y,m,d = 0;
	      String birth = "";
	      for(int i=0; i<500; i++) {
	          y=r.nextInt(3)+2017;
	          m=r.nextInt(11)+1;
	          if(m==2) {d=r.nextInt(28)+1;
	          }else if(m ==4||m==6||m==9||m==11) {
	              d=r.nextInt(30)+1;
	          }else {d=r.nextInt(31)+1;}
	          birth=String.format("%d%02d%02d",y,m,d);
	      }
	      return birth;
	}
	public String pnum() {
		return "001089";
	}
	@Transactional
	public void insertReview() {
		ArrayList<String> list= content();
		for (int i = 1; i <= 50; i++) {
			Collections.shuffle(list);
			txMapper.insertReview(new Review(String.valueOf(i), title(), list.get(0), rating(), revDate(), writer(), pnum()));

		}
	}
}
