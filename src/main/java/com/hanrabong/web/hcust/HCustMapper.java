package com.hanrabong.web.hcust;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface HCustMapper {
	public void insertHCust(HCust hCust);
	public HCust selectByCidCpw(HCust hCust);
	public int lastCNum();
	public int existId(String cid);
	public int countHCusts();
	public void createHCust(Map<?,?> paramMap);
	public void createFavorite(Map<?,?> paramMap);
	public void createDB(Map<?,?> paramMap);
	public void dropFavorite(Map<?,?> paramMap);

}