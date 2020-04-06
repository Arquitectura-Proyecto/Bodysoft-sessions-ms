package com.bodysoft.sessions.services;

import com.bodysoft.sessions.repositories.ScheduleRepository;
import com.bodysoft.sessions.models.Schedule;

import org.springframework.stereotype.Service;


@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService( ScheduleRepository scheduleRepository ){
        this.scheduleRepository = scheduleRepository;
    }

    public void save (Schedule schedule){
        scheduleRepository.save(schedule);
    }

    /**Cambiar a POJO */
    public boolean isRightSchedule( Schedule schedule ){
        boolean correctness = schedule.getDaySession( ) != null && schedule.getEndTime( ) != null 
        && schedule.getIdCoach() != 0 && schedule.getIniTime() !=null;
        
        return correctness;
    }

}