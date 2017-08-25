package com.ath.esqltool.application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

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

			LinkedHashSet<String> setOthersNamespaces = new LinkedHashSet<String>();

			setOthersNamespaces.add("urn://grupoaval.com/customers/v1/");
			setOthersNamespaces.add("urn://grupoaval.com/xsd/ifx/");
			setOthersNamespaces.add("urn://grupoaval.com/xsd/ifx/v2/");
			setOthersNamespaces.add("urn://grupoaval.com/xsd/ifx/v3/");
			
			HashMap<String, String> mapNamespaces = BUtil.genOthersNamespaces(setOthersNamespaces);

			particular.setMapOthersNamespaces(mapNamespaces);
			
			
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
