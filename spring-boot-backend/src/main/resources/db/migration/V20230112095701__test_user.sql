CREATE TABLE test_user (
	user_id bigserial NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT test_user_pkey PRIMARY KEY (user_id)
);