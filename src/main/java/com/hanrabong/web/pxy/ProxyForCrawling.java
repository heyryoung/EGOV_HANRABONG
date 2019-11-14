package com.hanrabong.web.pxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.hanrabong.web.brd.BrdMapper;
import com.hanrabong.web.enums.Path;

import lombok.Data;

@Component @Lazy
public class ProxyForCrawling extends Proxy{
	private String search;
	private final int BLOCK_SIZE = 5;
	private List<Integer>blist ;
	@Autowired Box<String> proxyList;	
	@Autowired BrdMapper brdMapper;

	
	public Box<String> choose(Map<?, ?> paramMap) {
		String url ="";
		switch (string(paramMap.get("targetSite"))) {
		case "google":
			url = "https://www.google.co.kr/";
			break;
		case "naver":
			url = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+paramMap.get("searchWrd");
			break;
		case "youtube":
			url = "https://www.youtube.com/results?search_query="+paramMap.get("searchWrd");
			break;
		default:
			url = Path.CLAWLING_TARGET.toString();
			break;
		}
		crawling(url);
		
		return proxyList;
	}

	private List<?> crawling(String url) {
		List<String> list= new ArrayList<>();
		list.clear();
		try {
			Document rawData = Jsoup.connect(url).timeout(10*1000).get();
			  Elements review = rawData.select("div[class=atc]"); 
			  System.out.println(review.text()); 
			  for(Element e : review) {
				  list.add(e.text()+"\n ***************** \n");
			  }
			   
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return list;
	} 
	
	public List<String> productListCrawl(String categoryNum) {

		List<String> tempList = new ArrayList<String>();
		try {
			for (int pageIndex = 0; pageIndex < 1; pageIndex++) {
				final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"; 
				String oliveUrl = "http://www.oliveyoung.co.kr/store/display/getMCategoryList.do?dispCatNo="+categoryNum+"&fltDispCatNo=&prdSort=01&pageIdx="+pageIndex+"&rowsPerPage=24&searchTypeSort=btn_thumb" ;

				Connection.Response homePage = Jsoup.connect(oliveUrl)  
				     .method(Connection.Method.GET)  
				     .userAgent(USER_AGENT)  
				     .execute();
 
				Document temp = homePage.parse();
				Elements  tempforPName = temp.select("p.tx_name");
				Elements  tempforCurPrice = temp.select("span.tx_cur");
				Elements  tempforBrand = temp.select("span.tx_brand");
				
				int index =0;
				for (Element element : tempforPName) {
					if (index == 12 ) {
						break;
					}
					
					if(!element.text().contains("(한정판매)")) {
						tempList.add("제품 이름:    "+ element.text().replace("기획", "").replace("[1+1]","").replace("[올리브영 단독]", "").replace("[한정]", "").replace("[온라인단독]","").replace("[닥터자르트]", "")
									+" 브랜드 :   "+tempforBrand.get(index).text()
									+ " 가격 :   "+tempforCurPrice.get(index).text() );
	//					tempList.removeIf(x -> x.contains("(한정판매)"));
	//					tempList.replaceAll( c -> c.replace("기획", "").replace("[1+1]","").replace("[올리브영 단독]", "").replace("[한정]", "").replace("[온라인단독]","").replace("[닥터자르트]", ""));
						index++;
					}
				}
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		return tempList;
	} 	
	
	
	public List<String> getCategoty() throws Exception {
		
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"; 
		String oliveurl = "http://www.oliveyoung.co.kr/store/main/main.do" ;

		Connection.Response homePage = Jsoup.connect(oliveurl)  
		     .method(Connection.Method.GET)  
		     .userAgent(USER_AGENT)  
		     .execute();		
		 
		Document temp = homePage.parse();
		
		Elements  temp1 = temp.select("ul.all_menu_wrap").select("a");
		
		System.out.println(temp1.size() + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" ); // 카테고리 갯수 
		
		List<String> tempList = new ArrayList<String>();
		List<String> tempList1 = new ArrayList<String>();
		for (Element element : temp1) {
			if (element.attr("data-ref-dispcatno").length() !=11) {
				tempList.add(element.attr("data-ref-dispcatno"));
			}
		}
		int index1 =0;
		List<String> List = new ArrayList<String>();
		for (String element : tempList) {
			tempList1 = productListCrawl(element);
			for (String string : tempList1) {
				if (string != null) {
					List.add(index1, string);  
					index1++;
				}
			}
		}
		return List;
	}
	
	
	public int random(int n , int m) {
		
		BiFunction<Integer,Integer,Integer> r = (t,u) ->(int) (Math.random() * (m-n)) +n;  
		System.out.println("public int random(int n , int m)public int random(int n , int m)public int random(int n , int m)");
		return r.apply(n, m);
	}
	
	
	
}