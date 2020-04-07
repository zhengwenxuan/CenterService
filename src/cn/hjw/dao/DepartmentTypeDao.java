package cn.hjw.dao;

import java.util.List;
import java.util.Map;

import cn.hjw.dao.ifc.DepartmentTypeMapper;
import cn.hjw.model.DepartmentType;

public class DepartmentTypeDao extends BaseDao implements DepartmentTypeMapper{

	@Override
	public int deleteByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(DepartmentType record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(DepartmentType record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DepartmentType selectByPrimaryKey(String objectid) {
		try {			
			return this.sqlSession.selectOne("cn.hjw.dao.DepartmentTypeMapper.selectByPrimaryKey",
					objectid);
		} catch(Exception ex){		
			throw ex;			
		}
	}

	@Override
	public int updateByPrimaryKeySelective(DepartmentType record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(DepartmentType record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> selectItems() {
		try {			
			return this.sqlSession.selectList("cn.hjw.dao.DepartmentTypeMapper.selectItems");
		} catch(Exception ex){		
			throw ex;			
		}
	}

}
