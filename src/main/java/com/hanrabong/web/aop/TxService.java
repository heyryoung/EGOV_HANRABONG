package com.hanrabong.web.aop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanrabong.web.pxy.PageProxy;
import com.hanrabong.web.pxy.ProxyForCrawling;

@Transactional
@Service
@Lazy
public class TxService {
	@Autowired TxMapper txMapper;
	@Autowired ProxyForCrawling pxy;

	public List<?> crawling(Map<?,?> paramMap) {
		 List<String> txServicelist = new ArrayList<>();
		txServicelist.clear();
		txServicelist = (List<String>) pxy.crawl(paramMap);
		return txServicelist;
	}

	public List<?> oliveCategodycrawling() throws Exception {
		List<String> txServicelist = new ArrayList<>();
		txServicelist.clear();
		txServicelist = (List<String>) pxy.getCategoty();
		int cnt =1;
		Consumer<String> s = t-> txMapper.insertDumpArticle(t);
		for (String string : txServicelist) {
			s.accept(string);
			cnt++;
		}
		return txServicelist;
	}
	
}