CREATE TABLE sakai_event (EVENT char(32) NULL, EVENT_CODE char(1) NULL, EVENT_DATE timestamp NULL, EVENT_ID longlong NOT NULL, ID longlong NOT NULL, REF char(255) NULL, SESSION_ID char(163) NULL);
CREATE TABLE sakai_locks (LOCK_TIME timestamp NULL, RECORD_ID char(512) NULL, TABLE_NAME char(64) NULL, USAGE_SESSION_ID char(36) NULL, id longlong NOT NULL);
CREATE TABLE sakai_realm_role (ROLE_KEY long NOT NULL, ROLE_NAME char(99) NOT NULL);
CREATE TABLE sakai_site_group_property (GROUP_ID char(99) NOT NULL, NAME char(99) NOT NULL, SITE_ID char(99) NOT NULL, VALUE text NULL);
CREATE TABLE SAKAI_USER_PROPERTY (NAME char(99) NOT NULL, USER_ID char(99) NOT NULL, VALUE text NULL);
CREATE TABLE content_resource_delete (BINARY_ENTITY object NULL, CONTEXT char(99) NULL, DELETE_DATE date NULL, DELETE_USERID char(36) NULL, FILE_PATH char(128) NULL, FILE_SIZE longlong NULL, ID longlong NOT NULL, IN_COLLECTION char(255) NULL, RESOURCE_ID char(255) NOT NULL, RESOURCE_TYPE_ID char(255) NULL, RESOURCE_UUID char(36) NULL, XML text NULL);
CREATE TABLE SAKAI_SITE_PROPERTY (NAME char(99) NOT NULL, SITE_ID char(99) NOT NULL, VALUE text NULL);
CREATE TABLE sakai_site_group (DESCRIPTION text NULL, GROUP_ID char(99) NOT NULL, ID longlong NOT NULL, SITE_ID char(99) NOT NULL, TITLE char(99) NULL);
CREATE TABLE content_resource_body_binary (BODY object NULL, ID longlong NOT NULL, RESOURCE_ID char(255) NOT NULL);
CREATE TABLE content_resource (BINARY_ENTITY object NULL, CONTEXT char(99) NULL, FILE_PATH char(128) NULL, FILE_SIZE longlong NULL, ID longlong NOT NULL, IN_COLLECTION char(255) NULL, RESOURCE_ID char(255) NOT NULL, RESOURCE_TYPE_ID char(255) NULL, RESOURCE_UUID char(36) NULL, XML text NULL);
CREATE TABLE sakai_alias (ALIAS_ID char(99) NOT NULL, CREATEDBY char(99) NOT NULL, CREATEDON timestamp NOT NULL, ID longlong NOT NULL, MODIFIEDBY char(99) NOT NULL, MODIFIEDON timestamp NOT NULL, TARGET char(255) NULL);
CREATE TABLE SAKAI_REALM_PROVIDER (PROVIDER_ID char(200) NOT NULL, REALM_KEY long NOT NULL);
CREATE TABLE SAKAI_ALIAS_PROPERTY (ALIAS_ID char(99) NOT NULL, NAME char(99) NOT NULL, VALUE text NULL);
CREATE TABLE content_collection (BINARY_ENTITY object NULL, COLLECTION_ID char(255) NOT NULL, ID longlong NOT NULL, IN_COLLECTION char(255) NULL, XML text NULL);
CREATE TABLE SAKAI_SITE_PAGE (ID longlong NOT NULL, LAYOUT char(1) NULL, PAGE_ID char(99) NOT NULL, POPUP char(1) NULL, SITE_ID char(99) NOT NULL, SITE_ORDER long NOT NULL, TITLE char(99) NULL);
CREATE TABLE sakai_site (CREATEDBY char(99) NULL, CREATEDON timestamp NULL, CUSTOM_PAGE_ORDERED char(1) NULL, DESCRIPTION text NULL, ICON_URL char(255) NULL, ID longlong NOT NULL, INFO_URL char(255) NULL, IS_SPECIAL char(1) NULL, IS_USER char(1) NULL, JOINABLE char(1) NULL, JOIN_ROLE char(99) NULL, MODIFIEDBY char(99) NULL, MODIFIEDON timestamp NULL, PUBLISHED long NULL, PUBVIEW char(1) NULL, SHORT_DESC text NULL, SITE_ID char(99) NOT NULL, SKIN char(255) NULL, TITLE char(99) NULL, TYPE char(99) NULL);
CREATE TABLE sakai_preferences (ID longlong NOT NULL, PREFERENCES_ID char(99) NOT NULL, XML text NULL);
CREATE TABLE SAKAI_CLUSTER (ID longlong NOT NULL, SERVER_ID char(64) NOT NULL, UPDATE_TIME timestamp NULL);
CREATE TABLE SAKAI_REALM_RL_FN (FUNCTION_KEY long NOT NULL, REALM_KEY long NOT NULL, ROLE_KEY long NOT NULL);
CREATE TABLE sakai_user_id_map (EID char(255) NOT NULL, USER_ID char(99) NOT NULL);
CREATE TABLE SAKAI_REALM_RL_GR (ACTIVE char(1) NULL, PROVIDED char(1) NULL, REALM_KEY long NOT NULL, ROLE_KEY long NOT NULL, USER_ID char(99) NOT NULL);
CREATE TABLE content_dropbox_changes (DROPBOX_ID char(255) NOT NULL, ID longlong NOT NULL, IN_COLLECTION char(255) NULL, LAST_UPDATE char(24) NULL);
CREATE TABLE content_resource_lock (asset_id char(36) NULL, date_added timestamp NULL, date_removed timestamp NULL, id longlong NOT NULL, is_active boolean NULL, is_system boolean NULL, qualifier_id char(36) NULL, reason char(36) NULL);
CREATE TABLE sakai_realm_function (FUNCTION_KEY long NOT NULL, FUNCTION_NAME char(99) NOT NULL, ID longlong NOT NULL);
CREATE TABLE SAKAI_REALM_ROLE_DESC (DESCRIPTION text NULL, PROVIDER_ONLY char(1) NULL, REALM_KEY long NOT NULL, ROLE_KEY long NOT NULL);
CREATE TABLE sakai_site_page_property (NAME char(99) NOT NULL, PAGE_ID char(99) NOT NULL, SITE_ID char(99) NOT NULL, VALUE text NULL);
CREATE TABLE content_type_registry (CONTEXT_ID char(99) NOT NULL, ENABLED char(1) NULL, ID long NOT NULL, RESOURCE_TYPE_ID char(255) NULL);
CREATE TABLE sakai_session (ID longlong NOT NULL, SESSION_ACTIVE boolean NULL, SESSION_END timestamp NULL, SESSION_ID char(36) NOT NULL, SESSION_IP char(128) NULL, SESSION_SERVER char(64) NULL, SESSION_START timestamp NULL, SESSION_USER char(99) NULL, SESSION_USER_AGENT char(255) NULL);
CREATE TABLE sakai_notification (ID binary NOT NULL, NOTIFICATION_ID char(99) NOT NULL, XML text NULL);
CREATE TABLE sakai_site_user (PERMISSION long NOT NULL, SITE_ID char(99) NOT NULL, USER_ID char(99) NOT NULL);
CREATE TABLE SAKAI_REALM (CREATEDBY char(99) NULL, CREATEDON timestamp NULL, ID longlong NOT NULL, MAINTAIN_ROLE long NULL, MODIFIEDBY char(99) NULL, MODIFIEDON timestamp NULL, PROVIDER_ID char(1024) NULL, REALM_ID char(255) NOT NULL, REALM_KEY long NOT NULL);
CREATE TABLE sakai_user (CREATEDBY char(99) NOT NULL, CREATEDON timestamp NOT NULL, EMAIL char(255) NULL, EMAIL_LC char(255) NULL, FIRST_NAME char(255) NULL, ID longlong NOT NULL, LAST_NAME char(255) NULL, MODIFIEDBY char(99) NOT NULL, MODIFIEDON timestamp NOT NULL, PW char(255) NULL, TYPE char(255) NULL, USER_ID char(99) NOT NULL);
CREATE TABLE sakai_site_tool_property (NAME char(99) NOT NULL, SITE_ID char(99) NOT NULL, TOOL_ID char(99) NOT NULL, VALUE text NULL);
CREATE TABLE SAKAI_SITE_TOOL (ID longlong NOT NULL, LAYOUT_HINTS char(99) NULL, PAGE_ID char(99) NOT NULL, PAGE_ORDER long NOT NULL, REGISTRATION char(99) NOT NULL, SITE_ID char(99) NOT NULL, TITLE char(99) NULL, TOOL_ID char(99) NOT NULL);
CREATE TABLE SAKAI_REALM_PROPERTY (NAME char(99) NOT NULL, REALM_KEY long NOT NULL, VALUE text NULL);
CREATE TABLE sakai_digest (DIGEST_ID char(99) NOT NULL, ID longlong NOT NULL, XML text NULL);
INSERT INTO _SYS_RELATIONSHIP (dest_table, dest_column, source_table, source_column, block_delete, cascade_delete, one_to_many, operator, relationshipName) VALUES ('SAKAI_REALM_RL_FN', 'ROLE_KEY', 'sakai_realm_role', 'ROLE_KEY', 0, 0, '1', '=', 'toSakaiRealmRole');
INSERT INTO _SYS_RELATIONSHIP (dest_table, dest_column, source_table, source_column, block_delete, cascade_delete, one_to_many, operator, relationshipName) VALUES ('SAKAI_REALM_RL_GR', 'ROLE_KEY', 'sakai_realm_role', 'ROLE_KEY', 0, 0, '1', '=', 'toSakaiRealmRole');
INSERT INTO _SYS_RELATIONSHIP (dest_table, dest_column, source_table, source_column, block_delete, cascade_delete, one_to_many, operator, relationshipName) VALUES ('SAKAI_REALM_ROLE_DESC', 'ROLE_KEY', 'sakai_realm_role', 'ROLE_KEY', 0, 0, '1', '=', 'toSakaiRealmRole');
INSERT INTO _SYS_RELATIONSHIP (dest_table, dest_column, source_table, source_column, block_delete, cascade_delete, one_to_many, operator, relationshipName) VALUES ('SAKAI_REALM', 'MAINTAIN_ROLE', 'sakai_realm_role', 'ROLE_KEY', 0, 0, '1', '=', 'toSakaiRealmRole');
CREATE PRIMARY KEY SAKAI_ALIAS_PROPERTY (ALIAS_ID, NAME);
CREATE INDEX SAKAI_ALIAS_PROPERTY (ALIAS_ID, NAME);
CREATE PRIMARY KEY SAKAI_CLUSTER (ID);
CREATE UNIQUE INDEX SAKAI_CLUSTER (ID);
CREATE PRIMARY KEY SAKAI_REALM (ID);
CREATE UNIQUE INDEX SAKAI_REALM (ID);
CREATE PRIMARY KEY SAKAI_REALM_PROPERTY (NAME, REALM_KEY);
CREATE INDEX SAKAI_REALM_PROPERTY (NAME, REALM_KEY);
CREATE PRIMARY KEY SAKAI_REALM_PROVIDER (PROVIDER_ID, REALM_KEY);
CREATE INDEX SAKAI_REALM_PROVIDER (PROVIDER_ID, REALM_KEY);
CREATE PRIMARY KEY SAKAI_REALM_RL_FN (FUNCTION_KEY, REALM_KEY, ROLE_KEY);
CREATE INDEX SAKAI_REALM_RL_FN (FUNCTION_KEY, REALM_KEY, ROLE_KEY);
CREATE PRIMARY KEY SAKAI_REALM_RL_GR (REALM_KEY, USER_ID);
CREATE INDEX SAKAI_REALM_RL_GR (REALM_KEY, USER_ID);
CREATE PRIMARY KEY SAKAI_REALM_ROLE_DESC (REALM_KEY, ROLE_KEY);
CREATE INDEX SAKAI_REALM_ROLE_DESC (REALM_KEY, ROLE_KEY);
CREATE PRIMARY KEY SAKAI_SITE_PAGE (ID);
CREATE UNIQUE INDEX SAKAI_SITE_PAGE (ID);
CREATE PRIMARY KEY SAKAI_SITE_PROPERTY (NAME, SITE_ID);
CREATE INDEX SAKAI_SITE_PROPERTY (NAME, SITE_ID);
CREATE PRIMARY KEY SAKAI_SITE_TOOL (ID);
CREATE UNIQUE INDEX SAKAI_SITE_TOOL (ID);
CREATE PRIMARY KEY SAKAI_USER_PROPERTY (NAME, USER_ID);
CREATE INDEX SAKAI_USER_PROPERTY (NAME, USER_ID);
CREATE PRIMARY KEY content_collection (ID);
CREATE UNIQUE INDEX content_collection (ID);
CREATE PRIMARY KEY content_dropbox_changes (ID);
CREATE UNIQUE INDEX content_dropbox_changes (ID);
CREATE PRIMARY KEY content_resource (ID);
CREATE UNIQUE INDEX content_resource (ID);
CREATE PRIMARY KEY content_resource_body_binary (ID);
CREATE UNIQUE INDEX content_resource_body_binary (ID);
CREATE PRIMARY KEY content_resource_delete (ID);
CREATE UNIQUE INDEX content_resource_delete (ID);
CREATE PRIMARY KEY content_resource_lock (id);
CREATE UNIQUE INDEX content_resource_lock (id);
CREATE PRIMARY KEY content_type_registry (ID);
CREATE UNIQUE INDEX content_type_registry (ID);
CREATE PRIMARY KEY sakai_alias (ID);
CREATE UNIQUE INDEX sakai_alias (ID);
CREATE PRIMARY KEY sakai_digest (ID);
CREATE UNIQUE INDEX sakai_digest (ID);
CREATE PRIMARY KEY sakai_event (ID);
CREATE UNIQUE INDEX sakai_event (ID);
CREATE PRIMARY KEY sakai_locks (id);
CREATE UNIQUE INDEX sakai_locks (id);
CREATE PRIMARY KEY sakai_notification (ID);
CREATE UNIQUE INDEX sakai_notification (ID);
CREATE PRIMARY KEY sakai_preferences (ID);
CREATE UNIQUE INDEX sakai_preferences (ID);
CREATE PRIMARY KEY sakai_realm_function (ID);
CREATE UNIQUE INDEX sakai_realm_function (ID);
CREATE PRIMARY KEY sakai_realm_role (ROLE_KEY);
CREATE UNIQUE INDEX sakai_realm_role (ROLE_KEY);
CREATE PRIMARY KEY sakai_session (ID);
CREATE UNIQUE INDEX sakai_session (ID);
CREATE PRIMARY KEY sakai_site (ID);
CREATE UNIQUE INDEX sakai_site (ID);
CREATE PRIMARY KEY sakai_site_group (ID);
CREATE UNIQUE INDEX sakai_site_group (ID);
CREATE PRIMARY KEY sakai_site_group_property (GROUP_ID, NAME);
CREATE INDEX sakai_site_group_property (GROUP_ID, NAME);
CREATE PRIMARY KEY sakai_site_page_property (NAME, PAGE_ID);
CREATE INDEX sakai_site_page_property (NAME, PAGE_ID);
CREATE PRIMARY KEY sakai_site_tool_property (NAME, TOOL_ID);
CREATE INDEX sakai_site_tool_property (NAME, TOOL_ID);
CREATE PRIMARY KEY sakai_site_user (SITE_ID, USER_ID);
CREATE INDEX sakai_site_user (SITE_ID, USER_ID);
CREATE PRIMARY KEY sakai_user (ID);
CREATE UNIQUE INDEX sakai_user (ID);
CREATE PRIMARY KEY sakai_user_id_map (USER_ID);
CREATE UNIQUE INDEX sakai_user_id_map (USER_ID);
