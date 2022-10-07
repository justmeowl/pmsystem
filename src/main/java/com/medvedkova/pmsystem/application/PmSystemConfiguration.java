package com.medvedkova.pmsystem.application;

import com.medvedkova.pmsystem.project.Project;
import com.medvedkova.pmsystem.project.ProjectRepository;
import com.medvedkova.pmsystem.task.TaskRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PmSystemConfiguration {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public PmSystemConfiguration(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Bean
    CommandLineRunner saveProjectsToDB(@Qualifier("projectListForUser1") List<Project> projects) {
        return args -> {
            for (Project project:
                    projects) {
                projectRepository.save(project);
                taskRepository.saveAll(project.getTasks());
            }
        };
    }

}
