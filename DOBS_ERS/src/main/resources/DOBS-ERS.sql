

----------------------------------------------------- employee
drop table if exists employee cascade;

create table employee (
	user_id serial primary key,
	username varchar(50) not null,
	password varchar(50) not null,
	firstname varchar(30) not null,
	lastname varchar(30) not null,
	email varchar(100) not null,
	avatar varchar
);

-- All pasword values are 123456
insert into employee (username, password, firstname,lastname, email) values 
('vyle','ef4dafda494ad517e9823ae7d102a4c8','Vy','Le','thaovyletruong@gmail.com'),
('thanh','ef4dafda494ad517e9823ae7d102a4c8','Thanh','Le','thanh.le@revature.net'),
('cindy','ef4dafda494ad517e9823ae7d102a4c8','Cindy','Koon','cindy123@gmail.com'),
('many','ef4dafda494ad517e9823ae7d102a4c8','Maidy','Moo','maidymoore@protonmail.com'),
('Wasabi','ef4dafda494ad517e9823ae7d102a4c8','Washabi','Sashimi','washabi.shi@gmail.com');

----------------------------------------------------- manager
drop table if exists manager cascade;

create table manager (
	user_id serial primary key,
	username varchar(50) not null,
	password varchar(50) not null
);
-- All pasword values are 123456
insert into manager (username, password) values 
('manager', 'ef4dafda494ad517e9823ae7d102a4c8'),
('rr', 'ef4dafda494ad517e9823ae7d102a4c8');

----------------------------------------------------- reimbushment type
drop table if exists reimbushment_type cascade;

create table reimbushment_type (
	type_id serial primary key,
	type_value varchar(50)
);

insert into reimbushment_type (type_value) values ('LODGING'),('TRAVEL'),('FOOD'),('OTHER');

----------------------------------------------------- reimbushment status
drop table if exists reimbushment_status cascade;

create table reimbushment_status (
	status_id serial primary key,
	status_value varchar(50)
);

insert into reimbushment_status (status_value) values ('pending'),('resolved'),('deny');

----------------------------------------------------- reimbushment
drop table if exists reimbushment cascade;

create table reimbushment (
	id serial primary key,
	amount decimal(19,2) not null,
	submited timestamp not null,
	resolved timestamp,
	author integer not null,
	foreign key (author) references employee(user_id),
	resolver integer,
	foreign key (resolver) references manager(user_id),
	status_id integer not null,
	foreign key (status_id) references reimbushment_status(status_id),
	type_id integer not null,
	foreign key (type_id) references reimbushment_type(type_id),
	receipt varchar
);
insert into reimbushment (amount, submited, resolved, author, resolver, status_id, type_id, receipt) values
(230.7, timestamp '2017-10-12 21:22:23', timestamp '2018-10-5 07:22:23', 2, 1, 2, 4, null),
(780, timestamp '2017-6-3 01:22:23', timestamp '2018-10-5 07:22:23', 4, null, 1, 3, null),
(5000, timestamp '2020-1-3 21:22:23', timestamp '2020-01-5 07:55:00', 1, null, 1, 3, null);

update manager set password='ef4dafda494ad517e9823ae7d102a4c8';
update employee set password='ef4dafda494ad517e9823ae7d102a4c8';