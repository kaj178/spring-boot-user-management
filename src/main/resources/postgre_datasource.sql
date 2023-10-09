CREATE DATEBASE "public-api";
SET search_path TO "public-api";

CREATE TABLE "public-api".public.role (
	role_id VARCHAR(10) NOT NULL,
	role_name VARCHAR(20) NOT NULL,
	PRIMARY KEY (role_id)
);

CREATE TABLE "public-api".public.accounts (
	username VARCHAR(200) NOT NULL UNIQUE,
	password VARCHAR(200) NOT NULL,
	role_id VARCHAR(10) NOT NULL,
	PRIMARY KEY (username)
);
ALTER TABLE public.accounts ADD CONSTRAINT fk_accounts_roles FOREIGN KEY (role_id) REFERENCES public.role(role_id);

CREATE TABLE "public-api".public.user_info (
	user_id VARCHAR(10) NOT NULL,
	user_last_name VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(50) NOT NULL,
	user_birthday DATE NOT NULL,
	user_email VARCHAR(50) NOT NULL,
	user_phone VARCHAR(10) NOT NULL,
	user_gender VARCHAR(20) NOT NULL,
	PRIMARY KEY (user_id)
);

INSERT INTO public.role VALUES 
('AD', 'Admin'),
('UR', 'User')

INSERT INTO public.accounts VALUES 
('admin1', '111', 'AD'),
('admin2', '222', 'AD'),
('kaj178', '123', 'UR'),
('leehaan2003', '456', 'UR')

INSERT INTO public.user_info
VALUES 
('001', 'Phan', 'Anh Khoa', '2002-08-17', 'anhkhoa5525@gmail.com', '0931517937', 'Male'),
('002', 'Le', 'Ngoc Han', '2003-10-04', 'leehaan2003@gmail.com', '0703152147', 'Female');


SELECT * FROM "public-api".public.role
SELECT * FROM "public-api".public.accounts
SELECT * FROM "public-api".public.user_info



