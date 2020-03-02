package com.system.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.person.Dindan;
import com.person.User;
import com.system.dao.DBhandel;

public class DindanExcute {
	public static boolean jiacaiExcute(String username) throws ClassNotFoundException, SQLException {
 		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();
 		String sql = "select username from zongdan where username = ? ";
 		String[] obj = new String[1];
 		obj[0] = username; 
 		ResultSet rs = dbUtil.executeQuery(sql,obj);
 		while(rs.next()) {
 			return true;
 		}
 		
 		return false;
 		
 	}
	
	
	
	/**
	 * 根据条件查询用户
	 * 
	 * @param schUname
	 * @return
	 */
	public List<Dindan> schUser(String schUname, int start, int pageSize)throws ClassNotFoundException, SQLException {

		// 连接数据库
		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();

		List<Dindan> dindanList = new ArrayList<>();

		// SELECT * FROM tb_user WHERE 1=1 AND uname = 'abc'
		String sql = "SELECT * FROM zongdan WHERE 1=1";

		if (!"".equals(schUname) && schUname != null) {
			sql += " AND username = '" + schUname + "'";
		}

		sql += " LIMIT " + start + "," + pageSize;
		
		// 预编译SQL语句
		ResultSet rs = dbUtil.executeQuery(sql);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				Dindan dindan=new Dindan();
				dindan.setDid(rs.getInt(1));
				dindan.setUsername(rs.getString(2));
				dindan.setAllprice(rs.getInt(3));
				dindan.setZhuozi(rs.getString(4));
				dindan.setSituation(rs.getString(5));
				dindanList .add(dindan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtil.close();
		} 
		return dindanList;
	}
	
	/**
	 * 查找所有用户
	 * 
	 * @param schUname
	 * @return
	 */
	public List<Dindan> schUser(String schUname)throws ClassNotFoundException, SQLException {

		// 连接数据库
		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();

		List<Dindan> dindanList = new ArrayList<>();

		// SELECT * FROM tb_user WHERE 1=1 AND uname = 'abc'
		String sql = "SELECT * FROM zongdan WHERE 1=1";

		if (!"".equals(schUname) && schUname != null) {
			sql += " AND username = '" + schUname + "'";
		}
		
		// 预编译SQL语句
		ResultSet rs = dbUtil.executeQuery(sql);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				Dindan dindan=new Dindan();
				dindan.setDid(rs.getInt(1));
				dindan.setUsername(rs.getString(2));
				dindan.setAllprice(rs.getInt(3));
				dindan.setZhuozi(rs.getString(4));
				dindan.setSituation(rs.getString(5));
				dindanList .add(dindan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtil.close();
		} 
		return dindanList;
	}
	
	public Dindan oneUser(String uid)throws ClassNotFoundException, SQLException {
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		Dindan dindan=new Dindan();
		String sql = "SELECT * FROM zongdan WHERE did=?";
		String[] obj = new String[1];
		obj[0] = uid; 
		ResultSet rs = dbUtil.executeQuery(sql,obj);
		while(rs.next()) {
			dindan.setDid(rs.getInt(1));
			dindan.setUsername(rs.getString(2));	
			dindan.setZhuozi(rs.getString(4));
		}	
		return dindan;
		
	}
	
	public int schPrice(String username)throws ClassNotFoundException, SQLException{
		DBhandel dbUtil = DBhandel.getDBUtil();
		Connection conn = dbUtil.getconn();
		String sql = "SELECT allprice FROM zongdan WHERE username=?";
		int price=0;
		String[] obj =new String[1];
		obj[0]=username;
		ResultSet rs=dbUtil.executeQuery(sql,obj);
		while(rs.next()){
			price=rs.getInt(1);
		}
		
		return price;
	}
	

}
