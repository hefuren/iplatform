/*2014-09-22 by elwin 建表语句*/
drop table fm_formField;

drop table fm_formListItem;

drop table fm_formList;

drop table fm_formViewItem;

drop table fm_formView;

drop table fm_formfilterItem;

drop table fm_formfilter;

drop table fm_formSchema;

drop user elwin;

/*==============================================================*/
/* User: elwin                                                  */
/*==============================================================*/
create user elwin;

/*==============================================================*/
/* Table: fm_formField                                          */
/*==============================================================*/
create table fm_formField (
   id                   int                  not null,
   schemaID             int                  null,
   fieldkey              varchar(50)          null,
   name                 varchar(50)          null,
   lableName            varchar(50)          null,
   status               int                  null,
   description          varchar(200)         null,
   tips                 varchar(200)         null,
   fieldType            int                  null,
   dataType             int                  null,
   numPrecision         int                  null,
   datePrecision        int                  null,
   seqno                int                  null,
   defaultValue         varchar(500)         null,
   companyID            int                  not null,
   constraint PK_FM_FORMFIELD primary key (id)
);

comment on column fm_formField.fieldType is
'表单字段/工作流字段';

-- set table ownership
alter table fm_formField owner to elwin
;
/*==============================================================*/
/* Table: fm_formList                                           */
/*==============================================================*/
create table fm_formList (
   id                   int                  not null,
   schemaID             int                  not null,
   orderBy              varchar(50)          null,
   orderType            int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMLIST primary key (id)
);

-- set table ownership
alter table fm_formList owner to elwin
;
/*==============================================================*/
/* Table: fm_formListItem                                       */
/*==============================================================*/
create table fm_formListItem (
   id                   int                  not null,
   schemaID             int                  not null,
   listID               int                  null,
   fieldkey              varchar(50)          null,
   width                int                  null,
   align                int                  null,
   orderType            int                  null,
   seqno                int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMLISTITEM primary key (id)
);

-- set table ownership
alter table fm_formListItem owner to elwin
;
/*==============================================================*/
/* Table: fm_formSchema                                         */
/*==============================================================*/
create table fm_formSchema (
   id                   int                  not null,
   name                 varchar(50)                  null,
   status               int                  null,
   description          varchar(500)         null,
   type                 int                  null,
   referenceID          int                  null,
   createBy             int                  null,
   createTime           timestamp            null,
   lastUpdateBy         int                  null,
   lastUpdateTime       timestamp            null,
   companyID            int                  not null,
   constraint PK_FM_FORMSCHEMA primary key (id)
);

-- set table ownership
alter table fm_formSchema owner to elwin
;
/*==============================================================*/
/* Table: fm_formView                                           */
/*==============================================================*/
create table fm_formView (
   id                   int                  not null,
   schemaID             int                  not null,
   name                 varchar(50)          null,
   applicableRoleID     varchar(500)         null,
   applicableStatus     varchar(200)         null,
   description          varchar(500)         null,
   seqno                int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMVIEW primary key (id)
);

comment on column fm_formView.applicableRoleID is
'适用角色';

comment on column fm_formView.applicableStatus is
'适用状态';

-- set table ownership
alter table fm_formView owner to elwin
;
/*==============================================================*/
/* Table: fm_formViewItem                                       */
/*==============================================================*/
create table fm_formViewItem (
   id                   int                  not null,
   schemaID             int                  not null,
   viewID               int                  null,
   fieldkey              varchar(50)          null,
   controlType          int                  null,
   width                int                  null,
   height               int                  null,
   editFlag             int                  null,
   defaultValue         varchar(500)         null,
   seqno                int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMVIEWITEM primary key (id)
);

-- set table ownership
alter table fm_formViewItem owner to elwin
;
/*==============================================================*/
/* Table: fm_formfilter                                         */
/*==============================================================*/
create table fm_formfilter (
   id                   int                  not null,
   schemaID             int                  not null,
   type                 int                  null,
   name                 varchar(50)          null,
   referenceID          int                  null,
   description          varchar(200)         null,
   companyID            int                  not null,
   constraint PK_FM_FORMFILTER primary key (id)
);

