package com.bodysoft.sessions.services;


import com.bodysoft.sessions.repositories.StatusRepository;
import com.bodysoft.sessions.models.SessionStatus;

import org.springframework.stereotype.Service;


@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService( StatusRepository statusRepository ){
        this.statusRepository = statusRepository;
    }
    public SessionStatus findByIdStatus( int id ){
        return statusRepository.findById(id).get();
    };

}