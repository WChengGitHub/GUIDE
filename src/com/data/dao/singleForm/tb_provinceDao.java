package com.data.dao.singleForm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.tb_provinceModel;

public class tb_provinceDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	public Object querytb_Province(String sql) {// 根据ADid
		return jdbcTemplate.queryForObject(sql, new RowMapper<Object>() {
			@Override
			public tb_provinceModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_provinceModel provinceModel=new tb_provinceModel();
				provinceModel.setPid(rs.getString("Pid"));
				provinceModel.setProvince(rs.getString("Province"));
				return provinceModel;
			}
		});
	}
}
