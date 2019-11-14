package com.hanrabong.web.pxy;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("favorite")
public class FavoriteProxy {
	@Autowired Trunk<String> map;
	
	public Map<?,?> insertFavBrand() {
		return null;
	}
	public Map<?,?> insertFavTheme() {
		return null;
	}
	public Map<?,?> insertMyTypeITem() {
		return null;
	}
	public Map<?,?> insertFavITem() {
		return null;
	}
	public Map<?,?> insertPurItem() {
		return null;
	}
	
	
	public Map<?,?> deleteFavBrand() {
		return null;
	}
	public Map<?,?> deleteFavTheme() {
		return null;
	}
	public Map<?,?> deleteMyTypeITem() {
		return null;
	}
	public Map<?,?> deleteFavITem() {
		return null;
	}
	public Map<?,?> deletePurItem() {
		return null;
	}
	
	
	
}
