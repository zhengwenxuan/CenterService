package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;


public class DepartmentType extends BaseJson {
	private List<DepartmentTypeOption> datas=new ArrayList<DepartmentTypeOption>();

	public List<DepartmentTypeOption> getDatas() {
		return datas;
	}

	public void setDatas(List<DepartmentTypeOption> datas) {
		this.datas = datas;
	}
	
}
