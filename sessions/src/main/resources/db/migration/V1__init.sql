/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     04/04/2020 7:53:24 p. m.                     */
/*==============================================================*/


drop table if exists schedule;

drop table if exists session_status;

/*==============================================================*/
/* Table: STATUS                                                */
/*==============================================================*/
create table session_status
(
   id_status                   smallint(4) not null AUTO_INCREMENT,
   name_status                 text not null,
   primary key (id_status)
);

/*==============================================================*/
/* Table: SCHEDULES                                             */
/*==============================================================*/
create table schedule
(
   id_schedule          bigint not null AUTO_INCREMENT,
   id_status            smallint(4) not null,
   id_coach             bigint(100) not null,
   day_session          date not null,
   ini_time             time not null,
   end_time            time not null,
   id_user              bigint(100),
   primary key (id_schedule)
);



alter table schedule add constraint session_status foreign key (id_status)
      references session_status (id_status) on delete CASCADE on update CASCADE;

/*===================================================================
 INSERTIONS
*/
INSERT INTO session_status (id_status, name_status) VALUES (1, 'Disponible');
INSERT INTO session_status (id_status, name_status) VALUES (2, 'Ocupado');
INSERT INTO session_status (id_status, name_status) VALUES (3, 'Cancelado');
INSERT INTO session_status (id_status, name_status) VALUES (4, 'Terminado');

