package com.data.dao.singleForm.checkAdvicePage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.tb_adviceModel;

public class TestDaoMain {
	private static  List<Object>param;
    private static List<Object> advice;
	  public static void main(String[]args) throws SQLException
	  {
		  ApplicationContext factory = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
		  TestDao testDao=(TestDao) factory.getBean("TestDao");
		  String aDid="201607302152143";
		  String sql="select Title ,Advice,Vid from tb_advice where ADid=?";
		  String sql1="select ADid,Title,Atime,type from tb_advice where status=?";
		  param=new LinkedList<Object>();
		  param.add("0");
		  advice=new LinkedList<Object>();
		  advice=testDao.query(sql1, param);
		  JSONArray jsonArray=JSONArray.fromObject(advice);
		  System.out.println(jsonArray);
		
		  Map map=new HashedMap();
		  map=(Map) advice.get(0);
		  System.out.println(map);
		  tb_adviceModel tbAdviceModel=new tb_adviceModel();
		  tbAdviceModel.setTitle((String) map.get("Title"));
		  System.out.println(tbAdviceModel.getTitle());
	  }
}
