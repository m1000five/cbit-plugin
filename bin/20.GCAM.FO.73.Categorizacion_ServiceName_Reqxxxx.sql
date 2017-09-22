/*===================================================================================================
  TIPO: insert
-----------------------------------------------------------------------------------------------------
  DESCRIPCION: 
-----------------------------------------------------------------------------------------------------
  FECHA PLANEADA PRODUCCION: DD/MM/YYYY
-----------------------------------------------------------------------------------------------------
  RESPONSABLE: FABRICA CBIT
-----------------------------------------------------------------------------------------------------
  No. RQ: ${ideRequirement}
-----------------------------------------------------------------------------------------------------
  BD AFECTADA: WSRR
-----------------------------------------------------------------------------------------------------
  TABLAS AFECTADAS: TRANCODE - MAPPING_CODE
-----------------------------------------------------------------------------------------------------
  ESQUEMA PRUEBAS PT: ORA12IIB10
-----------------------------------------------------------------------------------------------------
  ESQUEMA PRUEBAS QA: ORA12IIB10 
-----------------------------------------------------------------------------------------------------
  ESQUEMA PRODUCCION: PENDIENTE  
 -----------------------------------------------------------------------------------------------------
  NOMBRE SERVICIO: ${ServiceName}
===================================================================================================*/
/*===================================================================================================
  CODIGO FUENTE
===================================================================================================*/

-- ****PRUEBAS TECNICA (PT) Y CALIDAD (QA)****
--ALTER SESSION SET CURRENT_SCHEMA=ORA12IIB10;
-- ****PRODUCCIoN (PROD)****
---ALTER SESSION SET CURRENT_SCHEMA=ESBCATALOGS;
--SELECT SUBSTR(HOST_NAME,1,20) SERVIDOR,	sys_context('USERENV', 'DB_NAME') as Base_Datos, SUBSTR(SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA'),1,20) esquema, SUBSTR(SYS_CONTEXT('USERENV', 'HOST'), 1,20) Nombre_PC, SUBSTR(SYS_CONTEXT('USERENV', 'OS_USER'),1,30) Usuario_SO, SUBSTR(SYS_CONTEXT('USERENV', 'SESSION_USER'),1,20) Usuario_BD, TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') FECHA	FROM V$INSTANCE;

--*** TRANCODE
INSERT INTO TRANCODE (ID, IDENTIFIER, CODEVALUE, DESCRIPTION) VALUES ((SELECT MAX(ID) FROM TRANCODE) + 1, '${ServiceName}', 'C_${ServiceNameUpper}', '${ServiceNameDesc}');
INSERT INTO TRANCODE (ID, IDENTIFIER, CODEVALUE, DESCRIPTION) VALUES ((SELECT MAX(ID) FROM TRANCODE) + 1, '${ServiceName}${OperationName}', 'C_${ServiceNameAcron}${OperationNameUpper}', '${ServiceNameDesc}');

-- Codigos de error
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '300', '5', 'C_${ServiceNameUpper}', NULL, '1', 'Error de conexion con el cliente - TimeOut');
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '700', '3', 'C_${ServiceNameUpper}', NULL, '1', 'Error de conexion con el WSRR');
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '700', '4', 'C_${ServiceNameUpper}', NULL, '1', 'Error de conexion con el WSRR');
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '300', '2666', 'C_${ServiceNameUpper}', NULL, '1', 'Error al abrir Cola');

INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '200', 'env:Server', 'C_${ServiceNameAcron}${OperationNameUpper}', NULL, '1', 'Error en respuesta del servidor');
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '300', 'env:Client', 'C_${ServiceNameAcron}${OperationNameUpper}', NULL, '1', 'Error al realizar el consumo al servidor');
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '300', '5', 'C_${ServiceNameAcron}${OperationNameUpper}', NULL, '1', 'Error de conexion con el cliente - TimeOut');
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '700', '3', 'C_${ServiceNameAcron}${OperationNameUpper}', NULL, '1', 'Error de conexion con el WSRR');
INSERT INTO MAPPING_CODE(ID, BANKID, IFXCODE, HOSTCODE, TRANCODE, ACCTTYPEHOSTID, ERROR, DESC_ERROR_HOM) VALUES((SELECT MAX(ID) FROM MAPPING_CODE) + 1, '${BankId}', '700', '4', 'C_${ServiceNameAcron}${OperationNameUpper}', NULL, '1', 'Error de conexion con el WSRR');

COMMIT;