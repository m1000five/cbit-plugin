package com.ath.esqltool.delegates;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.ath.esqltool.domain.BAthParticularProject;
import com.ath.esqltool.domain.BAthTemplates;
import com.ath.esqltool.domain.IBAthConstants;
import com.ath.esqltool.util.BUtil;



public class BAthParticularGenerator {

	public BAthParticularGenerator() {
	}

	public void generar(BAthParticularProject particularProject) {
		generar(particularProject, null);
	}

	public void generar(BAthParticularProject particularProject, String path) {

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

			vc.put("Id", particularProject.getParticularId());
			vc.put("domain", particularProject.getDomain());
			vc.put("namespace", particularProject.getNamespace());
			vc.put("workspace", particularProject.getCurrentDir());
			vc.put("ServiceName", particularProject.getSrvName());
			vc.put("domain", particularProject.getDomain());
			vc.put("OperationName", particularProject.getOprName());
			vc.put("ServiceNameUpper", particularProject.getSrvName().toUpperCase());
			vc.put("OperationNameUpper", particularProject.getOprName().toUpperCase());
			vc.put("OperationNameCapital", BUtil.capitalizeFirstLetterOfEachWord(particularProject.getOprName()));
			vc.put("ServiceNameAcron", particularProject.getSrvNameAcron());
			vc.put("Org", particularProject.getOrgName());
			vc.put("SuffixOrg", particularProject.getSuffixOrg());
			vc.put("SuffixChannel", particularProject.getSuffixChannel());
			vc.put("BankId", particularProject.getBankId());
			vc.put("Channel", particularProject.getChannel());
			vc.put("ideRequirement", particularProject.getIdeRequirement());
			vc.put("CodService", particularProject.getCodService());
			vc.put("NombreDatasource", particularProject.getDatasource());
			vc.put("ideRequirement", particularProject.getIdeRequirement());
			vc.put("projectName", particularProject.getName());
			vc.put("workspace", particularProject.getCurrentDir());
			
//			vc.put("destination", particularProject.getAppUpper());
//			vc.put("destinationAlias", particularProject.getAliasApp());
//			vc.put("dest", particularProject.getAliasApp().toLowerCase());
//			vc.put("oprName", particularProject.getOprName());
//			vc.put("srvName", particularProject.getSrvName());
//			vc.put("oprNameLower", particularProject.getOprNameLower());
//			vc.put("nameStepRs", particularProject.getNameStepRs() != null ? particularProject.getNameStepRs() : "nameStepRs");
//			vc.put("nameDestResponse",
//					particularProject.getNameDestResponse() != null ? particularProject.getNameDestResponse() : "nameOpDestRs");
//			vc.put("nameOpDestRs",
//					particularProject.getNameOpDestRs() != null ? particularProject.getNameOpDestRs() : "nameOpDestRs");
//			vc.put("nameDestRequest",
//					particularProject.getNameDestRequest() != null ? particularProject.getNameDestRequest() : "nameDestRequest");
//			vc.put("nameOpDestRq",
//					particularProject.getNameOpDestRq() != null ? particularProject.getNameOpDestRq() : "nameOpDestRq");
//			vc.put("nameStepRq", particularProject.getNameStepRq() != null ? particularProject.getNameStepRq() : "nameStepRq");
//			vc.put("projectName", particularProject.getName());
//			//vc.put("fmgId", particularProject.getParticularBo().getParticular_id());
//			vc.put("desc", particularProject.getParticularDesc());
			//vc.put("legId", particularProject.getLegacyBo().getLeg_register_id());
			//vc.put("adapterQueue", particularProject.getLegacyBo().getFw_communicator_input_mq());
			
			

			File file = new File(particularProject.getPath()); 
			file.mkdirs();
			
			String templateParticular = BAthTemplates.TEMPLATE_PARTICULAR_PROJECT;

			Template tpl = ve.getTemplate(templateParticular );
			StringWriter sw = new StringWriter();
			tpl.merge(vc, sw);

			PrintWriter out = new PrintWriter(particularProject.getProjectPath() + IBAthConstants.PROJECT_FILE);
			out.println(sw.toString());
			out.close();

			
			String templateMsgFlow = BAthTemplates.TEMPLATE_MSGFLOW_PARTICULAR;
			
			tpl = ve.getTemplate(templateMsgFlow);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(particularProject.getPathParticularFlow());
			out.println(sw.toString());
			out.close();
			
			 

			String templateEsqlRq = BAthTemplates.TEMPLATE_ESQL_PARTICULAR_RQ;
			
			
			Template tpl1 = ve.getTemplate(templateEsqlRq);
			StringWriter sw1 = new StringWriter();
			tpl1.merge(vc, sw1);
			PrintWriter out1 = new PrintWriter(particularProject.getPathPrepareRq());
			out1.println(sw1.toString());
			out1.close();

			
//			String templateEsqlRs = BAthTemplates.TEMPLATE_ESQL_FMG_RS;
//			if (particularProject.getParticularType().equalsIgnoreCase("DS")) {
//				templateEsqlRs = BAthTemplates.TEMPLATE_ESQL_FMG_RS_SP;
//			}
//			Template tpl2 = ve.getTemplate(templateEsqlRs);
//			StringWriter sw2 = new StringWriter();
//			tpl2.merge(vc, sw2);
//			PrintWriter out2 = new PrintWriter(particularProject.getPathPrepareRs());
//			out2.println(sw2.toString());
//			out2.close();
//
//			String templateMqScript = BAthTemplates.TEMPLATE_MQ_FMG;
//			if (particularProject.getParticularType().equalsIgnoreCase("DS")) {
//				templateMqScript = BAthTemplates.TEMPLATE_MQ_FMG_SP;
//			}
//			//MQ_ESB_DEST_SP_NombreOperacion.txt
//			tpl = ve.getTemplate(templateMqScript);
//			sw = new StringWriter();
//			tpl.merge(vc, sw);
//
//			out = new PrintWriter(particularProject.getMqPath());
//			out.println(sw.toString());
//			out.close();
//
//			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_SQL_FMG);
//			sw = new StringWriter();
//			tpl.merge(vc, sw);
//
//			out = new PrintWriter(particularProject.getSqlPath());
//			out.println(sw.toString());
//			out.close();
//			
//			tpl = ve.getTemplate(BAthTemplates.FMG_ANT_BUILD);
//			sw = new StringWriter();
//			tpl.merge(vc, sw);
//
//			out = new PrintWriter(particularProject.getProjectPath() + "build.xml");
//			out.println(sw.toString());
//			out.close();
//			
//			
//			String templateEsqlConstants = BAthTemplates.FMG_CONSTANTS;
//			if (particularProject.getParticularType().equalsIgnoreCase("DS")) {
//				templateEsqlConstants = BAthTemplates.FMG_CONSTANTS_SP;
//			}
//			tpl = ve.getTemplate(templateEsqlConstants);
//			sw = new StringWriter();
//			tpl.merge(vc, sw);
//
//			out = new PrintWriter(particularProject.getPathConstants());
//			out.println(sw.toString());
//			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
