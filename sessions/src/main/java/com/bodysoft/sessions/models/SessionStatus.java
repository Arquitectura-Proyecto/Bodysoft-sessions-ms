package com.bodysoft.sessions.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table( name = "session_status", schema = "bs_sessions" )
public class SessionStatus implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column( name = "id_status", nullable = false, columnDefinition = "int default 1" )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;


    @Column( name = "name_status")
    private String nameStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<Schedule> schedule;



        /**
         * METODOS
         */


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public Set<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<Schedule> schedule) {
        this.schedule = schedule;
    }

	@Override
	public String toString() {
		return "SessionStatus [id=" + id + ", nameStatus=" + nameStatus + "]";
	}



    
}