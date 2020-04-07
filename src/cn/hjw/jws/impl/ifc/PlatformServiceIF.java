package cn.hjw.jws.impl.ifc;

import org.apache.axis2.AxisFault;



public interface PlatformServiceIF {
	/**
	 * 科室类型获取
	 * 
	 * @param jsonPara {"userId":"13201823316","examNum":"12345678","centerCode":"12345678"}
	 * @return
	 * @throws AxisFault
	 */
	public String departmentTypeList() throws AxisFault;
	public String departList(String jsonPara) throws AxisFault;
	public String insertDept(String jsonPara) throws AxisFault;
	public String updateDept(String jsonPara) throws AxisFault;
	public String deleteDept(String jsonPara) throws AxisFault;
	public String getDataById(String jsonPara) throws AxisFault;
	
	public String chargeItemList(String jsonPara) throws AxisFault;
	public String insertChargeItem(String jsonPara) throws AxisFault;
	public String updateChargeItem(String jsonPara) throws AxisFault;
	public String deleteChargeItem(String jsonPara) throws AxisFault;
	
	public String examinationItemList(String jsonPara) throws AxisFault;
	public String insertExaminationItem(String jsonPara) throws AxisFault;
	public String updateExaminationItem(String jsonPara) throws AxisFault;
	public String deleteExaminationItem(String jsonPara) throws AxisFault;
	//public String examItemTypes(String jsonPara) throws AxisFault;
	
	public String comboList(String jsonPara) throws AxisFault;
	public String insertCombo(String jsonPara) throws AxisFault;
	public String updateCombo(String jsonPara) throws AxisFault;
	public String deleteCombo(String jsonPara) throws AxisFault;
	
	public String dataDictionary(String jsonPara) throws AxisFault;
	
	public String anomalyTypeList() throws AxisFault;
	public String anomalyCategoryList() throws AxisFault;
	public String anomalyContentList(String jsonPara) throws AxisFault;
	public String insertAnomalyContent(String jsonPara) throws AxisFault;
	public String updateAnomalyContent(String jsonPara) throws AxisFault;
	public String deleteAnomalyContent(String jsonPara) throws AxisFault;
	public String checkAnomalyType(String jsonPara) throws AxisFault;
	public void setData(String jsonPara);
	
	public String examUserInfoList(String jsonPara)throws AxisFault;
	
}
