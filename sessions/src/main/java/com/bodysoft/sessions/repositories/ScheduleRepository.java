package com.bodysoft.sessions.repositories;



import com.bodysoft.sessions.models.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository <Schedule,Integer> {
    List<Schedule> findByIdCoach( int idCoach );

    List<Schedule> findByIdUser( int idUser );
    
    
}