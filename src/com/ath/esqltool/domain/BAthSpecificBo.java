package com.ath.esqltool.domain;

public class BAthSpecificBo implements BAthOrchestable {

	private String id;
	private String name;
	private String inputMq;
	private String type; 
	
	private Boolean passthrough;

	private String bankOrg;
	private String codService;
	private String bankId;
	
	public BAthSpecificBo() {
		passthrough = true;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInputMq() {
		return inputMq;
	}

	public void setInputMq(String inputMq) {
		this.inputMq = inputMq;
	}

	public String getType() {
		return "Specific Passthrough";
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLongDescription() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(getType());
		buffer.append("|");
		buffer.append(getName());
		buffer.append("_");
		buffer.append(getBankOrg());
		//CNTL|"+ combocntls.getText()
		return buffer.toString();
	}


	public String getBankOrg() {
		return bankOrg;
	}

	public void setBankOrg(String bankOrg) {
		this.bankOrg = bankOrg;
	}

	public String getCodService() {
		return codService;
	}

	public void setCodService(String codService) {
		this.codService = codService;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Boolean getPassthrough() {
		return passthrough;
	}

	public void setPassthrough(Boolean passthrough) {
		this.passthrough = passthrough;
	}

}
