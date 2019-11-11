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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.hanrabong.web.pxy.Proxy;
import com.hanrabong.web.pxy.ProxyMap;

@RestController
@RequestMapping("/articles")
public class BrdCtrl {
	@Autowired
	List<Brd> list;
	@Autowired
	Brd brd;
	@Autowired
	BrdMapper brdMapper;
	@Autowired
	Proxy pxy;
	@Autowired
	ProxyMap map;

	@GetMapping("/page/{pageNo}/size/{pageSize}") // GET / post 글 목록(posts)을 봅니다(GET)
	public Map<?, ?> articleList(@PathVariable String pageNo, @PathVariable String pageSize) {
		pxy.setPageNum(pxy.parseInt(pageNo));
		pxy.setPageSize(pxy.parseInt(pageSize));
		pxy.paging();
		System.out.println(pxy.getPageNum()+"pxy.getPageNum()pxy.getPageNum()pxy.getPageNum()pxy.getPageNum()pxy.getPageNum()");
		list.clear();
		Supplier<List<Brd>> n = () -> brdMapper.selectBrdArticles(pxy);
		map.accept(Arrays.asList("articles", "pageInfo"), Arrays.asList(n.get(), pxy));
		return map.get();
	}

	@GetMapping("/search/{searchWrd}") // GET / post 글 목록(posts)을 봅니다(GET)
	public Map<?, ?> searchWrd(@PathVariable String searchWrd) {
		pxy.paging();
		list.clear();
		Supplier<List<Brd>> n = () -> brdMapper.selectBrdArticles(pxy);
		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < (pxy.getEndPage() - pxy.getStartPage() + 1); i++) {
			temp.add(pxy.getStartPage() + i);
		}

		map.accept(Arrays.asList("articles", "pageInfo"), Arrays.asList(n.get(), pxy));
		return map.get();
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
		Supplier<List<Brd>> n = () -> brdMapper.selectBrdArticles(pxy);
		map.accept(Arrays.asList("msg", "SUCCESS"), Arrays.asList("SUCCESS", n.get()));
		return map.get();
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
		list.clear();
		// ISupplier<List<Brd>> n = ()-> brdMapper.selectBrdArticles();
		// list = (List<Brd>) n.get();
		return list;
	}
}