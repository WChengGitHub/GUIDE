package com.data.dao.singleForm;

//import java.sql.SQLClientInfoException;
//import java.sql.SQLException;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.JDBC42CallableStatement;
//import com.mysql.jdbc.PreparedStatement;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

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

	
	
	//已用(用于注册)
	public void add(Object object,String Sql) {  //增
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
			String sqlInsert=Sql;System.out.println("aaaaaaaaaaaaaa");//test
			System.out.println(tb_visitormodel.getVisitor()+tb_visitormodel.getPassword()+tb_visitormodel.getVid());
			jt.update(sqlInsert, tb_visitormodel.getVisitor(), tb_visitormodel.getPassword(), 12345);
			
	        System.out.println("test3");
            System.out.println("add success");
		} catch (Exception e) {
		  System.out.println("error4");
		}
	}

	
	
	@Override
	public void delete(Object object) {  //删
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		String del="delete from test where Visitor=?";
		jt.update(del,new Object[] {tb_visitormodel.getVisitor()}); 
		} catch (Exception e){
			System.out.println("error");
		}
	}

	
	
	@Override
	public List<Object> query(String sql, List<Object> param) {
		//String sqlSelect = "SELECT * FROM contact";//外部传进的参数
      /*  List<Contact> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {
 
            public Contact mapRow(ResultSet result, int rowNum) throws SQLException {
                Contact contact = new Contact();
                contact.setName(result.getString("name"));
                contact.setEmail(result.getString("email"));
                contact.setAddress(result.getString("address"));
                contact.setPhone(result.getString("telephone"));
                 
                return contact;
            }*/
		return null;
	}

	
	
	@Override
	public void update(Object object) { //改
		tb_visitorModel tb_visitormodel=(tb_visitorModel)object;
		JdbcTemplate jt=this.getJdbcTemple();
		try{
		String update=" update tb_visitor "+
				" Password=?"+" where Visitor=? ";
		jt.update(update,new Object[]{"1","2",tb_visitormodel.getPassword(),tb_visitormodel.getVisitor()});
		
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