comment on column fm_formfilter.type is
'公共/私有';

-- set table ownership
alter table fm_formfilter owner to elwin
;
/*==============================================================*/
/* Table: fm_formfilterItem                                     */
/*==============================================================*/
create table fm_formfilterItem (
   id                   int                  not null,
   filterID             int                  not null,
   schemaID             int                  not null,
   fieldkey              varchar(50)                  null,
   fieldType            int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMFILTERITEM primary key (id)
);

-- set table ownership
alter table fm_formfilterItem owner to elwin
;
alter table fm_formField
   add constraint FK_FM_FORMF_FIELD_RF__FM_FORMS foreign key (schemaID)
      references fm_formSchema (id)
      on delete cascade;

alter table fm_formList
   add constraint FK_FM_FORML_LIST_RF_S_FM_FORMS foreign key (schemaID)
      references fm_formSchema (id)
      on delete cascade;

alter table fm_formListItem
   add constraint FK_FM_FORML_LISTITEM__FM_FORML foreign key (listID)
      references fm_formList (id)
      on delete cascade;

alter table fm_formView
   add constraint FK_FM_FORMV_VIEW_RF_S_FM_FORMS foreign key (schemaID)
      references fm_formSchema (id)
      on delete cascade;

alter table fm_formViewItem
   add constraint FK_FM_FORMV_VIEWITEM__FM_FORMV foreign key (viewID)
      references fm_formView (id)
      on delete cascade;

alter table fm_formfilter
   add constraint FK_FM_FORMF_FILTER_RF_FM_FORMS foreign key (schemaID)
      references fm_formSchema (id)
      on delete cascade;

alter table fm_formfilterItem
   add constraint FK_FM_FORMF_FILTERITE_FM_FORMF foreign key (filterID)
      references fm_formfilter (id)
      on delete cascade;


/*2014-10-17 by elwin 用户角色权限建表语句*/

drop table st_company;

drop table st_department;

drop table st_function;

drop table st_role;

drop table st_functionRelation;

drop table st_user;

drop table st_roleRelation;

drop table st_userloginInfo;

drop table st_userprofile;

/*==============================================================*/
/* Table: st_company                                            */
/*==============================================================*/
create table st_company (
   id                   int                  not null,
   name                 varchar(50)         null,
   description          varchar(500)        null,
   status               int                  null,
   type                 int                  null,
   contactname          varchar(50)         null,
   contactemail         varchar(50)         null,
   contactphone         varchar(50)         null,
   contactfax           varchar(50)         null,
   zip                  varchar(20)         null,
   url                  varchar(100)        null,
   employess            int                  null,
   adress               varchar(500)        null,
   constraint PK_ST_COMPANY primary key (id)
);


/*==============================================================*/
/* Table: st_department                                         */
/*==============================================================*/
create table st_department (
   id                   int                  not null,
   name                 varchar(50)          null,
   description          varchar(500)         null,
   parentID             int                  null,
   level                int                  null,
   seqno                int                  null,
   companyID            int                  not null,
   managerids           varchar(200)         null,
   sourceType           int                  null,
   sourceName           varchar(50)          null,
   sourceID             int                  null,
   createby             int                  null,
   createTime           timestamp            null,
   lastupdateby         int                  null,
   lastupdatetime       timestamp            null,
   constraint PK_ST_DEPARTMENT primary key (id)
);

/*==============================================================*/
/* Table: st_function                                           */
/*==============================================================*/
create table st_function (
   id                   int                  not null,
   name                 varchar(50)          not null,
   parentID		int		    not null,
   functionname         varchar(50)         null,
   description          varchar(200)        null,
   type                 int                  null,
   status               int                  null,
   seqno                int                  null,
   applicationID        int                  null,
   policy               CHAR(10)             null,
   clientURL            varchar(100)        null,
   folderURL            varchar(100)        null,
   imageURL             varchar(100)        null,
   companyID            int                  not null,
   constraint PK_ST_FUNCTION primary key (id)
);

