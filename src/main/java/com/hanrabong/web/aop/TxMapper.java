package com.hanrabong.web.aop;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hanrabong.web.brd.Brd;

@Repository
@Transactional
public interface TxMapper {

	public void insertDumpArticle(String string);
	
}