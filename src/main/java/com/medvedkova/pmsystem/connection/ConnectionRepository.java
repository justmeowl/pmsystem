package com.medvedkova.pmsystem.connection;

import com.medvedkova.pmsystem.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository
        extends JpaRepository<Connection, Long> {

    @Modifying
    @Query("DELETE FROM Connection c where c.task = ?1")
    void deleteAllByTask(Task task);
}
