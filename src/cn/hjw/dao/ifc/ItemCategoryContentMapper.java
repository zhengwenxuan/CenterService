package cn.hjw.dao.ifc;

import cn.hjw.model.ItemCategoryContent;

public interface ItemCategoryContentMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ItemCategoryContent record);

    int insertSelective(ItemCategoryContent record);

    ItemCategoryContent selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ItemCategoryContent record);

    int updateByPrimaryKey(ItemCategoryContent record);
}