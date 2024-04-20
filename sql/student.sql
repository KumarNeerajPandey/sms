create database sms;
\c sms 

drop table if exists gender cascade;
drop table if exists bloodgroup cascade;

drop type if exists student cascade;

create type gender as enum('MALE', 'FEMALE', 'OTHERS');

create type bloodgroup as enum(
  'A_POSITIVE', 'B_POSITIVE', 'O_POSITIVE', 
  'AB_POSITIVE', 'A_NEGATIVE', 'B_NEGATIVE', 
  'O_NEGATIVE', 'AB_NEGATIVE'
);

create table if not exists student(
  student_id serial, 
  first_name varchar(64) not null, 
  middle_name varchar(64), 
  last_name varchar(64) not null, 
  dob date not null, 
  bloodgroup bloodgroup, 
  gender gender not null, 
  phone varchar(20) unique not null, 
  email_id varchar(254) unique not null, 
  father_name varchar(128) not null, 
  father_phone varchar(20) unique not null, 
  identity_type varchar(64) not null, 
  identity_number varchar(20) unique not null, 
  nationality varchar (64) not null, 
  address_line1 varchar(64) not null, 
  address_line2 varchar(64), 
  city varchar(55) not null, 
  state varchar(55) not null, 
  country varchar(55) not null, 
  postal_code varchar(10) not null, 
  created_date timestamp default now() not null, 
  modified_date timestamp default now() not null, 
  constraint student_id_pk primary key(student_id)
);
