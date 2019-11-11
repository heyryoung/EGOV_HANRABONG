package com.hanrabong.web.adm;

import org.springframework.stereotype.Repository;


@Repository
public interface AdmMapper {

	public Admin selectByAnumPnum(Admin param);
	public void insertAdmin(Admin param);
	public void modifyAdmin(Admin paeam);
	public void deleteAdmin(Admin paeam);
	
}