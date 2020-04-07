package cn.hjw.dao;

import java.util.List;



import cn.hjw.dao.ifc.ExamPacsSummaryOptionMapper;
import cn.hjw.model.ExamPacsSummaryOption;

public class ExamPacsSummaryOptionDao extends BaseDao implements ExamPacsSummaryOptionMapper{

	@Override
	public int deleteByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ExamPacsSummaryOption record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ExamPacsSummaryOption record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ExamPacsSummaryOption selectByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ExamPacsSummaryOption record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ExamPacsSummaryOption record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ExamPacsSummaryOption record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ExamPacsSummaryOption> selectByCondition(
			ExamPacsSummaryOption record) {
		try {
			
			List<ExamPacsSummaryOption> list = this.sqlSession.selectList(
					"cn.hjw.dao.ifc.ExamPacsSummaryOptionMapper.selectByCondition",
					record);
			
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}

}
