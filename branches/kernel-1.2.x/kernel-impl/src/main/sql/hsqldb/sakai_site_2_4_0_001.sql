-- Site related tables changes needed for 2.4.0 (SAK-7341)
ALTER TABLE SAKAI_SITE ADD CUSTOM_PAGE_ORDERED CHAR(1) DEFAULT '0';
