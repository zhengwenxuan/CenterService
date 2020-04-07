package cn.hjw.jws.json;

import java.util.List;

public class DeptInfo  {
	private List<ChargeItem> depValues;
	private String depSummary = "";
	private String depId = "";
	private String depName = "";
	private String  deptType = "";
	public List<ChargeItem> getDepValues() {
		return depValues;
	}
	public void setDepValues(List<ChargeItem> depValues) {
		this.depValues = depValues;
	}
	public String getDepSummary() {
		return depSummary;
	}
	public void setDepSummary(String depSummary) {
		this.depSummary = depSummary;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDeptType() {
		return deptType;
	}
	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}
	
}
