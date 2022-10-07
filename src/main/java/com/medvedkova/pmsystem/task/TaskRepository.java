package com.medvedkova.pmsystem.task;

import com.medvedkova.pmsystem.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository
        extends JpaRepository<Task, Integer> {

    Optional<List<Task>> findAllByProjectId(int id);

    Optional<Task> findTaskByProjectIdAndNumber(int projectId, int number);

    void delete(Task task);

    boolean existsByProjectAndNumber(Project project, int number);

}
