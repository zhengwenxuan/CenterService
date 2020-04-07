package cn.hjw.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.hjw.model.ExamInfo;


public class ExamInfoDao extends BaseDao {
	public int deleteReportByExamNum(Map<String,Object> para) throws Exception{
		try {			
			return this.sqlSession.delete("center.deleteReportByExamNum",
					para);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	public List<ExamInfo> selectExamInfos(Map<String,Object> para) throws Exception{
		try {			
			return this.sqlSession.selectList("center.selectExamInfo",
					para);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	
	
	public List<Map<String,Object>> selectByCondition(Map<String,Object> record, int page,int pageSize) {
		try {
			String sortString = "create_date.desc";
			PageBounds pageBounds = new PageBounds(page, pageSize,Order.formString(sortString));
			List<Map<String,Object>> list = this.sqlSession.selectList("center.selectExamInfoByMap",record, pageBounds);
			this.setPageList(list);
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public List<Map<String,Object>> selectExamInfoByMap(Map<String,Object> map) throws Exception{
		try {			
			return this.sqlSession.selectList("",map);
		} catch(Exception ex){		
			throw ex;			
		}

	}
}
