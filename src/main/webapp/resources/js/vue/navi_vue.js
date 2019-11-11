"use strict";
var navi_vue = navi_vue || {}
navi_vue ={
	navi_bd : ()=>{
	              return '<body class="bg-light">'+
	              ' <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-dark">'+
	              '  <a class="navbar-brand mr-auto mr-lg-0" href="#">'+
	              '<i class="fab fa-angrycreative"></i>'+
	              '</a>'+
	              '  <button class="navbar-toggler p-0 border-0" type="button" data-toggle="offcanvas">'+
	              '    <span class="navbar-toggler-icon"></span>'+
	              '  </button>'+
	              '  <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">'+
	              '    <ul class="navbar-nav mr-auto">'+
	              '      <li id="ago_write" class="nav-item active">'+
	              '      </li>'+
	              '      <li  class="nav-item">'+
	              '        <a class="nav-link" href="#">Notifications</a>'+
	              '      </li>'+
	              '      <li class="nav-item">'+
	              '        <a class="nav-link" href="#">Profile</a>'+
	              '      </li>'+
	              '      <li class="nav-item">'+
	              '        <a class="nav-link" href="#">Switch account</a>'+
	              '      </li>'+
	              '      <li class="nav-item dropdown">'+
	                      '<a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Settings</a>'+
	              '        <div class="dropdown-menu" aria-labelledby="dropdown01">'+
	              '          <a class="dropdown-item" href="#">Action</a>'+
	              '          <a class="dropdown-item" href="#">Another action</a>'+
	              '          <a class="dropdown-item" href="#">Something else here</a>'+
	              '        </div>'+
	              '      </li>'+
	              '<li id="logout" > </li>'+
	              '    </ul>'+
	              '    <form class="form-inline my-2 my-lg-0">'+
	                    '<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"  name= "searchWrd">'+
	              '      <div id ="searchBtn"></div>'+
	              '    </form>'+
	              '  </div>'+
	              '</nav>'+
	              '<div class="nav-scroller bg-white shadow-sm" id ="nav_under">'+
	              '  <nav class="nav nav-underline">'+
	              '    <a class="nav-link active" href="#" >Dashboard</a>'+
	              '    <a class="nav-link" href="#">'+
	              '      Friends'+
	              '      <span class="badge badge-pill bg-light align-text-bottom">27</span>'+
	              '    </a>'+
	              '    <a class="nav-link" href="#">Explore</a>'+
	              '    <a class="nav-link" href="#">Suggestions</a>'+
	              '    <a class="nav-link" href="#">Link</a>'+
	              '    <a class="nav-link" href="#">Link</a>'+
	              '    <a class="nav-link" href="#">Link</a>'+
	              '    <a class="nav-link" href="#">Link</a>'+
	              '    <a class="nav-link" href="#">Link</a>'+
	              '  </nav>'+
	              '</div>'+
	              '<main role="main" class="container"> ' +
	              '</main>'+
	              '<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>'
	             // '<script src="'+x.js+'/cmm/jquery-3.4.1.min.js" crossorigin="anonymous"></script>'+              
	                    '<script src="https://getbootstrap.com/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>'+
	              '        <script src="https://getbootstrap.com/docs/4.3/examples/offcanvas/offcanvas.js"></script>'
	                    '        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>'
	              '</body>'
	}
}