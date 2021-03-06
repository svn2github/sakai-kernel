-----------------------------------------------------------------------------
-- SAKAI_EVENT
-----------------------------------------------------------------------------

CREATE TABLE SAKAI_EVENT
(
	EVENT_ID BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	EVENT_DATE TIMESTAMP,
	EVENT VARCHAR (32),
	REF VARCHAR (255),
	CONTEXT VARCHAR (255),
	SESSION_ID VARCHAR (36),
	EVENT_CODE CHAR (1),
	PRIMARY KEY (EVENT_ID)
);

CREATE INDEX EVENT_SESSION_ID ON SAKAI_EVENT 
(
	SESSION_ID	ASC
) ALLOW REVERSE SCANS;

