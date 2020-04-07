package cn.hjw.dao.ifc;

import java.util.List;


import cn.hjw.model.ExamPacsSummaryOption;

public interface ExamPacsSummaryOptionMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ExamPacsSummaryOption record);

    int insertSelective(ExamPacsSummaryOption record);

    ExamPacsSummaryOption selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ExamPacsSummaryOption record);

    int updateByPrimaryKeyWithBLOBs(ExamPacsSummaryOption record);

    int updateByPrimaryKey(ExamPacsSummaryOption record);
    
    List<ExamPacsSummaryOption> selectByCondition(ExamPacsSummaryOption record);
}