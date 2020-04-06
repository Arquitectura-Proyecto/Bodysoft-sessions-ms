package com.bodysoft.sessions.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table( name = "session_status", schema = "bs_sessions" )
public class SessionStatus implements Serializable{

    private static final long serialVersionUID = 1L;

    private SessionStatus( Integer id, String nameStatus ){
        this.id = id;
        this.nameStatus = nameStatus;
        this.schedule = new ArrayList<>( );
    }

    @Id
    @Column( name = "id_status", nullable = false, columnDefinition = "int default 1" )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;


    @Column( name = "name_status")
    private String nameStatus;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private List<Schedule> schedule;



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

	public List<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}

    

    
}