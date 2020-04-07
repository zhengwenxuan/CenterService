package cn.hjw.dao.ifc;

import cn.hjw.model.ExamSummaryOption;

public interface ExamSummaryOptionMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ExamSummaryOption record);

    int insertSelective(ExamSummaryOption record);

    ExamSummaryOption selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ExamSummaryOption record);

    int updateByPrimaryKeyWithBLOBs(ExamSummaryOption record);

    int updateByPrimaryKey(ExamSummaryOption record);
}