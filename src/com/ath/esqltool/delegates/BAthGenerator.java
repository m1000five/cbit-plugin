package com.ath.esqltool.delegates;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils.Null;
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
			vc.put("workspace", facade.getCurrentDir());
//			vc.put("prefixns", facade.getPrefixns());
			String prefix = BUtil.getPrefix(facade.getNamespace(), null);
			vc.put("prefixns", prefix);
			
			
//			facade.setWsdlName("CardPswdAssignmentSvc.wsdl");
//			facade.setWsdlPort("CardPswdAssignmentSvc");
//			facade.setWsdlBinding("CardPswdAssignmentBinding");
//			facade.setWsdlSvcPort("CardPswdAssignmentPort");
			
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
					
					if (pair.getKey().toString().equalsIgnoreCase(facade.getNamespace())) {
						continue;
					}

					bufOtherNamespaces.append("\nDECLARE ").append(pair.getKey());
					bufOtherNamespaces.append("		NAMESPACE '");
					bufOtherNamespaces.append(pair.getValue());
					bufOtherNamespaces.append("';\n");
					
					if (facade.getPrefixauxns() == null) {
						facade.setPrefixauxns(pair.getKey().toString());
					}
					
					System.out.println(pair.getKey() + " = " + pair.getValue());

					it.remove(); // avoids a ConcurrentModificationException
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
			//
			//
			//
			// Template tpl3 = ve.getTemplate(BAthTemplates.TEMPLATE_ESQL_END_RS);
			// StringWriter sw3 = new StringWriter();
			// tpl3.merge(vc, sw3);
			// PrintWriter out3 = new PrintWriter(facade.getPathEndPr());
			// out3.println(sw3.toString());
			// out3.close();
			//
			// if (facade.getListSteps().size() > 1) {
			// vc.put("replyToQueuePpal", "esbStep1");
			// vc.put("replyToQueueName", "CNTL." + facade.getCtrlId() + ".STEP1.IN");
			// // replyToQueueName
			// } else {
			// vc.put("replyToQueuePpal", "esbEnd");
			// vc.put("replyToQueueName", facade.getEndQueue());
			// }
			//
			// tpl = ve.getTemplate(BAthTemplates.TEMPLATE_MSGFLOW_START);
			// sw = new StringWriter();
			// tpl.merge(vc, sw);
			// out = new PrintWriter(facade.getPathStartFlow());
			// out.println(sw.toString());
			// out.close();
			//
			// tpl1 = ve.getTemplate(BAthTemplates.TEMPLATE_ESQL_START);
			// sw1 = new StringWriter();
			// tpl1.merge(vc, sw1);
			// out1 = new PrintWriter(facade.getPathStartPr());
			// out1.println(sw1.toString());
			// out1.close();
			//
			// Iterator<BStepOrchestable> itSteps = facade.getListSteps().iterator();
			//
			// BStepOrchestable bStepDefault = itSteps.next();
			//
			// StringBuffer bufOtherNamespaces = new StringBuffer("");
			// StringBuffer bufListOfVarMqFmgs = new StringBuffer("");
			// StringBuffer bufStepsOfMq = new StringBuffer("");
			// StringBuffer bufListFmgsAntProps = new StringBuffer("");
			// StringBuffer bufListFmgsAntCalls = new StringBuffer("");
			// StringBuffer bufOrchestationStatements = new StringBuffer("");
			//
			// if (bStepDefault.getInputMq().toUpperCase().startsWith("FMG")) {
			// bufOrchestationStatements
			// .append("INSERT INTO TBL_MST_ORCHESTRATOR (ORCHESTRATOR_ID,
			// ORCHESTRATOR_CONTROLLER_ID, INVOKED_CONTROLLER_ID, INVOKED_FMG_ID) ")
			// .append(" VALUES (USRBRK.ORCHESTRATOR_ID_SEQ.NEXTVAL,");
			// bufOrchestationStatements.append(facade.getCtrlId()).append(", NULL , ");
			// bufOrchestationStatements.append(bStepDefault.getId()).append(");");
			// bufOrchestationStatements.append("\n");
			// }
			//
			// if (facade.getListSteps().size() > 1) {
			//
			// int i = 1;
			//
			// while (itSteps.hasNext()) {
			// BStepOrchestable bStepOrchestable = itSteps.next();
			// // DECLARE esbStep1 EXTERNAL CHARACTER 'Queue1';\nDECLARE
			// // esbStep2 EXTERNAL CHARACTER 'Queue2';
			// bufOtherNamespaces.append("\nDECLARE esbStep").append(i);
			// bufOtherNamespaces.append(" EXTERNAL CHARACTER '");
			// bufOtherNamespaces.append("CNTL." + facade.getCtrlId() + ".STEP" + (i) +
			// ".IN");
			// bufOtherNamespaces.append("';\n");
			//
			// // DEFINE QLOCAL('CNTL.700045.STEP1.IN') DEFPSIST(NO)
			// // REPLACE DESCR('SRV AccountBalanceAvalInquiry CNTRL
			// // getBalanceByProduct')
			// bufStepsOfMq.append("DEFINE QLOCAL('");
			// bufStepsOfMq.append(facade.getMqStep(i));
			// bufStepsOfMq.append("') DEFPSIST(NO) REPLACE
			// DESCR('").append(facade.getMqDescStep(i));
			// bufStepsOfMq.append("')");
			// bufStepsOfMq.append("\n");
			//
			// if (bStepOrchestable.getId() > 0) {
			// if (bStepOrchestable.getInputMq().toUpperCase().startsWith("FMG")) {
			// bufOrchestationStatements
			// .append("INSERT INTO TBL_MST_ORCHESTRATOR (ORCHESTRATOR_ID,
			// ORCHESTRATOR_CONTROLLER_ID, INVOKED_CONTROLLER_ID, INVOKED_FMG_ID) ")
			// .append(" VALUES (USRBRK.ORCHESTRATOR_ID_SEQ.NEXTVAL,");
			// bufOrchestationStatements.append(facade.getCtrlId()).append(", NULL , ");
			// bufOrchestationStatements.append(bStepOrchestable.getId()).append(");");
			// bufOrchestationStatements.append("\n");
			//
			//
			// bufListFmgsAntProps.append("<property name=\"fmg").append(i);
			// bufListFmgsAntCalls.append("<file
			// name=\"../").append(bStepOrchestable.getName()).append(i);
			// } else {
			//
			// bufListFmgsAntProps.append("<property name=\"cntl").append(i+1);
			// bufListFmgsAntCalls.append("<file
			// name=\"../").append(bStepOrchestable.getName()).append(i+1);
			//
			// }
			//
			// bufListFmgsAntProps.append("\" value=\"").append(bStepOrchestable.getName());
			// bufListFmgsAntProps.append("\"/>").append("\n").append("\t").append("\t");
			//
			// bufListFmgsAntCalls.append("/build.xml\"/>").append("\n").append("\t").append("\t").append("\t").append("\t");
			//
			// }
			//
			// bufListOfVarMqFmgs.append("DECLARE FMG_ID");
			// bufListOfVarMqFmgs.append(i);
			// bufListOfVarMqFmgs.append(" EXTERNAL CHARACTER '");
			// bufListOfVarMqFmgs.append(bStepOrchestable.getInputMq());
			// bufListOfVarMqFmgs.append("';");
			// bufListOfVarMqFmgs.append("\n");
			//
			// vc.put("fmgCntlStep", "FMG_ID" + i);
			// vc.put("fmgCntlStepValQueue", bStepOrchestable.getInputMq());
			// if ((itSteps.hasNext())) {
			// vc.put("replyToQueueVar", "esbStep" + (i + 1));
			// vc.put("replyToQueue", "CNTL." + facade.getCtrlId() + ".STEP" + (i + 1) +
			// ".IN");
			// } else {
			// vc.put("replyToQueueVar", "esbEnd");
			// vc.put("replyToQueue", facade.getEndQueue());
			// // replyToQueueName
			// }
			//
			// vc.put("stepNumber", "STEP" + i);
			//
			//
			//
			//
			// Template tplstep = ve.getTemplate(BAthTemplates.TEMPLATE_MSGFLOW_STEP);
			// sw = new StringWriter();
			// tplstep.merge(vc, sw);
			// out = new PrintWriter(facade.getPathStepFlow(i));
			// out.println(sw.toString());
			// out.close();
			//
			// Template tplstepprep = ve.getTemplate(BAthTemplates.TEMPLATE_ESQL_STEP);
			// sw1 = new StringWriter();
			//
			// tplstepprep.merge(vc, sw1);
			// out1 = new PrintWriter(facade.getPathStepPr(i));
			// out1.println(sw1.toString());
			// out1.close();
			//
			// Template tplsteperror = ve.getTemplate(BAthTemplates.TEMPLATE_ESQL_STEP_ERR);
			// sw2 = new StringWriter();
			// tplsteperror.merge(vc, sw2);
			// out2 = new PrintWriter(facade.getPathStepError(i));
			// out2.println(sw2.toString());
			// out2.close();
			// i++;
			// }
			//
			// }
			//
			// vc.put("cmdDefineStepsQueues", bufStepsOfMq.toString());

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

			// tpl = ve.getTemplate(BAthTemplates.CNTL_TEST_REQUEST);
			// sw = new StringWriter();
			// tpl.merge(vc, sw);
			//
			// out = new PrintWriter(facade.getProjectPath() +
			// BAthTemplates.CNTL_TEST_REQUEST);
			// out.println(sw.toString());
			// out.close();
			//
			// tpl = ve.getTemplate(IBAthConstants.CONSTANTS);
			// sw = new StringWriter();
			// tpl.merge(vc, sw);
			//
			// out = new PrintWriter(facade.getPath() + IBAthConstants.CONSTANTS);
			// out.println(sw.toString());
			// out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
