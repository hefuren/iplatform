create table test_user (
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
   constraint PK_TEST_USER primary key (id)
);
