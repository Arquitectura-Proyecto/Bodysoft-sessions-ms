package com.bodysoft.sessions.models;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table( name = "SESSIONS_STATUS", schema = "bs_sessions" )
public class Sessions_status implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator( name = "USER_USERID_GENERATOR", sequenceName = "public.user_user_id_seq", allocationSize = 1 )
    @GeneratedValue( generator = "USER_USERID_GENERATOR", strategy = GenerationType.SEQUENCE )
    @Column( name = "ID_SCHEDULE" )
    private int id_schedule;


    @NotNull
    private String name_status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<Schedule> orderDetail = new HashSet();


}