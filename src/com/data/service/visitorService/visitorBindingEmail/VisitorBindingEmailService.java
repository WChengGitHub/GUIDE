package com.data.service.visitorService.visitorBindingEmail;

import java.net.UnknownHostException;

import com.data.model.VisitorBindingEmailModel;

public interface VisitorBindingEmailService {
	 //用来给要绑定的邮箱发送信息  参数：Visitor,Email
     public boolean sendBingEmailInformation(VisitorBindingEmailModel visitorBindingEmailModel) throws UnknownHostException;
     //用来核实邮箱 参数：Vid,CreateTime,UUID,Email
     public boolean checkBindingEmailInformation(VisitorBindingEmailModel visitorBindingEmailModel);
}
