create table MAS_PERSON
(
  PER_ID NUMBER not null
    primary key,
  PER_SURNAME VARCHAR2(30) not null,
  PER_NAME VARCHAR2(30) not null,
  PER_ADDRESS VARCHAR2(100) default NULL not null
)
/

create table MAS_CLIENT
(
  CLI_PER_ID NUMBER not null
    primary key
    constraint MAS_CLIENT_OSB_ID_FK
    references MAS_PERSON
    on delete cascade,
  CLI_EMAIL VARCHAR2(100)
)
/

create table MAS_EMPLOYEE
(
  EMP_PER_ID NUMBER not null
    primary key
    constraint MAS_EMPLOYEE_OSB_ID_FK
    references MAS_PERSON
    on delete cascade,
  EMP_ACCOUNT_NUMBER VARCHAR2(100) not null,
  EMP_PHONE_NUMBER VARCHAR2(12),
  EMP_VALID_FROM TIMESTAMP(6) default SYSDATE not null
)
/

create table MAS_REPORT
(
  REP_EMP_ID NUMBER not null,
  REP_BAT_ID VARCHAR2(20) not null,
  REP_DESCRIPTION VARCHAR2(3000) not null,
  REP_CREATE_DATE TIMESTAMP(6) default SYSDATE not null,
  REP_ID NUMBER not null
    constraint MAS_REPORT_REP_ID_PK
    primary key
)
/

create table MAS_BATCH
(
  BAT_ID NUMBER default NULL not null
    primary key,
  BAT_CREATE_DATE TIMESTAMP(6),
  BAT_VOLUME NUMBER(19,4) default NULL not null,
  BAT_AVAILABLE_VOLUME NUMBER(19,4) default NULL not null,
  BAT_STA_ID NUMBER not null,
  BAT_EXPIRATION_DATE TIMESTAMP(6) not null
)
/

create table MAS_STATUS
(
  STA_ID NUMBER not null
    primary key,
  STA_NAME VARCHAR2(50) not null
)
/

create table MAS_RECIPE
(
  REC_ID NUMBER not null
    primary key,
  REC_NAME VARCHAR2(100) not null,
  REC_DESCRIPTION VARCHAR2(3000) not null,
  REC_PRICE NUMBER(19,4)
)
/

create table MAS_ING_IN_REC
(
  IIR_ING_ID NUMBER,
  IIR_REC_ID NUMBER,
  IIR_VOLUME NUMBER(19,4) default NULL not null,
  IIR_ID NUMBER not null
    constraint MAS_ING_IN_REC_IIR_ID_PK
    primary key
)
/

create table MAS_SUPPLIER
(
  SUP_ID NUMBER not null
    primary key,
  SUP_COMPANY_NAME VARCHAR2(200) not null,
  SUP_ADDRESS VARCHAR2(100) not null,
  SUP_PHONE_NUMBER VARCHAR2(12) not null
)
/

create table MAS_INGREDIENT
(
  ING_ID NUMBER not null
    primary key,
  ING_NAME VARCHAR2(50) not null,
  ING_PRICE_PER_KG NUMBER(19,4) default NULL not null,
  ING_YIELD_PER_KG NUMBER(19,4) default NULL not null,
  ING_SUP_ID NUMBER not null
    constraint MAS_INGREDIENT_SUP_ID_FK
    references MAS_SUPPLIER
    on delete cascade
)
/

create table MAS_VESSEL
(
  VES_ID NUMBER not null
    primary key,
  VES_NAME VARCHAR2(50) not null,
  VES_VOLUME NUMBER(19,4) default NULL not null,
  VES_PRICE NUMBER(19,4) default NULL not null,
  VES_STOCK NUMBER default 0 not null,
  VES_SUP_ID NUMBER not null
    constraint MAS_VESSEL_SUP_ID_FK
    references MAS_SUPPLIER
    on delete cascade
)
/

create table MAS_TEMPLATE
(
  TEM_ID NUMBER not null
    primary key,
  TEM_NAME VARCHAR2(50) not null,
  TEM_PRICE NUMBER(19,4) default NULL not null,
  TEM_VES_ID NUMBER not null
    constraint MAS_TEMPLATE_VES_ID_FK
    references MAS_VESSEL,
  TEM_REC_ID NUMBER not null
    constraint MAS_TEMPLATE_REC_ID_FK
    references MAS_RECIPE
    on delete cascade
)
/

