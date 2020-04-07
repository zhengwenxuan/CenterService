package cn.hjw.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.hjw.common.StringUtils;
import cn.hjw.dao.ifc.ItemCategoryMapper;
import cn.hjw.model.ExaminationItem;
import cn.hjw.model.ItemCategory;
import cn.hjw.model.ItemCategoryContent;
import cn.hjw.model.ItemCategory_2;

public class AnomalyContentDao extends BaseDao implements ItemCategoryMapper{

	@Override
	public int deleteByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteByList(List<String> objectids) {
		try {
			int r = 0;

			r = this.sqlSession
					.delete("cn.hjw.dao.ifc.ItemCategoryContentMapper.deleteByCategorys",
							objectids);
			if (r >= 0) {
				this.sqlSession.delete(
						"cn.hjw.dao.ifc.ItemCategoryMapper.deleteByCollection",
						objectids);
			}

			return r;

		} catch (Exception ex) {
			this.sqlSession.rollback();
			throw ex;
		}
	}
	@Override
	public int insert(ItemCategory_2 record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ItemCategory_2 record) {
		try {
			int r = this.sqlSession.insert(
					"cn.hjw.dao.ifc.ItemCategoryMapper.insertSelective", record);
			if (r > 0) {
				List<ItemCategoryContent>  options = record.getDatas();
				if (options != null && !options.isEmpty()) {
					for (ItemCategoryContent option : options) {
						option.setObjectid(StringUtils.getUUID());
						option.setCategoryId(record.getObjectid());
						this.sqlSession
								.insert("cn.hjw.dao.ifc.ItemCategoryContentMapper.insertSelective",
										option);
					}
				}
			}
			return r;
		} catch (Exception ex) {
			//this.sqlSession.rollback();
			throw ex;
		}
	}
	public List<ItemCategoryContent> selectByCategory(String objectid) {
		try{
			List<ItemCategoryContent> chargeItems=this.sqlSession
					.selectList("cn.hjw.dao.ifc.ItemCategoryContentMapper.selectByCategory",
							objectid);
			return  chargeItems;
		}catch (Exception ex) {		
			throw ex;
		}
	}
	
	public ItemCategory_2 selectByPrimaryKey(Map<?, ?> condtion) {
		try{
			ItemCategory_2 chargeItems=this.sqlSession
					.selectOne("cn.hjw.dao.ifc.ItemCategoryMapper.selectByPrimaryKey",
							condtion);
			if(chargeItems==null){
				ExaminationItemDao examinationItemDao=new ExaminationItemDao();
				ExaminationItem examinationItem=examinationItemDao.selectByPrimaryKey((String)condtion.get("itemId"));
				chargeItems =new ItemCategory_2();
				chargeItems.setItemId(examinationItem.getObjectId());
				chargeItems.setItemNum(examinationItem.getItemNum());
				chargeItems.setItemName(examinationItem.getItemName());
			}
			else{
				List<ItemCategoryContent> os=selectByCategory(chargeItems.getObjectid());
				if(os!=null){
					chargeItems.setDatas(os);
				}
			}
			
			
			return  chargeItems;
		}catch (Exception ex) {			
			throw ex;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(ItemCategory_2 record) {
		try {
			int rr = -1;
			int r = this.sqlSession
					.update("cn.hjw.dao.ifc.ItemCategoryMapper.updateByPrimaryKeySelective",
							record);
			rr = r;
			if (r > 0) {
				r = this.sqlSession
						.delete("cn.hjw.dao.ifc.ItemCategoryContentMapper.deleteByCategory",
								record.getObjectid());
				if (r >= 0) {
					List<ItemCategoryContent> options = record.getDatas();					
					if (options != null && !options.isEmpty()) {
						for (ItemCategoryContent option : options) {
							option.setObjectid(StringUtils.getUUID());
							option.setCategoryId(record.getObjectid());
							this.sqlSession
									.insert("cn.hjw.dao.ifc.ItemCategoryContentMapper.insertSelective",
											option);
						}
					}
				}
			}

			return rr;
		} catch (Exception ex) {
			this.sqlSession.rollback();
			throw ex;
		}
	}

	@Override
	public int updateByPrimaryKey(ItemCategory_2 record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItemCategory> selectByCondition(ItemCategory record, int page,
			int pageSize) {
		try {
			String sortString = "item_num.asc,anomaly_type.asc";			
			PageBounds pageBounds = new PageBounds(page, pageSize,
					Order.formString(sortString));			
			List<ItemCategory> list = this.sqlSession.selectList(
					"cn.hjw.dao.ifc.ItemCategoryMapper.selectByCondition", record,
					pageBounds);
			this.setPageList(list);
			return list; 
		} catch (Exception ex) {
			throw ex;
		}
	}
	@Override
	public ItemCategory_2 selectByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String checkAnomalyType(Map<String, Object> condtion) {
		try{
			List<ItemCategory_2> chargeItems=this.sqlSession
					.selectList("cn.hjw.dao.ifc.ItemCategoryMapper.checkAnomalyType",
							condtion);
			if(chargeItems==null||chargeItems.isEmpty()){
				return null;
			}
			else{
				return chargeItems.get(0).getObjectid();
			}
			
			
			
		}catch (Exception ex) {			
			throw ex;
		}
	}

	

}
