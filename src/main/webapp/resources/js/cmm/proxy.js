"use strict"
$.prototype.nullChecker = x =>{
	let flag = false
	let i =0
	for ( i in x){
		if(x[i] === ' '){
			flag = true
		}
	}
	return flag
}

$(document).ready(function(){
	   $('#searchWrd').keydown(function(key) {
	       if (key.keyCode == 13) {
	           alert('엔터키 입력')
	       }
	   })
})
$.prototype.checkExtension(x){
	let regex= new RegExp("(.*?)\.(exe|sh|zip|alz)$") 
	let maxSize = 5242880;
	if (x.fsize >=maxSize){
		alert('파일 사이즈초과')
		return false
	}
	if(regex.test(x.frame)){
		alert('파일업로드 불가 종류')
		return false
	}
	return true
}