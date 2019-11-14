package com.hanrabong.web.pxy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class Proxy {
	public int integer(String param) {
		Function<String,Integer>f = Integer::parseInt;
		return f.apply(param);
	}
	public boolean equals(String p1,String p2) {
		BiFunction<String,String,Boolean>f = String::equals;
		return f.apply(p1,p2);
	}
	public int parseInt(String param) {
		Function<String, Integer> f = Integer::parseInt;
		System.out.println("public int parseInt(String param)");
		return f.apply(param);
	}
	public String string(Object param) {
		Function<Object, String> f = String :: valueOf;
		return f.apply(param);
	}

	public int random(int n , int m) {
		
		BiFunction<Integer,Integer,Integer> r = (t,u) ->(int) (Math.random() * (m-n)) +n;  
		
		return r.apply(n, m);
	}
	
	public int[] intArray(int size) {
		Function<Integer,int[]> f = int[] :: new;
		return f.apply(size);
	}
	public String currentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public String cuttentTime() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}
	

}
