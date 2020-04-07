package cn.hjw.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.hjw.dao.ifc.DataDictionaryMapper;
import cn.hjw.model.DataDictionary;


public class DataDictionaryDao  extends BaseDao implements
DataDictionaryMapper {

	@Override
	public int deleteByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DataDictionary selectByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DataDictionary record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DataDictionary> selectByCondition(DataDictionary record,
			int page, int pageSize) {
		try {
			String sortString = "create_time.desc";
			PageBounds pageBounds = new PageBounds(page, pageSize,
					Order.formString(sortString));
			List<DataDictionary> list = this.sqlSession.selectList(
					"cn.hjw.dao.ifc.DataDictionaryMapper.selectByCondition",
					record, pageBounds);
			

			this.setPageList(list);
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}


}
