package cn.hjw.util;

import java.util.List;
import java.util.Map;

import cn.hjw.dao.ExamInfoDao;
import cn.hjw.model.ExamInfo;



public class ExamInfoUtil extends BaseUtil implements UtilIterface {
	private ExamInfoDao dao = null;
	private int totalCount=0;
	protected ExamInfoUtil() {
		super(ExamInfoUtil.class);
		this.dao = (ExamInfoDao)getObject(ExamInfoDao.class);
	}
	public int deleteReportByExamNum(Map<String,Object> para) throws Exception {
		return this.dao.deleteReportByExamNum(para);
	}
	public List<ExamInfo> selectExamInfos(Map<String,Object> para) throws Exception {
		return this.dao.selectExamInfos(para);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> selectByCondition(Object record, int page,int pageSize) throws Exception {
		List<Map<String,Object>> list = this.dao.selectByCondition((Map<String,Object>)record, page,
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
	public Object selectByPrimaryKey(Map<?, ?> condtion) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object checkExist(Map<String, Object> condtion) throws Exception {
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
}
