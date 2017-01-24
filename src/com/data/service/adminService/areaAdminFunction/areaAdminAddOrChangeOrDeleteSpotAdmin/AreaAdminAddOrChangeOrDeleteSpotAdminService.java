package com.data.service.adminService.areaAdminFunction.areaAdminAddOrChangeOrDeleteSpotAdmin;

import java.util.List;

import com.data.model.AreaAdminAddOrChangeOrDeleteSpotAdminModel;
import com.data.model.CompleteVisitorInformationModel;

public interface AreaAdminAddOrChangeOrDeleteSpotAdminService {
	  //根据景区管理员Account得到景区管理员所在景区的景点管理员的Account,Aid,以及景点管理员所管理景点的Spot,Sid
      public List<AreaAdminAddOrChangeOrDeleteSpotAdminModel> getSpotAdminInformations(AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel);
      
      //根据景区管理员Account得到景区管理员所在景区的还没有管理景点的景点管理员（还没有管理景点的景点管理员,Arid=景区Arid,Sid=null)的Account,Aid
      public List<Object> getSpotAdminInformations1(AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel);
      
      //用来更换景点的景点管理员  参数：Aid,newAid(新景点管理员Aid),Sid(景点id) 
      public boolean changeAdmin(AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel);
      
      //用来删除景点管理员 参数：Aid,Sid
      public boolean deleteAdmin(AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel);
      
      //用来得到还没有景点管理员的景点（status=0） 参数：景区管理员Account
      public List<Object> getSpotInformations(AreaAdminAddOrChangeOrDeleteSpotAdminModel areaAdminAddOrChangeOrDeleteSpotAdminModel);
      
      //用来增加一个景点管理员      参数：景点管理员Account,景点Sid
      public boolean addSpotAdmin(AreaAdminAddOrChangeOrDeleteSpotAdminModel addOrChangeOrDeleteSpotAdminModel);
      
      //用来判断管理员的Account是否存在     参数：Account 
      public int judgeAccount(AreaAdminAddOrChangeOrDeleteSpotAdminModel addOrChangeOrDeleteSpotAdminModel);
      
}
