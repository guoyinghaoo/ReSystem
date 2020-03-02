package com.system.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.person.Caidan;
import com.person.Car;
import com.person.Dindan;
import com.person.User;
import com.person.Zhuozi;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.system.dbutil.CaidanExcute;
import com.system.dbutil.CaidanUpdate;
import com.system.dbutil.CarUpdate;
import com.system.dbutil.DindanExcute;
import com.system.dbutil.DindanUpdate;
import com.system.dbutil.UserExcute;
import com.system.dbutil.UserUpdate;
import com.system.dbutil.ZhuoziExcute;
import com.system.dbutil.ZhuoziUpdate;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		if("first".equals(action)){
			try {
				try {
					adddindan(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("schDingdan".equals(action)){			
				try {
					schDindan(request,response);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else if("initEditZhuozi".equals(action)){
			try {
				initEditZhuozi(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("editDindan".equals(action)){
			try {
				editDindan(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("schZhuozi".equals(action)){
			try {
				schZhuozi(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("schCaidan".equals(action)){
			try {
				schCaidan(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("AddCai".equals(action)){
			try {
				addCai(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("addZhuozi".equals(action)){
			try {
				addZhuozi(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("delZhuozi".equals(action)){
			try {
				delZhuozi(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("delDindan".equals(action)){
			try {
				delDindan(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	//删除订单 打印订单
	//数据库订单表去掉     桌子清空
	public void delDindan(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException, SQLException {
		String id=req.getParameter("uid");
		String username=req.getParameter("uname");
		System.out.println("进来删除订单的username"+username);
		new DindanUpdate().delCaidan(id);
		new ZhuoziUpdate().freezhuozi(username);
		schDindan(req, resp);
		
		
	}
	
	
	
	//删除桌子
	
	public void delZhuozi(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException, SQLException {
		String id=req.getParameter("id");
		new ZhuoziUpdate().delZhuozi(id);
		schZhuozi(req,resp);
		
	}
	//增加桌子
	public void addZhuozi(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException, SQLException {
		new ZhuoziUpdate().zhuoziAdd();
		schZhuozi(req,resp);
	}
	
	//增加菜
	
	public void addCai(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		String cainame1 = request.getParameter("cainame");
		String caiprice = request.getParameter("caiprice");
		String img = request.getParameter("img");
		String miaoshu1=request.getParameter("miaoshu");
		String cainame= new String(cainame1.getBytes("iso-8859-1"),"utf-8");
		String miaoshu= new String(miaoshu1.getBytes("iso-8859-1"),"utf-8");
		new CaidanUpdate().addCai(cainame, Integer.parseInt(caiprice), img,miaoshu);
		schCaidan(request,response);
		
	}
	
	//显示菜单先获取总订单size好分页   每页显示多少条数据  pagesize 所有条数total 要分的页数totalpage
	//如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据； 获取当前页 ；控制当前页的值 
		//建立开始索引 
	public void schCaidan(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		List<Caidan> caiList=new CaidanExcute().AllExcute();
		// 每页显示多少条数据
					int pageSize = 5;

					// 不分页情况下，所有数据的条数
					int total = caiList.size();

					int totalPage = 0;

					totalPage = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
			        //如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据
					String currentPage = request.getParameter("currentPage");
					System.out.println(currentPage);
					// 当前页
					int page = Integer.parseInt(currentPage);
					
					if(page > totalPage) {
						page = totalPage;
					}
					
					if(page < 1) {
						page = 1;
					}

					// 数据查询的起始索引号
					int start = 0;

					start = (page - 1) * pageSize;
					System.out.println("start"+start+"pageSize"+pageSize);
					List<Caidan> list=new CaidanExcute().AllExcute(start, pageSize);					
		request.setAttribute("caiList", list);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPage", totalPage);
		System.out.println("totalPage"+totalPage);
		request.getRequestDispatcher("showcaidan.jsp").forward(request, response);
		
	}
	
	
	//显示所有桌子  先获取总订单size好分页   每页显示多少条数据  pagesize 所有条数total 要分的页数totalpage	
	//如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据； 获取当前页 ；控制当前页的值 
	//建立开始索引
	public void schZhuozi(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		List<Zhuozi> ZhuoziList = new ArrayList<>();
		   System.out.println("进来插订单");
		   String id=request.getParameter("id");
		   ZhuoziList=new ZhuoziExcute().schZhuozi(id);
		// 每页显示多少条数据
			int pageSize = 5;

			// 不分页情况下，所有数据的条数
			int total = ZhuoziList.size();

			int totalPage = 0;

			totalPage = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
	        //如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据
			String currentPage = request.getParameter("currentPage");
			System.out.println(currentPage);
			// 当前页
			int page = Integer.parseInt(currentPage);
			
			if(page > totalPage) {
				page = totalPage;
			}
			
			if(page < 1) {
				page = 1;
			}

			// 数据查询的起始索引号
			int start = 0;

			start = (page - 1) * pageSize;
			List<Zhuozi> list =new ZhuoziExcute().schZhuozi(id,start, pageSize);
			request.setAttribute("zhuoziList", list);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("showZhuozi.jsp").forward(request, response);
		
		
	}
	//在订单里边更新桌子信息  并且更新桌子表
	//如果zhuozi 为null没有输入 修改的是状态  记得getbytes id修改a单号   否则修改订单id 并且修改桌子空闲zhuozi也是id
	public void editDindan(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		String id= request.getParameter("uid");
		String zhuozi=request.getParameter("zhuozi");
		String username=request.getParameter("username");
		
		if("".equals(zhuozi)||zhuozi==null){
			String situation1=request.getParameter("situation");
			String situation= new String(situation1.getBytes("iso-8859-1"),"utf-8");
			new DindanUpdate().situationUpdate(id, situation);
		}else{
			new ZhuoziUpdate().zhuoziChange(zhuozi, username);
			new DindanUpdate().dindanUpdate(id, zhuozi);
			
		}
		
		schDindan(request,response);
		
	}
	//前台为用户选择桌号
	//传id用来搜订单 在修改页面显示 set dindan currentPage Attri
	public void initEditZhuozi(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		String uid= request.getParameter("uid");
		String currentPage=request.getParameter("currentPage");
		Dindan dindan=new DindanExcute().oneUser(uid);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("dindan", dindan);
		request.getRequestDispatcher("editZhuozi.jsp").forward(request, response);
	}
	//用户提交订单后调用此方法增加订单
	//根据username 搜索是否有订单，有price更新加菜  没有insert
	public void adddindan(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		   request.setCharacterEncoding("UTF-8");
		   String username = request.getParameter("username");
		   String allprice=request.getParameter("allprice");
		   String table=request.getParameter("table");
		   String situation1=request.getParameter("situation");
		   String situation= new String(situation1.getBytes("iso-8859-1"),"utf-8");
		   request.setAttribute("username", username);
		   request.setAttribute("allprice", allprice);
		   List<Car> list=new ArrayList<>();
		   
		   //此处要分两部分  首先如果数据库里边有这个username 那么用户就是加菜操作  如果username 没有  那么就增加一个订单
		   if(new DindanExcute().jiacaiExcute(username)){
			   int price=new DindanExcute().schPrice(username);
			   int nowprice=price+Integer.valueOf(allprice);
			   new DindanUpdate().priceUpdate(nowprice, username);
			   
			 //清空购物车操作
		   
		   }else {
			   
			 
			   System.out.println("username:"+username+"allprice:"+allprice+"table:"+table+"situation:"+situation);
					  new DindanUpdate().dindaninsert(username, allprice, table, situation);
					  //清空购物车操作
					  //桌子状态先不写      前台显示已经在里边的订单     输入桌子号码  订单更新  桌子状态更新 
		   }		  
				  request.getRequestDispatcher("User/pay.jsp").forward(request, response);
		
	}
	//前台打印订单 先获取总订单size好分页   每页显示多少条数据  pagesize 所有条数total 要分的页数totalpage
	//如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据； 获取当前页 ；控制当前页的值 
	//建立开始索引
	public void schDindan(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		   request.setCharacterEncoding("UTF-8");
		   List<Dindan> dindanList = new ArrayList<>();
		   String schUname= request.getParameter("schUname");
		   System.out.println("进来插订单");
		   dindanList=new DindanExcute().schUser(schUname);
		// 每页显示多少条数据
			int pageSize = 5;

			// 不分页情况下，所有数据的条数
			int total = dindanList.size();

			int totalPage = 0;

			totalPage = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
	        //如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据
			String currentPage = request.getParameter("currentPage");
			System.out.println(currentPage);
			// 当前页
			int page = Integer.parseInt(currentPage);
			
			if(page > totalPage) {
				page = totalPage;
			}
			
			if(page < 1) {
				page = 1;
			}

			// 数据查询的起始索引号
			int start = 0;

			start = (page - 1) * pageSize;
			List<Dindan> list = new DindanExcute().schUser(schUname, start, pageSize);
			request.setAttribute("dindanList", list);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("dindanshow.jsp").forward(request, response);
	}
		   

}
