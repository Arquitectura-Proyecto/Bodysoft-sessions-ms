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

import java.util.ArrayList;
import java.util.List;



@CrossOrigin
@RestController
public class GetbyIdControler {

    private ScheduleService scheduleService;
    private StatusService statusService;

    private final static Integer statedone = 4;
    private final static Integer statecancelled = 3;
    private final static Integer statecurrent = 2;
    private final static Integer stateavaible = 1;

    public GetbyIdControler( ScheduleService scheduleService , StatusService statusService){
        this.scheduleService = scheduleService;
        this.statusService =statusService;
    }

    /**
     * 
     * @param idCoach id of the coach
     * @return List of all the schedule of one coach
     */
    @GetMapping( value = { "/schedule/get-by-idCoach/{idCoach}" } )
    public ResponseEntity getAllbyIdCoach(  @PathVariable Integer idCoach){
        List <Schedule> schedules = scheduleService.getAllbyIdCoach(idCoach);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);

        List<Schedule> response = scheduleService.getAllbyIdCoach(idCoach);
        /*
        if(response.isEmpty()){
            return new ResponseEntity("No hay horarios por el id solicitado",HttpStatus.CONFLICT);
        }*/

        return new ResponseEntity(response,HttpStatus.OK);
        
    }

    /**
     * 
     * @param idUser id of the coach
     * @return List of the current schedules of one coach
     */
    @GetMapping(value = {"/schedule/get-by-idCoach/Current/{idCoach}"})
    public ResponseEntity getAllCoachCurrent(@PathVariable Integer idCoach){
        List <Schedule> schedules = scheduleService.getAllbyIdCoach(idCoach);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);
        SessionStatus current = statusService.findByIdStatus(statecurrent);
        SessionStatus avaible = statusService.findByIdStatus(stateavaible);
        

        List<Schedule> sList = scheduleService.getAllbyIdCoach(idCoach);
        List<Schedule> union = new ArrayList();

        union.addAll(statusService.getAllbystatus(avaible,sList));
        union.addAll(statusService.getAllbystatus(current,sList));
        /*
        if(union.isEmpty()){
            return new ResponseEntity("No hay horarios por el id solicitado",HttpStatus.CONFLICT);
        }*/

        return new ResponseEntity(union,HttpStatus.OK);

    }

    /**
     * 
     * @param idUser id of the coach
     * @return List of the avaible schedules of one coach
     */
    @GetMapping(value = {"/schedule/get-by-idCoach/Avaible/{idCoach}"})
    public ResponseEntity getAllCoachAvaible(@PathVariable Integer idCoach){
        List <Schedule> schedules = scheduleService.getAllbyIdCoach(idCoach);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);
        SessionStatus avaible = statusService.findByIdStatus(stateavaible);
        

        List<Schedule> sList = scheduleService.getAllbyIdCoach(idCoach);
        List<Schedule> union = statusService.getAllbystatus(avaible,sList);
/*
        if(union.isEmpty()){
            return new ResponseEntity("No hay horarios por el id solicitado",HttpStatus.CONFLICT);
        }
*/
        return new ResponseEntity(union,HttpStatus.OK);

    }
    /**
     * 
     * @param idUser id of the coach
     * @return List of all the schedule of one user
     */
    @GetMapping( value = { "/schedule/get-by-idUser/{idUser}" } )
    public ResponseEntity getAllbyIdUser(  @PathVariable Integer idUser){
        List <Schedule> schedules = scheduleService.getAllbyIdUser(idUser);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);

        List<Schedule> response = scheduleService.getAllbyIdUser(idUser);
/*
        if(response.isEmpty()){
            return new ResponseEntity("No hay horarios por el id solicitado",HttpStatus.CONFLICT);
        }
*/
        return new ResponseEntity(response,HttpStatus.OK);

    }

    /**
     * 
     * @param idUser id of the coach
     * @return List of the current schedules of one user
     */
    @GetMapping(value = {"/schedule/get-by-idUser/Current/{idUser}"})
    public ResponseEntity getAllUserCurrent(@PathVariable Integer idUser){
        List <Schedule> schedules = scheduleService.getAllbyIdUser(idUser);
        
        List<List <Schedule>> schedulesupdate = scheduleService.ispast(schedules);
        SessionStatus done = statusService.findByIdStatus(statedone);
        scheduleService.updateandremoveAll(schedulesupdate.get(1), done);
        SessionStatus current = statusService.findByIdStatus(statecurrent);

        List<Schedule> response = statusService.getAllbystatus(current,schedules);

        if(response.isEmpty()){
            return new ResponseEntity("No hay horarios por el id solicitado",HttpStatus.CONFLICT);
        }

        return new ResponseEntity(response,HttpStatus.OK);
    }


    /**
     * 
     * @param idSchedule id of the Schedule
     * @return Json with all the info of the schedule
     */
    @GetMapping( value = { "/schedule/get-by-idSchedule/{idSchedule}" } )
    public ResponseEntity getbyIdSchedule(  @PathVariable Integer idSchedule){
       
        if(!scheduleService.isRightId(idSchedule)){
            return new ResponseEntity(new ArrayList(),HttpStatus.OK);
        } 
        else{
            Schedule schedule= scheduleService.getbyid(idSchedule);
            return new ResponseEntity(schedule,HttpStatus.OK);
        }
    }
  
    

}
