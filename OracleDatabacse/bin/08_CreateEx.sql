select * from tab;
select * from bonus;
select * from channels;
select * from countries;
select * from customers;
select * from departments;

-- drop table Memberlist cascade constraints;


create table channels(
	CHANNEL_ID number(6) not null,
	CHANNEL_DESC varchar2(20) not null,
	CHANNEL_CLASS varchar2(20),
	CHANNEL_CLASS_ID number(6),
	CHANNEL_TOTAL varchar2(15),
	CHANNEL_TOTAL_ID number(6),
	CREATE_DATE DATE,
	UPDATE_DATE DATE
);


create table countries (
	COUNTRY_ID number(6) not null,
	COUNTRY_ISO_CODE char(2) not null,
	COUNTRY_NAME varchar2(40) not null,
	COUNTRY_SUBREGION varchar2(30),
	COUNTRY_SUBREGION_ID number(6),
	COUNTRY_REGION varchar2(20),
	COUNTRY_REGION_ID number(6),
	COUNTRY_TOTAL varchar2(11),
	COUNTRY_TOTAL_ID number(6),
	COUNTRY_NAME_HIST varchar2(40),
	CREATE_DATE DATE,
	UPDATE_DATE DATE
);

create table customers(
	CUST_ID number(6) not null,
	CUST_NAME varchar2(100) not null,
	CUST_GENDER char(1),
	CUST_YEAR_OF_BIRTH number(4),
	CUST_MARITAL_STATUS varchar2(20),
	CUST_STREET_ADDRESS varchar2(100),
	CUST_POSTAL_CODE varchar2(10),
	CUST_CITY varchar2(30),
	CUST_CITY_ID number(6),
	CUST_STATE_PROVINCE varchar2(40),
	CUST_STATE_PROVINCE_ID number(6), --
	COUNTRY_ID number(6),
	CUST_MAIN_PHONE_NUMBER varchar2(25), --
	CUST_INCOME_LEVEL varchar2(30),
	CUST_CREDIT_LIMIT NUMBER,
	CUST_EMAIL varchar2(30),
	CUST_TOTAL varchar2(20),
	CUST_TOTAL_ID number(6),
	CUST_SRC_ID number(6),
	CUST_EFF_FROM DATE,
	CUST_EFF_TO DATE,
	CUST_VALID char(1),
	CREATE_DATE DATE,
	UPDATE_DATE DATE
);

create table departments (
	DEPARTMENT_ID number(6) not null,
	DEPARTMENT_NAME varchar2(80) not null,
	PARENT_ID number(6),
	MANAGER_ID number(6),
	CREATE_DATE DATE,
	UPDATE_DATE DATE
);

create table employees (
	EMPLOYEE_ID number(6) not null,
	EMP_NAME varchar2(80) not null,
	EMAIL varchar2(50),
	PHONE_NUMBER varchar2(30),
	HIRE_DATE DATE not null,
	SALARY number(8,2),
	MANAGER_ID number(6),
	COMMISSION_PCT number(2,2),
	RETIRE_DATE DATE,
	DEPARTMENT_ID number(6),
	JOB_ID varchar2(10),
	CREATE_DATE DATE,
	UPDATE_DATE DATE
);

create table products(
	PROD_ID number(6) not null,
	PROD_NAME varchar2(50) not null,
	PROD_DESC varchar2(4000),
	PROD_SUBCATEGORY varchar2(50),
	PROD_SUBCATEGORY_ID number(6),
	PROD_SUBCATEGORY_DESC varchar2(2000),
	PROD_CATEGORY varchar2(50),
	PROD_CATEGORY_ID number(6),
	PROD_CATEGORY_DESC varchar2(2000),
	PROD_WEIGHT_CLASS number(3),
	PROD_UNIT_OF_MEASURE varchar2(20),
	PROD_PACK_SIZE varchar2(30),
	SUPPLIER_ID number(6),
	PROD_STATUS varchar2(20),
	PROD_LIST_PRICE number(8,2),
	PROD_MIN_PRICE number(8,2),
	PROD_TOTAL varchar2(13),
	PROD_TOTAL_ID number(6),
	PROD_SRC_ID number(6),
	PROD_EFF_FROM DATE,
	PROD_EFF_TO DATE,
	PROD_VALID char(1),
	CREATE_DATE DATE,
	UPDATE_DATE date
);