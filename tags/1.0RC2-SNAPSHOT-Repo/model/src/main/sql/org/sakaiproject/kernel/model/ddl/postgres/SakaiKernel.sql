CREATE TABLE content_collection (BINARY_ENTITY bytea NULL, COLLECTION_ID varchar(255) NOT NULL, ID bigint NOT NULL, IN_COLLECTION varchar(255) NULL, XML text NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_ALIAS_PROPERTY (ALIAS_ID varchar(99) NOT NULL, NAME varchar(99) NOT NULL, VALUE text NULL, PRIMARY KEY (ALIAS_ID, NAME));
CREATE TABLE sakai_notification (ID bytea NOT NULL, NOTIFICATION_ID varchar(99) NOT NULL, XML text NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_session (ID bigint NOT NULL, SESSION_ACTIVE boolean NULL, SESSION_END timestamp with time zone NULL, SESSION_ID varchar(36) NOT NULL, SESSION_IP varchar(128) NULL, SESSION_SERVER varchar(64) NULL, SESSION_START timestamp with time zone NULL, SESSION_USER varchar(99) NULL, SESSION_USER_AGENT varchar(255) NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_site_group_property (GROUP_ID varchar(99) NOT NULL, NAME varchar(99) NOT NULL, SITE_ID varchar(99) NOT NULL, VALUE text NULL, PRIMARY KEY (GROUP_ID, NAME));
CREATE TABLE sakai_site (CREATEDBY varchar(99) NULL, CREATEDON timestamp with time zone NULL, CUSTOM_PAGE_ORDERED character(1) NULL, DESCRIPTION text NULL, ICON_URL varchar(255) NULL, ID bigint NOT NULL, INFO_URL varchar(255) NULL, IS_SPECIAL character(1) NULL, IS_USER character(1) NULL, JOINABLE character(1) NULL, JOIN_ROLE varchar(99) NULL, MODIFIEDBY varchar(99) NULL, MODIFIEDON timestamp with time zone NULL, PUBLISHED integer NULL, PUBVIEW character(1) NULL, SHORT_DESC text NULL, SITE_ID varchar(99) NOT NULL, SKIN varchar(255) NULL, TITLE varchar(99) NULL, TYPE varchar(99) NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_event (EVENT varchar(32) NULL, EVENT_CODE varchar(1) NULL, EVENT_DATE timestamp with time zone NULL, EVENT_ID bigint NOT NULL, ID bigint NOT NULL, REF varchar(255) NULL, SESSION_ID varchar(163) NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_site_tool_property (NAME varchar(99) NOT NULL, SITE_ID varchar(99) NOT NULL, TOOL_ID varchar(99) NOT NULL, VALUE text NULL, PRIMARY KEY (NAME, TOOL_ID));
CREATE TABLE sakai_site_page_property (NAME varchar(99) NOT NULL, PAGE_ID varchar(99) NOT NULL, SITE_ID varchar(99) NOT NULL, VALUE text NULL, PRIMARY KEY (NAME, PAGE_ID));
CREATE TABLE content_resource_delete (BINARY_ENTITY bytea NULL, CONTEXT varchar(99) NULL, DELETE_DATE date NULL, DELETE_USERID varchar(36) NULL, FILE_PATH varchar(128) NULL, FILE_SIZE bigint NULL, ID bigint NOT NULL, IN_COLLECTION varchar(255) NULL, RESOURCE_ID varchar(255) NOT NULL, RESOURCE_TYPE_ID varchar(255) NULL, RESOURCE_UUID varchar(36) NULL, XML text NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_REALM_PROVIDER (PROVIDER_ID varchar(200) NOT NULL, REALM_KEY integer NOT NULL, PRIMARY KEY (PROVIDER_ID, REALM_KEY));
CREATE TABLE SAKAI_REALM_PROPERTY (NAME varchar(99) NOT NULL, REALM_KEY integer NOT NULL, VALUE text NULL, PRIMARY KEY (NAME, REALM_KEY));
CREATE TABLE SAKAI_SITE_TOOL (ID bigint NOT NULL, LAYOUT_HINTS varchar(99) NULL, PAGE_ID varchar(99) NOT NULL, PAGE_ORDER integer NOT NULL, REGISTRATION varchar(99) NOT NULL, SITE_ID varchar(99) NOT NULL, TITLE varchar(99) NULL, TOOL_ID varchar(99) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_site_group (DESCRIPTION text NULL, GROUP_ID varchar(99) NOT NULL, ID bigint NOT NULL, SITE_ID varchar(99) NOT NULL, TITLE varchar(99) NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_SITE_PROPERTY (NAME varchar(99) NOT NULL, SITE_ID varchar(99) NOT NULL, VALUE text NULL, PRIMARY KEY (NAME, SITE_ID));
CREATE TABLE SAKAI_SITE_PAGE (ID bigint NOT NULL, LAYOUT character(1) NULL, PAGE_ID varchar(99) NOT NULL, POPUP character(1) NULL, SITE_ID varchar(99) NOT NULL, SITE_ORDER integer NOT NULL, TITLE varchar(99) NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_USER_PROPERTY (NAME varchar(99) NOT NULL, USER_ID varchar(99) NOT NULL, VALUE text NULL, PRIMARY KEY (NAME, USER_ID));
CREATE TABLE content_resource (BINARY_ENTITY bytea NULL, CONTEXT varchar(99) NULL, FILE_PATH varchar(128) NULL, FILE_SIZE bigint NULL, ID bigint NOT NULL, IN_COLLECTION varchar(255) NULL, RESOURCE_ID varchar(255) NOT NULL, RESOURCE_TYPE_ID varchar(255) NULL, RESOURCE_UUID varchar(36) NULL, XML text NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_preferences (ID bigint NOT NULL, PREFERENCES_ID varchar(99) NOT NULL, XML text NULL, PRIMARY KEY (ID));
CREATE TABLE content_resource_lock (asset_id varchar(36) NULL, date_added timestamp with time zone NULL, date_removed timestamp with time zone NULL, id bigint NOT NULL, is_active boolean NULL, is_system boolean NULL, qualifier_id varchar(36) NULL, reason varchar(36) NULL, PRIMARY KEY (id));
CREATE TABLE sakai_locks (LOCK_TIME timestamp with time zone NULL, RECORD_ID varchar(512) NULL, TABLE_NAME varchar(64) NULL, USAGE_SESSION_ID varchar(36) NULL, id bigint NOT NULL, PRIMARY KEY (id));
CREATE TABLE sakai_user_id_map (EID varchar(255) NOT NULL, USER_ID varchar(99) NOT NULL, PRIMARY KEY (USER_ID));
CREATE TABLE sakai_digest (DIGEST_ID varchar(99) NOT NULL, ID bigint NOT NULL, XML text NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_alias (ALIAS_ID varchar(99) NOT NULL, CREATEDBY varchar(99) NOT NULL, CREATEDON timestamp with time zone NOT NULL, ID bigint NOT NULL, MODIFIEDBY varchar(99) NOT NULL, MODIFIEDON timestamp with time zone NOT NULL, TARGET varchar(255) NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_CLUSTER (ID bigint NOT NULL, SERVER_ID varchar(64) NOT NULL, UPDATE_TIME timestamp with time zone NULL, PRIMARY KEY (ID));
CREATE TABLE content_dropbox_changes (DROPBOX_ID varchar(255) NOT NULL, ID bigint NOT NULL, IN_COLLECTION varchar(255) NULL, LAST_UPDATE varchar(24) NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_realm_role (ROLE_KEY integer NOT NULL, ROLE_NAME varchar(99) NOT NULL, PRIMARY KEY (ROLE_KEY));
CREATE TABLE content_resource_body_binary (BODY bytea NULL, ID bigint NOT NULL, RESOURCE_ID varchar(255) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE content_type_registry (CONTEXT_ID varchar(99) NOT NULL, ENABLED varchar(1) NULL, ID integer NOT NULL, RESOURCE_TYPE_ID varchar(255) NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_REALM_ROLE_DESC (DESCRIPTION text NULL, PROVIDER_ONLY character(1) NULL, REALM_KEY integer NOT NULL, ROLE_KEY integer NOT NULL, PRIMARY KEY (REALM_KEY, ROLE_KEY));
CREATE TABLE sakai_user (CREATEDBY varchar(99) NOT NULL, CREATEDON timestamp with time zone NOT NULL, EMAIL varchar(255) NULL, EMAIL_LC varchar(255) NULL, FIRST_NAME varchar(255) NULL, ID bigint NOT NULL, LAST_NAME varchar(255) NULL, MODIFIEDBY varchar(99) NOT NULL, MODIFIEDON timestamp with time zone NOT NULL, PW varchar(255) NULL, TYPE varchar(255) NULL, USER_ID varchar(99) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE sakai_realm_function (FUNCTION_KEY integer NOT NULL, FUNCTION_NAME varchar(99) NOT NULL, ID bigint NOT NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_REALM_RL_FN (FUNCTION_KEY integer NOT NULL, REALM_KEY integer NOT NULL, ROLE_KEY integer NOT NULL, PRIMARY KEY (FUNCTION_KEY, REALM_KEY, ROLE_KEY));
CREATE TABLE sakai_site_user (PERMISSION integer NOT NULL, SITE_ID varchar(99) NOT NULL, USER_ID varchar(99) NOT NULL, PRIMARY KEY (SITE_ID, USER_ID));
CREATE TABLE SAKAI_REALM (CREATEDBY varchar(99) NULL, CREATEDON timestamp with time zone NULL, ID bigint NOT NULL, MAINTAIN_ROLE integer NULL, MODIFIEDBY varchar(99) NULL, MODIFIEDON timestamp with time zone NULL, PROVIDER_ID varchar(1024) NULL, REALM_ID varchar(255) NOT NULL, REALM_KEY integer NOT NULL, PRIMARY KEY (ID));
CREATE TABLE SAKAI_REALM_RL_GR (ACTIVE character(1) NULL, PROVIDED character(1) NULL, REALM_KEY integer NOT NULL, ROLE_KEY integer NOT NULL, USER_ID varchar(99) NOT NULL, PRIMARY KEY (REALM_KEY, USER_ID));
ALTER TABLE SAKAI_REALM_ROLE_DESC ADD FOREIGN KEY (ROLE_KEY) REFERENCES sakai_realm_role (ROLE_KEY);
ALTER TABLE SAKAI_REALM_RL_FN ADD FOREIGN KEY (ROLE_KEY) REFERENCES sakai_realm_role (ROLE_KEY);
ALTER TABLE SAKAI_REALM ADD FOREIGN KEY (MAINTAIN_ROLE) REFERENCES sakai_realm_role (ROLE_KEY);
ALTER TABLE SAKAI_REALM_RL_GR ADD FOREIGN KEY (ROLE_KEY) REFERENCES sakai_realm_role (ROLE_KEY);
CREATE SEQUENCE pk_sakai_alias_property INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_cluster INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm_property INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm_provider INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm_rl_fn INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm_rl_gr INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm_role_desc INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_page INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_property INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_tool INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_user_property INCREMENT 20 START 200;
CREATE SEQUENCE pk_content_collection INCREMENT 20 START 200;
CREATE SEQUENCE pk_content_dropbox_changes INCREMENT 20 START 200;
CREATE SEQUENCE pk_content_resource INCREMENT 20 START 200;
CREATE SEQUENCE pk_content_resource_body_binary INCREMENT 20 START 200;
CREATE SEQUENCE pk_content_resource_delete INCREMENT 20 START 200;
CREATE SEQUENCE pk_content_resource_lock INCREMENT 20 START 200;
CREATE SEQUENCE pk_content_type_registry INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_alias INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_digest INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_event INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_locks INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_notification INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_preferences INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm_function INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_realm_role INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_session INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_group INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_group_property INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_page_property INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_tool_property INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_site_user INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_user INCREMENT 20 START 200;
CREATE SEQUENCE pk_sakai_user_id_map INCREMENT 20 START 200;
