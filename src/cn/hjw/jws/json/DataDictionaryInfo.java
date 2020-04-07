package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;

import cn.hjw.model.DataDictionary;


public class DataDictionaryInfo extends BaseJson {
	private List<DataDictionary> datas=new ArrayList<DataDictionary>();

	public List<DataDictionary> getDatas() {
		return datas;
	}

	public void setDatas(List<DataDictionary> datas) {
		this.datas = datas;
	}
	
}
