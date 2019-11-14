package com.hanrabong.web.brd;

import java.util.Arrays;
import java.util.HashMap;
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

import com.hanrabong.web.pxy.PageProxy;
import com.hanrabong.web.pxy.Trunk;
import com.hanrabong.web.enums.SQL;
import com.hanrabong.web.pxy.Box;

@RestController
@RequestMapping("/reviews")
public class ReviewCtrl {
	@Autowired Box<Brd> list;
	@Autowired Review brd;
	@Autowired ReviewMapper revMapper;
	@Autowired PageProxy pager;
	@Autowired Trunk<Object> trunk;
	

	/*@GetMapping("/page/{pageNo}/size/{pageSize}") // GET / post 글 목록(posts)을 봅니다(GET)
	public Map<?, ?> reviewList(@PathVariable String pageNo, @PathVariable String pageSize) {
		pager.setPageNum(pager.parseInt(pageNo));
		pager.setPageSize(pager.parseInt(pageSize));
		pager.paging();
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
	public Review readArticle(@PathVariable String brdseq) {
		Supplier<Review> n = () -> brdMapper.selectArticle(brdseq);
		brd = n.get();
		return brd;
	}*/

	
	
	@GetMapping("/create/reviewTable")
	public Map<?,?> createReview(){
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("CREATE_REVIEW", SQL.CREATE_REVIEW.toString());
		System.out.println(paramMap);
		Consumer<HashMap<String,String>> c = t-> revMapper.createReview(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "Success");
		return paramMap;
	}
	/*@PutMapping("/{brdseq}") // 글(posts)을 수정합니다.(PUT)
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
	*/
}