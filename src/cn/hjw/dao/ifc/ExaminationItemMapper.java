package cn.hjw.dao.ifc;

import java.util.List;




import cn.hjw.model.ExaminationItem;

public interface ExaminationItemMapper {
    int deleteByPrimaryKey(String objectid);
    int deleteByList(List<String> objectids);
    int insert(ExaminationItem record);

    int insertSelective(ExaminationItem record) throws Exception;

    ExaminationItem selectByPrimaryKey(String objectid);

    int updateByPrimaryKeySelective(ExaminationItem record) throws Exception;

    int updateByPrimaryKey(ExaminationItem record);
    List<ExaminationItem> selectByCondition(ExaminationItem record,int page,
			int pageSize);
}