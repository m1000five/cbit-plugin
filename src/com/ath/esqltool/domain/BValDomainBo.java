package com.ath.esqltool.domain;

public class BValDomainBo { 

	private Integer value_domain_id;
	private Integer domain_id;
	private String val_domain;
	private String dat_from;
	private String dat_to;
	private String nam_value_domain;
	private String des_value_domain; 
	

	public BValDomainBo() {
		super();
	}

	public BValDomainBo(Integer value_domain_id, Integer domain_id, String val_domain, String dat_from, String dat_to,
			String nam_value_domain, String des_value_domain) {
		super();
		this.value_domain_id = value_domain_id;
		this.domain_id = domain_id;
		this.val_domain = val_domain;
		this.dat_from = dat_from;
		this.dat_to = dat_to;
		this.nam_value_domain = nam_value_domain;
		this.des_value_domain = des_value_domain;
	}

	public Integer getValue_domain_id() {
		return value_domain_id;
	}

	public void setValue_domain_id(Integer value_domain_id) {
		this.value_domain_id = value_domain_id;
	}

	public Integer getDomain_id() {
		return domain_id;
	}

	public void setDomain_id(Integer domain_id) {
		this.domain_id = domain_id;
	}

	public String getVal_domain() {
		return val_domain;
	}

	public void setVal_domain(String val_domain) {
		this.val_domain = val_domain;
	}

	public String getDat_from() {
		return dat_from;
	}

	public void setDat_from(String dat_from) {
		this.dat_from = dat_from;
	}

	public String getDat_to() {
		return dat_to;
	}

	public void setDat_to(String dat_to) {
		this.dat_to = dat_to;
	}

	public String getNam_value_domain() {
		return nam_value_domain;
	}

	public void setNam_value_domain(String nam_value_domain) {
		this.nam_value_domain = nam_value_domain;
	}

	public String getDes_value_domain() {
		return des_value_domain;
	}

	public void setDes_value_domain(String des_value_domain) {
		this.des_value_domain = des_value_domain;
	}

}
