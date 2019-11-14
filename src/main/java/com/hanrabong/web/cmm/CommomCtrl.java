package com.hanrabong.web.cmm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanrabong.web.enums.SQL;
import com.hanrabong.web.hcust.HCustMapper;
@Controller
public class CommomCtrl {
	@Autowired HCustMapper hCustMapper;
	
	@RequestMapping(value = "/cmm/create/db", method = RequestMethod.GET)
	public @ResponseBody Map<?,?> createdb(){
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("CREATE_DB", SQL.CREATE_DB.toString());
		System.out.println(paramMap);
		Consumer<HashMap<String,String>> c = t-> hCustMapper.createDB(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "Success");
		return paramMap;
	}
}
