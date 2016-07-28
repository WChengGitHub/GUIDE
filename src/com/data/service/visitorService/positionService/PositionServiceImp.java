package com.data.service.visitorService.positionService;
import java.util.LinkedList;
import java.util.List;



import com.data.dao.multipleForm.pageOne.PositionDao;
import com.data.model.PositionModel1;
public class PositionServiceImp implements PositionService{
	static List<Object>param;
    List<Object> positionModel1;
    private PositionDao positionDao;
	
	public PositionDao getPositionDao() {
		return positionDao;
	}
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
	@Override
	public String[] queryPosition(String province, String city, String longitude,String latitude) {
		// TODO Auto-generated method stub
		
		String sql="select Pid from tb_province where Province=?";
		String sql1="select Cid from tb_city where City=? and Pid=?";
		String sql2="select * from tb_area where Cid=?";
		String sql3="select * from tb_spot where Aid=?";
		String pid=queryPid(province, sql);
		String cid=queryCid(city, pid, sql1);
		PositionModel1 model=queryAid(cid, longitude, latitude, sql2);
		String aid=model.getAid();
		String area=model.getArea();
		
		String spot=querySpot(aid, longitude, latitude, sql3);
		String position[] = new String[4];
		position[0]=province;
		position[1]=city;
		position[2]=area;
		position[3]=spot;
		if(pid!=null);
		{
			if(cid!=null)
			{
				if(aid!=null)
				{
					if(spot!=null)
					{
						//System.out.println(spot);
						return position;
					}
					else return null;
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
		}
		
		
	}
	@Override
	public String queryPid(String province,String sql) {
		param=new LinkedList<Object>();
		param.add(province);
		positionModel1=positionDao.query(sql, param);
		if(positionModel1.size()==0)
			return null;
		else{
		PositionModel1 model1=(PositionModel1) positionModel1.get(0);
		if(model1.getPid()==null)
		// TODO Auto-generated method stub
		return null;
		else 
		{   String pid=model1.getPid();
		// TODO Auto-generated method stub
		   //System.out.println(pid);
		    return pid;
		}
		}
		
	}
	@Override
	public String queryCid(String city, String pid,String sql) {
		// TODO Auto-generated method stub
		param=new LinkedList<Object>();
		param.add(city);
		param.add(pid);
		positionModel1=positionDao.query(sql, param);
		if(positionModel1.size()==0)
			return null;
		else{
		PositionModel1 model1=(PositionModel1) positionModel1.get(0);
		if(model1.getCid()==null)
		// TODO Auto-generated method stub
		return null;
		else 
		{   String cid=model1.getCid();
		// TODO Auto-generated method stub
		   //System.out.println("Cid:"+cid);
		    return cid;
		}
		}
	}
	@Override
	public PositionModel1 queryAid(String cid, String longitude, String latitude,String sql) {
		// TODO Auto-generated method stub
		param=new LinkedList<Object>();
		param.add(cid);
		positionModel1=positionDao.query(sql, param);
		for(int i=0;i<positionModel1.size();i++)
		{
			//System.out.println("size:"+positionModel1.size());
			PositionModel1 model1=(PositionModel1) positionModel1.get(i);
			//double testDou = Double.parseDouble(str);
			double lon1=Double.parseDouble(longitude);
			double lat1=Double.parseDouble(latitude);
			double lon2=Double.parseDouble(model1.getLongitude());
			double lat2=Double.parseDouble(model1.getLatitude());
			 double distance =CalculateLonLat.GetDistance(lon1,lat1,lon2,lat2);
			 //System.out.println(distance);
			
			//C = sin(LatA)*sin(LatB) + cos(LatA)*cos(LatB)*cos(MLonA-MLonB)

          //Distance = R*Arccos(C)*Pi/180
			int radius=model1.getRadius();
			if(distance<radius||distance==radius)
			{
				String aid=model1.getAid();
				return model1;
			}
		}
		return null;
	}
	@Override
	public String querySpot(String aid, String longitude, String latitude,
			String sql) {
		// TODO Auto-generated method stub
		param=new LinkedList<Object>();
		param.add(aid);
		positionModel1=positionDao.query(sql, param);
		for(int i=0;i<positionModel1.size();i++)
		{
			//System.out.println("size:"+positionModel1.size());
			PositionModel1 model1=(PositionModel1) positionModel1.get(i);
			//double testDou = Double.parseDouble(str);
			double lon1=Double.parseDouble(longitude);
			double lat1=Double.parseDouble(latitude);
			double lon2=Double.parseDouble(model1.getLongitude());
			double lat2=Double.parseDouble(model1.getLatitude());
			 double distance =CalculateLonLat.GetDistance(lon1,lat1,lon2,lat2);
			 //System.out.println(distance);
			
			//C = sin(LatA)*sin(LatB) + cos(LatA)*cos(LatB)*cos(MLonA-MLonB)

          //Distance = R*Arccos(C)*Pi/180
			int radius=model1.getRadius();
			if(distance<radius||distance==radius)
			{
				String spot=model1.getSpot();
				return spot;
			}
		}
		return null;
		
	}
	
}
