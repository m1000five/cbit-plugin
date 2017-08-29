package com.ath.esqltool.application;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import com.ath.esqltool.delegates.BAthGenerator;
import com.ath.esqltool.domain.BAthFacadeProject;
import com.ath.esqltool.domain.BAthOrchestable;
import com.ath.esqltool.domain.BAthParticularProject;
import com.ath.esqltool.util.BUtil;

public class BAthApplication {

	public BAthApplication() {

		String current;
		try {

			current = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + current);

			BAthFacadeProject facade = new BAthFacadeProject(current);

			facade.setIdeRequirement("28496");

			facade.setDomain("customers");
			facade.setSrvName("CardPswdAssignmentSvc");
			facade.setOprName("modCardPsw");
			facade.setOrgName("AVV");//("AVV");
			facade.setChannel("BABN");
			facade.setBankId("00010524");
			facade.setNamespace("urn://grupoaval.com/customers/v1/");
			
			facade.setWsdlName("CardPswdAssignmentSvc.wsdl");
			facade.setWsdlPort("CardPswdAssignmentSvc");
			facade.setWsdlBinding("CardPswdAssignmentSvcBinding");
			facade.setWsdlSvcPort("CardPswdAssignmentSvcPort");
			
			LinkedHashSet<String> setOthersNamespaces = new LinkedHashSet<String>();

			setOthersNamespaces.add("urn://grupoaval.com/customers/v1/");
			setOthersNamespaces.add("urn://grupoaval.com/xsd/ifx/");
			setOthersNamespaces.add("urn://grupoaval.com/xsd/ifx/v2/");
			setOthersNamespaces.add("urn://grupoaval.com/xsd/ifx/v3/");
			
			HashMap<String, String> mapNamespaces = BUtil.genOthersNamespaces(setOthersNamespaces);

			if (!mapNamespaces.isEmpty()) {
				Iterator it = mapNamespaces.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					System.out.println(" NAMESPACES----------> " + pair.getKey().toString() + " -> " + pair.getValue());
				}
			}

//			facade.setPrefixns("v1");

//			HashMap<String, String> mapOthersNamespaces = new HashMap<String, String>();
//			mapOthersNamespaces.put("ifx", "urn://grupoaval.com/xsd/ifx/");
//			mapOthersNamespaces.put("v2", "urn://grupoaval.com/xsd/ifx/v2/");
//			mapOthersNamespaces.put("v3", "urn://grupoaval.com/xsd/ifx/v3/");

			facade.setMapOthersNamespaces(mapNamespaces);
			
			
			HashSet<BAthOrchestable> listStepsOrchestables = new HashSet<BAthOrchestable>();
			
			if (listStepsOrchestables != null && !listStepsOrchestables.isEmpty()) {
				Iterator<BAthOrchestable> iterator = listStepsOrchestables.iterator();
				while (iterator.hasNext()) {
					BAthOrchestable bAthOrchestable = (BAthOrchestable) iterator.next();
					
					BAthParticularProject particular = BAthParticularProject.valueOf(facade);
					
					
					
				}
				
			}

			// facade.setOprWsdlReqName("setSecurityRequest");
			// facade.setOprWsdlResName("setSecurityResponse");
			// facade.setMsgReq("SecurityAddRq");
			// facade.setMsgRes("SecurityAddRs");

			// controller.setNamespace("urn://bancodebogota.com/customers/product/service/");
			//
			// controller.setCtrlId(800050);
			//
			// BStepOrchestable defaultstep = new BFmgBo(0, "FMG.DEST.00000",
			// "ESB_DEST_NomServicio_NomOperacion_FMG" );
			//
			// controller.addStep(defaultstep);
			//
			// BStepOrchestable step1 = new BFmgBo(60003, "FMG.60003.IN", "FMG_PASO_1" );
			//
			// controller.addStep(step1);
			//
			//
			// BStepOrchestable step2 = new BFmgBo(60006, "FMG.60006.IN", "FMG_PASO_2" );
			//
			// controller.addStep(step2);

			// controller.setNumberSetps(1);

			BAthGenerator generator = new BAthGenerator();

			// String pathTemplates =
			// "C:\\all\\integracion\\SOA\\wmb2\\guidelines\\PlantillasPrototipos\\ESB_NombreServicio_NombreOperacion_CTRL\\com\\bancodebogota\\domain\\nombreservicio\\nombreoperacion";

			generator.generar(facade);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new BAthApplication();

	}

}
