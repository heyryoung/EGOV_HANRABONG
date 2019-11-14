package com.hanrabong.web.tx;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanrabong.web.brd.Review;
import com.hanrabong.web.pxy.Box;
import com.hanrabong.web.pxy.ProxyForCrawling;
import com.hanrabong.web.pxy.Trunk;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@Lazy
@RestController
@Transactional
@RequestMapping("/tx")
public class TxController {
	@Autowired Trunk<Object> map;
	@Autowired TxService txService;
	@Autowired ProxyForCrawling crawler;
	@Autowired TxMapper mapper;
	
	@GetMapping("/{targetSite}/{searchWrd}")
	public void register(@PathVariable String targetSite,
			@PathVariable String searchWrd){
		map.put(Arrays.asList("targetSite","searchWrd"), Arrays.asList(targetSite,searchWrd));
		txService.crawling(map.get());		
	}
	
	@GetMapping("/resister/HCusts")
	public Map<?,?> registerHCusts(){
		int hcustCount = txService.registerHCusts();
		map.put(Arrays.asList("count"),Arrays.asList(hcustCount));
		return map.get();
	}
	
	@GetMapping("/olivecrawling")
	public List<?> register() throws Exception{
		
		return txService.oliveCategodycrawling();
	}
	@PostMapping("/") // POST / posts 글(posts)을 생성합니다(POST)
	public Map<?, ?> writeReview(@RequestBody Review param) {
		int reviewCount = txService.registerReview();
		map.put(Arrays.asList("count"), Arrays.asList(reviewCount));
		return map.get();
	}
}