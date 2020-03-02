<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%
	String path = request.getRequestURI();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<base href="<%=basePath%>">
<title>产品</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">  
<link href="css/font-awesome.css" rel="stylesheet"> <!-- font-awesome icons --> 
<link rel="stylesheet" href="css/owl.carousel.css" type="text/css" media="all"/> <!-- Owl-Carousel-CSS -->
<!-- //Custom Theme files --> 
<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>  
<!-- //js -->
<script type="text/javascript">

<%
String username = (String)session.getAttribute("username");
if("".equals(username)||username==null){
	request.setAttribute("name", "<font color=red >游客</font>");
} else {
	request.setAttribute("name", username);
}
	%>
//   加一个商品的id参数
	function gologin() {
		
		 var username = "<%=session.getAttribute("username")%>";
		 if(""==username || username == "null"){
			 if (confirm("请先登录  现在去登录！！")) {
					window.location.href = "Userlogin.jsp" ;
				}
		 }else{
			  $(function(){                   
                   alert(username);
		            $.ajax({
		                url:'../ProductsCar',
		                type:'post',
		                dataType:'text',
		                data:$("#form").serialize(),
		                success:function(result){
		                	 if(result=="true") {
		                		 alert("添加购物车成功");
		                	 }else{
		                		 alert("添加购物车失败");
		                	 }
		                	
		                    //回调函数 
		                }
		           
		            });
		        });
			 
		 }
	// 先判断session里面的用户是否登录，
	// 未登录直接跳转到登录画面
	// 已经登录，则ajax访问后台servlet，并且传2个参数，用户id和商品id。
	// 后台servlet获取后，保存到购物车表中。并返回成功提示。
	// 前台ajax根据返回的数据判断，提示添加成功或者失败
		
	}
</script>
</head>
<body> 
	<!-- banner -->
	<div class="banner about-w3bnr">
		<!-- header -->
		<div class="header">
			<div class="w3ls-header"><!-- header-one --> 
				<div class="container">
					<div class="w3ls-header-left">
						<p>你好${name }
							<%
							
							if("".equals(username)||username==null){
								%>
								<a href="../UserServlet?action=over">去登录</a>
								
								<% 
							}else{
								%>
								
								<a href="../UserServlet?action=over">注销</a>
								<% 
							}
						
							%> </p>
					</div>
					<div class="w3ls-header-right">
						<ul> 
							<li class="head-dpdn">
								<i class="fa fa-phone" aria-hidden="true"></i> Call us: +86 666 6666 
							</li> 
							<li class="head-dpdn">
								<a href="Userlogin.jsp"><i class="fa fa-sign-in" aria-hidden="true"></i> 登录</a>
							</li> 
							<li class="head-dpdn">
								<a href="Usersignup.jsp"><i class="fa fa-user-plus" aria-hidden="true"></i> 注册</a>
							</li> 
							<li class="head-dpdn">
								<a href="offers.jsp"><i class="fa fa-gift" aria-hidden="true"></i> 提供</a>
							</li> 
							<li class="head-dpdn">
								<a href="help.jsp"><i class="fa fa-question-circle" aria-hidden="true"></i> 帮助</a>
							</li>
						</ul>
					</div>
					<div class="clearfix"> </div> 
				</div>
			</div>
			<!-- //header-one -->    
			<!-- navigation -->
			<div class="navigation agiletop-nav">
				<div class="container">
					<nav class="navbar navbar-default">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header w3l_logo">
							
							<h1><a href="Userindex.jsp">时光<span>Best Food Collection</span></a></h1>
						</div> 
						<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="Userindex.jsp" class="active">主页</a></li>	
								<!-- Mega Menu -->
								<li>
									<a href="Products.jsp" class="dropdown-toggle" >菜单 </a>
								</li>
								<li><a href="about.jsp">关于</a></li> 
								<li class="w3pages"><a href="<%=request.getContextPath()%>/UserServlet?action=show" >我的订单</a>
									
								</li>  
								<li><a href="contact.jsp">联系我们</a></li>
							</ul>
						</div>
						<div class="cart cart box_1"> 
							<form action="#" method="post" class="last"> 
								<input type="hidden" name="cmd" value="_cart" />
								<input type="hidden" name="display" value="1" />
								<button class="w3view-cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down" aria-hidden="true"></i></button>
							</form>   
						</div> 
					</nav>
				</div>
			</div>
			<!-- //navigation --> 
		</div>
		<!-- //header-end --> 
		<!-- banner-text -->
		<div class="banner-text">	
			<div class="container">
				<h2>唯有美食与爱不可辜负<br> <span>best chefs for you.</span></h2>
			</div>
		</div>
	</div>
	<!-- //banner -->    
	<!-- breadcrumb -->  
	<div class="container">	
		<ol class="breadcrumb w3l-crumbs">
			<li><a href="#"><i class="fa fa-home"></i> 主页</a></li> 
			<li class="active">食物</li>
		</ol>
	</div>
	<!-- //breadcrumb -->
	<!-- products -->
	
	<!-- modal --> 
	<div class="products">	 
		<div class="container">
				
					<div class="modal-body">
						<div class="col-md-5 modal_body_left">
							<img src="${caidan.img}" alt=" " class="img-responsive">
						</div>
						<div class="col-md-7 modal_body_right single-top-right">
						  <h3 class="item_name">${caidan.cainame}</h3>
						  <p></p>
						  <div class="single-rating">
								<ul>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li class="w3act"><i class="fa fa-star-o" aria-hidden="true"></i></li>
									<li class="rating">20 reviews</li>
									<li><a href="#">Add your review</a></li>
								</ul> 
							</div>
							<div class="single-price">
								<ul>
								  <li>￥${caidan.caiprice}</li>
								  <li><del>￥${caidan.caiprice+caidan.caiprice*0.1}</del></li>
								  <li><span class="w3off">10% OFF</span></li> 
								</ul>	
							</div>
							<p class="single-price-text">${caidan.miaoshu}</p>
							<form action="" method="post" id="form">
								<input type="hidden" name="w3ls_item" value="${caidan.cainame }" /> 
								<input type="hidden" name="amount" value="${caidan.caiprice }" /> 
								<button type="button" class="w3ls-cart" onclick="gologin()"><em class="fa fa-cart-plus" aria-hidden="true" ></em> 加入订单</button>
							</form>
