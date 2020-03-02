package com.system.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.person.Dindan;
import com.system.dbutil.CarUpdate;
import com.system.dbutil.DindanUpdate;

/**
 * Servlet implementation class NotifyServlet
 */
@WebServlet("/notify")

public class NotifyServlet extends HttpServlet {
	
	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("接收到支付宝的异步通知请求——");
        Map<String,String[]> parameterMap=request.getParameterMap();
        System.out.println(parameterMap);
        //成功处理后返回success
     
        //String username=request.getParameter("username");
     
       /* try {
        	
        	 new CarUpdate().tijiaodelCai(username);
			//new DindanUpdate().situationUp(username, "已付款");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        response.sendRedirect("TestDbToExcel");
       
    }

}
