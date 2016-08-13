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
import com.data.model.ChangeAndDelAdminModel;
import com.data.model.tb_visitorModel;

public class tb_adminDaoImp implements Dao{


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

	
	
	//增
	public void add(Object object,String Sql) {  
		ChangeAndDelAdminModel tb_adminmodel=(ChangeAndDelAdminModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
			//test
			String sqlInsert=Sql;System.out.println("aaaaaaaaaaaaaa");
			System.out.println(tb_adminmodel.getAccount()+" "
			+tb_adminmodel.getPassword()+" "+tb_adminmodel.getAccount());
			
			jt.update(sqlInsert, tb_adminmodel.getAccount(), 
					tb_adminmodel.getPassword(), tb_adminmodel.getAid(),tb_adminmodel.getSid());
			
	        System.out.println("test3");
            System.out.println("add success");
		} catch (Exception e) {
		  System.out.println("error4");
		}
	}

	
	
	@Override
	//删
	public void delete(Object object,String Sql) {  
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		//要用时应改为外部传参
		String del="delete from test where Visitor=?";
		jt.update(del,new Object[] {tb_visitormodel.getVisitor()}); 
		} catch (Exception e){
			System.out.println("error");
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
							    System.out.println(param.get(i)+"1111111111");
							} catch (SQLException e){
								System.out.println("Pstmt中Sql语句参数注入异常");
								e.printStackTrace();
							}
					}
				}
			},new RowMapper<Object>(){
			  @Override
			  public ChangeAndDelAdminModel mapRow(ResultSet rs, int arg1) throws SQLException {
					ChangeAndDelAdminModel tb_adminmodel=new ChangeAndDelAdminModel();
					try {
						
						tb_adminmodel.setAccount(rs.getString("Account"));
						System.out.println(tb_adminmodel.getAccount());
					} catch (Exception e){ System.out.println("error setAccount");}
					
					 try { 
				        	tb_adminmodel.setPrivilege(rs.getString("Privilege"));
				        	System.out.println(tb_adminmodel.getPrivilege());
						} catch (Exception e){ System.out.println("error setPrivilege");}
			        
			        try { 
			        	tb_adminmodel.setPassword(rs.getString("Password"));
			        	System.out.println(tb_adminmodel.getPassword());
					} catch (Exception e){ System.out.println("error setPassword");}
//			        
//			        try { 
//			        	tb_visitormodel.setTelephone(rs.getString("Telephone"));
//					} catch (Exception e){ System.out.println("error setTelephone");}
//			        
//			        try { 
//			        	tb_visitormodel.setEmail(rs.getString("Email"));
//					} catch (Exception e){ System.out.println("error setEmail");}
//			        
//			        try { 
//			        	tb_visitormodel.setGender(rs.getString("Gender"));
//					} catch (Exception e){ System.out.println("error setGender");}

			        return tb_adminmodel;
		}
	}); 
			
}

		
		
	//改	
	@Override
	public void update(Object object,String Sql) { 
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		//需要用时得改为外部传参	
		String update=" update tb_visitor "+
				" Password=?"+" where Visitor=? ";
		jt.update(update,new Object[]{"1","2",tb_visitormodel.getPassword(),tb_visitormodel.getVisitor()});
		
		} catch (Exception e){
			System.out.println("error");
		}
	}

	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	

	
