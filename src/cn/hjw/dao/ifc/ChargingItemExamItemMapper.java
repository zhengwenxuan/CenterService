package cn.hjw.dao.ifc;

import cn.hjw.model.ChargingItemExamItem;

public interface ChargingItemExamItemMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ChargingItemExamItem record);

    int insertSelective(ChargingItemExamItem record);

    ChargingItemExamItem selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ChargingItemExamItem record);

    int updateByPrimaryKey(ChargingItemExamItem record);
}