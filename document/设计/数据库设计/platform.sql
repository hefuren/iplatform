drop table elwin.fm_formField;

drop table elwin.fm_formList;

drop table elwin.fm_formListItem;

drop table elwin.fm_formSchema;

drop table elwin.fm_formView;

drop table elwin.fm_formViewItem;

drop table elwin.fm_formfilter;

drop table elwin.fm_formfilterItem;

drop table st_company;

drop table st_department;

drop table st_function;

drop table st_functionRelation;

drop table st_menu;

drop table st_role;

drop table st_roleRelation;

drop table st_user;

drop table st_userloginInfo;

drop table st_userprofile;

drop user elwin;

/*==============================================================*/
/* User: elwin                                                  */
/*==============================================================*/
create user elwin;

/*==============================================================*/
/* Table: fm_formField                                          */
/*==============================================================*/
create table elwin.fm_formField (
   id                   int                  not null,
   schemaID             int                  null,
   fieldID              varchar(50)          null,
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
alter table elwin.fm_formField owner to elwin
;
/*==============================================================*/
/* Table: fm_formList                                           */
/*==============================================================*/
create table elwin.fm_formList (
   id                   int                  not null,
   schemaID             int                  null,
   orderBy              varchar(50)          null,
   orderType            int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMLIST primary key (id)
);

-- set table ownership
alter table elwin.fm_formList owner to elwin
;
/*==============================================================*/
/* Table: fm_formListItem                                       */
/*==============================================================*/
create table elwin.fm_formListItem (
   id                   int                  not null,
   listID               int                  null,
   fieldID              varchar(50)          null,
   widht                int                  null,
   align                int                  null,
   orderType            int                  null,
   seqno                int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMLISTITEM primary key (id)
);

-- set table ownership
alter table elwin.fm_formListItem owner to elwin
;
/*==============================================================*/
/* Table: fm_formSchema                                         */
/*==============================================================*/
create table elwin.fm_formSchema (
   id                   int                  not null,
   name                 int                  null,
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
alter table elwin.fm_formSchema owner to elwin
;
/*==============================================================*/
/* Table: fm_formView                                           */
/*==============================================================*/
create table elwin.fm_formView (
   id                   int                  not null,
   schemaID             int                  null,
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
alter table elwin.fm_formView owner to elwin
;
/*==============================================================*/
/* Table: fm_formViewItem                                       */
/*==============================================================*/
create table elwin.fm_formViewItem (
   id                   int                  not null,
   viewID               int                  null,
   fieldID              varchar(50)          null,
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
alter table elwin.fm_formViewItem owner to elwin
;
/*==============================================================*/
/* Table: fm_formfilter                                         */
/*==============================================================*/
create table elwin.fm_formfilter (
   id                   int                  not null,
   schemaID             int                  null,
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
alter table elwin.fm_formfilter owner to elwin
;
/*==============================================================*/
/* Table: fm_formfilterItem                                     */
/*==============================================================*/
create table elwin.fm_formfilterItem (
   id                   int                  not null,
   filterID             int                  not null,
   schemaID             int                  null,
   fieldID              int                  null,
   fieldType            int                  null,
   companyID            int                  not null,
   constraint PK_FM_FORMFILTERITEM primary key (id)
);

-- set table ownership
alter table elwin.fm_formfilterItem owner to elwin
;
/*==============================================================*/
/* Table: st_company                                            */
/*==============================================================*/
create table st_company (
   id                   int                  not null,
   name                 varchar(50)          null,
   description          varchar(500)         null,
   status               int                  null,
   type                 int                  null,
   contactname          varchar(50)          null,
   contactemail         varchar(50)          null,
   contactphone         varchar(50)          null,
   contactfax           varchar(50)          null,
   zip                  varchar(20)          null,
   url                  varchar(100)         null,
   employess            int                  null,
   adress               varchar(500)         null,
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
   managers             varchar(200)         null,
   sourceType           int                  null,
   sourceName           varchar(50)          null,
   sourceID             int                  null,
   constraint PK_ST_DEPARTMENT primary key (id)
);

/*==============================================================*/
/* Table: st_function                                           */
/*==============================================================*/
create table st_function (
   id                   int                  not null,
   name                 varchar(50)          null,
   parentID             int                  not null,
   functionname         varchar(50)          null,
   description          varchar(200)         null,
   type                 int                  null,
   status               int                  null,
   seqno                int                  null,
   applicationID        int                  null,
   policy               int                  null,
   clientURL            varchar(100)         null,
   folderURL            varchar(100)         null,
   imageURL             varchar(100)         null,
   companyID            int                  not null,
   constraint PK_ST_FUNCTION primary key (id)
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

/*==============================================================*/
/* Table: st_role                                               */
/*==============================================================*/
create table st_role (
   id                   int                  not null,
   name                 varchar(50)          null,
   description          varchar(500)         null,
   parentID             int                  null,
   seqno                int                  null,
   companyID            int                  not null,
   type                 int                  null,
   status               int                  null,
   createby             int                  null,
   ceatetime            timestamp            null,
   lastupdateby         int                  null,
   lastupdatetime       timestamp            null,
   constraint PK_ST_ROLE primary key (id)
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
/* Table: st_user                                               */
/*==============================================================*/
create table st_user (
   id                   int                  not null,
   name                 varchar(50)          not null,
   password             varchar(50)          null,
   displayname          varchar(50)          null,
   firstname            varchar(20)          null,
   lastname             varchar(20)          null,
   seqno                int                  null,
   type                 int                  null,
   status               int                  null,
   email                varchar(50)          null,
   mobile               varchar(30)          null,
   tel                  varchar(30)          null,
   zip                  varchar(20)          null,
   jobnumber            varchar(30)          null,
   sex                  int                  null,
   birthday             timestamp            null,
   departmentID         int                  null,
   sourceType           int                  null,
   sourceName           varchar(50)          null,
   sourceID             int                  null,
   createby             int                  null,
   createTime           timestamp            null,
   lastupdateby         int                  null,
   lastupdatetime       timestamp            null,
   companyID            int                  not null,
   constraint PK_ST_USER primary key (id)
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
   status               int                  null,
   "position"           int                  null,
   jointime             timestamp            null,
   leavetime            timestamp            null,
   createby             int                  null,
   createtime           timestamp            null,
   lastupdateby         int                  null,
   lastupdatetime       timestamp            null,
   constraint PK_ST_USERPROFILE primary key (id)
);

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