/*==============================================================*/
/* Table: st_role                                               */
/*==============================================================*/
create table st_role (
   id                   int                  not null,
   name                 varchar(50)         null,
   description          varchar(500)        null,
   parentID             int                  null,
   seqno                int                  null,
   companyID            int                  not null,
   type                 int                  null,
   status               int                  null,
   createby             int                  null,
   createtime            timestamp            null,
   lastupdateby         int                  null,
   lastupdatetime       timestamp            null,
   constraint PK_ST_ROLE primary key (id)
);


/*==============================================================*/
/* Table: st_functionRelation                                   */
/*==============================================================*/
create table st_functionRelation (
   id                   int                  not null,
   roleID               int                  not null,
   functionid           int                  not null,
   objectid             int                  null,
   objecttype           int                  null,
   groupid              int                  null,
   status               int                  null,
   type                 int                  null,
   companyid            int                  not null,
   constraint PK_ST_FUNCTIONRELATION primary key (id)
);



/*==============================================================*/
/* Table: st_user                                               */
/*==============================================================*/
create table st_user (
   id                   int                  not null,
   name                 varchar(50)         not null,
   password             varchar(50)         null,
   displayname          varchar(50)         null,
   firstname            varchar(20)         null,
   lastname             varchar(20)         null,
   seqno                int                  null,
   type                 int                  null,
   status               int                  null,
   email                varchar(50)         null,
   mobile               varchar(30)         null,
   tel                  varchar(30)         null,
   zip                  varchar(20)         null,
   jobnumber            varchar(30)         null,
   sex                  int                  null,
   birthday             timestamp            null,
   departmentID         int                  null,
   sourceType           int                  null,
   sourceName           varchar(50)         null,
   sourceID             int                  null,
   createby             int                  null,
   createTime           timestamp            null,
   lastupdateby         int                  null,
   lastupdatetime       timestamp            null,
   companyID            int                  not null,
   constraint PK_ST_USER primary key (id)
);

/*==============================================================*/
/* Table: st_roleRelation                                       */
/*==============================================================*/
create table st_roleRelation (
   id                   int                  not null,
   userid               int                  not null,
   roleid               int                  not null,
   status               int                  null,
   type                 int                  null,
   companyid            int                  not null,
   constraint PK_ST_ROLERELATION primary key (id)
);

/*==============================================================*/
/* Table: st_userloginInfo                                      */
/*==============================================================*/
create table st_userloginInfo (
   id                   int                  not null,
   userID               int                  null,
   logintimes           int                  null,
   sessiontime          timestamp            null,
   lastlogintime        timestamp            null,
   currentlogintime     timestamp            null,
   companyid            int                  not null,
   constraint PK_ST_USERLOGININFO primary key (id)
);

/*==============================================================*/
/* Table: st_userprofile                                        */
/*==============================================================*/
create table st_userprofile (
   id                   int                  not null,
   userID               int                  not null,
   companyID            int                  not null,
   position           int                  null,
   status		int		     null,
   jointime             timestamp            null,
   leavetime            timestamp            null,
   createby             int                  null,
   createtime           timestamp            null,
   lastupdateby         int                  null,
   lastupdatetime       timestamp            null,
   constraint PK_ST_USERPROFILE primary key (id)
);

alter table st_functionRelation
   add constraint FK_ST_FUNCT_REFERENCE_ST_FUNCT foreign key (functionid)
      references st_function (id)
      on delete cascade on update restrict;

alter table st_functionRelation
   add constraint FK_ST_FUNCT_REFERENCE_ST_ROLE foreign key (roleID)
      references st_role (id)
      on delete cascade on update restrict;


alter table st_userprofile
   add constraint FK_ST_USERP_REFERENCE_ST_USER foreign key (userID)
      references st_user (id)
      on delete cascade on update restrict;


/*2014-10-21 22:19 by elwin 系统菜单表*/

drop table st_menu;

/*==============================================================*/
/* Table: st_menu                                               */
/*==============================================================*/
create table st_menu (
   id                   int                  not null,
   parentID             int                  not null,
   name                 varchar(50)          not null,
   menukey              varchar(50)          not null,
   description          varchar(200)         null,
   status               int                  null,
   type                 int                  null,
   policy               int                  null,
   seqno                int                  null,
   folderurl            varchar(200)         null,
   clienturl            varchar(200)         null,
   imageurl             varchar(200)         null,
   applicationid        int                  null,
   companyid            int                  not null,
   constraint PK_ST_MENU primary key (id)
);


