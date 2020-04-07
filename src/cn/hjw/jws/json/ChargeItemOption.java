package cn.hjw.jws.json;

import java.util.List;

import cn.hjw.model.ChargeItems;
import cn.hjw.model.ChargingItemExamItem;

public class ChargeItemOption {
	private ChargeItems chargeItem ;
	private List<ChargingItemExamItem> options;
	public ChargeItems getChargeItem() {
		return chargeItem;
	}
	public void setChargeItem(ChargeItems chargeItem) {
		this.chargeItem = chargeItem;
	}
	public List<ChargingItemExamItem> getOptions() {
		return options;
	}
	public void setOptions(List<ChargingItemExamItem> options) {
		this.options = options;
	}
	
}
