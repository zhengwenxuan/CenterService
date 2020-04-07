package cn.hjw.dao.ifc;

import java.util.List;





import cn.hjw.bean.Condition;
import cn.hjw.model.ChargingitemCombo;
import cn.hjw.model.Combo;

public interface ComboMapper {
    int deleteByPrimaryKey(String objectid);
    int deleteByList(List<String> objectids);
    int insert(Combo record);

    int insertSelective(Combo record);

    Combo selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(Combo record);

    int updateByPrimaryKey(Combo record);
    List<Combo> selectByCondition(Combo record,int page,
			int pageSize);
    List<Combo> selectByCondition(Condition record,int page,
			int pageSize);
    
    List<ChargingitemCombo> selectChargeItemByCombo(String comboId);
    
}