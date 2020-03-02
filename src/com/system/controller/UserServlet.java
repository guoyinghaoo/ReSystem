package com.system.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.person.Caidan;
import com.person.Car;
import com.person.User;
import com.system.dbutil.*;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("login".equals(action)){
			login(request, response);
		}else if("reg".equals(action)){
			try {
				regUser(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("show".equals(action)){
			try {
				Carshow(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("delCai".equals(action)){
			try {
				delCai(request, response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("over".equals(action)){
			request.getSession().invalidate();
			 response.sendRedirect("User/Userlogin.jsp");
		}else if("schUser".equals(action)){
			System.out.println("进来插所有");
			try {
				schUser(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("initEditUser".equals(action)){
			System.out.println("initEditUser");
			try {
				initEditUser(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("editUser".equals(action)){
			System.out.println("EditUser");
			try {
				EditUser(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("delUser".equals(action)){
			try {
				DelUser(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("addUser".equals(action)){
			try {
				addUser(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("food".equals(action)){
			try {
				food(request,response);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}
	
	
	//通过传过来的菜id,寻找菜单例的菜的相关信息,封装成菜单类并且传入详情页面
	public void food(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
		String caiid =req.getParameter("id");
		Caidan caidan=new CaidanExcute().showfood(Integer.parseInt(caiid));
		req.setAttribute("caidan", caidan);
		System.out.println("cainame"+caidan.getCainame()+"caiprice"+caidan.getCaiprice());
		req.getRequestDispatcher("User/food.jsp").forward(req, resp);
		
		
	}
	public void DelUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
		String uid=req.getParameter("uid");
		new UserUpdate().userDel(uid);
		schUser(req,resp);		
	}
	
		//传id用来搜用户在修改页面显示 set user currentPage Attri
	public void initEditUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		String uid=req.getParameter("uid");
		String currentPage=req.getParameter("currentPage");
		User user = new UserExcute().oneUser(uid);
		req.setAttribute("user", user);
		req.setAttribute("currentPage", currentPage);
		req.getRequestDispatcher("editUser.jsp").forward(req, resp);
		
	}
	public void EditUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException, SQLException {
		String uid=req.getParameter("uid");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		new UserUpdate().userUpdate(uid, username, password);
		schUser(req,resp);
	}
	
	/**
	 * 查询用户
	 * //显示用户先获取总订单size好分页   每页显示多少条数据  pagesize 所有条数total 要分的页数totalpage
	//如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据； 获取当前页 ；控制当前页的值 
		//建立开始索引   传start pageSize
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void schUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException, SQLException {
		// 获取请求的参数值
		String schUname = req.getParameter("schUname");
		List<User> listUser = new UserExcute().schUser(schUname);
		// 每页显示多少条数据
		int pageSize = 5;

		// 不分页情况下，所有数据的条数
		int total = listUser.size();

		int totalPage = 0;

		totalPage = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
        //如果总数据  不等于  每页数据  的倍数   那么页数=(总数据/每页数据)+1  如果相等那么 页数直接等于  总数据/每页数据
		String currentPage = req.getParameter("currentPage");
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

		List<User> list = new UserExcute().schUser(schUname, start, pageSize);
		req.setAttribute("userList", list);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPage", totalPage);
		req.getRequestDispatcher("userList1.jsp").forward(req, resp);
	}

	public void Carshow(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException{
		
				HttpSession session=request.getSession();
				String username=(String)session.getAttribute("username");
		List<Car> carlist=new ArrayList<>();
		try {
			carlist=new CarExcute().AllExcute(username);
			request.setAttribute("carlist", carlist);
			request.getRequestDispatcher("User/Car.jsp").forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
	public void delCai(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException{
		String caiid=request.getParameter("caiid");
		System.out.println("进来删除了  删除的id shi :"+caiid);
		new CarUpdate().delCai(Integer.parseInt(caiid));
		Carshow(request, response);
	
	
	}
	public void regUser(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException{
		   
		   String username = request.getParameter("username");
		   String password=request.getParameter("password");
		  try {
			if(new UserExcute().loginSerach(username)){
				  request.setAttribute("message", "<font color='red'>注册失败！用户名已存在！！</font>");
				  request.getRequestDispatcher("User/Usersignnup.jsp").forward(request, response);
			  }else{
				  new UserUpdate().userinsert(username, password); 
				  request.getRequestDispatcher("User/Userlogin.jsp").forward(request, response);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		     
	}
	
	public void addUser(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		 String username = request.getParameter("username");
		   String password=request.getParameter("password");
		   new UserUpdate().userinsert(username, password); 
		   schUser(request,response);
	}
		//先判断用户名密码这个用户有没有  如果有再判断adminlist是否包含这个username  有 建HttpSession session = request.getSession();跳后台   
	 public void login(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{

			
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String flag=request.getParameter("flag");
			List adminList=new ArrayList();
			
			try {
				if(UserExcute.loginExcute(username, password)){	
					adminList=new UserExcute().adminExcute();
					if(adminList.contains(username)){
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						// 单位为秒
						session.setMaxInactiveInterval(0);
						
						request.getRequestDispatcher("index1.jsp").forward(request, response);
						
					}else {
						
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						// 单位为秒
						session.setMaxInactiveInterval(0);
						request.getRequestDispatcher("User/Products.jsp").forward(request, response);
					}
					
					
				}
				else{
					request.setAttribute("message", "<font color='red'>登录失败！用户名或密码错误！！</font>");
					request.getRequestDispatcher("User/Userlogin.jsp").forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	   }
	 

}
