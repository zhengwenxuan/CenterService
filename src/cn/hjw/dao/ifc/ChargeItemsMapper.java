package cn.hjw.dao.ifc;

import java.util.List;




import cn.hjw.model.ChargeItems;
import cn.hjw.model.ExaminationItem;


public interface ChargeItemsMapper {
    int deleteByPrimaryKey(String objectid);
    int deleteByList(List<String> objectids);
    int insert(ChargeItems record);

    int insertSelective(ChargeItems record);

    ChargeItems selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ChargeItems record);

    int updateByPrimaryKey(ChargeItems record);
    
    List<ChargeItems> selectByCondition(ChargeItems record,int page,
			int pageSize);
    List<ExaminationItem> selectExamItemByCharge(String chargingItemId);
    
}