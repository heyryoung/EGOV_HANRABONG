"use strict"
var navi = navi || {}
navi =(()=>{
	let _, js,css,img, brd_vuejs, navi_js, cookie_js,cname,cid,cnum,navivue_js,brd_js
	    let init = x=>{
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
	       brd_js= js+'/brd/brd.js'	      
	   }
	    let run =x=>$.getScript(x+'/resources/js/cmm/router.js',
			            ()=>{$.extend(new Session(x));
			    })	
	    
	let onCreate = ()=>{
		run('/web')
		init()
		$.when(
				$.getScript(brd_vuejs),
				$.getScript(navivue_js)
		)
		.done(()=>{
			setContentView()
		})
	}
	let setContentView= ()=>{
		$('<a>',{
			href : '#',
			click : e =>{
				e.preventDefault()
				$.getScript(brd_js).done(()=>{
					brd.write({_ ,js, cname,cid,cnum })			
				})
			},
			text: '글쓰기'
		})
		.addClass('nav-link')
		.appendTo('#ago_write')	
		
		
		$('<a>',{
			href : '#',
			click : e =>{
			        	e.preventDefault()
			        	deleteCookie()
			        	app.run(_)
			},
			text: '로그아웃'
		})
		.css('float','right')
		.addClass('nav-link')
		.appendTo('#logout')	
		
             //'      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>'+
		
		$('<button>',{
			href : '#',
			click : e =>{
				e.preventDefault()
				$.getJSON(_+'/search/'+$('input[name="searchWrd"]').val() 
						,d=>{
							
				})				
				
			},
			text: 'Search'
		})
		.addClass('btn btn-outline-success my-2 my-sm-0')
		.appendTo('#searchBtn')		
	}
	
	return {onCreate}
	
})();