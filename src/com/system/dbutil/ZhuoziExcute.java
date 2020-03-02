package com.system.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.person.Dindan;
import com.person.Zhuozi;
import com.system.dao.DBhandel;

public class ZhuoziExcute {
	
	 /* 根据条件查询桌子
	 * 
	 * @param schUname
	 * @return
	 */
	public List<Zhuozi> schZhuozi(String zid, int start, int pageSize)throws ClassNotFoundException, SQLException {

		// 连接数据库
		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();

		List<Zhuozi> zhuoziList = new ArrayList<>();

		// SELECT * FROM tb_user WHERE 1=1 AND uname = 'abc'
		String sql = "SELECT * FROM zhuozi where 1=1";
		System.out.println("zidzid"+zid);
        if(zid!=null&&!"".equals(zid)){
        	sql+=" AND isfree=0";
        }
		sql += " LIMIT " + start + "," + pageSize;
		
		// 预编译SQL语句
		ResultSet rs = dbUtil.executeQuery(sql);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				Zhuozi zhuozi=new Zhuozi();
				zhuozi.setZid(rs.getInt(1));
				if("".equals(rs.getString(2)) && rs.getString(2) != null){
					
					zhuozi.setUsername("0");
				}
				else {
					zhuozi.setUsername(rs.getString(2));
				}
				zhuozi.setIsfree(rs.getString(3));
					
				
				zhuoziList.add(zhuozi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtil.close();
		} 
		return zhuoziList;
	}
	
	/**
	 * 查找所有用户
	 * 
	 * @param schUname
	 * @return
	 */
	public List<Zhuozi> schZhuozi(String zid)throws ClassNotFoundException, SQLException {

		DBhandel dbUtil = DBhandel.getDBUtil();
 		Connection conn = dbUtil.getconn();

		List<Zhuozi> zhuoziList = new ArrayList<>();

		// SELECT * FROM tb_user WHERE 1=1 AND uname = 'abc'
		String sql = "SELECT * FROM zhuozi where 1=1";
		System.out.println("zidzid"+zid);
		if(zid!=null&&!"".equals(zid)){
        	sql+=" AND isfree=0";
        }
		
		// 预编译SQL语句
		System.out.println(sql);
		ResultSet rs = dbUtil.executeQuery(sql);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				Zhuozi zhuozi=new Zhuozi();
				zhuozi.setZid(rs.getInt(1));
				if("".equals(rs.getString(2)) && rs.getString(2) != null){
					
					zhuozi.setUsername("");
				}
				else {
					zhuozi.setUsername(rs.getString(2));
				}
				zhuozi.setIsfree(rs.getString(3));
					
				
				zhuoziList.add(zhuozi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbUtil.close();
		} 
		return zhuoziList;
	}
	
	
	
	

}