/*2014-11-04 by elwin 插入菜单*/
delete from st_company where id = 1000;
insert into st_company (id,name,status) values (1000,'BlueSky',1);

delete from st_user where id =1000;
insert into st_user(id,name,password,displayname,status,companyid) values (1000,'Admin','','Admin',1,1000);

delete from st_menu where id = 100;
insert into st_menu (id,parentid,name,menukey,description,status,type,policy,seqno,folderurl,clienturl,imageurl,applicationid,companyid) 
values(100,0,'系统管理','M_SYS','系统管理',1,0,0,0,'','/StructureAction.do','icon-wrench',100,1000);


delete from st_menu where id = 101;
insert into st_menu (id,parentid,name,menukey,description,status,type,policy,seqno,folderurl,clienturl,imageurl,applicationid,companyid) 
values(101,100,'组织结构','M_SYS_DEPARTMENTLIST','组织结构',1,0,0,1,'','/StructureAction.do?operation=structureList',' icon-flow-tree',100,1000);

delete from st_menu where id = 102;
insert into st_menu (id,parentid,name,menukey,description,status,type,policy,seqno,folderurl,clienturl,imageurl,applicationid,companyid) 
values(102,100,'用户管理','M_SYS_USERLIST','用户管理',1,0,0,2,'','/UserAction.do?operation=userList','icon-users-1',100,1000);

/*2014-11-10 by elwin 初始部门表*/
insert into st_department (id,name,description,parentID,level,seqno,companyID) values (100,'BlueSky','BlueSky',0,1,1,1000);


/**
insert into st_department (id,name,description,parentID,level,seqno,companyID) values (100,'BlueSky','BlueSky',0,1,1,1000);

insert into st_department (id,name,description,parentID,level,seqno,companyID) values (1000,'研发部','负责软件研发',100,2,2,1000);
insert into st_department (id,name,description,parentID,level,seqno,companyID) values (1001,'专业服务部','负责项目实施',100,2,5,1000);
insert into st_department (id,name,description,parentID,level,seqno,companyID) values (1002,'市场部','负责市场拓展',100,2,6,1000);


insert into st_department (id,name,description,parentID,level,seqno,companyID) values (1003,'研发部1部','负责软件研发',1000,3,3,1000);
insert into st_department (id,name,description,parentID,level,seqno,companyID) values (1004,'研发部2部','负责软件研发',1000,3,4,1000);

*/

/*2014-12-21 by elwin 更新用户管理菜单*/
delete from st_menu where id = 102;
insert into st_menu (id,parentid,name,menukey,description,status,type,policy,seqno,folderurl,clienturl,imageurl,applicationid,companyid) 
values(102,100,'用户管理','M_SYS_USERLIST','用户管理',1,0,0,2,'','/UserAction.do?operation=userList','icon-users-1',100,1000);


/*2015-05-29 by elwin 插入系统代码表菜单*/
delete from st_menu where id = 103;
insert into st_menu (id,parentid,name,menukey,description,status,type,policy,seqno,folderurl,clienturl,imageurl,applicationid,companyid) 
values(103,100,'系统代码表','M_SYS_CODETABLE','系统代码表',1,0,0,3,'','/CodeTableAction.do?operation=codeTableList',' icon-list-1',100,1000);


/*2015-06-25 by elwin 创建系统代码表*/

drop table st_codetable;
/*==============================================================*/
/* Table: st_codetable                                          */
/*==============================================================*/
create table st_codetable (
   id                   int                  not null,
   tablename            varchar(120)         not null,
   name                 varchar(120)         null,
   description          varchar(500)         null,
   status               int                  null,
   idRule			int		     null,
   category             int                  null,
   source               int                  null,
   type                 int                  null,
   parentid             int                  null,
   sortby               varchar(50)          null,
   createtime           timestamp            null,
   createby             int                  null,
   lastupdatetime       timestamp            null,
   lastupdateby         int                  null,
   owner                int                  null,
   companyid            int                  not null,
   name1                varchar(120)         null,
   description1         varchar(500)         null,
   constraint pk_st_codetable primary key  (id)
);

