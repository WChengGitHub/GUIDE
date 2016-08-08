package com.data.dao.singleForm.checkAdvicePage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.tb_adviceModel;

public class TestDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	
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
						System.out
								.println("checkAdviceDao:Pstmt中的Sql语句参数注入异常。。。");
						e.printStackTrace();
					}
				}
			}
		}, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int arg1)
					throws SQLException {
				
				List<Object>list=new LinkedList<Object>();
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				
				if(columnCount==0)
					{
					System.out.println("数据差找失败，请检查sql语句是否正确");
					return null;
					}
				
				System.out.println("columnCount:"+columnCount);
				Map rowData = new HashMap();
				for(int i=1;i<=columnCount;i++)
				{
				System.out.println(md.getColumnName(i));
				if(md.getColumnName(i).equals("Atime"))
				rowData.put(md.getColumnName(i), sdf.format(rs.getObject(i)));
				else {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				}
				
				System.out.println(rowData);
				return rowData;
			}
		});

	}

}
