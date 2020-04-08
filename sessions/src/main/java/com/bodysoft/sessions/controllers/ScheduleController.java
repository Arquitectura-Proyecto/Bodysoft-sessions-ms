package com.bodysoft.sessions.controllers;

import com.bodysoft.sessions.POJO.RegisterSchedulePOJO;
import com.bodysoft.sessions.models.Schedule;
import com.bodysoft.sessions.models.SessionStatus;
import com.bodysoft.sessions.services.ScheduleService;
import com.bodysoft.sessions.services.StatusService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin
@RestController
public class ScheduleController {

    private ScheduleService scheduleService;
    private StatusService statusService;

    private final static Integer defaultIdstatus=1;
    private final static Integer defaultIduser=0;

    private final static Integer statedone = 4;
    private final static Integer statecancelled = 3;
    private final static Integer statecurrent = 2;

    public ScheduleController( ScheduleService scheduleService , StatusService statusService){
        this.scheduleService = scheduleService;
        this.statusService =statusService;
    }

    
    /**
     * 
     * @param schedulePOJO POJO with the body of the request
     * @return HttpStatus if create or not
     */
    @PostMapping( value = { "/schedule/new-schedule/" } )
    public ResponseEntity registerNewSchedule(@RequestBody RegisterSchedulePOJO schedulePOJO ){
        
       
        if(!scheduleService.IsTimeCorrect(schedulePOJO) || !scheduleService.isDateAvailable(schedulePOJO) || !scheduleService.isRightSchedule(schedulePOJO)){
            
            return new ResponseEntity( HttpStatus.BAD_REQUEST );
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
        
        return new ResponseEntity( HttpStatus.CREATED );
    }

    /**
     * 
     * @param idCoach id of the coach
     * @return List of all the schedule of one coach
     */
    @GetMapping( value = { "/schedule/get-by-idCoach/{idCoach}" } )
    public List<Schedule> getAllbyIdCoach(  @PathVariable Integer idCoach){
        List <Schedule> schedules = scheduleService.getAllbyIdCoach(idCoach);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);
        
        return scheduleService.getAllbyIdCoach(idCoach);
    }

    /**
     * 
     * @param idUser id of the coach
     * @return List of the current schedules of one coach
     */
    @GetMapping(value = {"/schedule/get-by-idCoach/Current/{idCoach}"})
    public List<Schedule> getAllCoachCurrent(@PathVariable Integer idCoach){
        List <Schedule> schedules = scheduleService.getAllbyIdCoach(idCoach);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);
        SessionStatus current = statusService.findByIdStatus(statecurrent);

        return statusService.getAllbystatus(current,schedulesupdate.get(0));
    }

    /**
     * 
     * @param idUser id of the coach
     * @return List of all the schedule of one user
     */
    @GetMapping( value = { "/schedule/get-by-idUser/{idUser}" } )
    public List<Schedule> getAllbyIdUser(  @PathVariable Integer idUser){
        List <Schedule> schedules = scheduleService.getAllbyIdUser(idUser);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);
        
        return scheduleService.getAllbyIdUser(idUser);
    }

    /**
     * 
     * @param idUser id of the coach
     * @return List of the current schedules of one user
     */
    @GetMapping(value = {"/schedule/get-by-idUser/Current/{idUser}"})
    public List<Schedule> getAllUserCurrent(@PathVariable Integer idUser){
        List <Schedule> schedules = scheduleService.getAllbyIdUser(idUser);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);
        SessionStatus current = statusService.findByIdStatus(statecurrent);

        return statusService.getAllbystatus(current,schedules);
    }


    /**
     * 
     * @param idSchedule id of the Schedule
     * @return Json with all the info of the schedule
     */
    @GetMapping( value = { "/schedule/get-by-idSchedule/{idSchedule}" } )
    public Schedule getbyIdSchedule(  @PathVariable Integer idSchedule){

        return scheduleService.getbyid(idSchedule);
    }

    

    /** QUITAR */
    @RequestMapping("/")
    public String home() {
        return "Hello Spring Boot with Docker";
    }

}
