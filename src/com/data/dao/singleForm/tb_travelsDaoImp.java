package com.data.dao.singleForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.dao.Dao;
import com.data.model.tb_travelsModel;
import com.data.model.tb_visitorModel;

public class tb_travelsDaoImp implements Dao{


	private JdbcTemplate jdbcTemplate;
	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	@Override
	public JdbcTemplate getJdbcTemple() {
		return jdbcTemplate;
	}

	@Override
	public void add(Object object,String Sql) {  
		tb_travelsModel tb_travelsmodel=(tb_travelsModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
			String sqlInsert=Sql;
			//test
			System.out.println(tb_travelsmodel.getTid()+" "
			+tb_travelsmodel.getSid()+" "+tb_travelsmodel.getText()+"test travelsdaoimp add");
			
			jt.update(sqlInsert, tb_travelsmodel.getTid(), 
					tb_travelsmodel.getText(), tb_travelsmodel.getVid(),
					tb_travelsmodel.getSid());//,tb_travelsmodel.getPublicty()a
            System.out.println(" add record success ");
		} catch (Exception e) {
			//输出出现的异常 e就是出现的异常
		    System.out.println(e);
		    System.out.println(" add record error ");
		}
	}

	@Override
	public void delete(Object object, String Sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
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
			public tb_travelsModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_travelsModel tb_travelsmodel = new tb_travelsModel();
				tb_travelsmodel.setText(rs.getString("Text"));
				tb_travelsmodel.setTime(rs.getString("Time"));
				tb_travelsmodel.setSid(rs.getString("Sid"));
				return tb_travelsmodel;
			}
		});
	}

	@Override
	public void update(Object object, String Sql) {
		// TODO Auto-generated method stub
		
	}

}
