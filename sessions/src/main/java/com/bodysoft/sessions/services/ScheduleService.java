package com.bodysoft.sessions.services;

import java.time.LocalDate;
import java.time.LocalTime;

import com.bodysoft.sessions.POJO.RegisterSchedulePOJO;
import com.bodysoft.sessions.models.Schedule;
import com.bodysoft.sessions.repositories.ScheduleRepository;

import org.springframework.stereotype.Service;


@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final int Numberofdays;

    public ScheduleService( ScheduleRepository scheduleRepository ){
        this.scheduleRepository = scheduleRepository;
        this.Numberofdays = 7;
    }

    /**
     * 
     * @param schedule Register POJO
     */
    public void save (Schedule schedule){
        scheduleRepository.save(schedule);
    }

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
}