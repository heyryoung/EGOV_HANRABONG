package com.hanrabong.web.hcust;

import org.springframework.stereotype.Repository;

@Repository
public interface HCustMapper {
 public void insertHCust(HCust hCust);
 public HCust selectByCidCpw(HCust hCust);
 public int lastCNum();
public int existId(String cid);
		
}