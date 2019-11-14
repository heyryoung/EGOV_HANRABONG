"use strict"
var brd = brd || {};
brd =(()=>{
	    let _, js,css,img, brd_vuejs, navi_js, cookie_js,cname,cid,cnum,navivue_js,router_js,routerInfo,page_js,pagevue_js,compovue_js 
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
	       page_js = js+'/cmm/pagination.js'
	       pagevue_js=  js+'/vue/page_vue.js'
	       compovue_js=  js+'/vue/compo_vue.js'
	       routerInfo = {_,js,css,img,cookie_js,router_js,cookie_js}
	   }
	let run =x=>$.getScript(x+'/resources/js/cmm/router.js',
		            ()=>{$.extend(new Session(x));
		    })	
	let onCreate =()=>{
		run('/web');
		init();
		$.getScript(brd_vuejs)
		$.getScript(navi_js)
		$.getScript(cookie_js)
		$.getScript(page_js)
		$.getScript(pagevue_js)
		$.getScript(pagevue_js)
		setContentView();
		contentList();
	}
	
	let setContentView = ()=>{
		$('head').html(brd_vue.brd_head({css , img, js}))
	}
	
	let write = ()=>{
		$('#Suggestions').remove();
		$('#pagination').remove();
		$('#recent').html(brd_vue.brd_write({cname ,cid ,cnum}));
		$('#write_form input[name="writer"]').val(getCookie("CNAME"));
		$('#write_form input[name="cnum"]').val(getCookie("CNUM"));
		$('<reset>',{
			text : 'CANCEL' , 
			href: '#' ,
			style : 'float:right;width:100px;margin-right:10px'
		})
		.addClass('btn btn-danger')
		.appendTo("#write_form")   
		.click(e=>{
			e.preventDefault()
			contentList()
		})
		$('<button>',{
			text : 'SUBMIT' , 
			href: '#' ,
			style:  'float:right;width:100px;margin-right:10px'
		})
		.addClass('btn btn-primary')
		.appendTo("#write_form")     
		.click(e=>{
			e.preventDefault()
			let json = { 
					cnum :  $('#write_form input[name="cnum"]').val() ,
					writer : $('#write_form input[name="writer"]').val(),
					content : $('#write_form textarea[name="content"]').val()
			}
			$.ajax({
				url : '/web/articles/', 
				type : 'POST',
				dataType : 'json',
				data: JSON.stringify(json) , 
				contentType : 'application/json',
				success : d =>{
					alert('게시 성공');
					e.preventDefault()
					$.getScript(brd_vuejs)
					contentList()
				},
				error : e =>{
					alert('게시 실패');
				}
			})  	
		})
		
		$('<button>',{
			text : 'UPLOAD' , 
			style: 'float:right;width:100px;margin-right:10px'
		})
		.addClass('btn btn-warning')
		.appendTo("#write_form")     
		.click(e=>{
			e.preventDefault()
			let formData = new FormData()
			let files = $('#upload')[0].files
			let i = 0
			for(;i<files.length;i++){
				/*if($.fn.checkExtension({fname: files[i].name, fsize : files[i].size}){
					return
				})*/
				formData.append("uploadFile", files[i])
			}
			$.ajax({
				url: '/web/articles/fileupload/',
				processData: false,
				contentType: false,
				data : formData,
				type : 'POST',
				success: d=>{
					alert('파일업로드')},
				error: e=>{
					alert('앗 실쑤')
				}
			})
			alert(files)
		})
		
		$('<input>',{
			id : "upload",
			type:"file"
		}).appendTo('#write_form')
		
	}
	let navigation =()=>{
		navi.onCreate()
	}
	
	let readArticle = data =>{
		$('#recent').html(brd_vue.brd_write(data));
		$('#pagination').remove();		
		$('#write_form input[name="writer"]').val(data.writer);
		$('#write_form input[name="cnum"]').val(data.cnum);		
		$('#write_form textarea[name="content"]').val(data.content);		
		$('#write_form input[name="cnum"]').val(data.cnum);		
		$('#write_form input[name="brdseq"]').val(data.brdseq);		
		$('#Suggestions').remove();
		$('h1').text('ARTICLE DETAIL');
	
		$('<button>',{
			text : '목록으로' , 
			href: '#' ,
			style : 'float:right;width:100px;margin-right:10px'
		})// button
		.addClass('btn btn-info')
		.appendTo("#write_form")   
		.click(e=>{
			e.preventDefault()
			contentList()
		})// click
	
		
		$('<button>',{
			text : 'Delete' , 
			href: '#' ,
			style : 'float:right;width:100px;margin-right:10px'
		})//button
		.addClass('btn btn-danger')
		.appendTo("#write_form")   
		.click(e=>{
			e.preventDefault()			
			let json = { 
	     			brdseq :  $('#write_form input[name="brdseq"]').val() ,
				}     	
			$.ajax({
				url : _+'/articles/'+json.brdseq, 
				type : 'DELETE',
				dataType : 'json',
				data: JSON.stringify(json) , 
				contentType : 'application/json',
				success : d =>{
					alert('Delete success');
					contentList()					
				},
				error : e =>{
					alert('Delete fail');
				}
			}) // ajax
		})// click

		$('<button>',{
			text : 'Modify' , 
			href: '#' ,
			style:  'float:right;width:100px;margin-right:10px;'
		})// button
		.addClass('btn btn-success')
		.appendTo("#write_form")     
		.click(e=>{
	 		e.preventDefault()
			let json = { 
	 			brdseq :  $('#write_form input[name="brdseq"]').val() ,
				content : $('#write_form textarea[name="content"]').val()
			}     	
			$.ajax({
				url : _+'/articles/'+data.brdseq, 
				type : 'PUT',
				dataType : 'json',
				data: JSON.stringify(json) , 
				contentType : 'application/json',
				success : d =>{
					alert('Modify성공');
					contentList()
				},
				error : e =>{
					alert('Modify fail');
				}
			})   // ajax
		})// click
	}// readArticle()

	let contentList = () =>{
		var pageNo= ($('input[name="pageNo"]').val() == null ) ? 1 : $('input[name="pageNo"]').val() ;
		var pageSize=($('select[name="pageSize"]').val() == null ) ? 5 : $('select[name="pageSize"]').val() ;   
		$('main[class="container"]').html(brd_vue.brd_body({ctx: '/web' , js, cname : getCookie("CNAME") , cid : getCookie("CID") , cnum : getCookie("CNUM")}))
		$('#recent div[class="media text-muted pt-3"]').remove()
		$('#recent small[class="d-block text-right mt-3"]').remove()
		$.ajax({
			url : '/web/articles/page/'+pageNo+'/size/'+pageSize, 
			type : 'GET',
			contentType : 'application/json',
			success : d =>{
					pagination(d)
					alert(d.pageInfo.existPrev)
					$.each(d.articles, ( i , brd)=>{
						$('<div>',{
							href : '#'
						})
						.append(brd_vue.brd_contentList(brd))
						.appendTo('#recent')			
						.click(e=>{
							e.preventDefault()
							readArticle(brd)
						})
					})// each
				$('#recent').append( brd_vue.brd_allUpdatest())
				$('#selectBox')				
			},
			error : e =>{
			}
		}) 
	}// contentList	
	
	let pagination=d=>{
		alert(d.pageInfo.existPrev)
		$('#pagination').append(page_vue.page_vue_body())
		var cnt = 0;
		if(d.pageInfo.existPrev) {
		$('<li class="page-item"><a class="page-link" href="#">Previous</a></li>')
		.appendTo('ul[class="pagination justify-content-center"')
		.click(e=>{
			$('input[name="pageNo"]').val(d.pageInfo.blist[0]-5),
			contentList()
			})
		}
		
		$.each(d.pageInfo.blist, (i,j)=>{
			if(j != d.pageInfo.pageNum){
				$('<li class="page-item"><a class="page-link"  href="#">'+j+'</a></li>')
				.appendTo('ul[class="pagination justify-content-center"]')
				.click(e=>{
					e.preventDefault()
					$('input[name="pageNo"]').val(j)
					contentList()
				})
			}else if(j == d.pageInfo.pageNum){
				$('<li class="page-item"><a class="page-link"  href="#">'+j+'</a></li>')
				.appendTo('ul[class="pagination justify-content-center"]')
				.addClass('active')
			}
			
		})			

		if(d.pageInfo.existNext ) {
			$('    <li class="page-item"><a class="page-link" href="#">Next</a></li>')
			.appendTo('ul[class="pagination justify-content-center"')
			.click(e=>{
				e.preventDefault()
				$('input[name="pageNo"]').val(d.pageInfo.blist[0]+5),
				contentList()
			})		
		}
		
		$('<form id="sch_form">'+
				'  <select id = "selectBox" name="pageSize" size="1" mutiple></select>'+
		'</form>')
		.appendTo('#pagination')

		$('#selectBox').change(()=>{
			contentList()
		})
		
		$.each([{sub:'5개씩 보기' , cnt : 5 },{sub:'10개씩 보기', cnt : 10},{sub:'15개씩 보기', cnt : 15},{sub:'25개씩 보기', cnt : 25}],(i,j)=>{
			$('<option value='+j.cnt+' >'+j.sub+'</option>').appendTo('#sch_form select')
		})
		
		$('#selectBox > option[value='+d.pageInfo.pageSize+']').attr("selected",true)
		
	}	
	
	
		
	return { onCreate , write ,contentList};
})();