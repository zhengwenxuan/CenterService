package cn.hjw.jws.json;

import java.util.List;

public class ChargeItem {
	private String chargeItemId = "";
	private String chargeItemName = "";
	private String examDoctor = "";
	private String approver = "";
	private List<ExamItem> chargeItemValues;

	public String getChargeItemId() {
		return chargeItemId;
	}

	public void setChargeItemId(String chargeItemId) {
		this.chargeItemId = chargeItemId;
	}

	public String getChargeItemName() {
		return chargeItemName;
	}

	public void setChargeItemName(String chargeItemName) {
		this.chargeItemName = chargeItemName;
	}

	public List<ExamItem> getChargeItemValues() {
		return chargeItemValues;
	}

	public void setChargeItemValues(List<ExamItem> chargeItemValues) {
		this.chargeItemValues = chargeItemValues;
	}

	public String getExamDoctor() {
		return examDoctor;
	}

	public void setExamDoctor(String examDoctor) {
		this.examDoctor = examDoctor;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}
}
