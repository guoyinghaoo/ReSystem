package com.system.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.dbutil.CarUpdate;

/**
 * Servlet implementation class ProductsCar
 */
@WebServlet("/ProductsCar")
public class ProductsCar extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

//PrintWriter out =response.getWriter(); 获取session getcainame getprice INsert car  ==-1out.flase
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		int result=0;
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		
			System.out.println("进来购物车了 ");
			String cainame=request.getParameter("w3ls_item");
			String caiprice=request.getParameter("amount");
			System.out.println(cainame);
			System.out.println(caiprice);
			System.out.println(username);
			try {
				
				result=new CarUpdate().caiinsert(username,cainame, caiprice);
				if(result==-1){					
					out.write("flase");
				}else{
					out.write("true");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				out.close();
			}
			request.setAttribute("msg", "<font color='green'>添加购物车成功</font>");
			/*if("".equals(username)||username==null){
				request.setAttribute("msg", "对不起请先登录！！！");
			}else {
			
		}		*/
//		request.getRequestDispatcher("User/Products.jsp").forward(request, response);
		
	}

}
