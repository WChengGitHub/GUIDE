package com.data.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.PositionModel;
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
	public PositionModel mapRow(ResultSet rs, int arg1) throws SQLException {
		PositionModel positionModel=new PositionModel();
		positionModel.setArea(rs.getString("Area"));
		positionModel.setCity(rs.getString("City"));
		positionModel.setProvince(rs.getString("Province"));
		positionModel.setAid(rs.getString("Aid"));
		return positionModel;
	}
	
});
	}

	@Override
	public void update(String Name) {
		// TODO Auto-generated method stub
		
	}

}
