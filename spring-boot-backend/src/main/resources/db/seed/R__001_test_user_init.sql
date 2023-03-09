--V: Version
--U: Undo
--R: Repeatable
--R:prefix는 버전과 상관없이 실행이 되지만 파일의 체크섬이 변경된 경우에만 실행이 되므로 매번 실행이 되지는 않음.
delete from test_user;

select setval('test_user_user_id_seq',1,false);