package com.hanrabong.web.adm;

import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminCtrl {
	@Autowired Map<String, Object>map;
	@Autowired Admin adm;
	@Autowired AdmMapper admMapper;
	
	
	@GetMapping("/")
	public Map<?,?> login(@RequestBody Admin adm){
	return map;
	}
	
	@PostMapping("/")
	public Map<?,?> register(@RequestBody Admin adm){
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
	@PostMapping("/{anum}")
	public Admin login(@PathVariable String anum ,@RequestBody Admin param) {
		Function<Admin,Admin> f = t ->  admMapper.selectByAnumPnum(param); 
		return f.apply(param);
	}	
	
	@PutMapping("/{anum}")
	public Map<?,?> modify(@PathVariable String anum, @RequestBody Admin param){
		map.clear();
		map.put("msg", "SUCCESS");
		return map;
	}
	
	@DeleteMapping("/{anum}")
	public Map<?,?> delete(@PathVariable String anum){
		
		return map;
	}
	
	
	
}