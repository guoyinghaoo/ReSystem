package com.system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBhandel {
	 private static DBhandel db;	
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/restrant?useUnicode=true&amp;characterEncoding=UTF-8";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "";
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	 public Connection getconn() throws ClassNotFoundException {
		 try {
			if(conn==null||conn.isClosed()){
				 Class.forName(JDBC_DRIVER);
				 conn = DriverManager.getConnection(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD);
				 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return conn;
		 
	 }
	 public int executeUpdate(String sql) {
	    	int result=-1;
	    	if(conn==null) {
	    		return result;
	    	}
	    	try {
				ps=conn.prepareStatement(sql);
				result=ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
	    	
	    }
	    
	    public int executeUpdate(String sql,Object[] objects) {
	    	int result=-1;
	    	if(conn==null) {
	    		return result;
	    	}
	    	try {
				ps=conn.prepareStatement(sql);
				
				for(int i=0;i<objects.length;i++) {
					ps.setObject(i+1,objects[i]);
				}
				result=ps.executeUpdate();
				close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;    	
	    }
	    public ResultSet executeQuery(String sql) throws ClassNotFoundException {
			if (getconn() == null) {
				return null;
			}
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
	    //锟叫讹拷锟角凤拷为锟斤拷 ps预锟斤拷锟斤拷coon sql锟斤拷锟�
	           	    	    
	    public ResultSet executeQuery(String sql, Object[] obj) throws ClassNotFoundException {
			if (getconn() == null) {
				return null;
			}
			try {
				ps = conn.prepareStatement(sql);
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);//锟斤拷锟斤拷一锟斤拷牡锟絠锟斤拷?锟斤拷值锟斤拷锟矫筹拷obj[i]
				}
				rs = ps.executeQuery();//锟劫帮拷锟斤拷锟斤拷锟斤拷锟斤拷值锟脚斤拷rs锟斤拷锟斤拷锟斤拷
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return rs;
		}
	    public void close() {
	    	try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    //锟斤拷锟矫碉拷锟斤拷模式锟斤拷锟斤拷止锟斤拷锟捷匡拷锟截革拷锟斤拷锟斤拷
	    public static DBhandel getDBUtil(){
			if(db == null){
				db = new DBhandel();
			}
			return db;
		}
	
	
	
	

}
