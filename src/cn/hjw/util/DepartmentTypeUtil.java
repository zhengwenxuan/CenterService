package cn.hjw.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import cn.hjw.common.StringUtils;
import cn.hjw.dao.DepartmentTypeDao;
import cn.hjw.model.DepartmentType;

public class DepartmentTypeUtil extends BaseUtil {
	private DepartmentTypeDao dao = null;

	protected DepartmentTypeUtil() {
		super(DepartmentTypeUtil.class);
		this.dao = (DepartmentTypeDao) getObject(DepartmentTypeDao.class);
	}

	public List<Map<String, Object>> selectItems() throws Exception {
		return this.dao.selectItems();
	}

	public List<DepartmentType> selectDepartmentType() throws Exception {
		List<Map<String, Object>> list = selectItems();
		List<DepartmentType> rs = null;
		if (list != null && !list.isEmpty()) {
			Type type = new TypeToken<ArrayList<DepartmentType>>() {
			}.getType();
			rs = gson.fromJson(StringUtils.convertToJson(list), type);
		}

		return rs;
	}

}
