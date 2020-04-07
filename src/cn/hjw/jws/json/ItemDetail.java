package cn.hjw.jws.json;

import java.util.List;

public class ItemDetail extends BaseJson {
private String refImg="";
private String itemName="";
private String depName="";
private String abbr="";
private String itemDesc="";
private List<ErrorType> anomalyTypes;
public String getRefImg() {
	return refImg;
}
public void setRefImg(String refImg) {
	this.refImg = refImg;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getDepName() {
	return depName;
}
public void setDepName(String depName) {
	this.depName = depName;
}
public String getAbbr() {
	return abbr;
}
public void setAbbr(String abbr) {
	this.abbr = abbr;
}
public String getItemDesc() {
	return itemDesc;
}
public void setItemDesc(String itemDesc) {
	this.itemDesc = itemDesc;
}
public List<ErrorType> getAnomalyTypes() {
	return anomalyTypes;
}
public void setAnomalyTypes(List<ErrorType> anomalyTypes) {
	this.anomalyTypes = anomalyTypes;
}

}
