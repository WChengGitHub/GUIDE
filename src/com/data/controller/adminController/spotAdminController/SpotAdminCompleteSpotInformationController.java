package com.data.controller.adminController.spotAdminController;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.data.model.SpotAdminCompleteSpotInformationModel;
import com.data.service.adminService.spotAdminFunctions.spotAdminCompleteSpotInformation.SpotAdminCompleteSpotInformationService;

public class SpotAdminCompleteSpotInformationController {
    public static void main(String[]args)
    {
    	ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
    	SpotAdminCompleteSpotInformationService spotAdminCompleteSpotInformationService=(SpotAdminCompleteSpotInformationService) factory.getBean("spotAdminCompleteSpotInformationService");
    	SpotAdminCompleteSpotInformationModel spotAdminCompleteSpotInformationModel=new SpotAdminCompleteSpotInformationModel();
    	spotAdminCompleteSpotInformationModel.setAccount("1");
    	spotAdminCompleteSpotInformationModel=spotAdminCompleteSpotInformationService.getSpotInformation(spotAdminCompleteSpotInformationModel);
    	JSONArray jsonArray = JSONArray.fromObject(spotAdminCompleteSpotInformationModel);
		 System.out.println(jsonArray);
    }
}
