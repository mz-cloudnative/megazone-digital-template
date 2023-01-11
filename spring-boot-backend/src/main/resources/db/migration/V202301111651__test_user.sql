create sequence test_user_user_id_seq;
ALTER TABLE test_user ALTER COLUMN  user_id set default nextval('test_user_user_id_seq');