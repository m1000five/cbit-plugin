package com.ath.esqltool.delegates;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.ath.esqltool.domain.BAthFacadeProject;
import com.ath.esqltool.domain.BAthTemplates;
import com.ath.esqltool.domain.IBAthConstants;
import com.ath.esqltool.util.BUtil;

public class BAthGenerator {

	public BAthGenerator() {
	}

	public void generar(BAthFacadeProject facade) {
		generar(facade, null);
	}

	public void generar(BAthFacadeProject facade, String path) {

		try {

			VelocityEngine ve = new VelocityEngine();
			if (path != null && path.length() > 0) {
				ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, path);
			} else {
				ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
				ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			}

			ve.init();

			Velocity.init();
			VelocityContext vc = new VelocityContext();

			// vc.put("ctrlId", facade.getSrvId());
			
			//TODO establecer valores por defecto si vienen los valores vacios?
			vc.put("ServiceName", facade.getSrvName());
			vc.put("BusinessServiceName", facade.getBusinessServiceName());
			vc.put("namespace", facade.getNamespace());
			vc.put("domain", facade.getDomain());
			vc.put("domainCapital", BUtil.capitalizeFirstLetterOfEachWord(facade.getDomain()));
			vc.put("OperationName", facade.getOprName());
			vc.put("ServiceNameUpper", facade.getSrvName().toUpperCase());
			vc.put("OperationNameUpper", facade.getOprName().toUpperCase());
			vc.put("OperationNameCapital", BUtil.capitalizeFirstLetterOfEachWord(facade.getOprName()));
			vc.put("ServiceNameAcron", facade.getSrvNameAcron());
			vc.put("Org", facade.getOrgName());
			vc.put("SuffixOrg", facade.getSuffixOrg());
			vc.put("SuffixChannel", facade.getSuffixChannel());
			vc.put("BankId", facade.getBankId());
			vc.put("Channel", facade.getChannel());
			vc.put("ideRequirement", facade.getIdeRequirement());
			vc.put("projectName", facade.getName());
			vc.put("ServiceNameDesc", facade.getSrvDescription());
			vc.put("workspace", facade.getCurrentDir());
			vc.put("msgReq", facade.getMsgReq());
			vc.put("msgRes", facade.getMsgRes());
			vc.put("msgFirstReq", facade.getFirstMsgReqElement());
			vc.put("msgFirstRes", facade.getFirstMsgResElement());

			String prefixPpal = BUtil.getPrefix(facade.getNamespace(), null);
			vc.put("prefixns", prefixPpal);
			
			vc.put("wsdlRelativePathName", facade.getWsdlRelativePathName() != null?facade.getWsdlRelativePathName():"WsdlName.wsdl");
			vc.put("wsdlName", facade.getWsdlName() != null?facade.getWsdlName():"WsdlName.wsdl");
			vc.put("port", facade.getWsdlPort() != null?facade.getWsdlPort():"wsdlPort");
			vc.put("binding", facade.getWsdlBinding() != null?facade.getWsdlBinding():"binding");
			vc.put("svcPort", facade.getWsdlSvcPort() != null?facade.getWsdlSvcPort():"svcPort");
			

			File file = new File(facade.getPath());
			file.mkdirs();

			Template tpl = ve.getTemplate(BAthTemplates.TEMPLATE_PROJECT);
			StringWriter sw = new StringWriter();
			tpl.merge(vc, sw);

			PrintWriter out = new PrintWriter(facade.getProjectPath() + IBAthConstants.PROJECT_FILE);
			out.println(sw.toString());
			out.close();

			
			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_MSGFLOW_FACADE_REQ);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(facade.getPathFacadeReqFlow());
			out.println(sw.toString());
			out.close();
			
			

			StringBuffer bufOtherNamespaces = new StringBuffer("");
			
//			LinkedHashSet<String> setNamespaces = facade.getSetNamespaces();
//			if (setNamespaces != null && !setNamespaces.isEmpty()) {
////				String prefix = BUtil.getPrefix((setNamespaces.toArray()[0]).toString(), null);
//			} else {
//				vc.put("prefixns", "ns");
//			}
			
			

			if (!facade.getMapOthersNamespaces().isEmpty()) {

				Iterator it = facade.getMapOthersNamespaces().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
					


					bufOtherNamespaces.append("\nDECLARE ").append(pair.getKey());
					bufOtherNamespaces.append("		NAMESPACE '");
					bufOtherNamespaces.append(pair.getValue());
					bufOtherNamespaces.append("';\n");
					
					if (facade.getPrefixauxns() == null) {
						if (pair.getKey().toString().equalsIgnoreCase(prefixPpal)) {
							continue;
						}
						facade.setPrefixauxns(pair.getKey().toString());
					}
					
					System.out.println(pair.getKey() + " = " + pair.getValue());

					//it.remove(); // avoids a ConcurrentModificationException
				}

			}

			
			vc.put("prefixauxns", facade.getPrefixauxns()!= null?facade.getPrefixauxns():"aux");
			vc.put("otherNamespaces", bufOtherNamespaces.toString());

			Template tpl1 = ve.getTemplate(BAthTemplates.TEMPLATE_ESQL_FACADE);
			StringWriter sw1 = new StringWriter();
			tpl1.merge(vc, sw1);
			PrintWriter out1 = new PrintWriter(facade.getPathFacadeReqEsql());
			out1.println(sw1.toString());
			out1.close();

			Template tpl2 = ve.getTemplate(BAthTemplates.TEMPLATE_MSGFLOW_FACADE_RES);
			StringWriter sw2 = new StringWriter();
			tpl2.merge(vc, sw2);
			PrintWriter out2 = new PrintWriter(facade.getPathFacadeResFlow());
			out2.println(sw2.toString());
			out2.close();
			

			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_MQ);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(facade.getMqPath());
			out.println(sw.toString());
			out.close();

			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_MQ_REV);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(facade.getMqRevPath());
			out.println(sw.toString());
			out.close();

			// vc.put("cmdOrchestableSteps", bufOrchestationStatements.toString());

			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_SQL);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(facade.getSqlPath());
			out.println(sw.toString());
			out.close();

			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_SQL_REV);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(facade.getSqlRevPath());
			out.println(sw.toString());
			out.close();

			// vc.put("esbSteps", bufVarStepsOfConstants.toString());
			// vc.put("esbFmgIds", bufListOfVarMqFmgs.toString());
			//
			// vc.put("ant_prop_fmgs", bufListFmgsAntProps.toString());
			// vc.put("ant_prop_fmgs_call", bufListFmgsAntCalls.toString());

			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_ANT_BUILD);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(facade.getProjectPath() + BAthTemplates.TEMPLATE_ANT_BUILD);
			out.println(sw.toString());
			out.close();

			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_XML);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(facade.getXmlPath());
			out.println(sw.toString());
			out.close();

			

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
