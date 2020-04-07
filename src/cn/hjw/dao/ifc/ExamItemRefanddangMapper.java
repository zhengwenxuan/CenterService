package cn.hjw.dao.ifc;

import cn.hjw.model.ExamItemRefanddang;

public interface ExamItemRefanddangMapper {
    int deleteByPrimaryKey(String objectid);

    int insert(ExamItemRefanddang record);

    int insertSelective(ExamItemRefanddang record);

    ExamItemRefanddang selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ExamItemRefanddang record);

    int updateByPrimaryKey(ExamItemRefanddang record);
}