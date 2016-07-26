package com.data.dao;

//import java.sql.SQLClientInfoException;
//import java.sql.SQLException;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.JDBC42CallableStatement;
//import com.mysql.jdbc.PreparedStatement;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.data.model.Visitor;

public class VisitorDaoImp implements Dao{


	
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
	public void add(Visitor visitor) {
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		String add=" insert into tb_visitor (name, password) VALUES(?,?) ";
		jt.update(add,new Object[] {visitor.getVisitor(), visitor.getPassword()});
		
		} catch (Exception e) {
		  System.out.println("error");
		}
	}

	
	
	@Override
	public void delete(String Visitor) {
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		String del="delete from test where name=?";
		jt.update(del,new Object[] {Visitor}); 
		} catch (Exception e){
			System.out.println("error");
		}
	}

	
	
	@Override
	public List<Object> query(String sql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void update(String Visitor) {
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		String update=" update tb_visitor "+
				" Password=?"+" where Name=? ";
		jt.update(update,new Object[]{"1","2",Visitor});
		
		} catch (Exception e){
			System.out.println("error");
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	//注入JDBC
//	private JdbcTemplate jdbcTemplate;
//	
//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate=jdbcTemplate;
//		}
//
//	public JdbcTemplate getJdbcTemple() {
//		return jdbcTemplate;
//		}
//	
////	@Override
//	public int query_name(String Name,String Password){
//	
//		@SuppressWarnings("resource")
//		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
//		ImpDao impdao=(ImpDao)factory.getBean("impdao");
//		JdbcTemplate jt=impdao.getJdbcTemple();
//		try {
//			String 	sql="select count(*) from tb_visitor "+" where Vid= "+Name;
//			int count=jt.queryForInt(sql);
//			return count;
//		} catch (Exception e) {
//		  return 0;
//		}
//		
//	}
//	
//	public String query_password(String Name,String Password){
//		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
//		ImpDao impdao=(ImpDao)factory.getBean("impdao");
//		JdbcTemplate jt=impdao.getJdbcTemple();
//		try {
//			String 	sql2="select Telephone from tb_visitor "+" where Vid= "+Name;
//			String Telephone=jt.queryForObject(sql2,String.class);
//			return Telephone;
//		} catch (Exception e) {
//		  return null;
//		}
//	}
