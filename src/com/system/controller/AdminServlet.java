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
	//ɾ������ ��ӡ����
	//���ݿⶩ����ȥ��     �������
	public void delDindan(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException, SQLException {
		String id=req.getParameter("uid");
		String username=req.getParameter("uname");
		System.out.println("����ɾ��������username"+username);
		new DindanUpdate().delCaidan(id);
		new ZhuoziUpdate().freezhuozi(username);
		schDindan(req, resp);
		
		
	}
	
	
	
	//ɾ������
	
	public void delZhuozi(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException, SQLException {
		String id=req.getParameter("id");
		new ZhuoziUpdate().delZhuozi(id);
		schZhuozi(req,resp);
		
	}
	//��������
	public void addZhuozi(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException, SQLException {
		new ZhuoziUpdate().zhuoziAdd();
		schZhuozi(req,resp);
	}
	
	//���Ӳ�
	
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
	
	//��ʾ�˵��Ȼ�ȡ�ܶ���size�÷�ҳ   ÿҳ��ʾ����������  pagesize ��������total Ҫ�ֵ�ҳ��totalpage
	//���������  ������  ÿҳ����  �ı���   ��ôҳ��=(������/ÿҳ����)+1  ��������ô ҳ��ֱ�ӵ���  ������/ÿҳ���ݣ� ��ȡ��ǰҳ �����Ƶ�ǰҳ��ֵ 
		//������ʼ���� 
	public void schCaidan(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		List<Caidan> caiList=new CaidanExcute().AllExcute();
		// ÿҳ��ʾ����������
					int pageSize = 5;

					// ����ҳ����£��������ݵ�����
					int total = caiList.size();

					int totalPage = 0;

					totalPage = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
			        //���������  ������  ÿҳ����  �ı���   ��ôҳ��=(������/ÿҳ����)+1  ��������ô ҳ��ֱ�ӵ���  ������/ÿҳ����
					String currentPage = request.getParameter("currentPage");
					System.out.println(currentPage);
					// ��ǰҳ
					int page = Integer.parseInt(currentPage);
					
					if(page > totalPage) {
						page = totalPage;
					}
					
					if(page < 1) {
						page = 1;
					}

					// ���ݲ�ѯ����ʼ������
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
	
	
	//��ʾ��������  �Ȼ�ȡ�ܶ���size�÷�ҳ   ÿҳ��ʾ����������  pagesize ��������total Ҫ�ֵ�ҳ��totalpage	
	//���������  ������  ÿҳ����  �ı���   ��ôҳ��=(������/ÿҳ����)+1  ��������ô ҳ��ֱ�ӵ���  ������/ÿҳ���ݣ� ��ȡ��ǰҳ �����Ƶ�ǰҳ��ֵ 
	//������ʼ����
	public void schZhuozi(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		List<Zhuozi> ZhuoziList = new ArrayList<>();
		   System.out.println("�����嶩��");
		   String id=request.getParameter("id");
		   ZhuoziList=new ZhuoziExcute().schZhuozi(id);
		// ÿҳ��ʾ����������
			int pageSize = 5;

			// ����ҳ����£��������ݵ�����
			int total = ZhuoziList.size();

			int totalPage = 0;

			totalPage = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
	        //���������  ������  ÿҳ����  �ı���   ��ôҳ��=(������/ÿҳ����)+1  ��������ô ҳ��ֱ�ӵ���  ������/ÿҳ����
			String currentPage = request.getParameter("currentPage");
			System.out.println(currentPage);
			// ��ǰҳ
			int page = Integer.parseInt(currentPage);
			
			if(page > totalPage) {
				page = totalPage;
			}
			
			if(page < 1) {
				page = 1;
			}

			// ���ݲ�ѯ����ʼ������
			int start = 0;

			start = (page - 1) * pageSize;
			List<Zhuozi> list =new ZhuoziExcute().schZhuozi(id,start, pageSize);
			request.setAttribute("zhuoziList", list);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("showZhuozi.jsp").forward(request, response);
		
		
	}
	//�ڶ�����߸���������Ϣ  ���Ҹ������ӱ�
	//���zhuozi Ϊnullû������ �޸ĵ���״̬  �ǵ�getbytes id�޸�a����   �����޸Ķ���id �����޸����ӿ���zhuoziҲ��id
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
	//ǰ̨Ϊ�û�ѡ������
	//��id�����Ѷ��� ���޸�ҳ����ʾ set dindan currentPage Attri
	public void initEditZhuozi(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		String uid= request.getParameter("uid");
		String currentPage=request.getParameter("currentPage");
		Dindan dindan=new DindanExcute().oneUser(uid);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("dindan", dindan);
		request.getRequestDispatcher("editZhuozi.jsp").forward(request, response);
	}
	//�û��ύ��������ô˷������Ӷ���
	//����username �����Ƿ��ж�������price���¼Ӳ�  û��insert
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
		   
		   //�˴�Ҫ��������  ����������ݿ���������username ��ô�û����ǼӲ˲���  ���username û��  ��ô������һ������
		   if(new DindanExcute().jiacaiExcute(username)){
			   int price=new DindanExcute().schPrice(username);
			   int nowprice=price+Integer.valueOf(allprice);
			   new DindanUpdate().priceUpdate(nowprice, username);
			   
			 //��չ��ﳵ����
		   
		   }else {
			   
			 
			   System.out.println("username:"+username+"allprice:"+allprice+"table:"+table+"situation:"+situation);
					  new DindanUpdate().dindaninsert(username, allprice, table, situation);
					  //��չ��ﳵ����
					  //����״̬�Ȳ�д      ǰ̨��ʾ�Ѿ�����ߵĶ���     �������Ӻ���  ��������  ����״̬���� 
		   }		  
				  request.getRequestDispatcher("User/pay.jsp").forward(request, response);
		
	}
	//ǰ̨��ӡ���� �Ȼ�ȡ�ܶ���size�÷�ҳ   ÿҳ��ʾ����������  pagesize ��������total Ҫ�ֵ�ҳ��totalpage
	//���������  ������  ÿҳ����  �ı���   ��ôҳ��=(������/ÿҳ����)+1  ��������ô ҳ��ֱ�ӵ���  ������/ÿҳ���ݣ� ��ȡ��ǰҳ �����Ƶ�ǰҳ��ֵ 
	//������ʼ����
	public void schDindan(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, SQLException{
		   request.setCharacterEncoding("UTF-8");
		   List<Dindan> dindanList = new ArrayList<>();
		   String schUname= request.getParameter("schUname");
		   System.out.println("�����嶩��");
		   dindanList=new DindanExcute().schUser(schUname);
		// ÿҳ��ʾ����������
			int pageSize = 5;

			// ����ҳ����£��������ݵ�����
			int total = dindanList.size();

			int totalPage = 0;

			totalPage = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
	        //���������  ������  ÿҳ����  �ı���   ��ôҳ��=(������/ÿҳ����)+1  ��������ô ҳ��ֱ�ӵ���  ������/ÿҳ����
			String currentPage = request.getParameter("currentPage");
			System.out.println(currentPage);
			// ��ǰҳ
			int page = Integer.parseInt(currentPage);
			
			if(page > totalPage) {
				page = totalPage;
			}
			
			if(page < 1) {
				page = 1;
			}

			// ���ݲ�ѯ����ʼ������
			int start = 0;

			start = (page - 1) * pageSize;
			List<Dindan> list = new DindanExcute().schUser(schUname, start, pageSize);
			request.setAttribute("dindanList", list);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalPage", totalPage);
			request.getRequestDispatcher("dindanshow.jsp").forward(request, response);
	}
		   

}
