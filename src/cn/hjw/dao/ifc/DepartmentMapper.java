package cn.hjw.dao.ifc;

import java.util.List;
import java.util.Map;

import cn.hjw.model.Department;

public interface DepartmentMapper {
	
    int deleteByPrimaryKey(String objectid);
    int deleteByCollection(List<String> objectids);
    
    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Map<String, Object>> selectByCondition(Department record,int page,
			int pageSize);
    List<Department> selectByCondition2(Department record,int page,
			int pageSize);
}