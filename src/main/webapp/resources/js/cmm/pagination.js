"use strict"
var pagination = pagination || {};
pagination = (()=>{
	    let _, js,css,img, brd_vuejs, navi_js, cookie_js,navivue_js,brd_js,pagevue_js,page_js
	    let init = x=>{
	       _= $.ctx()
	        js = $.js()
	        css = $.css()
	        img = $.img()
	       brd_vuejs = js+'/vue/brd_vue.js'
	       navi_js = js+'/cmm/navi.js'
	       navivue_js = js+'/vue/navi_vue.js'
	       cookie_js = js+'/cmm/cookie.js'
	       brd_js= js+'/brd/brd.js'	      
	       page_js = js+'/cmm/pagination.js'     
	       pagevue_js= js+'/vue/page_vue.js'      
	   }	
	
	let onCreate =d=>{
		init()
		$.when(
			$.getScript(brd_js),	
			$.getScript(pagevue_js)	
		)
		.done(()=>{
			setContentView(d)
		})		
		
	}
	let setContentView=d=>{
		$('#pagination').append(page_vue.page_vue_body())
		var cnt = 0;
		cnt = (d/10 == 0) ?  d/10-(d/10+1) : (d/10+1)
		//for (var i = 1; i < cnt ; i++) {
		$.each([1,2,3,4] , (i,j)=>{
			$('<li class="page-item"><a class="page-link" href="#">'+j+'</a></li>')
			.appendTo('#paginations')
			//.appendTo('ul[class="pagination justify-content-center"]')
		})			

		//}
		$('<li class="page-item"><a class="page-link" href="#">Next</a></li>')
		.appendTo('#paginations')		
		//.appendTo('ul[class="pagination justify-content-center"')
		
		
	}
	return {onCreate}
})();