package com.data.dao.singleForm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.ChangeAndDelAdminModel;

public class tb_spotDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	public ChangeAndDelAdminModel querySid(String sql) {// 根据Vid
		return  (ChangeAndDelAdminModel) jdbcTemplate.queryForObject(sql, new RowMapper<Object>() {
			@Override
			public ChangeAndDelAdminModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ChangeAndDelAdminModel changeAndDelAdminModel=new ChangeAndDelAdminModel();
				changeAndDelAdminModel.setSid(rs.getString("Sid"));
				return changeAndDelAdminModel;
			}
		});
	}
}
