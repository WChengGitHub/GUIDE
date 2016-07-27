package com.data.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.User;

public interface Dao {
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	public JdbcTemplate getJdbcTemple();

	public void add(User user);//澧�
	
	public void delete(String Name);//鍒�
	
	public List<Object> query(String sql, List<Object> param);//鏌�
    

	
	public void update(String Name);//鏀�

}
