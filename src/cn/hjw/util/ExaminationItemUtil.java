package cn.hjw.util;

import java.util.List;
import java.util.Map;

import cn.hjw.dao.ExaminationItemDao;
import cn.hjw.model.ExaminationItem;
import cn.hjw.model.ItemCategory;

public class ExaminationItemUtil extends BaseUtil implements UtilIterface{
	private ExaminationItemDao dao = null;
	private int totalCount = 0;

	protected ExaminationItemUtil() {
		super(ExaminationItemUtil.class);
		this.dao = (ExaminationItemDao) getObject(ExaminationItemDao.class);
	}

	public ExaminationItem selectExaminationItems(String itemNum)
			throws Exception {
		return this.dao.selectExaminationItems(itemNum);
	}

	public List<ItemCategory> selectAnomalyTypes(String itemId)
			throws Exception {
		return this.dao.selectAnomalyTypes(itemId);
	}

	public List<ItemCategory> selectCategories(ItemCategory condtion)
			throws Exception {
		return this.dao.selectCategories(condtion);
	}

	public List<ItemCategory> selectCategoryValues(String objectId)
			throws Exception {
		return this.dao.selectCategoryValues(objectId);
	}
	@Override
	public List<?> selectByCondition(Object record,
			int page, int pageSize) {
		List<ExaminationItem> list = this.dao.selectByCondition((ExaminationItem)record, page,
				pageSize);
		this.setTotalCount(this.dao.count);
		return list;

	}
	@Override
	public int insert(Object record) throws Exception {
		return this.dao.insertSelective((ExaminationItem)record);
	}
	@Override
	public int update(Object record) throws Exception {
		return this.dao.updateByPrimaryKeySelective((ExaminationItem)record);
	}
	@Override
	public int delete(List<String> objectids) throws Exception {
		return this.dao.deleteByList(objectids);
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
	public Object selectByPrimaryKey(String objectid) throws Exception {
		return this.dao.selectByPrimaryKey(objectid);
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
