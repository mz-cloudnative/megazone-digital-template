drop table public.sample;
CREATE TABLE public.sample (
	id int8 NOT NULL,
	"name" varchar(255) NOT NULL,
	reg_dtt timestamp NOT NULL,
	CONSTRAINT sample_pkey PRIMARY KEY (id)
);