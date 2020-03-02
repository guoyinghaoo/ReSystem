package com.system.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.person.Car;
import com.person.User;
import com.system.dao.DBhandel;

public class CarExcute {
	public static List<Car> AllExcute(String username) throws ClassNotFoundException{
		List<Car> list = new ArrayList<Car>();//List内存储AdminTable类对象
		com.system.dao.DBhandel dbhandel =DBhandel.getDBUtil();
		Connection conn = dbhandel.getconn();
		String sql = "select * from caicar where 1=1";	
		if(!"".equals(username)&&username!=null){
			sql += " and username = '"+ username + "'";
			
		}		
		ResultSet rs = dbhandel.executeQuery(sql);
		try {
			while(rs.next()){//把从数据库get到的数据传递给set里面的值！！
				Car car=new Car(); 
				car.setUsername(rs.getString(1));
				car.setId(rs.getInt(2));
				car.setCainame(rs.getString(3));
				car.setCaiprice(rs.getInt(4));
				
				list.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbhandel.close();
		}
		
		return list;
	}

}
