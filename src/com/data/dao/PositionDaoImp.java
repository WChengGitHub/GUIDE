package com.data.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.PositionModel1;
import com.data.model.User;

import java.sql.*;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

public class PositionDaoImp implements Dao{
	private JdbcTemplate jdbcTemplate;
	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
		// TODO Auto-generated method stub
		
	}

	@Override
	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
		
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String Name) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public List<Object> query(String sql, final List<Object> param) {
		return jdbcTemplate.query(sql,new PreparedStatementSetter()
		{
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		// TODO Auto-generated method stub
		for (int i = 0; i < param.size(); i++) {
			try {
				ps.setObject(i + 1, param.get(i));
			    System.out.println(param.get(i));
				//System.out.println("dao"+sql);
			} catch (SQLException e){
				System.out.println("Pstmt中的Sql语句参数注入异常。。。");
				e.printStackTrace();
			}
		}
		//System.out.println(sql);
	}
	
},new RowMapper<Object>(){

	@Override
	public PositionModel mapRow(ResultSet rs, int arg1) throws SQLException {
		PositionModel positionModel=new PositionModel();
		positionModel.setArea(rs.getString("Area"));
		positionModel.setCity(rs.getString("City"));
		positionModel.setProvince(rs.getString("Province"));
		positionModel.setAid(rs.getString("Aid"));
		return positionModel;
	}
	
});
	}*/
	@Override
	public List<Object> query(String sql, final List<Object> param) {
		return jdbcTemplate.query(sql,new PreparedStatementSetter()
		{
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		// TODO Auto-generated method stub
		for (int i = 0; i < param.size(); i++) {
			try {
				ps.setObject(i + 1, param.get(i));
			    System.out.println(param.get(i));
				//System.out.println("dao"+sql);
			} catch (SQLException e){
				System.out.println("Pstmt中的Sql语句参数注入异常。。。");
				e.printStackTrace();
			}
		}
		//System.out.println(sql);
	}
	
},new RowMapper<Object>(){

	@Override
	public PositionModel1 mapRow(ResultSet rs, int arg1) throws SQLException {
		PositionModel1 positionModel1=new PositionModel1();
		try {
			positionModel1.setAid(rs.getString("Aid"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        try { 
        	positionModel1.setCid(rs.getString("Cid"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        try {
        	positionModel1.setPid(rs.getString("Pid"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        try { 
        	positionModel1.setLatitude(rs.getString("Latitude"));
		} catch (Exception e) {
			// TODO: handle exception
		}
    try {
    	positionModel1.setLongitude(rs.getString("Longitude"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        try { 
        	positionModel1.setRadius(rs.getInt("Radius"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        try {
        	positionModel1.setSpot(rs.getString("Spot"));
		} catch (Exception e) {
			// TODO: handle exception
		}
        try { 
        	positionModel1.setArea(rs.getString("Area"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return positionModel1;
	}
	
});
	}
   
	@Override
	public void update(String Name) {
		// TODO Auto-generated method stub
		
	}

	

}
