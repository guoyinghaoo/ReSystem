package com.system.dbutil;

import java.sql.Connection;

import com.system.dao.DBhandel;

public class CaidanUpdate {
	public int addCai(String cainame,int caiprice,String img,String miaoshu) throws ClassNotFoundException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		int result=-1;
		String sql = "insert into caidan(cainame,caiprice,img,miaoshu) values(?,?,?,?)";
		Object[] obj = new Object[4];
		obj[0] = cainame;
		obj[1] = caiprice;
		obj[2]= img;
		obj[3]= miaoshu;
		result = dbUtil.executeUpdate(sql, obj);		
		return result;		
	}
	
	
}
