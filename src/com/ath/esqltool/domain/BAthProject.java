package com.ath.esqltool.domain;

import com.ath.esqltool.util.BUtil;

public abstract class BAthProject {

	protected String currentDir;

	protected String name;

	protected String ideRequirement;
	
	protected String srvName;

	protected String oprName;
	
	private String orgName;
	
	private String domain;
	
	private String channel;
	
	private String bankId;
	
	protected String errorMessage;
	
	private String wsdlName;
	
	private String wsdlPort;
	
	private String wsdlBinding;
	
	private String wsdlSvcPort;
	
	
	public abstract boolean validate();

	public abstract String getName();

	public abstract String getPackage();


	public String getProjectPath() {
		return getCurrentDir() + "/" + getName() + "/";
	}

	public String getPath() {
		return getProjectPath() + "/" + getPackage() + "/";
	}

	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String currentDir) {
		this.currentDir = currentDir;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getOprName() {
		return oprName;
	}
	
	public String getOprNameCapital() {
		return BUtil.capitalizeFirstLetterOfEachWord(oprName);
	}

	public void setOprName(String oprName) {
		this.oprName = oprName;
	}

	public String getIdeRequirement() {
		return ideRequirement;
	}

	public void setIdeRequirement(String ideRequirement) {
		this.ideRequirement = ideRequirement;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSrvName() {
		return srvName;
	}
	
	public String getSrvNameAcron() {
		String acron = BUtil.getAcron(srvName);
		if (acron.length() > 1 && acron.length() < 8) {
			return acron;
		}
		return srvName.substring(0,4).toUpperCase();
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getChannel() {
		return channel;
	}
	
	public String getSuffixChannel() {
		return "_" + channel.toUpperCase();
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getOrgName() {
		return orgName;
	}
	
	public String getSuffixOrg() {
		return "_" + orgName.toUpperCase();
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getWsdlName() {
		return wsdlName;
	}

	public void setWsdlName(String wsdlName) {
		this.wsdlName = wsdlName;
	}

	public String getWsdlPort() {
		return wsdlPort;
	}

	public void setWsdlPort(String wsdlPort) {
		this.wsdlPort = wsdlPort;
	}

	public String getWsdlBinding() {
		return wsdlBinding;
	}

	public void setWsdlBinding(String wsdlBinding) {
		this.wsdlBinding = wsdlBinding;
	}

	public String getWsdlSvcPort() {
		return wsdlSvcPort;
	}

	public void setWsdlSvcPort(String wsdlSvcPort) {
		this.wsdlSvcPort = wsdlSvcPort;
	}
	
	

}
