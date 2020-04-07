package cn.hjw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.hjw.bean.Condition;
import cn.hjw.common.StringUtils;
import cn.hjw.dao.ifc.ComboMapper;
import cn.hjw.model.ChargeItems;
import cn.hjw.model.ChargingitemCombo;
import cn.hjw.model.Combo;


public class ComboDao extends BaseDao implements ComboMapper{

	@Override
	public int deleteByPrimaryKey(String objectid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Combo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Combo record) {
		try {
			int r = this.sqlSession.insert(
					"cn.hjw.dao.ifc.ComboMapper.insertSelective", record);
			if (r > 0) {
				List<ChargingitemCombo> options = record.getOptions();
				if (options != null && !options.isEmpty()) {
					for (ChargingitemCombo option : options) {
						option.setObjectid(StringUtils.getUUID());
						//option.setChargingItemId(record.getObjectid());
						option.setComboId(record.getObjectid());
						this.sqlSession
								.insert("cn.hjw.dao.ifc.ChargingitemComboMapper.insertSelective",
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
	public Combo selectByPrimaryKey(String objectid) {
		try{
			Combo combo=this.sqlSession
					.selectOne("cn.hjw.dao.ifc.ComboMapper.selectByPrimaryKey",
							objectid);
			List<?> o=selectChargeItemByCombo(objectid);
			if(o!=null){
				combo.setOptions((List<ChargingitemCombo>)o);
			}
			
			return  combo;
		}catch (Exception ex) {			
			throw ex;
		}
	}
	@SuppressWarnings("unchecked")
	public Combo selectByPrimaryKey(String objectid,String itemCategory) {
		try{
			Combo combo=this.sqlSession
					.selectOne("cn.hjw.dao.ifc.ComboMapper.selectByPrimaryKey",
							objectid);
			List<?> o=selectChargeItemByCombo(objectid,itemCategory);
			if(o!=null){
				combo.setChargeItems((List<ChargeItems>)o);
			}
			
			return  combo;
		}catch (Exception ex) {			
			throw ex;
		}
	}
	public List<ChargeItems> selectChargeItemByCombo(String comboId,String itemCategory) {
		try{
			Map<String,String> para=new HashMap<String,String>();
			para.put("comboId", comboId);
			para.put("itemCategory", itemCategory);
			List<ChargeItems> chargeItems=this.sqlSession
					.selectList("cn.hjw.dao.ifc.ChargeItemsMapper.selectChargeItemByCombo",
							para);
			return  chargeItems;
		}catch (Exception ex) {		
			throw ex;
		}
	}
	
	@Override
	public List<ChargingitemCombo> selectChargeItemByCombo(String comboId) {
		try{
			List<ChargingitemCombo> chargeItems=this.sqlSession
					.selectList("cn.hjw.dao.ifc.ChargingitemComboMapper.selectChargeItemByCombo",
							comboId);
			return  chargeItems;
		}catch (Exception ex) {		
			throw ex;
		}
	}
	@Override
	public int updateByPrimaryKeySelective(Combo record) {
		try {
			int rr = -1;
			int r = this.sqlSession
					.update("cn.hjw.dao.ifc.ComboMapper.updateByPrimaryKeySelective",
							record);
			rr = r;
			if (r > 0) {
				r = this.sqlSession
						.delete("cn.hjw.dao.ifc.ChargingitemComboMapper.deleteByComboId",
								record.getObjectid());
				if (r >= 0) {
					List<ChargingitemCombo> options = record.getOptions();
					if (options != null && !options.isEmpty()) {
						for (ChargingitemCombo option : options) {
							option.setObjectid(StringUtils.getUUID());						
							option.setComboId(record.getObjectid());
							this.sqlSession
									.insert("cn.hjw.dao.ifc.ChargingitemComboMapper.insertSelective",
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
	public int updateByPrimaryKey(Combo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Combo> selectByCondition(Condition record, int page, int pageSize) {
		try {
			String sortString = "create_date.desc";
			PageBounds pageBounds = new PageBounds(page, pageSize,
					Order.formString(sortString));
			List<Combo> list = this.sqlSession.selectList(
					"cn.hjw.dao.ifc.ComboMapper.selectByCondition",
					record, pageBounds);
			this.setPageList(list);
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}
	public List<Combo> selectByCondition(Map<String,Object> condition, String sortString,int page, int pageSize) {
		try {
			PageBounds pageBounds =null;
		if(sortString!=null&&sortString.length()>0){
			pageBounds = new PageBounds(page, pageSize,
					Order.formString(sortString));
		}
		else{
			pageBounds = new PageBounds(page, pageSize);
		}
			 
			List<Combo> list = this.sqlSession.selectList(
					"cn.hjw.dao.ifc.ComboMapper.selectByCondition2",
					condition, pageBounds);
			this.setPageList(list);
			return list;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Override
	public List<Combo> selectByCondition(Combo record, int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByList(List<String> objectids) {
		try {
			int r = 0;

			r = this.sqlSession
					.delete("cn.hjw.dao.ifc.ChargingitemComboMapper.deleteByComboItems",
							objectids);
			if (r >= 0) {
				this.sqlSession.delete(
						"cn.hjw.dao.ifc.ComboMapper.deleteByCollection",
						objectids);
			}

			return r;

		} catch (Exception ex) {
			this.sqlSession.rollback();
			throw ex;
		}
	}

}
