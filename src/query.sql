spool ON
spool results.txt

SET heading OFF
SET PAGESIZE 0
set longchunksize 90000
set linesize 2000
set long 90000
set verify off

SELECT 
MESSAGE
FROM DB2ESBI1.MESSAGE_LOG
Where
RQUID = &1
ORDER BY MESSAGE_WAY_ID ASC;


SELECT 
*
FROM DB2ESBI1.ERROR_LOG
Where
RQUID = &1;

spool off
