package com.system.dbutil;

import java.sql.Connection;

import com.system.dao.DBhandel;

public class ZhuoziUpdate {
	public static int zhuoziChange(String uid,String username) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="update zhuozi set username=? ,isfree=1 where zid=?";
		Object[] obj=new Object[2];
		obj[1]=Integer.parseInt(uid);
		obj[0]=username;
		
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}
	
	public int freezhuozi(String username) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="update zhuozi set username=null ,isfree=0 where username=?";
		Object[] obj=new Object[1];		
		
		obj[0]=username;
		
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}
	
	public static int zhuoziAdd() throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="insert into zhuozi(username) values(?)";
		Object[] obj=new Object[1];
		obj[0]=null;				
		result = dbUtil.executeUpdate(sql,obj);
		return result;
	}
	public int delZhuozi(String uid)throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="delete from zhuozi where zid=?";
		Object[] obj=new Object[1];
		obj[0]=Integer.parseInt(uid);
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}

}
