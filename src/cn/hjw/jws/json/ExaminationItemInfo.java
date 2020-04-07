package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;

import cn.hjw.model.ExaminationItem;

public class ExaminationItemInfo extends BaseJson {
	private List<ExaminationItem> datas=new ArrayList<ExaminationItem>();
private ExaminationItem data=null;

	public ExaminationItem getData() {
	return data;
}

public void setData(ExaminationItem data) {
	this.data = data;
}

	public List<ExaminationItem> getDatas() {
		return datas;
	}

	public void setDatas(List<ExaminationItem> datas) {
		this.datas = datas;
	}
	
}