drop table st_codetablefield;

/*==============================================================*/
/* Table: st_codetablefield                                     */
/*==============================================================*/
create table st_codetablefield (
   id                   int                  not null,
   tableid              int                  null,
   name                 varchar(120)         null,
   description          varchar(500)         null,
   fieldid              int                  null,
   status               int                  null,
   type                 int                  null,
   fieldtype            int                  null,
   datatype             int                  null,
   datalength           int                  null,
   dataprecision        int                  null,
   dataformat           int                  null,
   defaultvalue         varchar(500)         null,
   seqno                int                  null,
   fieldlevel           int                  null,
   usecount             int                  null,
   companyid            int                  not null,
   constraint pk_st_codetablefield primary key  (id)
)
;

alter table st_codetablefield
   add constraint fk_st_codetablefield foreign key (tableid)
      references st_codetable (id)
         on delete cascade
;


/*
delete from ST_CodeTableField where tableID = 400;
INSERT INTO ST_CodeTableField(id, tableID, name, description, fieldID, status, type, fieldType, dataType, dataLength, dataPrecision, dataFormat, defaultValue, seqNo, fieldLevel, 
useCount, companyID)
VALUES(401, 400, 'name',  '名称', 0, 1, 0, 0, 0, 100, 0, 0, null, 1, 1, 0, 1);

INSERT INTO ST_CodeTableField(id, tableID, name, description, fieldID, status, type, fieldType, dataType, dataLength, dataPrecision, dataFormat, defaultValue, seqNo, fieldLevel, 
useCount, companyID)
VALUES(402, 400, 'description', '描述', 0, 1, 0, 0, 0, 500, 0, 0, null, 2, 1, 0, 1);
INSERT INTO ST_CodeTableField(id, tableID, name, description, fieldID, status, type, fieldType, dataType, dataLength, dataPrecision, dataFormat, defaultValue, seqNo, fieldLevel, 
useCount, companyID)
VALUES(403, 400, 'companyID', '公司', 0, 1, 0, 1, 1, 12, 0, 0, null, 3, 1, 0, 1);
INSERT INTO ST_CodeTableField(id, tableID, name, description, fieldID, status, type, fieldType, dataType, dataLength, dataPrecision, dataFormat, defaultValue, seqNo, fieldLevel, 
useCount, companyID)
VALUES(404, 400, 'seqNo', '次序', 0, 1, 0, 1, 1, 12, 0, 0, null, 4, 1, 0, 1);
INSERT INTO ST_CodeTableField(id, tableID, name, description, fieldID, status, type, fieldType, dataType, dataLength, dataPrecision, dataFormat, defaultValue, seqNo, fieldLevel, 
useCount, companyID)
VALUES(405, 400, 'parentID', '分类', 0, 1, 0, 1, 1, 12, 0, 0, null, 5, 1, 0, 1);
INSERT INTO ST_CodeTableField(id, tableID, name, description, fieldID, status, type, fieldType, dataType, dataLength, dataPrecision, dataFormat, defaultValue, seqNo, fieldLevel, 
useCount, companyID)
VALUES(406, 400, 'status', '状态', 0, 1, 0, 1, 1, 12, 0, 0, null, 6, 1, 0, 1);

INSERT INTO ST_CodeTableField(id, tableID, name, description, fieldID, status, type, fieldType, dataType, dataLength, dataPrecision, dataFormat, defaultValue, seqNo, fieldLevel, 
useCount, companyID)
select a.id, a.tableID, a.name, a.description, a.fieldID, a.status, a.type, a.fieldType, a.dataType, a.dataLength, a.dataPrecision, a.dataFormat, a.defaultValue, a.seqNo, 
a.fieldLevel, a.useCount, b.id
from ST_CodeTableField a, PM_Company b 
where a.companyID = 1 and b.id != 1 and a.tableID = 400;

*/

/*2016-09-25 by elwin 修改权限表，增加systemID*/
alter table  st_function add column systemid integer; 


