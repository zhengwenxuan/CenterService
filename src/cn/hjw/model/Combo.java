package cn.hjw.model;

import java.util.Date;
import java.util.List;

public class Combo {
    private String objectid;

    private String code;

    private String setPinyin;

    private String name;

    private String company;

    private String sex;

    private Double discount;

    private Double amount;
    private Double amounted;    

    private String isactive;

    private String deleteFlg;

    private Date createDate;

    private String createrId;

    private Date updateDate;

    private String updaterId;

    private Date deleteDate;

    private String deleterId;
    
    
    private String isgroup;

    private String issurvey;

    private String comboType;

    private String comboImg;

    private String comboComment;

    
    private String appointNote;

    private String examNote;
    
    
    public String getAppointNote() {
		return appointNote;
	}

	public void setAppointNote(String appointNote) {
		this.appointNote = appointNote;
	}

	public String getExamNote() {
		return examNote;
	}

	public void setExamNote(String examNote) {
		this.examNote = examNote;
	}

	public String getIsgroup() {
		return isgroup;
	}

	public void setIsgroup(String isgroup) {
		this.isgroup = isgroup;
	}

	public String getIssurvey() {
		return issurvey;
	}

	public void setIssurvey(String issurvey) {
		this.issurvey = issurvey;
	}

	public String getComboType() {
		return comboType;
	}

	public void setComboType(String comboType) {
		this.comboType = comboType;
	}

	public String getComboImg() {
		return comboImg;
	}

	public void setComboImg(String comboImg) {
		this.comboImg = comboImg;
	}

	public String getComboComment() {
		return comboComment;
	}

	public void setComboComment(String comboComment) {
		this.comboComment = comboComment;
	}
	
	private List<ChargeItems> chargeItems;
	
	public List<ChargeItems> getChargeItems() {
		return chargeItems;
	}

	public void setChargeItems(List<ChargeItems> chargeItems) {
		this.chargeItems = chargeItems;
	}

	private List<ChargingitemCombo> options;
    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid == null ? null : objectid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSetPinyin() {
        return setPinyin;
    }

    public void setSetPinyin(String setPinyin) {
        this.setPinyin = setPinyin == null ? null : setPinyin.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive == null ? null : isactive.trim();
    }

    public String getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(String deleteFlg) {
        this.deleteFlg = deleteFlg == null ? null : deleteFlg.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId == null ? null : createrId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId == null ? null : updaterId.trim();
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(String deleterId) {
        this.deleterId = deleterId == null ? null : deleterId.trim();
    }

	public List<ChargingitemCombo> getOptions() {
		return options;
	}

	public void setOptions(List<ChargingitemCombo> options) {
		this.options = options;
	}

	public Double getAmounted() {
		return amounted;
	}

	public void setAmounted(Double amounted) {
		this.amounted = amounted;
	}
    
}