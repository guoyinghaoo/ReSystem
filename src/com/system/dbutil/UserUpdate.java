package com.system.dbutil;

import java.sql.Connection;

import com.system.*;
import com.system.dao.DBhandel;

public class UserUpdate {
	public int userinsert(String username,String password) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql = "insert into user(username,password) values(?,?)";
		Object[] obj = new Object[2];
		obj[0] = username;
		obj[1] = password;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}
	
	public static int userUpdate(String uid ,String username,String password) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="update user set username=?,password=? where uid=?";
		Object[] obj=new Object[3];
		obj[0]=username;
		obj[1]=password;
		obj[2]=Integer.parseInt(uid);
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}
	public int userDel(String uid)throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil(); 
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql ="delete from user where uid=?";
		Object[] obj=new Object[1];
		obj[0]=Integer.parseInt(uid);
		result = dbUtil.executeUpdate(sql, obj);
		return result;
	}
}
