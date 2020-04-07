package cn.hjw.util;

import java.util.List;
import java.util.Map;

import cn.hjw.dao.ChargeItemDao;
import cn.hjw.model.ChargeItems;

public class ChargeItemUtil extends BaseUtil implements UtilIterface{
	private ChargeItemDao dao = null;
	private int totalCount = 0;

	protected ChargeItemUtil() {
		super(ChargeItemUtil.class);
		this.dao = (ChargeItemDao) getObject(ChargeItemDao.class);
	}
	@Override
	public List<ChargeItems> selectByCondition(Object record, int page,
			int pageSize) throws Exception {
		List<ChargeItems> list = this.dao.selectByCondition((ChargeItems)record, page,
				pageSize);
		for(ChargeItems i:list){
			Object o=selectByPrimaryKey(i.getObjectid());
			if(o!=null){
				//chargeItemInfo.setData((ChargeItems)o);
				ChargeItems item=(ChargeItems)o;
				i.setExamItems(item.getExamItems());
			}
		}
		
		
		this.setTotalCount(this.dao.count);
		return list;
	}
	@Override
	public int insert(Object record) throws Exception {
		return this.dao.insertSelective((ChargeItems)record);
	}
	@Override
	public int update(Object record) throws Exception {
		return this.dao.updateByPrimaryKeySelective((ChargeItems)record);
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
