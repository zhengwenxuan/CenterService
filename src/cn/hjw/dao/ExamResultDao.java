package cn.hjw.dao;

import java.util.List;
import java.util.Map;

import cn.hjw.bean.Condition;
import cn.hjw.model.ExamResult;
import cn.hjw.model.ExamSummaryOption;

public class ExamResultDao extends BaseDao {
	//**************6.12	体检报告--总检结论******************
	public List<ExamResult> selectChargeItems(Map<String,Object> para) throws Exception{
		try {			
			return this.sqlSession.selectList("center.selectChargeItems",
					para);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	public List<ExamResult> selectCheckItems(Condition condition) throws Exception{
		try {			
			return this.sqlSession.selectList("center.selectCheckItems",
					condition);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	public List<ExamSummaryOption> selectExamSummary(Map<String,Object> para) throws Exception{
		try {			
			return this.sqlSession.selectList("center.selectExamSummary",
					para);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	//**************6.12	体检报告--总检结论******************end
	
	//**************6.13	体检报告--报告详情******************
	public List<ExamResult> selectDeptInfo(Map<String,Object> para) throws Exception{
		try {			
			return this.sqlSession.selectList("center.selectDeptInfo",
					para);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	public List<ExamResult> selectDeptCheckItems(Condition condition) throws Exception{
		try {			
			return this.sqlSession.selectList("center.selectDeptCheckItems",
					condition);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	public List<ExamResult> selectDeptChargeItems(Condition condition) throws Exception{
		try {			
			return this.sqlSession.selectList("center.selectDeptChargeItems",
					condition);
		} catch(Exception ex){		
			throw ex;			
		}

	}
	//**************6.13	体检报告--报告详情******************end
}
