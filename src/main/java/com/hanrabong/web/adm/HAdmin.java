package com.hanrabong.web.adm;

import java.io.Serializable;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data @Component
public class HAdmin implements Serializable{
	private static final long serialVersionUID= 1L;
	
	private String anum, aid, apw, aname, authority, part;
	
	
}