/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     04/04/2020 7:52:22 p. m.                     */
/*==============================================================*/


drop table if exists SCHEDULES;

drop table if exists STATUS;

/*==============================================================*/
/* Table: SCHEDULES                                             */
/*==============================================================*/
create table SCHEDULES
(
   ID_SCHEDULE          text not null,
   ID_STATUS            smallint not null,
   ID_COACH             text not null,
   DAY                  date not null,
   INI_TIME             time not null,
   END_TIME             time not null,
   ID_USER              text,
   primary key (ID_SCHEDULE)
);

/*==============================================================*/
/* Table: STATUS                                                */
/*==============================================================*/
create table STATUS
(
   ID_STATUS            smallint not null,
   NAME                 text not null,
   primary key (ID_STATUS)
);

alter table SCHEDULES add constraint FK_STATUS_SCHEDULE foreign key (ID_STATUS)
      references STATUS (ID_STATUS) on delete restrict on update restrict;

