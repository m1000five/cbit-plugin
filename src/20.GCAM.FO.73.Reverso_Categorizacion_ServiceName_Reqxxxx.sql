/*===================================================================================================
  TIPO: delete
-----------------------------------------------------------------------------------------------------
  DESCRIPCION: Eliminacion de los Codigos de  Error.
-----------------------------------------------------------------------------------------------------
  FECHA PLANEADA PRODUCCION: DD/MM/YYYY
-----------------------------------------------------------------------------------------------------
  RESPONSABLE: FABRICA CBIT
-----------------------------------------------------------------------------------------------------
  No. RQ: ${ideRequirement}
-----------------------------------------------------------------------------------------------------
  BD AFECTADA: WSRR
-----------------------------------------------------------------------------------------------------
  TABLAS AFECTADAS: MAPPING_CODE - TRANCODE
-----------------------------------------------------------------------------------------------------
  ESQUEMA PRUEBAS PT: PENDIENTE
-----------------------------------------------------------------------------------------------------
  ESQUEMA PRUEBAS QA: PENDIENTE 
-----------------------------------------------------------------------------------------------------
  ESQUEMA PRODUCCION: PENDIENTE  
 -----------------------------------------------------------------------------------------------------
  NOMBRE SERVICIO: ${ServiceNameUpper}
===================================================================================================*/
/*===================================================================================================
  CODIGO FUENTE
===================================================================================================*/
-- ****PRUEBAS TECNICA (PT) Y CALIDAD (QA)****
--ALTER SESSION SET CURRENT_SCHEMA=DB2ESBI1;
-- ****PRODUCCIÓN (PROD)****
--ALTER SESSION SET CURRENT_SCHEMA=ESBCATALOGS;

--SELECT SUBSTR(HOST_NAME,1,20) SERVIDOR,	sys_context('USERENV', 'DB_NAME') as Base_Datos, SUBSTR(SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA'),1,20) esquema, SUBSTR(SYS_CONTEXT('USERENV', 'HOST'), 1,20) Nombre_PC, SUBSTR(SYS_CONTEXT('USERENV', 'OS_USER'),1,30) Usuario_SO, SUBSTR(SYS_CONTEXT('USERENV', 'SESSION_USER'),1,20) Usuario_BD, TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') FECHA	FROM V$INSTANCE;

------------------------------------------------------ 

DELETE MAPPING_CODE WHERE TRANCODE IN ('C_${ServiceNameUpper}','C_${ServiceNameAcron}${OperationNameUpper}') AND BANKID IN ('00010524', '0052');
DELETE TRANCODE WHERE CODEVALUE IN('C_${ServiceNameUpper}','C_${ServiceNameAcron}${OperationNameUpper}');

COMMIT;