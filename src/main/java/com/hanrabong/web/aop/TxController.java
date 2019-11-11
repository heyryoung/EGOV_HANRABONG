package com.hanrabong.web.aop;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Lazy
@RestController
@Transactional
@RequestMapping("/tx")
public class TxController {
	@Autowired Map<String, Object>map;
	@Autowired TxService txService;
	
	@GetMapping("/{targetSite}/{searchWrd}")
	public List<?> register(@PathVariable String targetSite,
			@PathVariable String searchWrd){
		map.put("targetSite", targetSite);
		map.put("searchWrd", searchWrd);
		
		return txService.crawling(map);		
	}
	
	
	
	@GetMapping("/olivecrawling")
	public List<?> register() throws Exception{
		
		return txService.oliveCategodycrawling();
	}
	
	

}