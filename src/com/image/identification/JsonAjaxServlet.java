package com.image.identification;
//import java.util.HashMap;
//import java.util.List; 
//import java.util.Map;
//import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.sf.json.JSONObject;

/*
 *@author: ZhangGuangMing  
 *mail:    844512330@qq.com
 *Sep 29, 2016  Guangdong,HZU,China
 */
public class JsonAjaxServlet extends HttpServlet{


	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		//在Upload.java中将识别出的前十张图片的路径用数组传到这
		UpLoadImage upLoadImage = new UpLoadImage();
		String[] imageUrl=upLoadImage.getImageUrl();
		
		
		//List<String> list=Arrays.asList(imageUrl);
		//for(int i=0;i<list.size();i++){  
        //    System.out.println(list.get(i));  
        //}  
		
		PrintWriter out = response.getWriter();
		
		//将数据拼接成JSON格式
		String str="{";
		for(int i=0;i<9;i++){
			str+="\"url"+i+"\":\"" + imageUrl[i] + "\",";
		}
		str=str+"\"url9\":\""+imageUrl[9]+"\"}";
		out.print(str);
		out.flush();
		out.close();
		
		
		/*Map map = new HashMap();
		map.put("list",list);
		JSONObject jso = JSONObject.fromObject(map);
		System.out.println(jso);
		out.print(jso);
		out.flush();
		out.close();*/
		

//		out.print("{\"url0\":\"" + imageUrl[0] + "\",\"url1\":\""+imageUrl[1]+"\","
//				+ "\"url2\":\""+imageUrl[2]+"\",\"url3\":\""+imageUrl[3]+"\","
//				+ "\"url4\":\""+imageUrl[4]+"\",\"url5\":\""+imageUrl[5]+"\","
//				+ "\"url6\":\""+imageUrl[6]+"\",\"url7\":\""+imageUrl[7]+"\","
//				+ "\"url8\":\""+imageUrl[8]+"\",\"url9\":\""+imageUrl[9]+"\"}");
		//out.print("{url0:" + imageUrl[0] +"}");
	}
}
