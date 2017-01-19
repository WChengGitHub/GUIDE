package com.data.dao.singleForm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.data.model.AreaAdminAddOrDeleteSpotModel;
import com.data.model.ChangeAndDelAdminModel;
import com.data.model.tb_provinceModel;
import com.data.model.tb_spotModel;

public class tb_spotDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		// TODO Auto-generated method stub

	}

	public JdbcTemplate getJdbcTemple() {
		// TODO Auto-generated method stub
		return jdbcTemplate;
	}
	
	@SuppressWarnings("deprecation")
	//查询景点的名字是否存在
	public int querySpotNumber(String sql) {
		return jdbcTemplate.queryForInt(sql);

	}
	public ChangeAndDelAdminModel querySid(String sql) {// 根据Vid
		return (ChangeAndDelAdminModel) jdbcTemplate.queryForObject(sql,
				new RowMapper<Object>() {
					@Override
					public ChangeAndDelAdminModel mapRow(ResultSet rs, int arg1)
							throws SQLException {
						ChangeAndDelAdminModel changeAndDelAdminModel = new ChangeAndDelAdminModel();
						changeAndDelAdminModel.setSid(rs.getString("Sid"));
						return changeAndDelAdminModel;
					}
				});
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
			public tb_spotModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_spotModel spotModel = new tb_spotModel();
				spotModel.setSpot(rs.getString("Spot"));
				spotModel.setSpot(rs.getString("Sid"));
				spotModel.setLatitude(rs.getString("Latitude"));
				spotModel.setLongitude(rs.getString("Longitude"));
				return spotModel;
			}
		});

	}

	// 用来查询Latitude,Longitude,Radius,Description,Voice
	public List<Object> querySpotInformation(String sql,
			final List<Object> param) {
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
			public tb_spotModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				tb_spotModel spotModel = new tb_spotModel();
				spotModel.setLatitude(rs.getString("Latitude"));
				spotModel.setLongitude(rs.getString("Longitude"));
				spotModel.setRadius(rs.getString("Radius"));
				spotModel.setDescription(rs.getString("Description"));
				spotModel.setVoice(rs.getString("Voice"));
				return spotModel;
			}
		});

	}
//查询景点id,景点名字，景点管理员，创建时间，需要参数Account(这个是多表查询，查询了tb_admin,tb_spot 重构时得重写，因为它已经不是单表查询)
	public List<Object> query1(String sql,
			final List<Object> param) {
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
			public AreaAdminAddOrDeleteSpotModel mapRow(ResultSet rs, int arg1)
					throws SQLException {
				AreaAdminAddOrDeleteSpotModel areaAdminAddOrDeleteSpotModel=new AreaAdminAddOrDeleteSpotModel();
				tb_spotModel spotModel = new tb_spotModel();
				areaAdminAddOrDeleteSpotModel.setSid(rs.getString("Sid"));
				areaAdminAddOrDeleteSpotModel.setSpot(rs.getString("Spot"));
				areaAdminAddOrDeleteSpotModel.setCreateTime(sdf.format(rs.getTimestamp("CreateTime")));
				return areaAdminAddOrDeleteSpotModel;
			}
		});

	}

	public void update(String sql, final List<Object> param) {

		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				for (int i = 0; i < param.size(); i++) {
					try {
						ps.setObject(i + 1, param.get(i));
						//System.out.println(param.get(i));
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
