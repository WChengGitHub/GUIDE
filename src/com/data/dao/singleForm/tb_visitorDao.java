package com.data.dao.singleForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.tb_visitorModel;

public class tb_visitorDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}

	// 用来判断用户是否存在,如果数目为0,则代表不存在,否则用户名已经存在
	@SuppressWarnings("deprecation")
	public int queryVisitorNumber(String sql) {
		return jdbcTemplate.queryForInt(sql);

	}

	public Object queryEmail(String sql) {// 根据Vid
		return jdbcTemplate.queryForObject(sql, new RowMapper<Object>() {
			@Override
			public tb_visitorModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_visitorModel tbVisitorModel = new tb_visitorModel();
				tbVisitorModel.setEmail(rs.getString("Email"));
				return tbVisitorModel;
			}
		});
	}

	// 查询用户信息
	public List<Object> queryVisitorInformation(String sql,
			final List<Object> param) {
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
			public tb_visitorModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_visitorModel tb_visitormodel = new tb_visitorModel();
				tb_visitormodel.setGender(rs.getString("Gender"));
				return tb_visitormodel;
			}
		});

	}
    //查询用户Vid
	public List<Object> queryVid(String sql,
			final List<Object> param) {
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
			public tb_visitorModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_visitorModel tb_visitormodel = new tb_visitorModel();
				tb_visitormodel.setVid(rs.getString("Vid"));
				return tb_visitormodel;
			}
		});

	}

	public void update(String sql, final List<Object> param) {

		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				for (int i = 0; i < param.size(); i++) {
					try {
						ps.setObject(i + 1, param.get(i));
						System.out.println(param.get(i));
						// System.out.println("dao"+sql);
					} catch (SQLException e) {
						System.out
								.println("checkAdviceDao:Pstmt中的Sql语句参数注入异常。。。");
						e.printStackTrace();
					}
				}
			}
		});

	}
}
