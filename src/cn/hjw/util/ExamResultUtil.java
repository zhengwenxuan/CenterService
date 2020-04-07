package cn.hjw.util;

import java.util.List;
import java.util.Map;

import cn.hjw.bean.Condition;
import cn.hjw.dao.ExamPacsSummaryOptionDao;
import cn.hjw.dao.ExamResultDao;
import cn.hjw.model.ExamPacsSummaryOption;
import cn.hjw.model.ExamResult;
import cn.hjw.model.ExamSummaryOption;


public class ExamResultUtil extends BaseUtil {
	private ExamResultDao dao = null;
private ExamPacsSummaryOptionDao summaryOptionDao=null;
	protected ExamResultUtil() {
		super(ExamResultUtil.class);
		this.dao = (ExamResultDao)getObject(ExamResultDao.class);
		this.summaryOptionDao = (ExamPacsSummaryOptionDao)getObject(ExamPacsSummaryOptionDao.class);
		
	}
	//**************6.12	体检报告--总检结论******************
	public List<ExamResult> selectChargeItems(Map<String,Object> para) throws Exception {
		
		return this.dao.selectChargeItems(para);
	}
	public List<ExamResult> selectCheckItems(Condition condition) throws Exception {
		return this.dao.selectCheckItems(condition);
	}
	public List<ExamSummaryOption> selectExamSummary(Map<String,Object> para) throws Exception {
		return this.dao.selectExamSummary(para);
	}	
	//**************6.12	体检报告--总检结论******************end
	
	//**************6.13	体检报告--报告详情******************
	public List<ExamResult> selectDeptChargeItems(Condition condition) throws Exception {
		return this.dao.selectDeptChargeItems(condition);
	}
	public List<ExamResult> selectDeptCheckItems(Condition condition) throws Exception {
		return this.dao.selectDeptCheckItems(condition);
	}
	public List<ExamResult> selectDeptInfo(Map<String,Object> para) throws Exception {
		return this.dao.selectDeptInfo(para);
	}
	public List<ExamPacsSummaryOption> selectExamPacsSummaryOption(ExamPacsSummaryOption condition) throws Exception {		
		return this.summaryOptionDao.selectByCondition(condition);
	}
	//**************6.13	体检报告--报告详情******************end
}
