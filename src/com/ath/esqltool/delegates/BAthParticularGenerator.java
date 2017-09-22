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
			vc.put("BusinessServiceName", particularProject.getBusinessServiceName());
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
			vc.put("BusinessServiceName", particularProject.getSrvName());
			vc.put("facadeName", particularProject.getFacadeName());
			vc.put("msgReq", particularProject.getMsgReq());
			vc.put("msgRes", particularProject.getMsgRes());
			vc.put("msgFirstReq", particularProject.getFirstMsgReqElement());
			vc.put("msgFirstRes", particularProject.getFirstMsgResElement());
			
			
			String prefix = BUtil.getPrefix(particularProject.getNamespace(), null);
			vc.put("prefixns", prefix);


			File file = new File(particularProject.getPath());
			file.mkdirs();

			String templateParticular = BAthTemplates.TEMPLATE_PARTICULAR_PROJECT;

			Template tpl = ve.getTemplate(templateParticular);
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

			StringBuffer bufOtherNamespaces = new StringBuffer("");
			StringBuffer bufSpecificNamespaces = new StringBuffer("");

			if (!particularProject.getMapOthersNamespaces().isEmpty()) {

				Iterator it = particularProject.getMapOthersNamespaces().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();

					System.out.println("namespace-> " + pair.getKey() + " = " + pair.getValue());

					if (pair.getKey().toString().equalsIgnoreCase(particularProject.getNamespace())) {
						continue;
					}

					bufOtherNamespaces.append("\nDECLARE ").append(pair.getKey());
					bufOtherNamespaces.append("		NAMESPACE '");
					bufOtherNamespaces.append(pair.getValue());
					bufOtherNamespaces.append("';\n");

					if (particularProject.getPrefixauxns() == null) {
						particularProject.setPrefixauxns(pair.getKey().toString());
					}

					it.remove(); // avoids a ConcurrentModificationException
				}

			}

			if (!particularProject.isPassthrough() && !particularProject.getMapSpecificNamespaces().isEmpty()) {

				Iterator it = particularProject.getMapSpecificNamespaces().entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();

					System.out.println("specific namespace-> " + pair.getKey() + " = " + pair.getValue());
					if (pair.getKey().toString().equalsIgnoreCase(particularProject.getNamespace())) {
						continue;
					}

					bufSpecificNamespaces.append("\nDECLARE ").append(pair.getKey());
					bufSpecificNamespaces.append("		NAMESPACE '");
					bufSpecificNamespaces.append(pair.getValue());
					bufSpecificNamespaces.append("';\n");

					it.remove(); // avoids a ConcurrentModificationException
				}

			}

			vc.put("prefixauxns",
					particularProject.getPrefixauxns() != null ? particularProject.getPrefixauxns() : "aux");
			vc.put("otherNamespaces", bufOtherNamespaces.toString());

			vc.put("specificNamespaces", bufSpecificNamespaces.toString());

			String templateEsqlRq = BAthTemplates.TEMPLATE_ESQL_PARTICULAR_RQ;

			Template tpl1 = ve.getTemplate(templateEsqlRq);
			StringWriter sw1 = new StringWriter();
			tpl1.merge(vc, sw1);
			PrintWriter out1 = new PrintWriter(particularProject.getPathPrepareRq());
			out1.println(sw1.toString());
			out1.close();
			
			
			tpl = ve.getTemplate(BAthTemplates.TEMPLATE_ANT_SPECIFIC_BUILD);
			sw = new StringWriter();
			tpl.merge(vc, sw);

			out = new PrintWriter(particularProject.getProjectPath() + BAthTemplates.TEMPLATE_ANT_BUILD);
			out.println(sw.toString());
			out.close();


		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
