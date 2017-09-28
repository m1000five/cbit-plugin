package com.ath.esqltool.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ath.esqltool.util.BUtil;

public class BAthFacadeProject extends BAthProject { 

	private Integer srvId;
	
	private String prefixns;
	private String prefixauxns;
	
	private String oprWsdlReqName;
	private String oprWsdlResName;
	
	
	
	
	

	private HashMap<String, String> mapOthersNamespaces = new HashMap<String, String>();
	
	private LinkedHashSet<String> setNamespaces = new LinkedHashSet<String>();

	private List<IBAthParticularFlow> listParticulars = new ArrayList<IBAthParticularFlow>();
	
	private String shortSrvName;
	

	public BAthFacadeProject() {
		setIdeRequirement("00001");
		listParticulars = new ArrayList<IBAthParticularFlow>();
	}

	public BAthFacadeProject(String current) {
		setCurrentDir(current);
		setIdeRequirement("00001");
		listParticulars = new ArrayList<IBAthParticularFlow>();
	}
	
	public boolean validate() {
		
		
		if (StringUtils.isBlank(domain)) {
			setErrorMessage("ERROR: Domain Null");
			return false;
		}
		
		if (StringUtils.isBlank(srvName)) {
			setErrorMessage("ERROR: srvName Null");
			return false;
		}
		
		if (StringUtils.isBlank(oprName)) {
			setErrorMessage("ERROR: oprName Null");
			return false;
		}
		
		setErrorMessage(null);
		return true;
	}
	
	public String getName() {
		return getSrvName() + "FcdWs";
	}

	public String getPackage() {
		return "com/ath/services/" + getDomain();
	}
	
	public String getPathFacadeReqFlow() {
		return getPath() + getSrvName() + IBAthConstants.SUFFIX_REQ_FLOW;
	}

	public String getPathFacadeResFlow() {
		return getPath() + getSrvName() + IBAthConstants.SUFFIX_RES_FLOW;
	}
	
	public String getPathFacadeReqEsql() {
		return getPath() + getSrvName() + IBAthConstants.SUFFIX_REQ_ESQL;
	}
	
	

	public String getPathEndError() {
		return getPath() + IBAthConstants.PREFIX_CTRL + getSrvName() + IBAthConstants.SUFFIX_REQ_FLOW;
	}

	public String getPathEndPrError() {
		return getPath() + IBAthConstants.PREFIX_CTRL + getSrvName() + IBAthConstants.SUFFIX_END_PR_ERROR;
	}

	public String getPathEndPr() {
		return getPath() + IBAthConstants.PREFIX_CTRL + getSrvName() + IBAthConstants.SUFFIX_END_PR;
	}

	public String getPathStartPr() {
		return getPath() + IBAthConstants.PREFIX_CTRL + getSrvName() + IBAthConstants.SUFFIX_START_PR;
	}

	public String getPathStepFlow(int i) {
		return getPath() + IBAthConstants.PREFIX_CTRL + getSrvName() + IBAthConstants.SUFFIX_STEP_WITHOUT_EXT + i
				+ IBAthConstants.SUFFIX_FLOW;
	}

	public String getPathStepError(int i) {
		return getPath() + IBAthConstants.PREFIX_CTRL + getSrvName() + IBAthConstants.SUFFIX_STEP_WITHOUT_EXT + i
				+ IBAthConstants.SUFFIX_REQ_ESQL;
	}

	public String getPathStepPr(int i) {
		return getPath() + IBAthConstants.PREFIX_CTRL + getSrvName() + IBAthConstants.SUFFIX_STEP_WITHOUT_EXT + i
				+ IBAthConstants.SUFFIX_ESQL_PR;
	}

	public String getSqlPath() {
		return getProjectPath() + IBAthConstants.PREFIX_SQL + getSrvName() + "_" + "RQ" + getIdeRequirement() + IBAthConstants.SUFFIX_SQL;
	}
	
	public String getSqlRevPath() {
		return getProjectPath() + IBAthConstants.PREFIX_SQL_REVERSE + getSrvName() + "_" + "RQ" + getIdeRequirement() + IBAthConstants.SUFFIX_SQL;
	}

	public String getMqPath() {
		return getProjectPath() + IBAthConstants.PREFIX_MQ + getSrvName() + "_" + "RQ" + getIdeRequirement() + IBAthConstants.SUFFIX_MQ;
	}
	
