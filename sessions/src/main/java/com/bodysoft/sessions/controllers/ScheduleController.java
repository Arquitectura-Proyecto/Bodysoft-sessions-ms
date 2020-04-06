package com.bodysoft.sessions.controllers;

import com.bodysoft.sessions.POJO.RegisterSchedulePOJO;
import com.bodysoft.sessions.models.Schedule;
import com.bodysoft.sessions.models.SessionStatus;
import com.bodysoft.sessions.services.ScheduleService;
import com.bodysoft.sessions.services.StatusService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ScheduleController {

    private ScheduleService scheduleService;
    private StatusService statusService;
    

    public ScheduleController( ScheduleService scheduleService , StatusService statusService){
        this.scheduleService = scheduleService;
        this.statusService =statusService;
    }

    

    @PostMapping( value = { "/registro/nuevo-horario/" } )
    public ResponseEntity registerNewSchedule(@RequestBody RegisterSchedulePOJO schedulePOJO ){
        
       
        if(!scheduleService.IsTimeCorrect(schedulePOJO) || !scheduleService.isDateAvailable(schedulePOJO) || !scheduleService.isRightSchedule(schedulePOJO)){
            
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
        }

        SessionStatus status = statusService.findByIdStatus(1);
        

        Schedule newSchedule = new Schedule( );
        
        newSchedule.setDaySession(schedulePOJO.getDaySession());
        newSchedule.setEndTime(schedulePOJO.getEndTime());
        newSchedule.setIniTime(schedulePOJO.getIniTime());
        newSchedule.setIdCoach(schedulePOJO.getIdCoach());
        newSchedule.setStatus(status);

        
        scheduleService.save( newSchedule);
        
        return new ResponseEntity( HttpStatus.CREATED );
    }

}