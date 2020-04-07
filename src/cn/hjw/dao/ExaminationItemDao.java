package cn.hjw.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.hjw.common.StringUtils;
import cn.hjw.dao.ifc.ExaminationItemMapper;
import cn.hjw.model.ExamItemRefanddang;
import cn.hjw.model.ExaminationItem;
import cn.hjw.model.ItemCategory;

public class ExaminationItemDao extends BaseDao implements
		ExaminationItemMapper {
	public ExaminationItem selectExaminationItems(String itemNum)
			throws Exception {
		try {
			return this.sqlSession.selectOne(
					"center.items.selectExaminationItems", itemNum);
		} catch (Exception ex) {
			throw ex;
		}

	}

	/**
	 * 指标异常类型
	 * 
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	public List<ItemCategory> selectAnomalyTypes(String itemId)
			throws Exception {
		try {
			return this.sqlSession.selectList(
					"center.items.selectAnomalyTypes", itemId);
		} catch (Exception ex) {
			throw ex;
		}

	}

	/**
	 * 异常类型知识分类
	 * 
	 * @param itemId
	 * @param anomalyType
	 * @return
	 * @throws Exception
	 */
	public List<ItemCategory> selectCategories(ItemCategory condtion)
			throws Exception {
		try {
			return this.sqlSession.selectList("center.items.selectCategories",
					condtion);
		} catch (Exception ex) {
			throw ex;
		}

	}

	/**
	 * 分类详情
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public List<ItemCategory> selectCategoryValues(String objectId)
			throws Exception {
		try {
			return this.sqlSession.selectList(
					"center.items.selectCategoryValues", objectId);
		} catch (Exception ex) {
			throw ex;
		}

	}

	@Override
	public int deleteByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ExaminationItem record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ExaminationItem record) throws Exception {
		try {
			int r = 0;
			r = this.sqlSession.insert(
					"cn.hjw.dao.ifc.ExaminationItemMapper.insertSelective",
					record);
			if (record.getItemCategory().equals("短文本型")
					|| record.getItemCategory().equals("长文本型")) {
				Map<String, Map<String, String>> dataMap = record
						.getDangReferMap();
				if (dataMap != null) {
					Map<String, String> dangMap = dataMap.get("D");
					Map<String, String> referMap = dataMap.get("R");
					insertRefanddang(dangMap, record, "D");
					insertRefanddang(referMap, record, "R");
				}
			}

			return r;
		} catch (Exception ex) {
			 this.sqlSession.rollback();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	private void insertRefanddang(Map<String, String> dataMap,
			ExaminationItem record, String flg) throws Exception {
		if (dataMap == null)
			return;
		Iterator<?> iter = dataMap.entrySet().iterator();
		ExamItemRefanddang examItemRefanddang = null;
		while (iter.hasNext()) {
			Entry<String, String> e = (Entry<String, String>) iter.next();
			String key = e.getKey();
			String value = e.getValue();
			examItemRefanddang = new ExamItemRefanddang();
			examItemRefanddang.setObjectid(StringUtils.getUUID());
			examItemRefanddang.setCreateDate(new Date());
			examItemRefanddang.setCreaterId(record.getCreaterId());
			examItemRefanddang.setExamItemId(record.getObjectId());
			examItemRefanddang.setValIndex(key);
			examItemRefanddang.setValInfo(value);
			examItemRefanddang.setIsRefordang(flg);
			this.sqlSession.insert(
					"cn.hjw.dao.ifc.ExamItemRefanddangMapper.insertSelective",
					examItemRefanddang);
		}
	}
	public List<ExamItemRefanddang> selectExamItemRefanddang(ExamItemRefanddang	condition){
	List<ExamItemRefanddang> examItemRefanddangList = this.sqlSession.selectList(
			"cn.hjw.dao.ifc.ExamItemRefanddangMapper.selectByCondition",
			condition);
	return examItemRefanddangList;
}
	@Override
	public ExaminationItem selectByPrimaryKey(String objectId) {
		try {
			ExaminationItem examinationItem = this.sqlSession.selectOne(
					"cn.hjw.dao.ifc.ExaminationItemMapper.selectByPrimaryKey",
					objectId);
			
			Map<String,Map<String,String>> dangReferMap=new HashMap<String,Map<String,String>>();
			Map<String,String> dangMap=new HashMap<String,String>();
			Map<String,String> referMap=new HashMap<String,String>();
			List<ExamItemRefanddang> examItemRefanddangList =null;
			ExamItemRefanddang	condition = new ExamItemRefanddang();
			condition.setExamItemId(objectId);
			condition.setIsRefordang("D");			
			examItemRefanddangList = this.selectExamItemRefanddang(condition);
			examinationItem.setDangList(examItemRefanddangList);
			for(ExamItemRefanddang e:examItemRefanddangList){				
				dangMap.put("D"+e.getValIndex(), e.getValInfo());
			}
			dangReferMap.put("D", dangMap);
			
			condition = new ExamItemRefanddang();
			condition.setExamItemId(objectId);
			condition.setIsRefordang("R");			
			 examItemRefanddangList = this.selectExamItemRefanddang(condition);
			 examinationItem.setRefList(examItemRefanddangList);
			for(ExamItemRefanddang e:examItemRefanddangList){
				referMap.put("R"+e.getValIndex(), e.getValInfo());
			}
			dangReferMap.put("R", referMap);
			
			
			examinationItem.setDangReferMap(dangReferMap);
			
		
			return examinationItem;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(ExaminationItem record)
			throws Exception {
		try {
			int r = this.sqlSession
					.update("cn.hjw.dao.ifc.ExaminationItemMapper.updateByPrimaryKeySelective",
							record);
			this.sqlSession
			.delete("cn.hjw.dao.ifc.ExamItemRefanddangMapper.deleteByExamItemId",
					record.getObjectId());
			if (record.getItemCategory().equals("短文本型")
					|| record.getItemCategory().equals("长文本型")) {
			
				Map<String, Map<String, String>> dataMap = record
						.getDangReferMap();
				if (dataMap != null) {
					Map<String, String> dangMap = dataMap.get("D");
					Map<String, String> referMap = dataMap.get("R");
					insertRefanddang(dangMap, record, "D");
					insertRefanddang(referMap, record, "R");
				}

			}
			

			return r;
		} catch (Exception ex) {
			 this.sqlSession.rollback();
			throw ex;
		}
	}

	@Override
	public int updateByPrimaryKey(ExaminationItem record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ExaminationItem> selectByCondition(ExaminationItem record,
			int page, int pageSize) {
		try {
			String sortString = "create_date.desc";
			PageBounds pageBounds = new PageBounds(page, pageSize,
					Order.formString(sortString));
			List<ExaminationItem> list = this.sqlSession.selectList(
					"cn.hjw.dao.ifc.ExaminationItemMapper.selectByCondition",
					record, pageBounds);
			for (ExaminationItem item : list) {
				if (item.getItemCategory().equals("短文本型")
						|| item.getItemCategory().equals("长文本型")) {
					ExamItemRefanddang condtion = new ExamItemRefanddang();
					condtion.setExamItemId(item.getObjectId());
					condtion.setIsRefordang("D");
					List<ExamItemRefanddang> sublist = this.sqlSession
							.selectList(
									"cn.hjw.dao.ifc.ExamItemRefanddangMapper.selectByCondition",
									condtion);
					item.setDangList(sublist);

					condtion = new ExamItemRefanddang();
					condtion.setExamItemId(item.getObjectId());
					condtion.setIsRefordang("R");
					sublist = this.sqlSession
							.selectList(
									"cn.hjw.dao.ifc.ExamItemRefanddangMapper.selectByCondition",
									condtion);
					item.setRefList(sublist);

				}
			}

			this.setPageList(list);
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int deleteByList(List<String> objectids) {
		try {
			int r = 0;

			r = this.sqlSession
					.delete("cn.hjw.dao.ifc.ChargingItemExamItemMapper.deleteByExaminationItem",
							objectids);
			if (r >= 0) {
				this.sqlSession
						.delete("cn.hjw.dao.ifc.ExaminationItemMapper.deleteByCollection",
								objectids);
			}

			return r;

		} catch (Exception ex) {
			this.sqlSession.rollback();
			throw ex;
		}
	}
}
