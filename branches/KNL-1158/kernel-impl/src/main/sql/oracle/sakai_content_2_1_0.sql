ALTER TABLE CONTENT_RESOURCE ADD (RESOURCE_UUID VARCHAR2 (36));

CREATE INDEX CONTENT_UUID_RESOURCE_INDEX ON CONTENT_RESOURCE
(
	RESOURCE_UUID
);
