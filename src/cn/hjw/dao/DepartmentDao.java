package cn.hjw.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.gson.reflect.TypeToken;

import cn.hjw.common.StringUtils;
import cn.hjw.dao.ifc.DepartmentMapper;
import cn.hjw.model.Department;

public class DepartmentDao extends BaseDao implements DepartmentMapper {

	@Override
	public int deleteByPrimaryKey(String objectid) {
		try {
			return this.sqlSession.delete(
					"cn.hjw.dao.DepartmentMapper.deleteByPrimaryKey", objectid);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int insert(Department record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Department record) {
		try {
			return this.sqlSession.insert(
					"cn.hjw.dao.DepartmentMapper.insertSelective", record);
		} catch (Exception ex) {
			throw ex;
		}

	}

	@Override
	public Department selectByPrimaryKey(String objectid) {
		try {
			return this.sqlSession.selectOne(
					"cn.hjw.dao.DepartmentMapper.selectByPrimaryKey", objectid);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		try {
			return this.sqlSession.update(
					"cn.hjw.dao.DepartmentMapper.updateByPrimaryKeySelective",
					record);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> selectByCondition(Department record,
			int page, int pageSize) {
		try {
			String sortString = "create_date.desc";
			PageBounds pageBounds = new PageBounds(page, pageSize,
					Order.formString(sortString));
			List<Map<String, Object>> list = this.sqlSession.selectList(
					"cn.hjw.dao.DepartmentMapper.selectByCondition", record,
					pageBounds);
			this.setPageList(list);
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Department> selectByCondition2(Department record, int page,
			int pageSize) {
		List<Map<String, Object>> list = selectByCondition(record, page,
				pageSize);
		List<Department> rs = null;
		if (list != null && !list.isEmpty()) {
			Type type = new TypeToken<ArrayList<Department>>() {
			}.getType();
			rs = StringUtils.gson.fromJson(StringUtils.convertToJson(list),
					type);
		}

		return rs;
	}

	@Override
	public int deleteByCollection(List<String> objectids) {
		try {
			int r = this.sqlSession
					.delete("cn.hjw.dao.DepartmentMapper.deleteByCollection",
							objectids);
			return r;

		} catch (Exception ex) {
			this.sqlSession.rollback();
			throw ex;
		}
	}

}
