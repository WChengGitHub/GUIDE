package com.data.dao.multipleForm.pageOne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.tb_visitorModel;

public class AdviceDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
//这个是返回查询一条或多条的查询语句，需要sql语句，和参数param
	public List<Object> query(String sql, final List<Object> param) {
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				for (int i = 0; i < param.size(); i++) {
					try {
						ps.setObject(i + 1, param.get(i));
						System.out.println(param.get(i));
						// System.out.println("dao"+sql);
					} catch (SQLException e) {
						System.out.println("Pstmt中的Sql语句参数注入异常。。。");
						e.printStackTrace();
					}
				}
				// System.out.println(sql);
			}

		}, new RowMapper<Object>() {

			@Override
			public tb_visitorModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_visitorModel model = new tb_visitorModel();
				model.setVid(rs.getString("Vid"));
				return model;
			}

		});
	}
	
	public void addAdvice(String sql, final List<Object>param)
	{

		 jdbcTemplate.update(sql,new PreparedStatementSetter() {  		                         
			 public void setValues(PreparedStatement ps) throws SQLException { 
				 for (int i = 0; i < param.size(); i++) {
						try {
							ps.setObject(i + 1, param.get(i));
							System.out.println(param.get(i));
							// System.out.println("dao"+sql);
						} catch (SQLException e) {
							System.out.println("Pstmt中的Sql语句参数注入异常。。。");
							e.printStackTrace();
						}
					}
		                         }  
	                      });  


	}
	/*
	 * public static void main(String[]args) { 
	 * Timestamp ts = new Timestamp(System.currentTimeMillis());
	 *  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	 * ts =Timestamp.valueOf(sdf.format(ts));
	 *  System.out.println(ts);
	 * 
	 * }
	 */
}