<div class="single-page-icons social-icons"> 
			<ul>
									<li><h4>Share on</h4></li>
									<li><a href="#" class="fa fa-facebook icon facebook"> </a></li>
									<li><a href="#" class="fa fa-twitter icon twitter"> </a></li>
									<li><a href="#" class="fa fa-google-plus icon googleplus"> </a></li>
									<li><a href="#" class="fa fa-dribbble icon dribbble"> </a></li>
									<li><a href="#" class="fa fa-rss icon rss"> </a></li> 
								</ul>
							</div> 
						</div> 
						<div class="clearfix"> </div>
					</div>
				
			</div>
		
	</div> 
	<!-- //modal -->
	<!-- subscribe -->
	
	<!-- //subscribe --> 
	<!-- footer -->
	<div class="footer agileits-w3layouts">
		<div class="container">
			<div class="w3_footer_grids">
				<div class="col-xs-6 col-sm-3 footer-grids w3-agileits">
					<h3>公司</h3>
					<ul>
						<li><a href="about.html">关于我们</a></li>
						<li><a href="contact.html">联系我们</a></li>  
						<li><a href="careers.html">职业</a></li>  
						<li><a href="help.html">合作伙伴</a></li>   
					</ul>
				</div> 
				<div class="col-xs-6 col-sm-3 footer-grids w3-agileits">
					<h3>帮助</h3>
					<ul>
						<li><a href="faq.html">问答</a></li> 
						<li><a href="login.html">回复</a></li>   
						<li><a href="login.html">订单状态</a></li> 
						<li><a href="offers.html">提供</a></li> 
					</ul>  
				</div>
				<div class="col-xs-6 col-sm-3 footer-grids w3-agileits">
					<h3>政策信息</h3>
					<ul>  
						<li><a href="terms.html">条款和条件</a></li>  
						<li><a href="privacy.html">隐私权政策</a></li>
						<li><a href="login.html">退货政策</a></li> 
					</ul>   
				</div>
				<div class="col-xs-6 col-sm-3 footer-grids w3-agileits">
					<h3>菜单</h3> 
					<ul>
						<li><a href="menu.html">全日菜单</a></li> 
						<li><a href="menu.html">午餐</a></li>
						<li><a href="menu.html">晚餐</a></li>
						<li><a href="menu.html">特色</a></li> 
					</ul>  
				</div> 
				<div class="clearfix"> </div>
			</div>
		</div> 
	</div>
	<div class="copyw3-agile"> 
		<div class="container">
			<p>版权归 &copy; 2017.必胜客 所有.<a target="_blank" href="http://sc.chinaz.com/moban/"></a></p>
		</div>
	</div>
	<!-- //footer -->   
	<!-- cart-js -->
	<script src="js/minicart.js"></script>
	<script>
        w3ls.render();

        w3ls.cart.on('w3sb_checkout', function (evt) {
        	var items, len, i;

        	if (this.subtotal() > 0) {
        		items = this.items();

        		for (i = 0, len = items.length; i < len; i++) { 
        		}
        	}
        });
    </script>  
	<!-- //cart-js --> 
	<!-- Owl-Carousel-JavaScript -->
	<script src="js/owl.carousel.js"></script>
	<script>
		$(document).ready(function() {
			$("#owl-demo").owlCarousel ({
				items : 3,
				lazyLoad : true,
				autoPlay : true,
				pagination : true,
			});
		});
	</script>
	<!-- //Owl-Carousel-JavaScript -->  	
	<!-- the jScrollPane script -->
	<script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
	<script type="text/javascript" id="sourcecode">
		$(function()
		{
			$('.scroll-pane').jScrollPane();
		});
	</script>
	<!-- //the jScrollPane script -->
	<script type="text/javascript" src="js/jquery.mousewheel.js"></script> <!-- the mouse wheel plugin --> 
	<!-- start-smooth-scrolling -->
	<script src="js/SmoothScroll.min.js"></script>  
	<script type="text/javascript" src="js/move-top.js"></script>
	<script type="text/javascript" src="js/easing.js"></script>	
	<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
			
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
	</script>
	<!-- //end-smooth-scrolling -->	  
	<!-- smooth-scrolling-of-move-up -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			};
			*/
			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
	<!-- //smooth-scrolling-of-move-up -->  
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/bootstrap.js"></script>
</body>
</html>