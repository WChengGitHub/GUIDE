package com.data.dao.singleForm;

//import java.sql.SQLClientInfoException;
//import java.sql.SQLException;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.JDBC42CallableStatement;
//import com.mysql.jdbc.PreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.dao.Dao;
import com.data.model.tb_visitorModel;

public class tb_visitorDaoImp implements Dao{


	//JdbcTemplate注入
	private JdbcTemplate jdbcTemplate;
	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	@Override
	public JdbcTemplate getJdbcTemple() {
		return jdbcTemplate;
	}

	
	
	//已用(用于注册)//增
	public void add(Object object,String Sql) {  
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
			String sqlInsert=Sql;
			//test
			System.out.println(tb_visitormodel.getVisitor()+" "
			+tb_visitormodel.getPassword()+" "+tb_visitormodel.getVid()+"test visitordaoimp add");
			
			jt.update(sqlInsert, tb_visitormodel.getVisitor(), 
					tb_visitormodel.getPassword(), tb_visitormodel.getVid(),tb_visitormodel.getEmail());
            System.out.println("add success");
		} catch (Exception e) {
			//输出出现的异常 e就是出现的异常
		    System.out.println(e);
		    System.out.println("error add visitor");
		}
	}

	
	
	@Override
	//删
	public void delete(Object object,String Sql) {  
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		
		jt.update(Sql,tb_visitormodel.getVid()); 
		} catch (Exception e){
			//输出出现的异常 e就是出现的异常
		    System.out.println(e);
			System.out.println("error delete visitor");
		}
	}

	
	//用于登录注册
	@Override
	public List<Object> query(String sql, final List<Object> param) {
			return jdbcTemplate.query(sql,new PreparedStatementSetter()
			{
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					for (int i = 0; i < param.size(); i++) {
						try {
								ps.setObject(i + 1, param.get(i));
							} catch (SQLException e){
								//输出出现的异常 e就是出现的异常
							    System.out.println(e);
								System.out.println("Pstmt中Sql语句参数注入异常");
								e.printStackTrace();
							}
					}
				}
			},new RowMapper<Object>(){
			  @Override
			  public tb_visitorModel mapRow(ResultSet rs, int arg1) throws SQLException {
					tb_visitorModel tb_visitormodel=new tb_visitorModel();
					try {
						
						tb_visitormodel.setVisitor(rs.getString("Visitor"));
					//	System.out.println(tb_visitormodel.getVisitor());
					} catch (Exception e){ System.out.println("error setVisitor");}
					
			        try { 
			        	tb_visitormodel.setVid(rs.getString("Vid"));
					} catch (Exception e){ System.out.println("error setVid");}
			        
			        try { 
			        	tb_visitormodel.setPassword(rs.getString("Password"));
					} catch (Exception e){ System.out.println("error setPassword");}
			        
			        try { 
			        	tb_visitormodel.setTelephone(rs.getString("Telephone"));
					} catch (Exception e){ System.out.println("error setTelephone");}
			        
			        try { 
			        	tb_visitormodel.setEmail(rs.getString("Email"));
					} catch (Exception e){ System.out.println("error setEmail");}
			        
			        try { 
			        	tb_visitormodel.setGender(rs.getString("Gender"));
					} catch (Exception e){ System.out.println("error setGender");}
			        try { 
			        	tb_visitormodel.setLockstate(rs.getString("Lockstate"));
					} catch (Exception e){ System.out.println("error setLockstate");}

			        
			        return tb_visitormodel;
		}
	}); 
			
}

		
		
	//改	
	@Override
	public void update(Object object,String Sql) { 
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
			jt.update(Sql,tb_visitormodel.getVid());
		} catch (Exception e){
			//输出出现的异常 e就是出现的异常
		    System.out.println(e);
			System.out.println("error update visitor");
		}
	}

	
}
	
	
