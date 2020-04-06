package com.bodysoft.sessions.POJO;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegisterSchedulePOJO {

    private int idCoach;
    private LocalDate daySession;
    private LocalTime iniTime;
    private LocalTime endTime;

    /**
     *  METODOS
     */

    public int getIdCoach() {
        return idCoach;
    }

    public LocalDate getDaySession() {
        return daySession;
    }

    public LocalTime getIniTime() {
        return iniTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public void setDaySession(LocalDate daySession) {
        this.daySession = daySession;
    }

    public void setIniTime(LocalTime iniTime) {
        this.iniTime = iniTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    
    


}