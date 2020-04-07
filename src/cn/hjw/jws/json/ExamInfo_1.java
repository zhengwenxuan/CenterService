package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 体检报告--总检结论 json bean
 * @author feng
 *
 */
public class ExamInfo_1 extends BaseJson {
	private List<ChargeItem> anomalyData;
	//private String examSummary = "";
	//private String examSuggest = "";
	private List<Map<String,String>> resultValues=new ArrayList<Map<String,String>>();
	

	

	public List<Map<String, String>> getResultValues() {
		return resultValues;
	}
	public void setResultValues(List<Map<String, String>> resultValues) {
		this.resultValues = resultValues;
	}

	
	public List<ChargeItem> getAnomalyData() {
		return anomalyData;
	}

	public void setAnomalyData(List<ChargeItem> anomalyData) {
		this.anomalyData = anomalyData;
	}

}
