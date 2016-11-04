package com.data.service.visitorService.completeVisitorInformationService;

import com.data.model.CompleteVisitorInformationModel;

public interface CompleteVisitorInformationService {
     // public CompleteVisitorInformationModel getGender(CompleteVisitorInformationModel completeVisitorInformationModel);
      public CompleteVisitorInformationModel getVisitorInformation(CompleteVisitorInformationModel completeVisitorInformationModel);
      public int judgeVisitor(CompleteVisitorInformationModel completeVisitorInformationModel);
      public boolean updateVisitorInformation(CompleteVisitorInformationModel completeVisitorInformationModel);
      public CompleteVisitorInformationModel getVid(CompleteVisitorInformationModel completeVisitorInformationModel);
}
