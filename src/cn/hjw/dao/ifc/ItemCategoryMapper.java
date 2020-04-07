package cn.hjw.dao.ifc;

import java.util.List;




import java.util.Map;

import cn.hjw.model.ItemCategory;
import cn.hjw.model.ItemCategory_2;

public interface ItemCategoryMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ItemCategory_2 record);

    int insertSelective(ItemCategory_2 record);

    ItemCategory_2 selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ItemCategory_2 record);

    int updateByPrimaryKey(ItemCategory_2 record);
    
    List<ItemCategory> selectByCondition(ItemCategory record,int page,
			int pageSize);
    public int deleteByList(List<String> objectids);
    
    public String checkAnomalyType(Map<String, Object> condtion) ;
}