create table MAS_SET
(
  SET_ID NUMBER not null
    primary key,
  SET_DESCRIPTION VARCHAR2(3000) not null,
  SET_PRICE NUMBER(19,4) default NULL not null,
  SET_NAME VARCHAR2(50) default NULL not null
)
/

create table MAS_TEM_IN_SET
(
  TIS_TEM_ID NUMBER
    constraint MAS_TIS_TEM_ID_FK
    references MAS_TEMPLATE
    on delete cascade,
  TIS_SET_ID NUMBER
    constraint MAS_TIS_SET_ID_FK
    references MAS_SET
    on delete cascade,
  TIS_QUANTITY NUMBER default 1 not null,
  TIS_ID NUMBER not null
    constraint MAS_TEM_IN_SET_PK
    primary key
)
/

create table MAS_SUBSCRIPTION
(
  SUB_ID NUMBER not null
    primary key,
  SUB_SET_ID NUMBER
    constraint MAS_SUBSCRIPTION_SET_ID_FK
    references MAS_SET
    on delete cascade,
  SUB_CYCLE_LENGTH NUMBER not null,
  SUB_CYCLE_COUNT NUMBER not null,
  SUB_START_DATE TIMESTAMP(6) default SYSDATE not null,
  SUB_END_DATE TIMESTAMP(6) not null,
  SUB_CLI_ID NUMBER not null
    constraint MAS_SUBSCRIPTION_CLI_ID_FK
    references MAS_CLIENT
    on delete cascade
)
/

create table MAS_ROLE
(
  ROL_ID NUMBER not null
    primary key,
  ROL_NAME VARCHAR2(50) not null
)
/

create table MAS_EMP_ROL
(
  ERO_ID NUMBER not null
    primary key,
  ERO_EMP_ID NUMBER not null
    constraint MAS_EMP_ROL_EMP_FK
    references MAS_EMPLOYEE,
  ERO_ROL_ID NUMBER not null
    constraint MAS_EMP_ROL_ROL_FK
    references MAS_ROLE
)
/

comment on column MAS_SUBSCRIPTION.SUB_CYCLE_LENGTH is 'In days'
/

create table MAS_ORDER
(
  ORD_ID NUMBER not null
    primary key,
  ORD_CLI_ID NUMBER not null
    constraint MAS_ORDER_CLI_ID_FK
    references MAS_CLIENT,
  ORD_CREATE_DATE TIMESTAMP(6) default SYSDATE not null,
  ORD_DELIVERY_DATE TIMESTAMP(6),
  ORD_PRICE NUMBER(19,4) default NULL not null
)
/

create table MAS_BEVERAGE
(
  BEV_ID NUMBER not null
    primary key,
  BEV_TEM_ID NUMBER not null
    constraint SUB_BEVERAGE_TEM_ID_FK
    references MAS_TEMPLATE
    on delete cascade,
  BEV_ORD_ID NUMBER not null
    constraint MAS_BEVERAGE_ORD_ID_FK
    references MAS_ORDER,
  BEV_FILL_DATE TIMESTAMP(6) default SYSDATE not null,
  BEV_BAT_ID NUMBER default NULL not null
    constraint MAS_BEVERAGE_BAT_ID_FK
    references MAS_BATCH
)
/

create table MAS_EMP_AUTH
(
  AUT_EMP_ID NUMBER not null
    primary key
    constraint MAS_EMP_AUTH_EMP_ID_FK
    references MAS_EMPLOYEE,
  AUT_LOGIN VARCHAR2(50) not null,
  AUT_HASH VARCHAR2(64) not null
)
/



CREATE SEQUENCE MAS_BATCH_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_BEVERAGE_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_ING_IN_REC_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_INGREDIENT_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_ORDER_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_PERSON_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_RECIPE_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_REPORT_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_SET_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_STATUS_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_SUBSCRIPTION_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_SUPPLIER_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_TEM_IN_SET_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_TEMPLATE_SEQ
  START WITH 100;
/

CREATE SEQUENCE MAS_VESSEL_SEQ
  START WITH 100;