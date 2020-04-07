package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;

import cn.hjw.model.Combo;


public class ComboInfo extends BaseJson {
	private List<Combo> datas=new ArrayList<Combo>();
private Combo data;

	public Combo getData() {
	return data;
}

public void setData(Combo data) {
	this.data = data;
}

	public List<Combo> getDatas() {
		return datas;
	}

	public void setDatas(List<Combo> datas) {
		this.datas = datas;
	}
	
}
