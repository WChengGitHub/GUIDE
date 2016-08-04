package com.data.service.visitorService.checkAdviceService;

import java.util.List;

public interface CheckAdvice {
     public int queryAdviceNumber();
     public List<Object> queryAdviceRecord();
     public Object queryTitleAndAdvice(String ADid);
     public void updateAdviceStatus(String ADid,String Status);
     public String queryVisitorEmail(String Vid);
}
