package com.bodysoft.sessions.repositories;


import com.bodysoft.sessions.models.SessionStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<SessionStatus, Integer>{

    SessionStatus findByNameStatus( String nameStatus );
}