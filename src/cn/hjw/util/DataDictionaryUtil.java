package cn.hjw.util;

import java.util.List;
import java.util.Map;

import cn.hjw.dao.DataDictionaryDao;
import cn.hjw.model.DataDictionary;


public class DataDictionaryUtil extends BaseUtil implements UtilIterface{
	private DataDictionaryDao dao = null;
	private int totalCount = 0;
	protected DataDictionaryUtil() {
		super(DataDictionaryUtil.class);
		this.dao = (DataDictionaryDao) getObject(DataDictionaryDao.class);
	}

	@Override
	public List<?> selectByCondition(Object record, int page, int pageSize)
			throws Exception {
		List<DataDictionary> list = this.dao.selectByCondition((DataDictionary)record, page,
				pageSize);
		this.setTotalCount(this.dao.count);
		return list;

	}

	@Override
	public int insert(Object record) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object record) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(List<String> objectids) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object selectByPrimaryKey(String objectid) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
