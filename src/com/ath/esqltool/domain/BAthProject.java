package com.ath.esqltool.domain;

import java.util.StringTokenizer;

import com.ath.esqltool.util.BUtil;

public abstract class BAthProject {

	protected String currentDir;

	protected String name;

	protected String ideRequirement;
	
	protected String srvName;

	protected String oprName;
	
	protected String orgName;
	
	protected String domain;
	
	protected String channel;
	
	protected String namespace;
	
	protected String bankId;
	
	protected String errorMessage;
	
	protected String wsdlName;
	
	protected String wsdlRelativePathName;
	
	protected String wsdlPort;
	
	protected String wsdlBinding;
	
	protected String wsdlSvcPort;
	
	private String srvDescription;
	
	private String msgReq = "operationNameRequest";
	private String msgRes = "operationNameResponse";
	
	
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

	public String getWsdlRelativePathName() {
		if (wsdlRelativePathName == null || wsdlRelativePathName.length() == 0) {
			return wsdlName;
		}
		return wsdlRelativePathName;
	}

	public void setWsdlRelativePathName(String wsdlRelativePathName) {
		this.wsdlRelativePathName = wsdlRelativePathName;
	}
	
	
	public String getSrvDescription() {
		return srvDescription;
	}

	public void setSrvDescription(String srvDescription) {
		this.srvDescription = srvDescription;
	}
	
	public String getMsgReq() {
		return msgReq;
	}

	public void setMsgReq(String msgReq) {
		this.msgReq = msgReq;
	}

	public String getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(String msgRes) {
		this.msgRes = msgRes;
	}
	
	public String getBusinessServiceName() {
		if (srvDescription != null && srvDescription.length() > 1) {
			StringTokenizer tokenizer = new StringTokenizer(srvDescription, " ");
			StringBuffer buf = new StringBuffer();
			while (tokenizer.hasMoreElements()) {
				String token = (String) tokenizer.nextToken();
				if (token.length() > 3) {
					buf.append(BUtil.capitalizeFirstLetterOfEachWord(token));
				}
			}
			return buf.toString();
			
		}
		return "";
	}
	

}
