package com.bodysoft.sessions.services;

import com.bodysoft.sessions.repositories.StatusRepository;

import java.util.ArrayList;
import java.util.List;

import com.bodysoft.sessions.models.Schedule;
import com.bodysoft.sessions.models.SessionStatus;

import org.springframework.stereotype.Service;


@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService( StatusRepository statusRepository ){
        this.statusRepository = statusRepository;
    }
    public SessionStatus findByIdStatus( Integer id ){
        return statusRepository.findById(id).orElse(null);
    };

    /**
     * 
     * @param status Thee status  filter
     * @param schedules List of schedules that is going to be filtered
     * @return
     */    
    public List<Schedule> getAllbystatus (SessionStatus status,List<Schedule> schedules ){
        List<Schedule> statusList = new ArrayList();
        
        for (Schedule schedule : schedules) {
            if(schedule.getStatus().getId()==status.getId()){
                statusList.add(schedule);
            }
        }
        return statusList;
    }
}