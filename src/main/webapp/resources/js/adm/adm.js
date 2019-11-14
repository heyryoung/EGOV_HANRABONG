"use strict"
var adm = adm || {};
adm = (()=>{
	    let _, js,css,img, brd_vuejs, navi_js, cookie_js,cname,cid,cnum,navivue_js,router_js,routerInfo,admvue_js,proxyjs;
	    let init = ()=>{
	       _= $.ctx()
	        js = $.js()
	        css = $.css()
	        img = $.img()
	        cname : getCookie("CNAME") 
	        cid : getCookie("CID")
	        cnum : getCookie("CNUM")
	       brd_vuejs = js+'/vue/brd_vue.js'
	       navi_js = js+'/cmm/navi.js'
	       navivue_js = js+'/vue/navi_vue.js'
	       cookie_js = js+'/cmm/cookie.js'
	       router_js =  js+'/cmm/router.js'
	       admvue_js =  js+'/vue/adm_vue.js'
	       routerInfo = {_,js,css,img,cookie_js,router_js,cookie_js}
	        proxyjs = js+'/cmm/proxy.js'
	   }
	let run =x=>$.getScript(x+'/resources/js/cmm/router.js',
		            ()=>{$.extend(new Session(x));
		    })	
	let onCreate = ()=>{
		run('/web');		
		init()
		$.when(
			$.getScript(admvue_js)	,			
			$.getScript(proxyjs)				
		).done(()=>{
			setContentView()		
		}).fail()


	}
	let setContentView = ()=>{
		
		$('<table id="tab"><tr></tr></table>')
		       .css({border: '1px solid black', width: '80%', height:'500px', margin: '0 auto'})
		       .appendTo('main')
		       
		 $.each([{name : 'left', width : '20%' },{name : 'right', width : '80%' }],(i,j)=>{
		        $('<td id="'+j.name+'"></td>')
		         .css({border: '1px solid black', width: j.width, 'vertical-align': 'top'})
		         .appendTo('#tab tr')
		 })

		 let arr = [{innerText : 'WebCrawling'  ,  name : 'webcrawling' },
			 {innerText : '고객관리'  ,  name : 'customctrl' },
			 {innerText : '커뮤니티관리'  ,  name : 'commctrl' },
			 {innerText : '상품등록'  ,  name : 'adminctrl'  },
			 {innerText : '상품삭제'  ,  name : 'pythonctrl'  },
			 {innerText : '상품수정'  ,  name : 'javactrl'}]
		 $.each(arr,(i,j)=>{
			 $('<h3 name='+j.name+'>'+j.innerText+'</h3>')
			 .css({border: '1px solid #ddd', width: '100%', margin: '0 auto'})		
			 .appendTo('#left')	
			 .click(function(){
				 event.preventDefault()
				  $(this).addClass('active')
				  $(this).siblings().removeClass('active')				 
				  $('#right').empty()	
				  switch($(this).attr('name')){
				  case 'webcrawling' : webCrawl()
					  break;
				  case 'customctrl' : custmgmt()
				  		break;
				  case 'commctrl' : commmgmt()
					  break;
				  case 'adminctrl' : adminVue()
					  break;
				  case 'pythonctrl' : pythonVue()
					  break;
				  case 'javactrl' : javaVue()
					  break;
				  }
			 })
		 })		 
	}
	
	
	let webCrawl=()=>{
		$('#right').empty()
		$('</br></br><h2>Web Crawling</h2></br></br>'+
				'<form id="crawl_form" class="form-inline my-2 my-lg-0">'+
				'  <select name="targetSite" size="1" multiple></select>'+
		          '<input id= "searchWrd" class="form-control mr-sm-2" type="text" value="네이버리뷰" placeholder="insert URL for crawling" aria-label="Search" name ="searchWrd" >'+
				'</form>')
		.appendTo('#right')
		
		
		$('#crawl_form input[class="form-control mr-sm-2"]')
		.css({width:'80%'})
		$.each([{sub:'직접입력'},
			{sub:'daum'},
			{sub:'google'},
			{sub:'youtube'},
			{sub:'naver'}],
			(i,j)=>{
			$('<option value='+j.sub+'>'+j.sub+'</option>').appendTo('#crawl_form select')
		})
		
		$('<button class="btn btn-secondary my-2 my-sm-0" type="submit">go crawl</button>')
		.appendTo('#crawl_form')
		.click(e=>{
			e.preventDefault()
			activeCrawler()			
			let arr = [$('#crawl_form option:selected').val(),
					$('#crawl_form input[type="text"]').val() ]
			if(!$.fn.nullChecker(arr)){
				alert($('#crawl_form select[name="targetSite"]').val())
				alert($('#crawl_form input[name="searchWrd"]').val())
				$.getJSON(_+'/tx/'+$('#crawl_form select[name="targetSite"]').val() 
						+'/'+$('#crawl_form input[name="searchWrd"]').val() 
						,d=>{$('<br/><div>'+d[0]+'</div>')
							.appendTo('#cResult')
				})
			}
		})
		
	}	

	
	let olivecrawling=()=>{
		$('#right').empty()
		$('</br></br><h2>olive Crawling</h2></br></br>'+
				'<div id= "InsertDumpData">'+
		'</div>')
		.appendTo('#right')
		
		
		$('<button class="btn btn-secondary my-2 my-sm-0" type="submit">InsertDumpData</button>')
		.appendTo('#InsertDumpData')
		.click(e=>{
			e.preventDefault()
			activeCrawler()			
				$.getJSON(_+'/tx/olivecrawling'
						,d=>{
							var temp = ""
							$.each(d , (i,j)=>{
								temp +=j +'<br/>'
							})
							$('#cResult').append(temp)	
						})
		})
	}	
	
	let activeCrawler = () =>{
		$('#cResult').remove()
		$('<div id="cResult"></div>')
		.css({width:'100%',height : '50%','overflow-x':'scroll', 'overflow-y':'scroll'})
		.append('<h4>RESULT</h4>')
		.appendTo('#right')


	}
	
	 let custmgmt =()=>{
		 $('#right').empty()
		 $(adm_vue.hcustctrl_body()).appendTo('#right')
		 $('<button>',{
			text:'데이터베이스 생성',
			href: '#'
		 }).appendTo('#right')
		 .click(e=>{
			 e.preventDefault()
			 $.getJSON(_+'/cmm/create/db',d=>{
				 alert(d.msg)
			 })
		 })
		 $('<button>',{
			 text:'리뷰테이블 생성',
			 href: '#'
		 }).appendTo('#right')
		 .click(e=>{
			 e.preventDefault()
			 $.getJSON(_+'//create/table',d=>{
				 alert(d.msg)
			 })
		 })
		 $('<button>',{
			 text:'즐겨찾기 테이블 생성',
			 href: '#'
		 }).appendTo('#right')
		 .click(e=>{
			 e.preventDefault()
			 $.getJSON(_+'/hcusts/create/favoriteTable',d=>{
				 alert(d.msg)
			 })
		 })
		 $('<button>',{
			 text:'즐찾 테이블 드롭',
			 href: '#'
		 }).appendTo('#right')
		 .click(e=>{
			 e.preventDefault()
			 $.getJSON(_+'/hcusts/drop/favoriteTable',d=>{
				 alert(d.msg)
			 })
		 })
		 $('<button>',{
			 text:'고객명단 대량등록',
			 href: '#'
		 }).appendTo('#right')
		 .click(e=>{
			 e.preventDefault()
			 $.getJSON(_+'/tx/resister/HCusts',d=>{
				 alert(d.count)
				 $('#registCount').text(d.count)
			 })
		 })
		$('#right button').css({margin: "10px"})
			 
	 } 
	 let commctrl =()=>{
		 $('#right').empty()
		 $('<a>커뮤니티 테이블 생성</a><br/>')
		 .appendTo('#right')
		 .click(e=>{
			 e.preventDefault()
			 $.getJSON(_+'/brds/fileupload')
		 })
	 } 
	 let adminVue =()=>{
		 $('#right').append(adm_vue.adminctrl_body())	
	 } 
	 let pythonVue =()=>{
		 $('#right').append(adm_vue.pythonctrl_body())	
	 } 
	 let javaVue =()=>{
		 $('#right').append(adm_vue.javactrl_body())	
	 } 	
	
	return {onCreate}
	
})();