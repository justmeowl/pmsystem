package com.medvedkova.pmsystem.project;

import com.medvedkova.pmsystem.exception.project.ProjectEmptyInputException;
import com.medvedkova.pmsystem.exception.project.ProjectNotFoundException;
import com.medvedkova.pmsystem.user.UserDetailsService;
import com.medvedkova.pmsystem.utils.DataUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    private final UserDetailsService userDetailsService;
    private final ProjectRepository projectRepository;

    public ProjectService(UserDetailsService userDetailsService,
                          ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        this.userDetailsService = userDetailsService;
    }

    public List<Project> getProjects() {
        return projectRepository.findAllByUser(userDetailsService.getCurrentUser());
    }

    public Project getProject(String id) {
        int parsedId = DataUtils.parseInt(id);
        return projectRepository.findProjectByIdAndUser(parsedId, userDetailsService.getCurrentUser())
                .orElseThrow(ProjectNotFoundException::new);
    }

    public void hasProject(String id) {
        boolean hasNotProject = !projectRepository.existsById(DataUtils.parseInt(id));
        if (hasNotProject)
            throw new ProjectNotFoundException();
    }

    public void addProject(Project project) {
        project.setUser(userDetailsService.getCurrentUser());
        //Checked InputData for existence
        if (hasMissedInput(project))
            throw new ProjectEmptyInputException("add",
                    "Input is required! Please fill out more than one field!");
        projectRepository.save(project);
    }

    public void editProject(String id, Project newProject) {
        //Checked InputData for existence
        if (newProject.isEmpty())
            throw new ProjectEmptyInputException(id + "/edit",
                    "Input is required! Please fill out all field!");
        Project oldProject = getProject(id);
        //Find inputs that will change the Project field
        boolean isCorrectInputName = DataUtils.isCorrectInputData(oldProject.getName(), newProject.getName());
        boolean isCorrectInputNumberEmployees = DataUtils.isCorrectInputData(oldProject.getNumberEmployees(),
                newProject.getNumberEmployees());
        if (isCorrectInputName)
            oldProject.setName(newProject.getName());
        if (isCorrectInputNumberEmployees)
            oldProject.setNumberEmployees(newProject.getNumberEmployees());
        //Save changes
        projectRepository.save(oldProject);
    }

    public void deleteProject(String id) {
        int parsedId = DataUtils.parseInt(id);
        hasProject(id);
        projectRepository.deleteById(parsedId);
    }

    private boolean hasMissedInput(Project project) {
        return project.getName().trim().isEmpty() || project.getNumberEmployees() == null;
    }
}
