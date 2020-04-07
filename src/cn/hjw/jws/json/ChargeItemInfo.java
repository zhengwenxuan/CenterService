package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;

import cn.hjw.model.ChargeItems;


public class ChargeItemInfo extends BaseJson {
	private ChargeItems data=null;
	private List<ChargeItems> datas=new ArrayList<ChargeItems>();

	public List<ChargeItems> getDatas() {
		return datas;
	}

	public void setDatas(List<ChargeItems> datas) {
		this.datas = datas;
	}

	public ChargeItems getData() {
		return data;
	}

	public void setData(ChargeItems data) {
		this.data = data;
	}
	
}
