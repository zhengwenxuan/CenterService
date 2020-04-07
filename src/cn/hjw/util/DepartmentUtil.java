package cn.hjw.util;

import java.util.List;













import java.util.Map;

import cn.hjw.dao.DepartmentDao;
import cn.hjw.model.Department;


public class DepartmentUtil extends BaseUtil implements UtilIterface{
	private DepartmentDao dao = null;
private int totalCount=0;
	public DepartmentUtil() {
		super(DepartmentUtil.class);
		this.dao = (DepartmentDao) getObject(DepartmentDao.class);
	}
	@Override
	public List<?> selectByCondition(Object record,int page,
			int pageSize) throws Exception {		
		List<Department> list=this.dao.selectByCondition2((Department)record, page, pageSize);
		this.setTotalCount(this.dao.count);
		return list;
	}
	@Override
	public int insert(Object record) throws Exception{
		return this.dao.insertSelective((Department)record);
	}
	@Override
	public int update(Object record) throws Exception{
		return this.dao.updateByPrimaryKeySelective((Department)record);
	}
	@Override
	public int delete(List<String> objectids) throws Exception{
		return this.dao.deleteByCollection(objectids);
	}
	@Override
	public int getTotalCount() {
		return totalCount;
	}
	@Override
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public Department selectByPrimaryKey(String objectid) throws Exception {
		return (Department)this.dao.selectByPrimaryKey(objectid);
	}
	@Override
	public Object selectByPrimaryKey(Map<?, ?> condtion) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object checkExist(Map<String, Object> condtion) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
