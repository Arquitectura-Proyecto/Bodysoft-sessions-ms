package com.bodysoft.sessions.controllers;

import com.bodysoft.sessions.POJO.ChangeStatePOJO;
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

    private final static Integer defaultIdstatus=1;
    private final static Integer defaultIduser=0;


    public ScheduleController( ScheduleService scheduleService , StatusService statusService){
        this.scheduleService = scheduleService;
        this.statusService =statusService;
    }

    
    /**
     * 
     * @param schedulePOJO POJO with the body of the request
     * @return HttpStatus if create or not
     */
    @PostMapping( value = { "/schedule/schedule/create" } )
    public ResponseEntity registerNewSchedule(@RequestBody RegisterSchedulePOJO schedulePOJO ){
        
       
        if(!scheduleService.IsTimeCorrect(schedulePOJO) || !scheduleService.isDateAvailable(schedulePOJO) || !scheduleService.isRightSchedule(schedulePOJO)){
            
            return new ResponseEntity("Campos con valores no validos", HttpStatus.BAD_REQUEST );
        }

        SessionStatus status = statusService.findByIdStatus(defaultIdstatus);
        

        Schedule newSchedule = new Schedule( );
        
        newSchedule.setDaySession(schedulePOJO.getDaySession());
        newSchedule.setEndTime(schedulePOJO.getEndTime());
        newSchedule.setIniTime(schedulePOJO.getIniTime());
        newSchedule.setIdCoach(schedulePOJO.getIdCoach());
        newSchedule.setIdUser(defaultIduser);
        newSchedule.setStatus(status);

        
        scheduleService.save( newSchedule);
        return new ResponseEntity( newSchedule, HttpStatus.CREATED );
    }
     /**
     * 
     * @param pojo POJO with the information of the id coach and the id schedule to be cancelled
     * @return HTTP status
     */
    @DeleteMapping( value = {"/schedule/schedule/delete"})
    public ResponseEntity deleteSchedule (@RequestBody ChangeStatePOJO pojo){

        boolean isrigthid = scheduleService.isRightId(pojo.getSchedule());
        if(!isrigthid){
            return new ResponseEntity("El horario no existe", HttpStatus.CONFLICT );
        }
        Schedule schedule = scheduleService.getbyid(pojo.getSchedule());

        boolean isrigthCoach = scheduleService.isRightCoach(schedule, pojo.getPerson());
        if(!isrigthCoach){
            return new ResponseEntity("Usuario no autorizado", HttpStatus.FORBIDDEN );
        }
        boolean isRightUser = scheduleService.isRightUser(schedule, 0);
        if(!isRightUser){
            return new ResponseEntity("El horario ya esta ocupado", HttpStatus.CONFLICT );
        }

        scheduleService.delete(schedule);

        return new ResponseEntity( HttpStatus.NO_CONTENT );

    }

    /** QUITAR */
    @RequestMapping("/")
    public String home() {
        return "Session microservice running";
    }

}
