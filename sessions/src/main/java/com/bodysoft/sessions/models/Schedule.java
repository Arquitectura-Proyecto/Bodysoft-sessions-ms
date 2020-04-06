package com.bodysoft.sessions.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table( name = "schedule", schema = "bs_sessions" )
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;

    

    /**
     * ATRIBUTOS
     */


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column( name = "id_schedule" ,nullable = false, columnDefinition = "int default 1")
    private int id_schedule;


    @ManyToOne
	@JoinColumn(name="id_status", insertable = false, updatable = false )
    private SessionStatus status;

    @NotNull
    private int idCoach;

    @NotNull
    private LocalDate daySession;

    @NotNull
    private LocalTime iniTime;

    @NotNull
    private LocalTime endTime;

    @NotNull
    private int idUser;

     /**
     *  METODOS
     */


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


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDaySession(LocalDate daySession) {
        this.daySession = daySession;
    }

    public void setIniTime(LocalTime iniTime) {
        this.iniTime = iniTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

   
    

}