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
    @Column( name = "id_schedule" ,nullable = false, columnDefinition = "int default 1")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_schedule;


    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_status")
    private SessionStatus status;

    @NotNull
    @Column( name = "id_coach")
    private Integer idCoach;

    @NotNull
    @Column( name = "day_session")
    private LocalDate daySession;

    @NotNull
    @Column( name = "ini_time")
    private LocalTime iniTime;

    @NotNull
    @Column( name = "end_time")
    private LocalTime endTime;

    
    @Column( name = "id_user")
    private Integer idUser;

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