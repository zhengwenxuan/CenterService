package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;

import cn.hjw.model.ItemCategory;

public class AnomalyContentInfo  extends BaseJson {
	private List<ItemCategory> datas=new ArrayList<ItemCategory>();
	private ItemCategory data;
	public List<ItemCategory> getDatas() {
		return datas;
	}
	public void setDatas(List<ItemCategory> datas) {
		this.datas = datas;
	}
	public ItemCategory getData() {
		return data;
	}
	public void setData(ItemCategory data) {
		this.data = data;
	}
	
}
