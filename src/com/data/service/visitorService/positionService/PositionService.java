package com.data.service.visitorService.positionService;

import com.data.model.PositionModel1;

public interface PositionService {
	public String[] queryPosition(String province,String city,String longitude,String latitude);
	public String queryPid(String province,String sql);
	public String queryCid(String city,String pid,String sql);
	public PositionModel1 queryAid(String cid,String longitude,String latitude,String sql);
    public String querySpot(String aid,String longitude,String latitude,String sql);
}
