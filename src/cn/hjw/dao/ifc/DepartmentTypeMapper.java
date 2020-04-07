package cn.hjw.dao.ifc;

import java.util.List;
import java.util.Map;

import cn.hjw.model.DepartmentType;

public interface DepartmentTypeMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(DepartmentType record);

    int insertSelective(DepartmentType record);

    DepartmentType selectByPrimaryKey(String objectid);
    
    List<Map<String,Object>> selectItems();

    int updateByPrimaryKeySelective(DepartmentType record);

    int updateByPrimaryKey(DepartmentType record);
}