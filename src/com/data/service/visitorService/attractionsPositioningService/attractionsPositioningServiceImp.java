package com.data.service.visitorService.attractionsPositioningService;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.dao.singleForm.tb_areaDao;
import com.data.dao.singleForm.tb_cityDao;
import com.data.dao.singleForm.tb_spotDao;
import com.data.model.VisitorPositioningModel;
import com.data.model.tb_areaModel;
import com.data.model.tb_cityModel;
import com.data.model.tb_spotModel;

public class attractionsPositioningServiceImp implements attractionsPositioningService{
	//变量
	static List<Object> param,param2,param3;
	List<Object> tb_citymodellist;
	List<Object> tb_areamodellist;
	List<Object> tb_spotmodellist;
	
	static String AttractionsPositioningFalse="0",unfind="1";
	
	private static final double PI = 3.141592653589793238;  
	String latitude ; //景区经度 
    String longitude; //景区维度
	int radius;//景区半径
	String slatitude; //景点经度
	String slongitude; //景点纬度
	int sradius;//景点半径
	
	//存放经纬度范围的计算结果
	double[] result = new double[4]; 
	//景区id 测试用 arid=1
	String arid = "1";
	String sid;
	String spot;
	@Override
	public String AttractionsPositioning(VisitorPositioningModel visitorPositioningModel) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext factory= new ClassPathXmlApplicationContext("applicationContext.xml");
		tb_cityDao tb_citydao=(tb_cityDao) factory.getBean("cityDao");
		tb_areaDao tb_areadao=(tb_areaDao) factory.getBean("areaDao");
		tb_spotDao tb_spotdao=(tb_spotDao) factory.getBean("spotDao");
	try{	
		 String cid = null;
		 String sql=" select * from tb_city where City=? ";
		 param=new LinkedList<Object>();
		 //填sql语句参数的
		 param.add(visitorPositioningModel.getCity());
	
		 tb_citymodellist=tb_citydao.query(sql, param);	
		 
		 tb_cityModel tb_citymodel2=(tb_cityModel) tb_citymodellist.get(0);
		 
		 cid=tb_citymodel2.getCid();
		 System.out.println(cid+"cid   test");
		 
		 //取游客当前经纬度值 vlat/vlon
		 double vlat = visitorPositioningModel.getLatitude();
		 double vlon = visitorPositioningModel.getLongitude();
		 //根据Cid查景区
		 String sql2=" select * from tb_area where Cid=? ";
		 param2=new LinkedList<Object>();
		 //填sql语句参数的
		 param2.add(cid);
	
		 tb_areamodellist=tb_areadao.query(sql2, param2);	
		 //遍历景区表找到所在景区的id
		 int size = tb_areamodellist.size();
		 for(int i=0;i<size;i++){
			 tb_areaModel tb_areamodel2=(tb_areaModel) tb_areamodellist.get(i);
			 
			 latitude=tb_areamodel2.getLongitude();
			 longitude=tb_areamodel2.getLongitude();
			 radius=tb_areamodel2.getRadius();
			System.out.println(latitude+longitude+radius+"  test latitude+longitude+radius");
			//String转为double
			double Lat = Double.parseDouble(latitude);
			double Lon = Double.parseDouble(longitude);
			result=getAround(Lat,Lon,radius);
			//测试
			System.out.println(result[0]);
			System.out.println(result[1]);
			System.out.println(result[2]);
			System.out.println(result[3]);
			if(vlat>=result[0] && vlat<=result[2]){
				if(vlon>=result[1] && vlon<=result[3]){
					arid = tb_areamodel2.getArid();
					break;
				}
			}
			//测试
			else
			{
				System.out.println(vlat+" vlat"+vlon+" vlon"+result[0]+" minlat"+result[2]+" maxlat"+result[1]+" minlon"+result[3]+" maxlon");
				return unfind;
			}
		 }
		 
		 
		 //遍历属于所在景区的所有景点
		 String sql3 = " select * from tb_spot where Arid=? ";
		 param3=new LinkedList<Object>();
		 //填sql语句参数的
		 param3.add(arid);
		 tb_spotmodellist=tb_spotdao.query(sql3, param3);	
		 

		 int size2 = tb_spotmodellist.size();
		 //测试
		 System.out.println(size2+ "   test  spot's size");
		 for(int i=0;i<size2;i++){
			 tb_spotModel tb_spotmodel=(tb_spotModel) tb_spotmodellist.get(i);
			 slatitude=tb_spotmodel.getLongitude();
			 slongitude=tb_spotmodel.getLongitude();
			 //调试时设定
			 sradius=6;//(int) Double.parseDouble(tb_spotmodel.getRadius());//景点半径是String型 这里转为int型
			 //测试
			 System.out.println(slatitude+slongitude+sradius+"  test slatitude+slongitude+sradius");
			 
			 double sLat = Double.parseDouble(slatitude);
			 double sLon = Double.parseDouble(slongitude);
			 result=getAround(sLat,sLon,sradius);
			 //测试
			 System.out.println(result[0]);
			 System.out.println(result[1]);
			 System.out.println(result[2]);
			 System.out.println(result[3]);
			 if(vlat>=result[0] && vlat<=result[2]){
			 	if(vlon>=result[1] && vlon<=result[3]){
					spot = tb_spotmodel.getSpot();
					break;
			 	}
			 }
			 //测试
			 else
			 {
				System.out.println(vlat+" vlat"+vlon+" vlon"+result[0]+" minlat"+result[2]+" maxlat"+result[1]+" minlon"+result[3]+" maxlon");
				return unfind;
			 }			 
			 
		 }
		 
		 return spot;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("AttractionsPositioning fail");
		return AttractionsPositioningFalse;
	}	
		
	}
	
	//计算经纬度范围
	public static double[] getAround(double lat,double lon,int raidus){  
        double latitude2 = lat;  
        double longitude2 = lon;  
        double degree = (24901*1609)/360.0;  
        double raidusMile = raidus;  
        double dpmLat = 1/degree;  
        double radiusLat = dpmLat*raidusMile;  
        double minLat = latitude2 - radiusLat;  
        double maxLat = latitude2 + radiusLat;  
        double mpdLng = degree*Math.cos(latitude2 * (PI/180));  
        double dpmLng = 1 / mpdLng;  
        double radiusLng = dpmLng*raidusMile;  
        double minLng = longitude2 - radiusLng;  
        double maxLng = longitude2 + radiusLng;  
        //System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");  
        return new double[]{minLat,minLng,maxLat,maxLng};  
    }    

}
