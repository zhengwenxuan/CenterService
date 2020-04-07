package cn.hjw.dao.ifc;

import cn.hjw.model.ChargingitemCombo;

public interface ChargingitemComboMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ChargingitemCombo record);

    int insertSelective(ChargingitemCombo record);

    ChargingitemCombo selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ChargingitemCombo record);

    int updateByPrimaryKey(ChargingitemCombo record);
}