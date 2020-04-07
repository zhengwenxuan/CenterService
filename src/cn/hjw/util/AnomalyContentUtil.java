package cn.hjw.util;

import java.util.List;
import java.util.Map;

import cn.hjw.dao.AnomalyContentDao;
import cn.hjw.model.ItemCategory;
import cn.hjw.model.ItemCategory_2;


public class AnomalyContentUtil extends BaseUtil implements UtilIterface{
private AnomalyContentDao dao=null;
private int totalCount=0;
	protected AnomalyContentUtil() {
		super(AnomalyContentUtil.class);	
		this.dao = (AnomalyContentDao) getObject(AnomalyContentDao.class);
	}

	@Override
	public List<?> selectByCondition(Object record, int page, int pageSize)
			throws Exception {
		List<ItemCategory> list=this.dao.selectByCondition((ItemCategory)record, page, pageSize);
		this.setTotalCount(this.dao.count);
		return list;
	}

	@Override
	public int insert(Object record) throws Exception {
		return this.dao.insertSelective((ItemCategory_2)record);
	}

	@Override
	public int update(Object record) throws Exception {		
		return this.dao.updateByPrimaryKeySelective((ItemCategory_2)record);
	}

	@Override
	public int delete(List<String> objectids) throws Exception {
		return this.dao.deleteByList(objectids);
	}

	@Override
	public Object selectByPrimaryKey(String objectid) throws Exception {
		return this.dao.selectByPrimaryKey(objectid);
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
	public Object selectByPrimaryKey(Map<?, ?> condtion) throws Exception {
		return this.dao.selectByPrimaryKey(condtion);
		
	}

	

	@Override
	public String checkExist(Map<String, Object> condtion) throws Exception {
		return this.dao.checkAnomalyType(condtion);
	}

}
