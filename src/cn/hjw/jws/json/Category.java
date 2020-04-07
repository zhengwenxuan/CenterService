package cn.hjw.jws.json;

import java.util.List;

public class Category {
private String categoryIcon="";
private String categoryName="";
private List<CategoryValue> categoryValues;
public String getCategoryIcon() {
	return categoryIcon;
}
public void setCategoryIcon(String categoryIcon) {
	this.categoryIcon = categoryIcon;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public List<CategoryValue> getCategoryValues() {
	return categoryValues;
}
public void setCategoryValues(List<CategoryValue> categoryValues) {
	this.categoryValues = categoryValues;
}

}
