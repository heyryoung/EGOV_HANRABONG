package com.hanrabong.web.pxy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hanrabong.web.enums.Path;
@Component("filemgr")
public class FileProxy extends Proxy {
	public void fileupload(MultipartFile[] uploadFile) {
		System.out.println("들어옴");
		String uploadFolder =Path.UPLOAD_PATH.toString();
		File uploadPath= new File(uploadFolder,getFolder());
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		for(MultipartFile f:uploadFile) {
			String fname=f.getOriginalFilename();
			String extension=fname.substring(fname.lastIndexOf(".")+1);
			fname=fname.substring(fname.lastIndexOf("\\")+1,fname.lastIndexOf("."));
			System.out.println("파일명 :"+fname);
			File savefile =new File(uploadPath, fname+"-"+UUID.randomUUID().toString()+"."+extension);
			
			try {
				f.transferTo(savefile);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	public String getFolder() {
		
		
		return currentDate().replace("-", File.separator);
			
	}
}
