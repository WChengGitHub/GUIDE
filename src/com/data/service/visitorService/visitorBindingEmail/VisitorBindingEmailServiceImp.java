package com.data.service.visitorService.visitorBindingEmail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.data.dao.singleForm.tb_visitorDao;
import com.data.model.VisitorBindingEmailModel;
import com.data.model.tb_visitorModel;
import com.data.service.SendEmail;

public class VisitorBindingEmailServiceImp implements VisitorBindingEmailService{
	private tb_visitorDao visitorDao;
	private List<Object>param;
    private List<Object>list;
	public tb_visitorDao getVisitorDao() {
		return visitorDao;
	}

	public void setVisitorDao(tb_visitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}
	//用来给要绑定的邮箱发送信息  参数：Visitor,Email
	@Override
	public boolean sendBingEmailInformation(
			VisitorBindingEmailModel visitorBindingEmailModel) throws UnknownHostException {
		// TODO Auto-generated method stub
		String sql="update tb_visitor set UUID=? where Visitor=?";
		String sql1="select Vid from tb_visitor where Visitor=?";
		InetAddress address=InetAddress.getLocalHost();
		String Ip=address.getHostAddress();
		String Email=visitorBindingEmailModel.getEmail();
		String Visitor=visitorBindingEmailModel.getVisitor();
		
		param=new LinkedList<Object>();
		String Vid="";
		param.add(Visitor);
		try {
			list=visitorDao.queryVid(sql1, param);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(list!=null)
		{
			tb_visitorModel visitorModel=(tb_visitorModel) list.get(0);
			Vid=visitorModel.getVid();
		}
		
		String UUID1=UUID.randomUUID().toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");//设置日期格式
		String Time=df.format(new Date());
		
		String linkAddress="http://"+Ip+":8080/GUIDE/VisitorBindingEmailController/checkBingingEamilInformation?Vid="+Vid+"&Email="+Email+"&UUID="+UUID1+"&CreateTime="+Time;
		String emailContent = "点击下面的链接,验证邮箱<br/><a href="
                + linkAddress + " target='_BLANK'>" + "邮箱验证"
                + "</a> " 
                + "<br/>tips:本邮件超过10分钟,链接将会失效" ;
//		System.out.println("Ip:"+Ip+"Email:"+Email+"Vid:"+Vid+"UUID:"+UUID1);
//		System.out.println("Time:"+Time);
//		System.out.println("linkAddress:"+linkAddress);
//	    System.out.println("emailContent:"+emailContent);
		param=new LinkedList<Object>();
        param.add(UUID1);
        param.add(Visitor);
        try {
        	SendHTMLEmail.sendHTMLEmail(Email, emailContent);
        	visitorDao.update1(sql, param);
        	return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//      System.out.println("本机计算机名："+address.getHostName());  
//      System.out.println("当前主机的IP地址："+address.getHostAddress());  
		return false;
	}
	//用来核实邮箱 参数：Vid,CreateTime,UUID,Email
	@Override
	public boolean checkBindingEmailInformation(
			VisitorBindingEmailModel visitorBindingEmailModel) {
		String Vid=visitorBindingEmailModel.getVid();
		String UUID1=visitorBindingEmailModel.getUUID();
		String Email=visitorBindingEmailModel.getEmail();
		String CreateTime=visitorBindingEmailModel.getCreateTime();
		// TODO Auto-generated method stub
		
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");//设置日期格式
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time=df.format(new Date());
		try
		{
		Date d1 = df1.parse(CreateTime);
		Date d2 = df.parse(Time);
		long diff = d2.getTime() - d1.getTime();
		long minutes = diff / (1000 * 60 );
		System.out.println("minutes:"+minutes);
		if(minutes>10)
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String sql="select count(*) from tb_visitor where Vid=\""+Vid+"\" and UUID=\""+UUID1+"\"";
		int number=visitorDao.queryVisitorNumber(sql);
		System.out.println("number:"+number);
		if(number!=1)
		{
			return false;
		}
		
		String sql1="update tb_visitor set UUID=null,Email=? where Vid=?";
		param=new LinkedList<Object>();
		param.add(Email);
		param.add(Vid);
		try {
			visitorDao.update1(sql1, param);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

}
