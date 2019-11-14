"use strict";
var auth = auth || {}

auth = (()=>{
	const WHEN_ERR = '호출하는 JS 파일을 찾지 못했습니다.'
	let _,js,img,css,auth_vuejs,brd_vuejs,brd_js, cookie_js,navi_js,navivue_js,adm_js;
	let init = x=>{
		_=$.ctx();
		js=$.js();
		css = $.css();
		img = $.img();
		auth_vuejs = js+'/vue/auth_vue.js';
		brd_vuejs= js+'/vue/brd_vue.js';
		brd_js= js+'/brd/brd.js';
		cookie_js = js + '/cmm/cookie.js';
		navi_js = js+'/cmm/navi.js';
		navivue_js = js+'/vue/navi_vue.js';		
		adm_js = js+'/adm/adm.js';		
	} 
	let onCreate =()=>{
		init();
		$.when(
			$.getScript(cookie_js),
			$.getScript(auth_vuejs),
			$.getScript(navi_js),
			$.getScript(navivue_js),
			$.getScript(brd_vuejs),			
			$.getScript(brd_js),
			$.getScript(adm_js)
		).done(()=>{
		        	setContentView()
		        	automate()
	    		$('#a_go_join').click(e=>{
		         		e.preventDefault()
		         		joinForm()
	    		})
		}).fail(()=>{alert(WHEN_ERR)})
	}

	
	let setContentView = ()=>{
		$('head').html(auth_vue.login_head({_,js,css, img}))
		$('body')
		.addClass('text-center')
		.html(auth_vue.login_body( {css , img , js }))
		login()
		access()
		
	}

	
	let joinForm =()=>{
		$('head').html(auth_vue.join_head())
		$('body').html(auth_vue.join_body())
		$('#cid').keyup(()=>{
				if($('#cid').val().length > 2){
					$.ajax({
						url : _+'/hcusts/'+$('#cid').val()+'/exist', 
						contentType : 'application/json',
						success : d =>{
							if (d.msg==='SUCCESS') {
								$('#dupl_check').val('사용가능한 ID 입니다').css('color','green');
							}else{
								$('#dupl_check').val('사용불가능한 ID입니다.').css('color','red');	
							}
						},
						error : e =>{
							alert('error' )
							return 'false';
						}
					})  
				}
			});
		$('<button>',{
				text : 'Continue to checkout' , 
				href: '#' ,
				click : e=>{
	         		e.preventDefault()
					let data = { 
							cid :  $('#cid').val() ,
							cpw : $('#cpw').val(),
							cname : $('#cname').val()
					}
	         		existId(data)
				} 
			})
			.addClass('btn btn-primary btn-lg btn-block')
			.appendTo('#btn_join')       
	}
	
	let join = data=>{
				$.ajax({
					url : _+'/hcusts/', 
					type : 'POST',
					dataType : 'json',
					data: JSON.stringify(data) , 
					contentType : 'application/json',
					success : d =>{
						alert('회원가입  ' + d.msg)
						if (d.msg==='SUCCESS') 
							$('head').html(auth_vue.login_head( {css : $.css(), img : $.img(), js:$.js() }))
							$('body')
							.addClass('text-center')
							.html(auth_vue.login_body( {css : $.css(), img : $.img(), js:$.js() }))
							login()
					},
					error : e =>{
						alert('AJAX실패' )
					}
				})    
	}	
	
	let existId = data =>{
		$.ajax({
			url : _+'/hcusts/'+data.cid +'/exist', 
			type : 'GET',
			contentType : 'application/json',
			success : d =>{
				if (d.msg==='SUCCESS') {
					join(data)
					return true;
				}else{
					alert('있는 아이디 입니다.');	
				return false;
				}
			},
			error : e =>{
				alert('error' )
				return 'false';
			}
		})    
	}

	    let access =()=>{
		    	$('#a_go_admin').click(()=>{
		    		let ok = confirm('사원입니까?')
		        	if(ok){
						$('body').empty()
						$('body').removeClass()
						$(navi_vue.navi_bd()).appendTo('body')							
						$.when(navi.onCreate()).done(()=>{
							$('#nav_under').remove()
							adm.onCreate()
						})
					        		
		        		
		        		/*let anum = prompt('사원번호를 입력하시오') 
		        		$.ajax({
		        			url:_+'/admins/'+anum,
		        			type:'POST',
					dataType : 'json',
					data: JSON.stringify({anum : anum , pnum : prompt('비밀번호를 입력하시오')}) , 
		        			contentType:'application/json',
		        			success:d=>{
		        				if(d.msg === 'SUCCESS'){
		        					alert('환영합니다')
		        					// admin.onCreate()
		        				}else{
		        					alert('접근권한이 없습니다')
		        					// app.run(_)
		        				}
		        			},
		        			error:e=>{}
		        		})*/
		        	}
		    	})
		    	
		    	
		    }	
	
	
	let login = ()=>{
		$('<button>',{
			type: "submit",
			text : "Log In",
			click: 	e=>{				
					e.preventDefault()
				let data = { 
						cid :  $('#cid').val() ,
						cpw : $('#cpw').val()
					}
					$.ajax({
						url : _+'/hcusts/'+data.cid, 
						type : 'POST',
						dataType : 'json',
						data: JSON.stringify(data) , 
						contentType : 'application/json',
						success : d =>{
							setCookie("CID", d.cid)
							setCookie("CNAME", d.cname)
							setCookie("CNUM", d.cnum)
							$('body').empty()
							$('body').removeClass()
							$(navi_vue.navi_bd()).appendTo('body')							
							$.when(navi.onCreate()).done(()=>{brd.onCreate()})
							alert(d.cname	 + '님 환영합니다.');
						},
						error : e =>{
							alert('아이디와 비밀번호가 맞지 않습니다.' )
						}
					})    
			}
		})
		.addClass('btn btn-lg btn-primary btn-block')
		.appendTo('#btn_login')		
	}
	
	let automate = ()=>{
			// $('#btn_login').trigger("click")
			$('#cid').val('2') ,
			$('#cpw').val('2')
	}

	
	return {onCreate, join, login }
})();
