package cn.hjw.util;

import java.util.List;
import java.util.Map;





public interface UtilIterface {
	public List<?> selectByCondition(Object record, int page,
			int pageSize) throws Exception;
	public int insert(Object record) throws Exception;
	public int update(Object record) throws Exception;
	public int delete(List<String> objectids) throws Exception;
	public Object selectByPrimaryKey(String objectid) throws Exception;	
	public Object selectByPrimaryKey(Map<?,?> condtion) throws Exception;	
	public Object checkExist(Map<String,Object> condtion) throws Exception;
	
	
	public void setTotalCount(int totalCount);
	public int getTotalCount() ;
}
