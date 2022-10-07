package com.medvedkova.pmsystem.project;

import com.medvedkova.pmsystem.project.Project;
import com.medvedkova.pmsystem.task.Task;
import com.medvedkova.pmsystem.task.TaskConfiguration;
import com.medvedkova.pmsystem.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class ProjectConfiguration {

    @Bean
    public List<Project> projectListForUser1(@Qualifier("user1") User user1) {
        List<Project> projects = new LinkedList<>();

        for(char c = 'A'; c < 'F'; c++) {
            Project project = new Project("Project" + c, 1, user1);
            user1.addProject(project);
            if ((int) c % 2 == 0) {
                for (Task task:
                        TaskConfiguration.firstTaskList()) {
                    task.setProject(project);
                    project.getTasks().add(task);
                }
            }
            else {
                for (Task task:
                        TaskConfiguration.secondTaskList()) {
                    task.setProject(project);
                    project.getTasks().add(task);
                }
            }
            projects.add(project);
        }
        return projects;
    }
}
