package com.hanrabong.web.pxy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.hanrabong.web.brd.BrdMapper;

import lombok.Data;

@Data @Component @Lazy
public class Proxy {
	private int totalCount, startRow, endRow
			,pageCount, pageSize, startPage, endPage, pageNum  // 
			,blockCount; 
	private boolean existNext,existPrev;
	private String search;
	private final int BLOCK_SIZE = 5; 
	private List<Integer>blist ;
	private List<String> proxyList;	
	@Autowired BrdMapper brdMapper;
	
	public boolean getExistPrev() {
		return this.existPrev;
	}
	public boolean getExistNext() {
		return this.existNext;
	} 
	public void setExistNext(boolean existNext) {
		this.existNext = existNext;
	}
	public void setExistPrev(boolean existPrev) {
		this.existPrev = existPrev;
	}	
	
	public void paging() {
		Supplier<Integer> s = ()->brdMapper.countAllArticle();
		totalCount = s.get();
		pageCount =(totalCount %pageSize==0) ?  totalCount/pageSize : (totalCount/pageSize)+1;
		startRow = (pageNum-1) * pageSize;
		System.out.println("pageNum>>>>>>>>>>>>>"+pageNum);
		endRow = (pageNum == pageCount) ?   totalCount -1 : pageNum*pageSize-1;
		blockCount = (pageCount %BLOCK_SIZE==0) ?  pageCount/BLOCK_SIZE : (pageCount/BLOCK_SIZE)+1; //블럭의 개수
		startPage =   ((pageNum-1)/BLOCK_SIZE)*BLOCK_SIZE+1; // 
		endPage = ((pageCount-startPage)<(BLOCK_SIZE) ) ?   pageCount : (startPage + BLOCK_SIZE -1); // 
		existPrev = (startPage < (BLOCK_SIZE+1) ) ? false : true;  // start Page가 BLOCK_SIZE+1보다 작으면 없음.
		existNext =  (pageCount == endPage) ? false : true; // 페이지수가 endPage와 같으면 없음.
		blist = new ArrayList<>();
		for (int i = startPage;  i < endPage+1 ; i++) {
			blist.add(i);
		}
				
	}
	public int parseInt(String param) {
		Function<String, Integer> f = s-> Integer.parseInt(s);
		System.out.println("public int parseInt(String param)public int parseInt(String param)public int parseInt(String param)public int parseInt(String param)public int parseInt(String param)public int parseInt(String param)public int parseInt(String param)public int parseInt(String param)public int parseInt(String param)");
		return f.apply(param);
	}

	public int random(int n , int m) {
		
		BiFunction<Integer,Integer,Integer> r = (t,u) ->(int) (Math.random() * (m-n)) +n;  
		
		return r.apply(n, m);
	}

	
	
	
	
}