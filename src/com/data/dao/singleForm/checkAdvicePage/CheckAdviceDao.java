package com.data.dao.singleForm.checkAdvicePage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.tb_adviceModel;

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

	public Object query1(String sql) {
		return jdbcTemplate.queryForObject(sql, new RowMapper<Object>() {
			@Override
			public tb_adviceModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_adviceModel adviceModel = new tb_adviceModel();
                adviceModel.setVid(rs.getString("Vid"));
				adviceModel.setTitle(rs.getString("Title"));
				adviceModel.setAdvice(rs.getString("Advice"));
				return adviceModel;
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
						System.out.println("Pstmt中Sql语句参数注入异常");
						e.printStackTrace();
					}
				}
			}
		}, new RowMapper<Object>() {
			@Override
			public tb_adviceModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_adviceModel adviceModel = new tb_adviceModel();
				adviceModel.setADid(rs.getString("ADid"));
				adviceModel.setTitle(rs.getString("Title"));
				adviceModel.setType(rs.getString("type"));
				adviceModel.setAtime(sdf.format(rs.getTimestamp("Atime")));
				return adviceModel;
			}
		});

	}

}
