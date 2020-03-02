<%@page import="com.person.Caidan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.system.dbutil.CaidanExcute"%>
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
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Custom Theme files -->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet"
	media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
<link href="css/font-awesome.css" rel="stylesheet">
<!-- font-awesome icons -->
<link rel="stylesheet" href="css/owl.carousel.css" type="text/css"
	media="all" />
<!-- Owl-Carousel-CSS -->
<!-- //Custom Theme files -->
<!-- js -->
<script src="js/jquery-2.2.3.min.js"></script>
<!-- //js -->
<%
	    String username = (String)session.getAttribute("username");
	    if("".equals(username)||username==null){
	    	request.setAttribute("name", "<font color=red >游客</font>");
	    } else {
			request.setAttribute("name", username);
		}
	    	%>

<script type="text/javascript">
//   加一个商品的id参数
	function gologin(form) {
		// var username = session.getAttribute("username");
		 var username = "<%=session.getAttribute("username")%>";
		 if(""==username || username == "null"){
			 if (confirm("请先登录  现在去登录！！")) {
					window.location.href = "Userlogin.jsp" ;
				}
		 }else{
			  $(function(){                   
                    var myform="#"+form;
		            $.ajax({
		                url:'../ProductsCar',
		                type:'post',
		                dataType:'text',
		                data:$(myform).serialize(),
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
			<div class="w3ls-header">
				<!-- header-one -->
				<div class="container">
					<div class="w3ls-header-left">
						<p>
							你好${name }
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
						
							%>
							
						</p>
					</div>
					<div class="w3ls-header-right">
						<ul>
							<li class="head-dpdn"><i class="fa fa-phone"
								aria-hidden="true"></i> Call us: +86 666 6666</li>
							<li class="head-dpdn"><a href="Userlogin.jsp"><i
									class="fa fa-sign-in" aria-hidden="true"></i> 登录</a></li>
							<li class="head-dpdn"><a href="Usersignup.jsp"><i
									class="fa fa-user-plus" aria-hidden="true"></i> 注册</a></li>
							<li class="head-dpdn"><a href="offers.html"><i
									class="fa fa-gift" aria-hidden="true"></i> 提供</a></li>
							<li class="head-dpdn"><a href="help.html"><i
									class="fa fa-question-circle" aria-hidden="true"></i> 帮助</a></li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<!-- //header-one -->
			<!-- navigation -->
			<div class="navigation agiletop-nav">
				<div class="container">
					<nav class="navbar navbar-default"> <!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header w3l_logo">

						<h1>
							<a href="Userindex.jsp">时光<span>Best Food Collection</span></a>
						</h1>
					</div>
					<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="Userindex.jsp" class="active">主页</a></li>
							<!-- Mega Menu -->
							<li><a href="Products.jsp" class="dropdown-toggle">菜单 </a>

							</li>
							<li><a href="about.jsp">关于</a></li>
							<li class="w3pages"><a
								href="<%=request.getContextPath()%>/UserServlet?action=show">我的订单</a>

							</li>
							<li><a href="contact.jsp">联系我们</a></li>
						</ul>
					</div>
					<div class="cart cart box_1">
						<form action="#" method="post" class="last">
							<input type="hidden" name="cmd" value="_cart" /> <input
								type="hidden" name="display" value="1" />
							<button class="w3view-cart" type="submit" name="submit" value="">
								<i class="fa fa-cart-arrow-down" aria-hidden="true"></i>
							</button>
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
				<h2>
					唯有美食与爱不可辜负<br> <span>best chefs for you.</span>
				</h2>
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
	<div class="products">
		<div class="container">
			<div class="col-md-9 product-w3ls-right">
				<div class="product-top">
					<h4>食品种类</h4>
					<ul>
						<li class="dropdown head-dpdn"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Filter By<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">低价</a></li>
								<li><a href="#">高价</a></li>
								<li><a href="#">最新</a></li>
							</ul></li>
						<li class="dropdown head-dpdn"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown">Food Type<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">早餐</a></li>
								<li><a href="#">午餐</a></li>
								<li><a href="#">晚餐</a></li>
							</ul></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="products-row">
				 <%
				       List<Caidan> list=new ArrayList<>();
					   list=new CaidanExcute().AllExcute();
					for(int i=0;i<list.size();i++){
						Caidan caidan =list.get(i);
						%>
						
						
						
						<div class="col-xs-6 col-sm-4 product-grids">
                           <div class="flip-contain">
						<div class="flipper agile-products">
							<div class="front">
								<img src="<%=caidan.getImg() %>" class="img-responsive" alt="img">
								<div class="agile-product-text">
									<h5><%=caidan.getCainame()%><%=caidan.getCaiprice() %>￥</h5>
									<form action="" id="myform<%=i%>"
										method="post">
										<input type="hidden" id="w3ls_item" name="w3ls_item" value="<%=caidan.getCainame()%>">
										<input type="hidden" id="amount"  name="amount" value="<%=caidan.getCaiprice() %>">
										<button type="button" class="w3ls-cart pw3ls-cart"  onclick="gologin('myform<%=i%>')">
											<i class="fa fa-cart-plus" aria-hidden="true"></i>加入购物车
										</button>
										<a href="../UserServlet?action=food&id=<%=caidan.getCaiid()%>">更多</a>
									</form>
								</div>
							</div>

						</div>
                      </div>
					</div>
				
						<% 					
					}			 
				 %>
					

				
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="col-md-3 rsidebar">
				<div class="rsidebar-top">

					<div class="sidebar-row">
						<h4>餐品类型</h4>
						<ul class="faq">
							<li class="item1"><a href="#">主食</a></li>
							<li class="item2"><a href="#">小吃</a></li>
							<li class="item3"><a href="#">饮料</a></li>
						</ul>
						<div class="clearfix"></div>
						<!-- script for tabs -->
						<script type="text/javascript">
							$(function() {

								var menu_ul = $('.faq > li > ul'), menu_a = $('.faq > li > a');

								menu_ul.hide();

								menu_a
										.click(function(e) {
											e.preventDefault();
											if (!$(this).hasClass('active')) {
												menu_a.removeClass('active');
												menu_ul.filter(':visible')
														.slideUp('normal');
												$(this).addClass('active')
														.next()
														.stop(true, true)
														.slideDown('normal');
											} else {
												$(this).removeClass('active');
												$(this).next().stop(true, true)
														.slideUp('normal');
											}
										});

							});
						</script>
						<!-- script for tabs -->
					</div>

				</div>

			</div>
			<div class="clearfix"></div>
		</div>
	</div>



	<!-- subscribe -->
	<div class="subscribe agileits-w3layouts">
		<div class="container">
			<div class="col-md-6 social-icons w3-agile-icons">
				<h4>保持联系</h4>
				<ul>
					<li><a href="#" class="fa fa-facebook icon facebook"> </a></li>
					<li><a href="#" class="fa fa-twitter icon twitter"> </a></li>
					<li><a href="#" class="fa fa-google-plus icon googleplus">
					</a></li>
					<li><a href="#" class="fa fa-dribbble icon dribbble"> </a></li>
					<li><a href="#" class="fa fa-rss icon rss"> </a></li>
				</ul>
				<ul class="apps">
					<li><h4>下载我们的应用 :</h4></li>
					<li><a href="#" class="fa fa-apple"></a></li>
					<li><a href="#" class="fa fa-windows"></a></li>
					<li><a href="#" class="fa fa-android"></a></li>
				</ul>
			</div>
			<div class="col-md-6 subscribe-right">
				<h3 class="w3ls-title">
					订阅我们 <br>
					<span>业务通讯</span>
				</h3>
				<form action="#" method="post">
					<input type="email" name="email" placeholder="输入你的E-mail"
						required=""> <input type="submit" value="订阅">
					<div class="clearfix"></div>
				</form>
				<img src="images/i1.png" class="sub-w3lsimg" alt="" />
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
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
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="copyw3-agile">
		<div class="container">
			<p>
				版权归 &copy; 2017.必胜客 所有.<a target="_blank"
					href="http://sc.chinaz.com/moban/"></a>
			</p>
		</div>
	</div>
	<!-- //footer -->
	<!-- cart-js -->
	<script src="js/minicart.js"></script>
	<script>
		w3ls.render();

		w3ls.cart.on('w3sb_checkout', function(evt) {
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
			$("#owl-demo").owlCarousel({
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
		$(function() {
			$('.scroll-pane').jScrollPane();
		});
	</script>
	<!-- //the jScrollPane script -->
	<script type="text/javascript" src="js/jquery.mousewheel.js"></script>
	<!-- the mouse wheel plugin -->
	<!-- start-smooth-scrolling -->
	<script src="js/SmoothScroll.min.js"></script>
	<script type="text/javascript" src="js/move-top.js"></script>
	<script type="text/javascript" src="js/easing.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event) {
				event.preventDefault();

				$('html,body').animate({
					scrollTop : $(this.hash).offset().top
				}, 1000);
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

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<!-- //smooth-scrolling-of-move-up -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/bootstrap.js"></script>

</body>
</html>