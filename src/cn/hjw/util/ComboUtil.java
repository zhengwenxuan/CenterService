package cn.hjw.util;

import java.util.List;
import java.util.Map;

import cn.hjw.bean.Condition;
import cn.hjw.dao.ComboDao;
import cn.hjw.model.Combo;



public class ComboUtil extends BaseUtil implements UtilIterface{
	private ComboDao dao = null;
private int totalCount=0;
	protected ComboUtil() {
		super(ComboUtil.class);		
		this.dao = (ComboDao) getObject(ComboDao.class);
	}

	@Override
	public List<?> selectByCondition(Object record, int page, int pageSize)
			throws Exception {
		List<Combo> list=this.dao.selectByCondition((Condition)record, page, pageSize);
		this.setTotalCount(this.dao.count);
		return list;
	}
	public List<?> selectByCondition(Map<String,Object> condition, String sortString,int page, int pageSize)
			throws Exception {
		List<Combo> list=this.dao.selectByCondition(condition,sortString, page, pageSize);
		this.setTotalCount(this.dao.count);
		return list;
	}
	@Override
	public int insert(Object record) throws Exception {
		return this.dao.insertSelective((Combo)record);
	}

	@Override
	public int update(Object record) throws Exception {
		return this.dao.updateByPrimaryKeySelective((Combo)record);
	}

	@Override
	public int delete(List<String> objectids) throws Exception {
		return this.dao.deleteByList(objectids);
	}

	@Override
	public void setTotalCount(int totalCount) {		
		this.totalCount = totalCount;
	}

	@Override
	public int getTotalCount() {		
		return this.totalCount;
	}

	@Override
	public Object selectByPrimaryKey(String objectid) throws Exception {
		return this.dao.selectByPrimaryKey(objectid);
	}
	public Object selectByPrimaryKey(String objectid,String itemCategory) throws Exception {
		return this.dao.selectByPrimaryKey(objectid,itemCategory);
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
