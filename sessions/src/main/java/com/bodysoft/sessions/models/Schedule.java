package com.bodysoft.sessions.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table( name = "SCHEDULES", schema = "bs_sessions" )
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * ATRIBUTOS
     */

    @Id
    @SequenceGenerator( name = "USER_USERID_GENERATOR", sequenceName = "public.user_user_id_seq", allocationSize = 1 )
    @GeneratedValue( generator = "USER_USERID_GENERATOR", strategy = GenerationType.SEQUENCE )
    @Column( name = "ID_SCHEDULE" )
    private String id_schedule;


    @ManyToOne
	@JoinColumn(name="ID_STATUS", insertable = false, updatable = false )
    private Sessions_status status;

    @NotNull
    private String id_coach;

    @NotNull
    private Date day_session;

    @NotNull
    private Date ini_time;

    @NotNull
    private Date end_time;

    @NotNull
    private String id_user;

    /**
     *  METODOS
     */

    public String getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(String id_schedule) {
        this.id_schedule = id_schedule;
    }


    public String getId_coach() {
        return id_coach;
    }

    public void setId_coach(String id_coach) {
        this.id_coach = id_coach;
    }

    public Date getDay_session() {
        return day_session;
    }

    public void setDay_session(Date day_session) {
        this.day_session = day_session;
    }

    public Date getIni_time() {
        return ini_time;
    }

    public void setIni_time(Date ini_time) {
        this.ini_time = ini_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public Sessions_status getStatus() {
        return status;
    }

    public void setStatus(Sessions_status status) {
        this.status = status;
    }

    



}