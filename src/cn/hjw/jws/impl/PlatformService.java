package cn.hjw.jws.impl;



import org.apache.axis2.AxisFault;

import cn.hjw.common.StringUtils;
import cn.hjw.jws.auth.DataAuth;
import cn.hjw.jws.impl.ifc.PlatformServiceIF;
import cn.hjw.util.BaseUtil;
import cn.hjw.util.WebserviceUtil;

public class PlatformService extends BaseUtil implements PlatformServiceIF{
	private Object result = null;
	private WebserviceUtil util=null;
	public PlatformService() {
		super(PlatformService.class);
		this.util=(WebserviceUtil)getObject(WebserviceUtil.class);	
	}
///==============科室管理 start===================
	@Override
	public String departmentTypeList() throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();	
			result = util.departmentTypeList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}

	@Override
	public String departList(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.departList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}



	@Override
	public void setData(String jsonPara) {
		util.setParameterData(StringUtils.analysisJsonPara(jsonPara));
		
	}

	@Override
	public String insertDept(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.insertDept();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}

	@Override
	public String updateDept(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.updateDept();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}

	@Override
	public String deleteDept(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.deleteDept();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	///==============科室管理 end===================
	
	///==============收费项目管理 start===================	
	@Override
	public String chargeItemList(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.chargeItemList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String insertChargeItem(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.insertChargeItem();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String updateChargeItem(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.updateChargeItem();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String deleteChargeItem(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			//this.setData(jsonPara);
			util.setParameterData(jsonPara);
			result = util.deleteChargeItem();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	///==============收费项目管理 end===================
	
	///==============检查项目管理 start===================		
	@Override
	public String examinationItemList(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.examinationItemList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String insertExaminationItem(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.insertExaminationItem();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String updateExaminationItem(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.updateExaminationItem();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String deleteExaminationItem(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			//this.setData(jsonPara);
			util.setParameterData(jsonPara);
			result = util.deleteExaminationItem();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	///==============检查项目管理 end===================
	
	///==============套餐管理 start===================	
	@Override
	public String comboList(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.comboList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String insertCombo(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.insertCombo();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String updateCombo(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.updateCombo();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String deleteCombo(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			//this.setData(jsonPara);
			util.setParameterData(jsonPara);
			result = util.deleteCombo();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}	
	///==============套餐管理 end===================
	@Override
	public String getDataById(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.getDataById();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String dataDictionary(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.dataDictionary();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	
	///==============检查项目异常类型管理 start===================	
	@Override
	public String anomalyTypeList() throws AxisFault {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String anomalyCategoryList() throws AxisFault {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String anomalyContentList(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.anomalyContentList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String insertAnomalyContent(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.insertAnomalyContent();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String updateAnomalyContent(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			util.setParameterData(jsonPara);
			result = util.updateAnomalyContent();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String deleteAnomalyContent(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			//this.setData(jsonPara);
			util.setParameterData(jsonPara);
			result = util.deleteAnomalyContent();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	@Override
	public String checkAnomalyType(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			//util.setParameterData(jsonPara);
			result = util.checkAnomalyType();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	///==============检查项目异常类型管理 end===================
	
	///==============体检用户 begin===================
	@Override
	public String examUserInfoList(String jsonPara) throws AxisFault {
		// TODO Auto-generated method stub
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			//util.setParameterData(jsonPara);
			result = util.examUserInfoList();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
	
	///==============检查用户end===================
	
}
