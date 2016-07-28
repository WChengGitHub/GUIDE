package com.data.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.tb_visitorModel;

public interface Dao {
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	public JdbcTemplate getJdbcTemple();

	public void add(Object object,String Sql);//澧�
	
	public void delete(Object object);//鍒�
	
	public List<Object> query(String sql, List<Object> param);//鏌�
    

	
	public void update(Object object);//鏀�

}
