package com.hanrabong.web.pxy;

import java.io.File;
import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

@Component
public class GenFile {
	public File makeDir(String t, String u) {
		BiFunction<String,String,File> f = File :: new;
		return f.apply(t, u);
	}
	public File makeFile(File t, String u) {
		BiFunction<File, String, File> f = File :: new;
		return f.apply(t, u);
	}

}
/*제네릭 사용하기.
 * public class GenFile<T>{
	private File file;
	public File makeFile(T t1, String t2) {
		HashMap<String, T> o = new HashMap<>();
		o.put("T", t1);
		
		if(o.get("T") instanceof String) {
			file = new File((String)o.get("T"),t2);
		}else if (o.get("T") instanceof File) {
			file = new File((File)o.get("T"), t2);
		}
		return file;
	}
}

사용법
File uploadPath = new GenFile<String>().makeFile(uploadFolder,getFolder());
File savedFile = new GenFile<File>().makeFile(uploadPath, "");
-> 둘다 파일경로를 출력함.
*/
