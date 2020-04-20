package com.bodysoft.sessions.controllers;

import com.bodysoft.sessions.POJO.ChangeStatePOJO;
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
public class ChangeStatusControler {

    private ScheduleService scheduleService;
    private StatusService statusService;

    private final static Integer defaultIduser = 0;

    private final static Integer statedone = 4;
    private final static Integer statecancelled = 3;
    private final static Integer statecurrent = 2;
    private final static Integer stateavaible = 1;
    private final static Integer timetocancel = 30;
    private final static Integer timetoSetup = 30;

    public ChangeStatusControler(ScheduleService scheduleService, StatusService statusService) {
        this.scheduleService = scheduleService;
        this.statusService = statusService;
    }

    /**
     * 
     * @param pojo POJO with the information of the id user and thee id schedule to
     *             be set
     * @return HTTP status
     */
    @PutMapping(value = { "/schedule/set-a-date" })
    public ResponseEntity setAdate(@RequestBody ChangeStatePOJO pojo) {
        boolean isrigthid = scheduleService.isRightId(pojo.getSchedule());
        if (!isrigthid) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        Schedule schedule = scheduleService.getbyid(pojo.getSchedule());

        boolean isrigthUser = scheduleService.isRightUser(schedule, this.defaultIduser);
        if (!isrigthUser) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        boolean isPosible = scheduleService.isPosible(schedule, this.timetoSetup);

        if (!isPosible) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        SessionStatus status = statusService.findByIdStatus(this.statecurrent);
        schedule.setStatus(status);
        schedule.setIdUser(pojo.getPerson());
        scheduleService.save(schedule);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    /**
     * 
     * @param pojo POJO with the information of the id user and thee id schedule to
     *             be cancelled
     * @return HTTP status
     */
    @PutMapping(value = { "/schedule/cancel/user" })
    public ResponseEntity calcelUser(@RequestBody ChangeStatePOJO pojo) {
        boolean isrigthid = scheduleService.isRightId(pojo.getSchedule());
        if (!isrigthid) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        Schedule schedule = scheduleService.getbyid(pojo.getSchedule());
        boolean isrightstate = statusService.isrightState(schedule.getStatus(), this.statecurrent);

        if (!isrightstate) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        boolean isrigthUser = scheduleService.isRightUser(schedule, pojo.getPerson());
        if (!isrigthUser) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        boolean isPosible = scheduleService.isPosible(schedule, this.timetocancel);

        if (!isPosible) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        SessionStatus status = statusService.findByIdStatus(this.stateavaible);
        schedule.setStatus(status);
        schedule.setIdUser(this.defaultIduser);
        scheduleService.save(schedule);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    /**
     * 
     * @param pojo POJO with the information of the id coach and the id schedule to
     *             be cancelled
     * @return HTTP status
     */
    @PutMapping(value = { "/schedule/cancel/coach" })
    public ResponseEntity calcelCoach(@RequestBody ChangeStatePOJO pojo) {

        boolean isrigthid = scheduleService.isRightId(pojo.getSchedule());

        if (!isrigthid) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        Schedule schedule = scheduleService.getbyid(pojo.getSchedule());

        boolean isrightstate = statusService.isrightState(schedule.getStatus(), this.statecurrent);

        if (!isrightstate) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        boolean isrigthCoach = scheduleService.isRightCoach(schedule, pojo.getPerson());
        if (!isrigthCoach) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        boolean isPosible = scheduleService.isPosible(schedule, this.timetocancel);

        if (!isPosible) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        SessionStatus status = statusService.findByIdStatus(this.statecancelled);
        schedule.setStatus(status);
        scheduleService.save(schedule);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}