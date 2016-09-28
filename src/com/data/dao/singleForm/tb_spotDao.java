package com.data.dao.singleForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.ChangeAndDelAdminModel;
import com.data.model.tb_provinceModel;
import com.data.model.tb_spotModel;

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

	public ChangeAndDelAdminModel queryArid(String sql) {// 根据Vid
		return (ChangeAndDelAdminModel) jdbcTemplate.queryForObject(sql,
				new RowMapper<Object>() {
					@Override
					public ChangeAndDelAdminModel mapRow(ResultSet rs, int arg1)
							throws SQLException {
						ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
						changeAndDelAdminModel.setArid(rs.getString("Arid"));
						return changeAndDelAdminModel;
					}
				});
	}

	public List<Object> query(String sql, final List<Object> param) {
		final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				for (int i = 0; i < param.size(); i++) {
					try {
						ps.setObject(i + 1, param.get(i));
						System.out.println(param.get(i) + "1111111111");
					} catch (SQLException e) {
						System.out
								.println("checkAdviceDao:Pstmt中的Sql语句参数注入异常。。。");
						e.printStackTrace();
					}
				}
			}
		}, new RowMapper<Object>() {
			@Override
			public tb_spotModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_spotModel spotModel=new tb_spotModel();
				spotModel.setSpot(rs.getString("Spot"));
				return spotModel;
			}
		});

	}

}
