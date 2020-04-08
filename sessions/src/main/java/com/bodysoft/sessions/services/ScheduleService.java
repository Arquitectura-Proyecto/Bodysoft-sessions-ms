package com.bodysoft.sessions.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.bodysoft.sessions.POJO.RegisterSchedulePOJO;
import com.bodysoft.sessions.models.Schedule;
import com.bodysoft.sessions.models.SessionStatus;
import com.bodysoft.sessions.repositories.ScheduleRepository;

import org.springframework.stereotype.Service;


@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final int Numberofdays;
    private final Integer stateDone;
    private final Integer statecancelled;


    public ScheduleService( ScheduleRepository scheduleRepository ){
        this.scheduleRepository = scheduleRepository;
        this.Numberofdays = 7;
        this.statecancelled=3;
        this.stateDone=4;
    }


    /* GET BY __ */

    /**
     * 
     * @param idCoach id of the coach
     * @return List of all the schedule of one coach
     */
    public List<Schedule> getAllbyIdCoach (Integer idCoach){
        return scheduleRepository.findByIdCoach(idCoach);
    }

    /**
     * 
     * @param idUser id of the user
     * @return List of all the schedule of one user
     */
    public List<Schedule> getAllbyIdUser (Integer idUser){
        return scheduleRepository.findByIdUser(idUser);
    }


    /**
     * 
     * @param idSchedule id of the shedule
     * @return Object with the whole info of the schedule
     */
    public Schedule getbyid (Integer idSchedule){
        return scheduleRepository.findById(idSchedule).orElse(null);
    }


    /* SAVE */

    /**
     * 
     * @param schedule Register POJO
     */
    public void save (Schedule schedule){
        scheduleRepository.save(schedule);
    }

    /* CONFIRMATION */
    /**
     * 
     * @param schedule Register POJO
     * @return if all the necessary attributes are in the POJO
     */
    public boolean isRightSchedule( RegisterSchedulePOJO schedule ){
        boolean correctness = schedule.getDaySession( ) != null && schedule.getEndTime( ) != null  && schedule.getIdCoach() != 0 && schedule.getIniTime() !=null;
        
        return correctness;

    }



    /**
     * 
     * @param schedule Registere POJO
     * @return If the date is available (Beforee the actual time and no more than 7 days after today)
     */
    public boolean isDateAvailable (RegisterSchedulePOJO schedule){
       LocalDate today = LocalDate.now();
       LocalTime time = LocalTime.now();
  
       boolean DayAvaible = today.plusDays(this.Numberofdays).isAfter(schedule.getDaySession()) && today.isBefore(schedule.getDaySession());
      
       boolean TimeAvaible = true;

       if(today.isEqual(schedule.getDaySession())){
             TimeAvaible = time.isBefore(schedule.getIniTime());
       }

       boolean correctness = DayAvaible && TimeAvaible;

       return correctness;
    };

    /**
     * 
     * @param schedule Register POJO
     * @return If the endTime in the POJO is before the iniTime
     */
    public boolean IsTimeCorrect (RegisterSchedulePOJO schedule){
        boolean correctness = schedule.getIniTime().isBefore(schedule.getEndTime());
        return correctness;
    }


    /* UPDATE */

    /**
     * 
     * @param schedules List of schedule to be updated
     * @param done New State
     */
    public void updateandremoveAll (List<Schedule> schedules,SessionStatus done ){


        for (Schedule schedule : schedules) {
            
            if(schedule.getIdUser()==0){
                scheduleRepository.deleteById(schedule.getId_schedule());
            }
            else{
                schedule.setStatus(done);
                scheduleRepository.save(schedule);
            }
                        
        }

    }
    /**
     * 
     * @param schedules List of schedule to be filtered
     * @return List of current schedules and the list of schedule to be updated
     */
    public List<List<Schedule>> ispast (List<Schedule> schedules){
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.now();

        List<Schedule> past = new ArrayList();
        List<Schedule> current = new ArrayList();

        for (Schedule schedule : schedules) {
            if(today.isEqual(schedule.getDaySession())){
                if(time.isAfter(schedule.getEndTime())){
                    if (schedule.getStatus().getId()!=this.statecancelled ||schedule.getStatus().getId()!=this.stateDone){
                        past.add(schedule);
                    }
                
                }
            }
            else if (today.isAfter(schedule.getDaySession())){
                if (schedule.getStatus().getId()!=this.statecancelled ||schedule.getStatus().getId()!=this.stateDone){
                    past.add(schedule);
                }
            }
            else{
                current.add(schedule);
            }
        }
            List<List<Schedule>> finalList = new ArrayList();
            finalList.add(current);
            finalList.add(past);
        return finalList;

    }

    /**
     * 
     * @param schedule Schedulee that is going to be updated
     * @param status New status
     */
    public void updateStatus (Schedule schedule, SessionStatus status){
        schedule.setStatus(status);
        scheduleRepository.save(schedule);
    }
}