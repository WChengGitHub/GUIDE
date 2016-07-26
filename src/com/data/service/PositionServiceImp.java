package com.data.service;
import java.util.LinkedList;
import java.util.List;

import com.data.dao.Dao;
import com.data.model.PositionModel;
public class PositionServiceImp implements PositionService{
	List<Object>param;
    List<Object> positionModel;
    private Dao dao;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	@Override
	public boolean queryPosition(String province, String city, String area) {
		// TODO Auto-generated method stub
		String sql="select tb_province.Province,tb_city.City,tb_area.Area,tb_area.Aid"
				+ " from tb_province,tb_city,tb_area "
				+ " where tb_province.Province=? and tb_city.City=? and tb_area.Area=?";
		/*数据库语句一定要写正确，注意空格*/
		param=new LinkedList<Object>();
		param.add(province);
		param.add(city);
		param.add(area);
		positionModel=dao.query(sql, param);
		if(positionModel.size()==0)
			return false;
		else{
		PositionModel model=(PositionModel) positionModel.get(0);
		if(model.getAid()==null)
		// TODO Auto-generated method stub
		return false;
		else return true;}
	}

}
