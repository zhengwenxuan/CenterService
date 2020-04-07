package cn.hjw.model;

public class ChargingitemCombo {
	private String objectid;

	private String chargingItemId;

	private String comboId;

	private Double discount;

	private Double amount;
	private String chargingItemCode;

	private String chargingItemName;

	private String deptName;

	private Double chargingItemAmount;

	public String getChargingItemCode() {
		return chargingItemCode;
	}

	public void setChargingItemCode(String chargingItemCode) {
		this.chargingItemCode = chargingItemCode;
	}

	public String getChargingItemName() {
		return chargingItemName;
	}

	public void setChargingItemName(String chargingItemName) {
		this.chargingItemName = chargingItemName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Double getChargingItemAmount() {
		return chargingItemAmount;
	}

	public void setChargingItemAmount(Double chargingItemAmount) {
		this.chargingItemAmount = chargingItemAmount;
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid == null ? null : objectid.trim();
	}

	public String getChargingItemId() {
		return chargingItemId;
	}

	public void setChargingItemId(String chargingItemId) {
		this.chargingItemId = chargingItemId == null ? null : chargingItemId
				.trim();
	}

	public String getComboId() {
		return comboId;
	}

	public void setComboId(String comboId) {
		this.comboId = comboId == null ? null : comboId.trim();
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}