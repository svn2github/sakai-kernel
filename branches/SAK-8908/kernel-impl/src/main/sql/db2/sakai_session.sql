-----------------------------------------------------------------------------
-- SAKAI_SESSION
-----------------------------------------------------------------------------

CREATE TABLE SAKAI_SESSION
(
	SESSION_ID VARCHAR (36),
	SESSION_SERVER VARCHAR (64),
	SESSION_USER VARCHAR (99),
	SESSION_IP VARCHAR (128),
	SESSION_HOSTNAME VARCHAR (255),
	SESSION_USER_AGENT VARCHAR (255),
	SESSION_START TIMESTAMP,
	SESSION_END TIMESTAMP,
	SESSION_ACTIVE smallint	
);

CREATE UNIQUE INDEX SESSION_INDEX ON SAKAI_SESSION
(
	SESSION_ID 
) ALLOW REVERSE SCANS;

CREATE INDEX SESSION_SRV_INDEX ON SAKAI_SESSION
(
	SESSION_SERVER
);

CREATE INDEX SESSION_START_END ON SAKAI_SESSION
(
	SESSION_START,
	SESSION_END,
	SESSION_ID
) ALLOW REVERSE SCANS;

CREATE INDEX SESSION_ACTIVE_IE ON SAKAI_SESSION
(
	SESSION_ACTIVE
) ALLOW REVERSE SCANS;
