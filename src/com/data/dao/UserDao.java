package com.data.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.User;

public interface UserDao {
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	public JdbcTemplate getJdbcTemple();
	
//	public int query_name(String Name,String Password);
//	public String query_password(String Name,String Password);

	public void add(User U);//增
	
	public void delete(String Name);//删
	
	public List<Object> query();//查
	
	public void update(String Name);//改

}
