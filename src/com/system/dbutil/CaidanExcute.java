package com.system.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.person.Caidan;
import com.person.Car;
import com.system.dao.DBhandel;

public class CaidanExcute {
	public  List<Caidan> AllExcute() throws ClassNotFoundException{
		List<Caidan> list = new ArrayList<Caidan>();//List内存储AdminTable类对象
		com.system.dao.DBhandel dbhandel =DBhandel.getDBUtil();
		Connection conn = dbhandel.getconn();
		String sql = "select * from caidan where 1=1";	
		/*if(!"".equals(username)&&username!=null){
			sql += " and username = '"+ username + "'";
			
		}	*/	
		ResultSet rs = dbhandel.executeQuery(sql);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				Caidan caidan = new  Caidan(); 
				caidan.setCaiid(rs.getInt(1));
				caidan.setCainame(rs.getString(2));
				caidan.setCaiprice(rs.getInt(3));
				caidan.setImg(rs.getString(4));
				list.add(caidan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbhandel.close();
		}
		
		return list;
	}
	
	
	
	public static List<Caidan> AllExcute(int start, int pageSize) throws ClassNotFoundException{
		List<Caidan> list = new ArrayList<Caidan>();//List内存储AdminTable类对象
		com.system.dao.DBhandel dbhandel =DBhandel.getDBUtil();
		Connection conn = dbhandel.getconn();
		String sql = "select * from caidan ";	
		/*if(!"".equals(username)&&username!=null){
			sql += " and username = '"+ username + "'";
			
		}	*/	
		sql += " LIMIT " + start + "," + pageSize;
		ResultSet rs = dbhandel.executeQuery(sql);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				Caidan caidan = new  Caidan(); 
				caidan.setCaiid(rs.getInt(1));
				caidan.setCainame(rs.getString(2));
				caidan.setCaiprice(rs.getInt(3));
				caidan.setImg(rs.getString(4));
				list.add(caidan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbhandel.close();
		}
		
		return list;
	}

    public static Caidan showfood(int caiid) throws ClassNotFoundException{
    	Caidan caidan=new Caidan();
    	com.system.dao.DBhandel dbhandel =DBhandel.getDBUtil();
		Connection conn = dbhandel.getconn();
		String sql = "select * from caidan where caiid=?";	
		String[] obj = new String[1];
 		obj[0] = String.valueOf(caiid); 
		ResultSet rs = dbhandel.executeQuery(sql,obj);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				caidan.setCaiid(rs.getInt(1));
				caidan.setCainame(rs.getString(2));
				caidan.setCaiprice(rs.getInt(3));
				caidan.setImg(rs.getString(4));
				caidan.setMiaoshu(rs.getString(5));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbhandel.close();
		}
    	return caidan;
    }

}