	public String getMqRevPath() {
		return getProjectPath() + IBAthConstants.PREFIX_MQ_REVERSE + getSrvName() + "_" + "RQ" + getIdeRequirement() + IBAthConstants.SUFFIX_MQ;
	}
	
	public String getXmlPath() {
		return getProjectPath() + getSrvName() + IBAthConstants.SUFFIX_XML;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Integer getSrvId() {
		return srvId;
	}

	public void setSrvId(Integer ctrlId) {
		this.srvId = ctrlId;
	}

	public Integer getNumberSetps() {
		return (listParticulars != null ? listParticulars.size() : 0);
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSrvName() {
		return srvName;
	}
	
	public String getDesController() {
		return "Controller - " + srvName + "." + oprName;
	}
	
	public void setSrvName(String srvName) {
		this.srvName = srvName;
		this.setShortSrvName(srvName != null ? BUtil.shortenText(srvName.toLowerCase()) : "");
	}

	public String getOprName() {
		return oprName;
	}

	public void setOprName(String oprName) {
		this.oprName = oprName;
	}

	public String getOprWsdlReqName() {
		return oprWsdlReqName;
	}

	public void setOprWsdlReqName(String oprWsdlReqName) {
		this.oprWsdlReqName = oprWsdlReqName;
	}

	public String getOprWsdlResName() {
		return oprWsdlResName;
	}

	public void setOprWsdlResName(String oprWsdlResName) {
		this.oprWsdlResName = oprWsdlResName;
	}

	

	public String getOprNameLower() {
		return getOprName().toLowerCase();
	}

	public String getSrvNameLower() {
		return getSrvName().toLowerCase();
	}

	public String getCurrentDir() {
		return currentDir;
	}

	public void setCurrentDir(String currentDir) {
		this.currentDir = currentDir.replace('\\', '/');
	}

	public List<IBAthParticularFlow> getListSteps() {
		return listParticulars;
	}

	public void setListSteps(List<IBAthParticularFlow> listSteps) {
		this.listParticulars = listSteps;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BAthFacadeProject [rqId=");
		builder.append(srvId);
		builder.append(", domain=");
		builder.append(domain);
		builder.append(", srvName=");
		builder.append(srvName);
		builder.append(", oprName=");
		builder.append(oprName);
		builder.append(", currentDir=");
		builder.append(currentDir);
		builder.append("]");
		return builder.toString();
	}
	
	public void addParticular(BAthParticularProject particular) {
		if (particular != null) {
			//listParticulars.add(particular);
		}

	}

	public void addParticular(IBAthParticularFlow particular) {
		if (particular != null) {
			listParticulars.add(particular);
		}

	}

	public String getFmgCntlPpal() {
		if (listParticulars != null && !listParticulars.isEmpty()) {
			return ((IBAthParticularFlow) listParticulars.toArray()[0]).getInputMq();
		}
		return "FMG.APP.IN";
	}

	public String getInputQueue() {
		return "CNTL." + getSrvId() + ".IN";
	}

	public String getEndQueue() {
		return "CNTL." + getSrvId() + ".END";
	}

	public String getSmQueue() {
		return "CNTL." + getSrvId() + ".SM";
	}

	public String getOutputQueue() {
		return "CNTL." + getSrvId() + ".OUT";
	}

	public String getShortSrvName() {
		return shortSrvName;
	}

	public void setShortSrvName(String shortSrvName) {
		this.shortSrvName = shortSrvName;
	}

	public String getMqStep(int i) {
		return "CNTL." + getSrvId() + ".STEP" + (i) + ".IN";
	}

	public String getMqDescStep(int i) {  
		return "CNTL " + getSrvName() + " STEP" + i ;
	}

	public String getPrefixns() {
		return prefixns;
	}

	public void setPrefixns(String prefixns) {
		this.prefixns = prefixns;
	}

	public LinkedHashSet<String> getSetNamespaces() {
		return setNamespaces;
	}

	public void setSetNamespaces(LinkedHashSet<String> setOthersNamespaces) {
		this.setNamespaces = setOthersNamespaces;
	}

	public String getPrefixauxns() {
		return prefixauxns;
	}

	public void setPrefixauxns(String prefixauxns) {
		this.prefixauxns = prefixauxns;
	}

	public HashMap<String, String> getMapOthersNamespaces() {
		return mapOthersNamespaces;
	}

	public void setMapOthersNamespaces(HashMap<String, String> mapOthersNamespaces) {
		this.mapOthersNamespaces = mapOthersNamespaces;
	}




	
	
	

}
