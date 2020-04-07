package cn.hjw.jws.json;

import java.util.List;

public class ErrorType {
private String typeName="";
private List<Category> categories;
public String getTypeName() {
	return typeName;
}
public void setTypeName(String typeName) {
	this.typeName = typeName;
}
public List<Category> getCategories() {
	return categories;
}
public void setCategories(List<Category> categories) {
	this.categories = categories;
}

}
