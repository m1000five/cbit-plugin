package com.ath.esqltool.domain;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class BAthParticularProject extends BAthProject {

	private String org;// destination // dest
	
	private String descOrg;
	private Integer particularId;

	private String particularType;
	private String particularDesc;
	
	private String codService;

	private String nameStepRq;
	private String nameDestRequest;
	private String nameDestResponse;
	private String nameOpDestRq;
	private String nameStepRs;
	private String nameOpDestRs;
	private String namespace;
	
	private String datasource;
	
	private boolean generateId = false;
	private boolean newLegacyRegister = false;
	
	private HashMap<String, String> map = new HashMap<String, String>();

//	private BParticularBo fmgBo;
//	private BLegacyBo legacyBo;

	public BAthParticularProject() {
		setIdeRequirement("00001");
	}

	public BAthParticularProject(String current) {
		setCurrentDir(current);
		setIdeRequirement("00001");
	}

	public boolean validate() {

//		if (particularId == null || particularId <= 0) {
//			setErrorMessage("ERROR: FMG ID Null");
//			return false;
//		}

//		if (StringUtils.isBlank(particularType)) {
//			setErrorMessage("ERROR: FMG type Null");
//			return false;
//		}
		
		if (StringUtils.isBlank(particularDesc)) {
			setErrorMessage("ERROR: FMG Description Null");
			return false;
		}
		
		if (StringUtils.isBlank(org)) {
			setErrorMessage("ERROR: FMG Destination App Null");
			return false;
		}

		if (StringUtils.isBlank(srvName)) {
			setErrorMessage("ERROR: Service Name Null");
			return false;
		}

		if (StringUtils.isBlank(oprName)) {
			setErrorMessage("ERROR: Operation Name Null");
			return false;
		}
		
		if (StringUtils.isBlank(getBankId())) {
			setErrorMessage("ERROR: BankId Name Null");
			return false;
		}
		
//		if (getParticularBo() == null) {
//			setErrorMessage("ERROR: FMG Register Null");
//			return false;
//		}
//		if (legacyBo == null || legacyBo.getLeg_register_id() == null || legacyBo.getLeg_register_id() <= 0) {
//			setErrorMessage("ERROR: Legacy Register Null");
//			return false;
//		}

		setErrorMessage(null);
		return true;
	}

	public String getName() {
		return getSrvName() + "Svc_" + getOrgName();
	}

	public String getPackage() {
		return "com/ath/services/" + getDomain().toLowerCase();
	}

	//C:\Users\milton.vega\eclipse-workspace\generator\src\ServiceNameSvcOperationName_REQ.esql
	public String getPathParticularFlow() {
		return getPath() + getSrvName()  + "Svc" + getOprNameCapital() + "_REQ" + IBAthConstants.SUFFIX_FLOW;
	}

	public String getPathPrepareRq() {
		return getPath() + getSrvName()  + "Svc" + getOprNameCapital() + "_REQ.esql";
	}

	public String getPathPrepareRs() {
		return getPath() + IBAthConstants.PREFIX_FMG + getOprName() + "_PrepareResponseMesg.esql";
	}

	public String getSqlPath() {
		return getProjectPath() + IBAthConstants.PREFIX_FMG + "BD_" + getOprName() + IBAthConstants.SUFFIX_SQL;
	}

	public String getMqPath() {
		return getProjectPath() + IBAthConstants.PREFIX_FMG + getOprName() + IBAthConstants.SUFFIX_MQ;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getSrvName() {
		return srvName;
	}

	
	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getOprNameLower() {
		return getOprName().toLowerCase();
	}

	public String getSrvNameLower() { 
		return getSrvName().toLowerCase();
	}

	public String getInputQueue() {
		return "FMG." + getAliasApp() + "." + getParticularId() + ".IN";
	}

	public String getOutputQueue() {
		return "FMG." + getAliasApp() + "." + getParticularId() + ".RESP";
	}

	public String getApp() {
		return org;
	}

	public String getAppUpper() {
		return org.toUpperCase();
	}

	public void setApp(String app) {
		this.org = app;
	}

	public Integer getParticularId() {
		return particularId;
	}

	public void setParticularId(Integer fmgId) {
		this.particularId = fmgId;
	}

	public String getNameStepRq() {
		return nameStepRq;
	}

	public void setNameStepRq(String nameStepRq) {
		this.nameStepRq = nameStepRq;
	}

	public String getNameDestRequest() {
		return nameDestRequest;
	}

	public void setNameDestRequest(String nameDestRequest) {
		this.nameDestRequest = nameDestRequest;
	}

	public String getNameDestResponse() {
		return nameDestResponse;
	}

	public void setNameDestResponse(String nameDestResponse) {
		this.nameDestResponse = nameDestResponse;
	}

	public String getNameOpDestRq() {
		return nameOpDestRq;
	}

	public void setNameOpDestRq(String nameOpDestRq) {
		this.nameOpDestRq = nameOpDestRq;
	}

	public String getNameStepRs() {
		return nameStepRs;
	}

	public void setNameStepRs(String nameStepRs) {
		this.nameStepRs = nameStepRs;
	}

	public String getNameOpDestRs() {
		return nameOpDestRs;
	}

	public void setNameOpDestRs(String nameOpDestRs) {
		this.nameOpDestRs = nameOpDestRs;
	}

//	public BParticularBo getParticularBo() {
//		if (getLegacyBo() != null) {
//			fmgBo = new BParticularBo();
//			fmgBo.setParticular_id(getParticularId());
//			fmgBo.setParticular_desc(getParticularName());
//			fmgBo.setParticular_name(getParticularDesc());
//			fmgBo.setParticular_input_mq(getInputQueue());
//			fmgBo.setLegacy_register_id(getLegacyBo().getLeg_register_id());
//		}
//		return fmgBo; 
//	}
//
//	public void setParticularBo(BParticularBo fmgBo) {
//		this.fmgBo = fmgBo;
//	}
//
//	public BLegacyBo getLegacyBo() {
//		return legacyBo;
//	}
//
//	public void setLegacyBo(BLegacyBo legacyBo) {
//		this.legacyBo = legacyBo;
//	}

	public String getParticularType() {
		return particularType;
	}

	public void setParticularType(String fmgType) {
		this.particularType = fmgType;
	}

	public String getParticularName() {
		return "ESB_" + getApp().toUpperCase() + "_" + getOprName();
	}

	public String getParticularDesc() {
		return particularDesc;
	}

	public void setParticularDesc(String fmgDesc) {
		this.particularDesc = fmgDesc;
	}

	public boolean isGenerateId() {
		return generateId;
	}

	public void setGenerateId(boolean generateId) {
		this.generateId = generateId;
	}

	public boolean isNewLegacyRegister() {
		return newLegacyRegister;
	}

	public void setNewLegacyRegister(boolean newLegacyRegister) {
		this.newLegacyRegister = newLegacyRegister;
	}
	
	
	public static String getAliasApp(String app_param, String descapp_param) {
		if (app_param != null) {
			try {
				Integer.parseInt(app_param);
				if (descapp_param != null && descapp_param.length() > 2) {
					if (descapp_param.indexOf(" ") != -1) {
						String[] strings = descapp_param.split(" ");
						return strings[0].toUpperCase();
					}
					return descapp_param.toUpperCase();
				}
			} catch (NumberFormatException e) {
				return app_param.toUpperCase();
			}
		}
		
		return app_param.toUpperCase();
	}
	
	
	public String getAliasApp() {
		if (org != null) {
			try {
				Integer.parseInt(org);
				if (descOrg != null && descOrg.length() > 2) {
					if (descOrg.indexOf(" ") != -1) {
						String[] strings = descOrg.split(" ");
						return strings[0].toUpperCase();
					}
					return descOrg.toUpperCase();
				}
			} catch (NumberFormatException e) {
				return org.toUpperCase();
			}
		}
		
		return org.toUpperCase();
	}

	public String getDescApp() {
		return descOrg;
	}

	public void setDescApp(String descApp) {
		this.descOrg = descApp;
	}
	
	

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BParticularProject [app=");
		builder.append(org);
		builder.append(", fmgId=");
		builder.append(particularId);
		builder.append(", fmgType=");
		builder.append(particularType);
		builder.append(", fmgDesc=");
		builder.append(particularDesc);
		builder.append(", srvName=");
		builder.append(srvName);
		builder.append(", generateId=");
		builder.append(generateId);
		builder.append(", newLegacyRegister=");
		builder.append(newLegacyRegister);
		//builder.append(", fmgBo=");
		//builder.append(fmgBo);
		//builder.append(", legacyBo=");
		//builder.append(legacyBo);
		builder.append("]");
		return builder.toString();
	}

	public String getPathConstants() {
		if (getParticularType().equalsIgnoreCase("DS")) {
			//FMG_DEST_SP_NombreSp_Constants.esql
			return getPath() + IBAthConstants.PREFIX_FMG + getAliasApp() + "_SP_"  + getOprName() + "_Constants.esql"; //+ BTemplates.FMG_CONSTANTS;
		}
		return getPath() + BAthTemplates.FMG_CONSTANTS;
	}

	public String getPathCallSp() {
		//FMG_DEST_SP_NombreSp_CallSP.esql
		return getPath() + IBAthConstants.PREFIX_FMG + getAliasApp() + "_SP_"  + getOprName() + "_CallSP.esql";
	}

	public String getCodService() {
		return codService;
	}

	public void setCodService(String codService) {
		this.codService = codService;
	}
	
	
	
	
	
	
	

}
