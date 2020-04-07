package cn.hjw.jws.json;

import java.util.ArrayList;
import java.util.List;

import cn.hjw.model.Department;

public class DepartmentInfo extends BaseJson {
	private List<Department> datas=new ArrayList<Department>();
	private Department data;
	public List<Department> getDatas() {
		return datas;
	}

	public void setDatas(List<Department> datas) {
		this.datas = datas;
	}

	public Department getData() {
		return data;
	}

	public void setData(Department data) {
		this.data = data;
	}

}
