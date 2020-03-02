package com.system.dbutil;

import java.sql.Connection;

import com.system.dao.DBhandel;

public class CarUpdate {
	public int caiinsert(String username ,String cainame,String caiprice) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		System.out.println("Carupdate"+cainame);
		String sql = "insert into caicar(username,cainame,caiprice) values(?,?,?)";
		Object[] obj = new Object[3];
		obj[0] = username;
		obj[1] = cainame;
		obj[2]= caiprice;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}
	public static int delCai(int uid) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql = "delete from caicar where id=?";
		Object[] obj = new Object[1];
		obj[0] = uid;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}
	
	public static int tijiaodelCai( String username) throws ClassNotFoundException {//提交订单后根据用户名清空购物车
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql = "delete from caicar where username=?";
		Object[] obj = new Object[1];
		obj[0] = username;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}

}
