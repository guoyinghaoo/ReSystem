package com.system.controller;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.person.Car;
import com.system.dbutil.CarExcute;
import com.system.dbutil.CarUpdate;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Servlet implementation class TestDbToExcel
 */
@WebServlet("/TestDbToExcel")
public class TestDbToExcel extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   HttpSession session = request.getSession();
	        String username=(String)session.getAttribute("username");
		
		try {
            WritableWorkbook wwb = null;
             
               // 创建可写入的Excel工作簿
               String fileName = "G://";
               fileName+=username+"Car.xls";
               File file=new File(fileName);
               if (!file.exists()) {
                   file.createNewFile();
               }
               //以fileName为文件名来创建一个Workbook
               wwb = Workbook.createWorkbook(file);

               // 创建工作表
               WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
               
               //查询数据库中所有的数据
               List<Car> list =new ArrayList<>();
               list=new CarExcute().AllExcute(username);
               //要插入到的Excel表格的行号，默认从0开始
               Label labelId= new Label(0, 0, "用户名(username)");//表示第
               Label labelName= new Label(1, 0, "编号(id)");
               Label labelSex= new Label(2, 0, "菜名(cainame)");
               Label labelNum= new Label(3, 0, "菜价(caiprice)");
               
               ws.addCell(labelId);
               ws.addCell(labelName);
               ws.addCell(labelSex);
               ws.addCell(labelNum);
               for (int i = 0; i < list.size(); i++) {
                   
                   Label labelId_i= new Label(0, i+1, list.get(i).getUsername()+"");
                   Label labelName_i= new Label(1, i+1, String.valueOf(list.get(i).getId()));
                   Label labelSex_i= new Label(2, i+1, list.get(i).getCainame());
                   Label labelNum_i= new Label(3, i+1, list.get(i).getCaiprice()+"");
                   ws.addCell(labelId_i);
                   ws.addCell(labelName_i);
                   ws.addCell(labelSex_i);
                   ws.addCell(labelNum_i);
               }
             
              //写进文档
               wwb.write();
              // 关闭Excel工作簿对象
               wwb.close();
               System.out.println("导出成功");
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
		 try {
			new CarUpdate().tijiaodelCai(username);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 response.sendRedirect("User/Products.jsp");
			
	}
	

}
