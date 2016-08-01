package com.data.dao.singleForm.checkAdvicePage;

import org.springframework.jdbc.core.JdbcTemplate;

public class CheckAdviceDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	@SuppressWarnings("deprecation")
	public int query(String sql) {
		return jdbcTemplate.queryForInt(sql);
	}
}
