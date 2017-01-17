package com.data.dao.singleForm;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.dao.Dao;
import com.data.model.tb_travelsModel;

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
	public List<Object> query(String sql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object object, String Sql) {
		// TODO Auto-generated method stub
		
	}

}
