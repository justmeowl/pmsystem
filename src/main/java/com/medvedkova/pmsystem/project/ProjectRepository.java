package com.medvedkova.pmsystem.project;

import com.medvedkova.pmsystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository
        extends JpaRepository<Project, Integer> {

    List<Project> findAllByUser(User user);

    Optional<Project> findProjectByIdAndUser(int id, User user);
}
