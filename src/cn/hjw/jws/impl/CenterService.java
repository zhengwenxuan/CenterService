package cn.hjw.jws.impl;





import org.apache.axis2.AxisFault;

import cn.hjw.common.StringUtils;
import cn.hjw.jws.auth.DataAuth;
import cn.hjw.jws.impl.ifc.MainServiceIF;
import cn.hjw.util.BaseUtil;
import cn.hjw.util.WebserviceUtil;

public class CenterService extends BaseUtil implements MainServiceIF{
	private Object result = null;
	private WebserviceUtil util=null;
	public CenterService() {
		super(CenterService.class);	
		this.util=(WebserviceUtil)getObject(WebserviceUtil.class);		
	}
	/**
	 * 删除体检报告数据
	 * @return
	 */
	@Override
	public String deleteReportByExamNum(String jsonPara) throws AxisFault {	
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.deleteReportByExamNum();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return StringUtils.convertToJson(result);
	}
	/**
	 * 总检结论 for web
	 * 
	 * @return
	 
	public String getExamSummary(String jsonPara) throws AxisFault {	
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.getExamSummary();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return StringUtils.convertToJson(result);
	}*/
	/**
	 * 总检结论 for web
	 * 
	 * @return
	 */
	public String getExamInfo(String jsonPara) throws AxisFault {	
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.getExamInfo();			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return StringUtils.convertToJson(result);
	}
	
	/**
	 * 6.12	体检报告--总检结论
	 * @return
	 */
	@Override
	public String reportSummary(String jsonPara) throws AxisFault {	
		log.debug("[▼▼▼] cn.hjw.jws.impl.CenterService.reportSummary(String)");
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.getExamInfo_1();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		log.debug("[▲▲▲] cn.hjw.jws.impl.CenterService.reportSummary(String)");
		return StringUtils.convertToJson(result);
	}
	
/**
 * 体检报告报告详情
 */
	@Override
	public String reportDetail(String jsonPara) throws AxisFault {
		log.debug("[▼▼▼] cn.hjw.jws.impl.CenterService.reportDetail(String)");
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.getExamInfo_2();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		log.debug("[▲▲▲] cn.hjw.jws.impl.CenterService.reportDetail(String)");
		return StringUtils.convertToJson(result);
	}
	/**
	 * 体检报告--指标详情
	 */
	@Override
	public String itemDetail(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.itemDetail();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return StringUtils.convertToJson(result);
	}
	@Override
	public String comboList(String jsonPara) throws AxisFault {
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.packagesFilter();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}		
		return StringUtils.convertToJson(result);
	}
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
	/**
	 * 获得收费项目信息
	 * @return
	 */
	@Override
	public String getChargeItemInfo(String jsonPara) throws AxisFault {	
		try {		
			// 身份验证
			DataAuth.checkUserPwd();
			this.setData(jsonPara);
			result = util.getChargeItemInfo();
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
	
}
