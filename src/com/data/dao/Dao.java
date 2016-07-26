package com.data.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.Visitor;

public interface Dao {
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	public JdbcTemplate getJdbcTemple();

	public void add(Visitor visitor);//澧�
	
	public void delete(String Visitor);//鍒�
	
	public List<Object> query(String sql, List<Object> param);//鏌�
	
	public void update(String Visitor);//鏀�

}
