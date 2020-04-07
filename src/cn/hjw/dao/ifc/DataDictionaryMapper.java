package cn.hjw.dao.ifc;

import java.util.List;

import cn.hjw.model.DataDictionary;


public interface DataDictionaryMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
    
    List<DataDictionary> selectByCondition(DataDictionary record,int page,
			int pageSize);
}