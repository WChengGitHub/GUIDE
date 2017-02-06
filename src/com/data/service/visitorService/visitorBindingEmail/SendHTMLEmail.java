package com.data.service.visitorService.visitorBindingEmail;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

 
public class SendHTMLEmail
{
	public static void main(String[] args) {  
        // 不要使用SimpleEmail,会出现乱码问题  
        HtmlEmail email = new HtmlEmail();  
        // SimpleEmail email = new SimpleEmail();  
        try {  
            // 这里是SMTP发送服务器的名字：qq的如下：  
            email.setHostName("smtp.163.com");  
            // 字符编码集的设置  
            email.setCharset("UTF-8");  
            // 收件人的邮箱  
            email.addTo("154468476@qq.com");  
            // 发送人的邮箱  
            email.setFrom("13794566179@163.com", "Hello");  
            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
            email.setAuthentication("13794566179@163.com", "a154468");  
            email.setSubject("下午3：00会议室讨论，请准时参加");  
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.setMsg("<h1 style='color:red'>下午3：00会议室讨论</h1>" + " 请准时参加！");  
            // 发送  
            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
            CommandMap.setDefaultCommandMap(mc);
            email.send();  
  
            System.out.println("邮件发送成功!");  
        } catch (EmailException e) {  
            e.printStackTrace();  
            System.out.println("邮件发送失败!");  
        }  
  
    }  
	public static void sendHTMLEmail(String Email,String content)
	{
		 HtmlEmail email = new HtmlEmail();  
	        // SimpleEmail email = new SimpleEmail();  
	        try {  
	            // 这里是SMTP发送服务器的名字：qq的如下：  
	            email.setHostName("smtp.163.com");  
	            // 字符编码集的设置  
	            email.setCharset("UTF-8");  
	            // 收件人的邮箱  
	            email.addTo(Email);  
	            // 发送人的邮箱  
	            email.setFrom("13794566179@163.com", "Hello");  
	            // 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
	            email.setAuthentication("13794566179@163.com", "a154468");  
	            email.setSubject("邮箱验证");  
	            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
	            email.setMsg(content);  
	            // 发送  
	            MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	            mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	            mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	            mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	            mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	            mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	            CommandMap.setDefaultCommandMap(mc);
	            email.send();  
	  
	            System.out.println("邮件发送成功!");  
	        } catch (EmailException e) {  
	            e.printStackTrace();  
	            System.out.println("邮件发送失败!");  
	        }  
	}
}
