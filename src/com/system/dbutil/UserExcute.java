package com.system.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.person.Car;
import com.person.User;
import com.system.dao.*;



public class UserExcute {
	public static boolean loginExcute(String username ,String password) throws ClassNotFoundException, SQLException {
 		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();
 		String sql = "select username from user where username = ? and password=?";
 		String[] obj = new String[2];
 		obj[0] = username; 
 		obj[1]= password;
 		ResultSet rs = dbUtil.executeQuery(sql,obj);
 		while(rs.next()) {
 			return true;
 		}
 		
 		return false;
 		
 	}
	
	public static List adminExcute( ) throws ClassNotFoundException, SQLException {
 		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();
 		List adminlist =new ArrayList();
 		String sql = "select username from user where flag = 0 ";
 		
 	
 		ResultSet rs = dbUtil.executeQuery(sql);
 		while(rs.next()) {
 			adminlist.add(rs.getString(1));
 		}
 		
 		return adminlist;
 		
 	}
	
	public static boolean loginSerach(String username) throws ClassNotFoundException, SQLException {
 		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();
 		String sql = "select username from user where username = ?";
 		String[] obj = new String[1];
 		obj[0] = username; 
 		ResultSet rs = dbUtil.executeQuery(sql,obj);
 		while(rs.next()) {
 			return true;
 		}
 		
 		return false;
 		

}   
	public User oneUser(String uid)throws ClassNotFoundException, SQLException {
	DBhandel dbUtil = DBhandel.getDBUtil();
	Connection conn = dbUtil.getconn();
	User user = new User();
	String sql = "SELECT * FROM user WHERE uid=?";
	String[] obj = new String[1];
	obj[0] = uid; 
	ResultSet rs = dbUtil.executeQuery(sql,obj);
	while(rs.next()) {
		user.setUid(rs.getInt(2));
		user.setUsername(rs.getString(3));
		user.setPassword(rs.getString(4));		
	}	
	return user;
	
}
	
	/**
	 * ����������ѯ�û�
	 * 
	 * @param schUname
	 * @return
	 */
	public List<User> schUser(String schUname, int start, int pageSize)throws ClassNotFoundException, SQLException {

		// �������ݿ�
		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();

		List<User> userList = new ArrayList<>();

		// SELECT * FROM tb_user WHERE 1=1 AND uname = 'abc'
		String sql = "SELECT * FROM user WHERE 1=1";

		if (!"".equals(schUname) && schUname != null) {
			sql += " AND username = '" + schUname + "'";
		}

		sql += " LIMIT " + start + "," + pageSize;
		
		// Ԥ����SQL���
		ResultSet rs = dbUtil.executeQuery(sql);
		try {
			while(rs.next()){//�Ѵ����ݿ�get�������ݴ��ݸ�set�����ֵ����
				User user =new User();
				user.setUid(rs.getInt(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));
				
				userList .add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtil.close();
		} 
		return userList;
	}
	
	/**
	 * ���������û�
	 * 
	 * @param schUname
	 * @return
	 */
	public List<User> schUser(String schUname)throws ClassNotFoundException, SQLException {

		// �������ݿ�
		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();

		List<User> userList = new ArrayList<>();

		// SELECT * FROM tb_user WHERE 1=1 AND uname = 'abc'
		String sql = "SELECT * FROM user WHERE 1=1";

		if (!"".equals(schUname) && schUname != null) {
			sql += " AND username = '" + schUname + "'";
		}
		
		// Ԥ����SQL���
		ResultSet rs = dbUtil.executeQuery(sql);
		try {
			while(rs.next()){//�Ѵ����ݿ�get�������ݴ��ݸ�set�����ֵ����
				User user =new User();
				user.setUid(rs.getInt(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));
				
				userList .add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtil.close();
		} 
		return userList;
	}
	
	
	
}
