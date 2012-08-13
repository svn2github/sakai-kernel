-----------------------------------------------------------------------------
-- CONTENT_RESOURCE_DELETE
-- TODO: add CONTENT_RESOURCE_BODY_BINARY_DELETE table if required
-----------------------------------------------------------------------------

CREATE TABLE CONTENT_RESOURCE_DELETE
(
    RESOURCE_ID VARCHAR (255) NOT NULL,
    RESOURCE_UUID VARCHAR (36),
	 IN_COLLECTION VARCHAR (255),
	 CONTEXT VARCHAR (99),
	 FILE_PATH VARCHAR (128),
    FILE_SIZE BIGINT,
    RESOURCE_TYPE_ID VARCHAR (255),
	 DELETE_DATE TIMESTAMP,
	 DELETE_USERID VARCHAR (36),
    XML CLOB,
    BINARY_ENTITY BLOB
);

CREATE UNIQUE INDEX CONT_RSRC_UUID_DEL ON CONTENT_RESOURCE_DELETE
(
	RESOURCE_UUID
) ALLOW REVERSE SCANS;

CREATE INDEX CONT_RSRC_DEL ON CONTENT_RESOURCE_DELETE
(
	RESOURCE_ID
) ALLOW REVERSE SCANS;
