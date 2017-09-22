package com.ath.esqltool.application;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

import com.ath.esqltool.delegates.BAthParticularGenerator;
import com.ath.esqltool.domain.BAthParticularProject;
import com.ath.esqltool.util.BUtil;


public class BAthParticularApplication {

	public BAthParticularApplication() {

		String current;
		try {

			current = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + current); 

			BAthParticularProject particular = new BAthParticularProject(current); 

			//particular.setParticularId(70050);
			//particular.setParticularType("DS");
			
			particular.setIdeRequirement("28496");
			particular.setDomain("customers");
			
			particular.setFacadeName("CardPswdAssignmentSvcFcdWs");
			
			particular.setSrvName("CardPswAssignmentSvc");
			particular.setOprName("modCardPsw");
			particular.setOrgName("AVV");
			particular.setChannel("BABN"); 
			particular.setBankId("00010524");
			particular.setApp("AVV");
			particular.setNamespace("urn://grupoaval.com/domain/v1/");
			particular.setCodService("5023_BCK");
			particular.setParticularDesc("Description CardPswAssignment");
			particular.setDatasource("ESBDATA");
			particular.setPassthrough(false);
			particular.setWsdlName("TokenInquiryManagementV2.wsdl");
			

			LinkedHashSet<String> setFacadeNamespaces = new LinkedHashSet<String>();

			setFacadeNamespaces.add("urn://grupoaval.com/customers/v1/");
			setFacadeNamespaces.add("urn://grupoaval.com/xsd/ifx/");
			setFacadeNamespaces.add("urn://grupoaval.com/xsd/ifx/v2/");
			setFacadeNamespaces.add("urn://grupoaval.com/xsd/ifx/v3/");
			
			HashMap<String, String> mapFacadeNamespaces = BUtil.genOthersNamespaces(setFacadeNamespaces);
			
			
			LinkedHashSet<String> setSpecificNamespaces = new LinkedHashSet<String>();

			setSpecificNamespaces.add("urn://bancodebogota.com/customers/token/event/");
			setSpecificNamespaces.add("urn://bancodebogota.com/customers/product/v1/");
			setSpecificNamespaces.add("urn://bancodebogota.com/ifx/base/v1/");
			setSpecificNamespaces.add("urn://bancodebogota.com/customers/product/service/v2/");
			setSpecificNamespaces.add("urn://bancodebogota.com/customers/involvedparty/v1/");
			
			HashSet<String> tmpSet = new HashSet<String>(); 
			tmpSet.addAll(mapFacadeNamespaces.keySet()); 
			
			HashMap<String, String> mapSpecificNamespaces = BUtil.genOthersNamespaces(setSpecificNamespaces, tmpSet);


			particular.setMapOthersNamespaces(mapFacadeNamespaces);

			particular.setMapSpecificNamespaces(mapSpecificNamespaces);
			
			
			if (particular.validate()) {
				BAthParticularGenerator generator = new BAthParticularGenerator(); 
				generator.generar(particular);
			} else {
				System.out.println("ERROR------------>" + particular.getErrorMessage());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new BAthParticularApplication();

	}

}
