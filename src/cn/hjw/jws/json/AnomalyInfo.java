package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;


import cn.hjw.model.ItemCategory_2;

public class AnomalyInfo extends BaseJson{
	private List<ItemCategory_2> datas=new ArrayList<ItemCategory_2>();
private ItemCategory_2 data;
public List<ItemCategory_2> getDatas() {
	return datas;
}
public void setDatas(List<ItemCategory_2> datas) {
	this.datas = datas;
}
public ItemCategory_2 getData() {
	return data;
}
public void setData(ItemCategory_2 data) {
	this.data = data;
}

}
