package com.data.dao.singleForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.CheckTravelsModel;

public class tb_travelsDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	public List<Object> query(String sql, final List<Object> param) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				for (int i = 0; i < param.size(); i++) {
					try {
						ps.setObject(i + 1, param.get(i));
					} catch (SQLException e) {
						// 输出出现的异常 e就是出现的异常
						System.out.println(e);
						System.out.println("Pstmt中Sql语句参数注入异常");
						e.printStackTrace();
					}
				}
			}
		}, new RowMapper<Object>() {
			@Override
			public CheckTravelsModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				CheckTravelsModel CTModel = new CheckTravelsModel();
				CTModel.setText(rs.getString("Text"));
				CTModel.setTime(rs.getString("Time"));
				CTModel.setSpot(rs.getString("Spot"));
				return CTModel;
			}
		});
	}
}
