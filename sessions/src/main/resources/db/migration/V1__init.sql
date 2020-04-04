/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     04/04/2020 7:53:24 p. m.                     */
/*==============================================================*/


drop table if exists SCHEDULES;

drop table if exists SESSION_STATUS;

/*==============================================================*/
/* Table: STATUS                                                */
/*==============================================================*/
create table SESSION_STATUS
(
   ID_STATUS                   smallint(4) not null,
   NAME_STATUS                 text not null,
   primary key (ID_STATUS)
);

/*==============================================================*/
/* Table: SCHEDULES                                             */
/*==============================================================*/
create table SCHEDULES
(
   ID_SCHEDULE          varchar(100) not null,
   ID_STATUS            smallint(4) not null,
   ID_COACH             varchar(100) not null,
   DAY_SESSION          date not null,
   INI_TIME             time not null,
   END_TIME             time not null,
   ID_USER              varchar(100),
   primary key (ID_SCHEDULE)
);



alter table SCHEDULES add constraint FK_SESSION_STATUS_SCHEDULE foreign key (ID_STATUS)
      references SESSION_STATUS (ID_STATUS) on delete CASCADE on update CASCADE;

/*===================================================================
 INSERTIONS
*/
INSERT INTO SESSION_STATUS (ID_STATUS, NAME_STATUS) VALUES (1, 'Disponible');
INSERT INTO SESSION_STATUS (ID_STATUS, NAME_STATUS) VALUES (2, 'Ocupado');
INSERT INTO SESSION_STATUS (ID_STATUS, NAME_STATUS) VALUES (3, 'Cancelado');
INSERT INTO SESSION_STATUS (ID_STATUS, NAME_STATUS) VALUES (4, 'Terminado');

