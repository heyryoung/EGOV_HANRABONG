package com.hanrabong.web.brd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.hanrabong.web.pxy.PageProxy;
import com.hanrabong.web.pxy.Trunk;
import com.hanrabong.web.pxy.Box;
import com.hanrabong.web.pxy.FileProxy;

@RestController
@RequestMapping("/articles")
public class BrdCtrl {
	@Autowired Box<Brd> list;
	@Autowired Brd brd;
	@Autowired BrdMapper brdMapper;
	@Autowired PageProxy pager;
	@Autowired Trunk<Object> trunk;
	@Autowired FileProxy filemgr;

	@GetMapping("/page/{pageNo}/size/{pageSize}") // GET / post 글 목록(posts)을 봅니다(GET)
	public Map<?, ?> articleList(@PathVariable String pageNo, @PathVariable String pageSize) {
		pager.setPageNum(pager.parseInt(pageNo));
		pager.setPageSize(pager.parseInt(pageSize));
		pager.paging();
		System.out.println(pager.getPageNum()+"pxy.getPageNum()pxy.getPageNum()pxy.getPageNum()pxy.getPageNum()pxy.getPageNum()");
		Supplier<List<Brd>> n = () -> brdMapper.selectBrdArticles(pager);
		trunk.put(Arrays.asList("articles", "pageInfo"), Arrays.asList(n.get(), pager));
		return trunk.get();
	}

	@GetMapping("/search/{searchWrd}") // GET / post 글 목록(posts)을 봅니다(GET)
	public Map<?, ?> searchWrd(@PathVariable String searchWrd) {
		pager.paging();
		Supplier<List<Brd>> n = () -> brdMapper.selectBrdArticles(pager);
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < (pager.getEndPage() -pager.getStartPage() + 1); i++) {
			temp.add(pager.getStartPage() + i);
		}

		trunk.put(Arrays.asList("articles", "pageInfo"), Arrays.asList(n.get(), pager));
		return trunk.get();
	}

	@GetMapping("/{brdseq}") // GET / post 글 (posts)을 봅니다(GET)
	public Brd readArticle(@PathVariable String brdseq) {
		Supplier<Brd> n = () -> brdMapper.selectArticle(brdseq);
		brd = n.get();
		return brd;
	}

	@PostMapping("/") // POST / posts 글(posts)을 생성합니다(POST)
	public Map<?, ?> writeArticle(@RequestBody Brd param) {
		Consumer<Brd> c = t -> brdMapper.insertArticle(param);
		c.accept(param);
		Supplier<List<Brd>> n = () -> brdMapper.selectBrdArticles(pager);
		trunk.put(Arrays.asList("msg", "SUCCESS"), Arrays.asList("SUCCESS", n.get()));
		return trunk.get();
	}

	@PutMapping("/{brdseq}") // 글(posts)을 수정합니다.(PUT)
	public Brd updateArticle(@PathVariable String brdseq, @RequestBody Brd param) {
		Consumer<Brd> c = t -> brdMapper.modify(param);
		c.accept(param);
		Supplier<Brd> n = () -> brdMapper.selectArticle(param.getBrdseq());
		brd = n.get();
		return brd;
	}

	@DeleteMapping("/{brdseq}") // delete / posts /:id 글(posts)을 삭제합니다.(DELETE)
	public List<Brd> deleteArticle(@PathVariable String brdseq, @RequestBody Brd param) {
		Consumer<Brd> c = t -> brdMapper.delete(param);
		c.accept(param);
		// ISupplier<List<Brd>> n = ()-> brdMapper.selectBrdArticles();
		// list = (List<Brd>) n.get();
		return list.getList();
	}
	@PostMapping("/fileupload")
	public void fileupload(MultipartFile[] uploadFile) {
		filemgr.fileUpload(uploadFile);
	}

	
	
}