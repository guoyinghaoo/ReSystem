package com.system.dbutil;

import java.sql.Connection;

import com.system.dao.DBhandel;

public class DindanUpdate {
	public int dindaninsert(String username,String allprice,String table,String situation) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql = "insert into zongdan(username,allprice,situation) values(?,?,?)";
		Object[] obj = new Object[3];
		obj[0] = username;
		obj[1] = Integer.parseInt(allprice);
		obj[2] = situation;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}
	public static int dindanUpdate(String uid,String zhuozi) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="update zongdan set zhuozi=? where did=?";
		Object[] obj=new Object[2];
		obj[1]=Integer.parseInt(uid);
		obj[0]=zhuozi;
		
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}
	
	public static int situationUpdate(String uid,String situation) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="update zongdan set situation=? where did=?";
		Object[] obj=new Object[2];
		obj[1]=Integer.parseInt(uid);
		obj[0]=situation;		
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}
	public static int priceUpdate(int price,String username)throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="update zongdan set allprice=? where username=?";
		Object[] obj=new Object[2];		
		obj[0]=price;
		obj[1]=username;
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}
	public static int delCaidan(String uid) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql = "delete from zongdan where did=?";
		Object[] obj = new Object[1];
		obj[0] = uid;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}
	
	public static int situationUp(String username,String situation) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql = "update zongdan set situation=? where username=?";
		Object[] obj = new Object[2];
		obj[0] = situation;
		obj[1] = username;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}

}
