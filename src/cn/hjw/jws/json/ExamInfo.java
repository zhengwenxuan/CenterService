package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExamInfo extends BaseJson {
	private List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}
	
	
}
