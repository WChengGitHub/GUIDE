package com.data.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.data.model.tb_visitorModel;
 
public class SendMsg_webchinese{

	public int sendMessage  (tb_visitorModel tb_visitormodel) {
 
		//随机数
		int randomNumber = (int)(Math.random() * 1000000 );
        System.out.println(randomNumber);//test
		
      try{
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "Test_zgm"),
				new NameValuePair("Key", "bbba217a827f6978db2a"),
				new NameValuePair("smsMob", tb_visitormodel.getTelephone()),
				new NameValuePair("smsText", "您绑定手机的验证码为"+randomNumber) };
		post.setRequestBody(data);
 
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result);
 
		post.releaseConnection();
		return randomNumber;
      } 
      catch (Exception e) {
			System.out.println("error1");
		e.printStackTrace();
		return 0;
      }
		
 
	}
 
}