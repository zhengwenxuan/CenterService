package cn.hjw.jws.impl.ifc;

import org.apache.axis2.AxisFault;

public interface MainServiceIF {
	/**
	 * 体检报告总检结论
	 * 
	 * @param jsonPara {"userId":"13201823316","examNum":"12345678","centerCode":"12345678"}
	 * @return
	 * @throws AxisFault
	 */
	public String reportSummary(String jsonPara) throws AxisFault;

	/**
	 * 体检报告报告详情
	 * 
	 * @param jsonPara {"userId":"13201823316","examNum":"12345678","centerCode":"12345678"}
	 * @return
	 * @throws AxisFault
	 */
	public String reportDetail(String jsonPara) throws AxisFault;
	/**
	 * 体检报告--指标详情
	 * 
	 * @param jsonPara {"userId":"13201823316","itemId":"12345678","centerCode":"12345678"}
	 * @return
	 * @throws AxisFault
	 */
	public String itemDetail(String jsonPara) throws AxisFault;	
	/**
	 * 体检套餐筛选
	 * @param jsonPara
	 * @return
	 * @throws AxisFault
	 */
	public String comboList(String jsonPara) throws AxisFault;
	public String deleteReportByExamNum(String jsonPara) throws AxisFault;
	public String getChargeItemInfo(String jsonPara) throws AxisFault;
	public String getDataById(String jsonPara) throws AxisFault;
	public void setData(String jsonPara);
}
