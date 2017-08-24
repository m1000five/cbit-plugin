package com.ath.esqltool.domain;

import java.util.ArrayList;
import java.util.List;

public class BAthTemplates {

	public static final String TEMPLATE_SQL = "20.GCAM.FO.73.Categorizacion_ServiceName_Reqxxxx.sql";
	public static final String TEMPLATE_SQL_REV = "20.GCAM.FO.73.Reverso_Categorizacion_ServiceName_Reqxxxx.sql";
	public static final String TEMPLATE_XML = "ServiceNameSvc.xml";
	
	public static final String TEMPLATE_MQ = "Colas_MigracionBUS_ServiceNameSvc_Reqxxxx.mq";
	public static final String TEMPLATE_MQ_REV = "Reverso_Colas_MigracionBUS_ServiceNameSvc_Reqxxxx.mq";
	
	public static final String TEMPLATE_ESQL_FACADE = "ServiceNameSvcFcdWs_REQ.esql";
	
	//TODO modificar plantilla de flujo para nuevos nodos soapinput y soapextract preconfigurados con los valores del WSDL
	public static final String TEMPLATE_MSGFLOW_FACADE_REQ = "ServiceNameSvcFcdWs_REQ.msgflow";
	public static final String TEMPLATE_MSGFLOW_FACADE_RES = "ServiceNameSvcFcdWs_RES.msgflow";
	
	public static final String TEMPLATE_PROJECT = "athproject.xml";
	
	public static final String TEMPLATE_ANT_BUILD = "build.xml";
	
	//generator/src/ServiceNameSvcOperationName_REQ.esql
	public static final String TEMPLATE_ESQL_PARTICULAR_RQ = "ServiceNameSvcOperationName_REQ.esql";
	//generator/src/ServiceNameSvcOperationName_REQ.msgflow
	public static final String TEMPLATE_MSGFLOW_PARTICULAR = "ServiceNameSvcOperationName_REQ.msgflow";
	//generator/src/athparticular.xml
	public static final String TEMPLATE_PARTICULAR_PROJECT = "athparticular.xml"; 
	
	
	
	public static final String TEMPLATE_ESQL_CONST = "CTRL_ConstantFile.esql";
	public static final String TEMPLATE_ESQL_END_ERR = "CTRL_NombreOperacion_END_Error.esql";
	public static final String TEMPLATE_ESQL_END_ERR_RS = "CTRL_NombreOperacion_END_PrepareMsgErrorResponse.esql";
	public static final String TEMPLATE_ESQL_END_RS = "CTRL_NombreOperacion_END_PrepareMsgResponse.esql";
	public static final String TEMPLATE_MSGFLOW_END = "CTRL_NombreOperacion_END.msgflow";
	public static final String TEMPLATE_ESQL_STEP_ERR = "CTRL_NombreOperacion_STEP1_Error.esql";
	public static final String TEMPLATE_ESQL_STEP = "CTRL_NombreOperacion_STEP1_PrepareMsgFMG.esql";
	public static final String TEMPLATE_MSGFLOW_STEP = "CTRL_NombreOperacion_STEP1.msgflow";
	public static final String TEMPLATE_FMG_SP_PROJECT = "fmg_sp_project.xml";
	public static final String TEMPLATE_ADP_PROJECT = "adapter.xml";
	
	public static final String TEMPLATE_MSGFLOW_FMG_SP = "FMG_DEST_SP_NombreSp.msgflow";
	public static final String TEMPLATE_MSGFLOW_ADP = "ADP_WMB_WS.msgflow";
	
	
	public static final String TEMPLATE_ESQL_FMG_RQ_SP = "FMG_DEST_SP_NombreSp_PrepareMsgRq.esql";
	public static final String TEMPLATE_ESQL_FMG_RS = "FMG_NombreOperacion_PrepareResponseMesg.esql";
	public static final String TEMPLATE_ESQL_FMG_RS_SP = "FMG_DEST_SP_NombreSp_PrepareMsgRs.esql";
	
	public static final String TEMPLATE_SQL_FMG = "FMG_BD_NombreOperacion.sql";
	public static final String FMG_CONSTANTS = "FMG_ConstantFile.esql";
	public static final String FMG_CONSTANTS_SP = "FMG_DEST_SP_NombreSp_Constants.esql";
	public static final String TEMPLATE_MQ_FMG = "MQ_ESB_DEST_NombreOperacion.txt";
	public static final String TEMPLATE_ESQL_FMG_CALL_SP = "FMG_DEST_SP_NombreSp_CallSP.esql";
	public static final String TEMPLATE_MQ_FMG_SP = "MQ_ESB_DEST_SP_NombreOperacion.txt";
	
	
	public static final String TEMPLATE_ESQL_ADP_RQ = "ADP_WMB_WS_PrepareMsg.esql"; 
	public static final String TEMPLATE_ESQL_ADP_RS = "ADP_WMB_WS_PrepareMsgRs.esql";
	public static final String TEMPLATE_ESQL_ADP_FUNCTION = "SP_PR_OPERACION_DB_BUS.esql";
	public static final String TEMPLATE_MQ_ADP = "MQ_ADAPTER.txt";
	public static final String FMG_ANT_BUILD = "build-fmg.xml";
	
	public static final String CNTL_TEST = "test.xml";
	public static final String CNTL_TEST_REQUEST = "request1.xml";
	
	
	public static List<String> map = new ArrayList<String>();


	static {
		map.add(TEMPLATE_SQL);
		map.add(TEMPLATE_SQL_REV);
		map.add(TEMPLATE_XML);
		map.add(TEMPLATE_MQ);
		map.add(TEMPLATE_MQ_REV);
		map.add(TEMPLATE_ESQL_FACADE);
		map.add(TEMPLATE_MSGFLOW_FACADE_REQ);
		map.add(TEMPLATE_MSGFLOW_FACADE_RES);
		map.add(TEMPLATE_PROJECT);
		map.add(TEMPLATE_ANT_BUILD);
		
	}

	
	
	
	

}
