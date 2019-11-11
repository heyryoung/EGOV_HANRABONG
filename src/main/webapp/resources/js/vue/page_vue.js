"use strict";
var page_vue = page_vue || {};
page_vue ={
		page_vue_body : ()=>{
		return'  <ul class="pagination justify-content-center" style="margin:20px 0">'+
		'  </ul>'+
		'<input type="hidden" value="1" name ="pageNo"/>' + 
		'<input type="hidden" value="5" name ="pageSize"/>'
		
	}
}