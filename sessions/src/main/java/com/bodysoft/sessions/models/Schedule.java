package com.bodysoft.sessions.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table( name = "schedule", schema = "bs_sessions" )
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * ATRIBUTOS
     */

    @Id
    @SequenceGenerator( name = "SCHEDULE_SCHEDULEID_GENERATOR", sequenceName = "bs_sessions.schedule_schedule_id_seq", allocationSize = 1 )
    @GeneratedValue( generator = "SCHEDULE_SCHEDULEID_GENERATOR", strategy = GenerationType.SEQUENCE )
    @Column( name = "id_schedule" )
    private int id_schedule;


    @ManyToOne
	@JoinColumn(name="id_status", insertable = false, updatable = false )
    private SessionStatus status;

    @NotNull
    private int idCoach;

    @NotNull
    private Date daySession;

    @NotNull
    private Date iniTime;

    @NotNull
    private Date endTime;

    @NotNull
    private int idUser;

    public int getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(int id_schedule) {
        this.id_schedule = id_schedule;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public Date getDaySession() {
        return daySession;
    }

    public void setDaySession(Date daySession) {
        this.daySession = daySession;
    }

    public Date getIniTime() {
        return iniTime;
    }

    public void setIniTime(Date iniTime) {
        this.iniTime = iniTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     *  METODOS
     */

    

}