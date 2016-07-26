package com.data.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.Visitor;

public interface VisitorDao {
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	public JdbcTemplate getJdbcTemple();

	public void add(Visitor visitor);//增
	
	public void delete(String Visitor);//删
	
	public List<Object> query();//查
	
	public void update(String Visitor);//改

}
