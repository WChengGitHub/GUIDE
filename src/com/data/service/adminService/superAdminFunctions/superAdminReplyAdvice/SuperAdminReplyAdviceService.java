package com.data.service.adminService.superAdminFunctions.superAdminReplyAdvice;

import net.sf.json.JSONArray;

import com.data.model.SuperAdminReplyAdviceModel;

public interface SuperAdminReplyAdviceService {
     public int getSoftwareAdviceNumber(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
     public JSONArray getSoftwareAdvices(SuperAdminReplyAdviceModel superAdminReplyAdviceModel);
}
