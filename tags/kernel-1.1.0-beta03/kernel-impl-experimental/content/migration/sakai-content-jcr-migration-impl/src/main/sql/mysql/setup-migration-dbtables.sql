-- This script is for creating the tables for JCR Content Hosting and JCR 
-- MailArchive migration.  Each table has the ID's of the things to copy
-- and a status indicator.
--
-- This Script is for MySQL

-- Status codes
--
--   0 = Not Started
--   1 = Completed

-- Table and Migration Data for Content Hosting Resources and Collections
--DROP TABLE IF EXISTS MIGRATE_CHS_CONTENT_TO_JCR;
CREATE TABLE MIGRATE_CHS_CONTENT_TO_JCR (
  id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  CONTENT_ID varchar(255),
  STATUS int NOT NULL DEFAULT 0,
  TIME_ADDED_TO_QUEUE datetime,
  -- ORIGINAL_MIGRATION will be used for the 
  -- original copy. Things added to the queue 
  -- later will use their actual event code, ex
  -- content.new, content.delete, etc
  EVENT_TYPE varchar(32) DEFAULT 'ORIGINAL_MIGRATION'
  
);

-- Indexes
CREATE INDEX MIGRATE_CHS_CONTENT_TO_JCR_STATUS_INDEX
  ON MIGRATE_CHS_CONTENT_TO_JCR(STATUS);
CREATE INDEX MIGRATE_CHS_CONTENT_TO_JCR_TIME_ADDED_INDEX
  ON MIGRATE_CHS_CONTENT_TO_JCR(TIME_ADDED_TO_QUEUE);
CREATE INDEX MIGRATE_CHS_CONTENT_TO_JCR_EVENT_TYPE_INDEX
  ON MIGRATE_CHS_CONTENT_TO_JCR(EVENT_TYPE);

-- Table and Migration Data for Content Hosting Collections
-- DROP TABLE IF EXISTS MIGRATE_JCR_CONTENT_COLLECTION;
-- CREATE TABLE MIGRATE_JCR_CONTENT_COLLECTION (
--   id INT NOT NULL AUTO_INCREMENT,
--   PRIMARY KEY (id),
--   COLLECTION_ID varchar(255),
--   STATUS int NOT NULL DEFAULT 0 );

-- INSERT INTO MIGRATE_JCR_CONTENT_COLLECTION 
--   (COLLECTION_ID)
--   SELECT CONTENT_COLLECTION.COLLECTION_ID
--   FROM CONTENT_COLLECTION;

-- Table and Migration Data for Content Hosting Resources
-- DROP TABLE IF EXISTS MIGRATE_JCR_CONTENT_RESOURCE;
-- CREATE TABLE MIGRATE_JCR_CONTENT_RESOURCE (
--   id INT NOT NULL AUTO_INCREMENT,
--   PRIMARY KEY (id),
--   RESOURCE_ID varchar(255),
--   RESOURCE_UUID varchar(36),
--   STATUS int NOT NULL DEFAULT 0 );

-- INSERT INTO MIGRATE_JCR_CONTENT_RESOURCE 
--   (RESOURCE_ID, RESOURCE_UUID)
--   SELECT CONTENT_RESOURCE.RESOURCE_ID, CONTENT_RESOURCE.RESOURCE_UUID
--   FROM CONTENT_RESOURCE;