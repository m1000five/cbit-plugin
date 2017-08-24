package com.ath.esqltool.application;

import java.io.IOException;

import com.ath.esqltool.delegates.BAthParticularGenerator;
import com.ath.esqltool.domain.BAthParticularProject;


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
			particular.setSrvName("CardPswAssignment");
			particular.setOprName("modCardPsw");
			particular.setOrgName("AVV");
			particular.setChannel("BABN"); 
			particular.setBankId("00010524");
			particular.setApp("AVV");
			particular.setNamespace("urn://grupoaval.com/domain/v1/");
			particular.setCodService("5023_BCK");
			particular.setParticularDesc("Description CardPswAssignment");
			particular.setDatasource("ESBDATA");
			
//			particular.setIdeRequirement("28496");
//			particular.setDomain("domain");
//			particular.setSrvName("ServiceName");
//			particular.setOprName("operationName");
//			particular.setOrgName("ORG");
//			particular.setChannel("Channel"); 
//			particular.setBankId("BankId");
//			particular.setApp("AVV");
//			particular.setNamespace("urn://grupoaval.com/domain/v1/");
//			particular.setCodService("5023_BCK");
//			particular.setParticularDesc("Description CardPswAssignment");
//			particular.setDatasource("ESBDATA");
			
			
//			particular.setIdeRequirement("28496");
//			particular.setDomain("customers");
//			particular.setSrvName("CardPswAssignment");
//			particular.setOprName("modCardPsw");
//			particular.setOrgName("AVV");
//			particular.setChannel("BABN");
//			particular.setBankId("00010524");
//			particular.setApp("AVV");
//			particular.setNamespace("urn://grupoaval.com/domain/v1/");
//			particular.setCodService("5023_BCK");
//			particular.setParticularDesc("Description CardPswAssignment");
//			particular.setDatasource("ESBDATA");
			
			
			
			
			
//			particular.setNameStepRq("nameStepRq");
//			particular.setNameDestRequest("nameDestRequest");
//			particular.setNameDestResponse("nameDestResponse");
//			particular.setNameOpDestRq("nameOpDestRq");
//			particular.setNameStepRs("nameStepRs");
//			particular.setNameOpDestRs("nameOpDestRs");
			
			
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
