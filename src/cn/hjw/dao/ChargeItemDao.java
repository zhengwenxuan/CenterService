package cn.hjw.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.hjw.common.StringUtils;
import cn.hjw.dao.ifc.ChargeItemsMapper;
import cn.hjw.model.ChargeItems;
import cn.hjw.model.ChargingItemExamItem;
import cn.hjw.model.ExaminationItem;

public class ChargeItemDao extends BaseDao implements ChargeItemsMapper {

	@Override
	public int deleteByPrimaryKey(String objectid) {
		try {

			int r = this.sqlSession
					.delete("cn.hjw.dao.ifc.ChargingItemExamItemMapper.deleteByChargeItem",
							objectid);
			if (r >= 0) {
				this.sqlSession.delete(
						"cn.hjw.dao.ifc.ChargeItemsMapper.deleteByPrimaryKey",
						objectid);
			}

			return r;

		} catch (Exception ex) {
			this.sqlSession.rollback();
			throw ex;
		}

	}

	@Override
	public int deleteByList(List<String> objectids) {
		try {
			int r = 0;

			r = this.sqlSession
					.delete("cn.hjw.dao.ifc.ChargingItemExamItemMapper.deleteByChargeItems",
							objectids);
			if (r >= 0) {
				this.sqlSession.delete(
						"cn.hjw.dao.ifc.ChargeItemsMapper.deleteByCollection",
						objectids);
			}

			return r;

		} catch (Exception ex) {
			this.sqlSession.rollback();
			throw ex;
		}
	}

	@Override
	public int insert(ChargeItems record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ChargeItems record) {
		try {
			int r = this.sqlSession.insert(
					"cn.hjw.dao.ifc.ChargeItemsMapper.insertSelective", record);
			if (r > 0) {
				List<ChargingItemExamItem> options = record.getOptions();
				if (options != null && !options.isEmpty()) {
					for (ChargingItemExamItem option : options) {
						option.setObjectid(StringUtils.getUUID());
						option.setChargingItemId(record.getObjectid());
						this.sqlSession
								.insert("cn.hjw.dao.ifc.ChargingItemExamItemMapper.insertSelective",
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

	@SuppressWarnings("unchecked")
	@Override
	public ChargeItems selectByPrimaryKey(String objectid) {
		try{
			ChargeItems chargeItems=this.sqlSession
					.selectOne("cn.hjw.dao.ifc.ChargeItemsMapper.selectByPrimaryKey",
							objectid);
			List<?> o=selectExamItemByCharge(objectid);
			if(chargeItems!=null&&o!=null){
				chargeItems.setExamItems((List<ExaminationItem>)o);
			}
			
			return  chargeItems;
		}catch (Exception ex) {			
			throw ex;
		}
	}
	@Override
	public List<ExaminationItem> selectExamItemByCharge(String chargingItemId) {
		try{
			List<ExaminationItem> chargeItems=this.sqlSession
					.selectList("cn.hjw.dao.ifc.ChargingItemExamItemMapper.selectExamItemByCharge",
							chargingItemId);
			return  chargeItems;
		}catch (Exception ex) {		
			throw ex;
		}
	}
	@Override
	public int updateByPrimaryKeySelective(ChargeItems record) {
		try {
			int rr = -1;
			int r = this.sqlSession
					.update("cn.hjw.dao.ifc.ChargeItemsMapper.updateByPrimaryKeySelective",
							record);
			rr = r;
			if (r > 0) {
				r = this.sqlSession
						.delete("cn.hjw.dao.ifc.ChargingItemExamItemMapper.deleteByChargeItem",
								record.getObjectid());
				if (r >= 0) {
					List<ChargingItemExamItem> options = record.getOptions();
					if (options != null && !options.isEmpty()) {
						for (ChargingItemExamItem option : options) {
							option.setObjectid(StringUtils.getUUID());
							option.setChargingItemId(record.getObjectid());
							this.sqlSession
									.insert("cn.hjw.dao.ifc.ChargingItemExamItemMapper.insertSelective",
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
	public int updateByPrimaryKey(ChargeItems record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChargeItems> selectByCondition(ChargeItems record, int page,
			int pageSize) {
		try {
			String sortString = "create_date.desc";
			PageBounds pageBounds = new PageBounds(page, pageSize,
					Order.formString(sortString));
			List<ChargeItems> list = this.sqlSession.selectList(
					"cn.hjw.dao.ifc.ChargeItemsMapper.selectByCondition",
					record, pageBounds);
			this.setPageList(list);
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}

	

}
