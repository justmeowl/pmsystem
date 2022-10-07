package com.medvedkova.pmsystem.employeedesign;

import com.medvedkova.pmsystem.application.PMSystemController;
import com.medvedkova.pmsystem.employeedesign.employee.EmployeeDesign;
import com.medvedkova.pmsystem.employeedesign.graph.ModifiedNetworkGraph;
import com.medvedkova.pmsystem.employeedesign.graph.ModifiedNetworkGraphBuilder;
import com.medvedkova.pmsystem.project.Project;
import com.medvedkova.pmsystem.project.ProjectService;
import com.medvedkova.pmsystem.task.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDesignService
        implements PMSystemController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private EmployeeDesign employeeDesign;

    public EmployeeDesignService(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    int minNumberEmployeesToCompleteProjectInMinTime() {
        return employeeDesign.minNumberEmployeesToCompleteProjectInMinTime();
    }

    long minProjectExecutionTimeAtLimitedNumberEmployees(int numberEmployees) {
        return employeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(numberEmployees);
    }

    int getNumberEmployees(String projectId) {
        Project project = projectService.getProject(projectId);
        return project.getNumberEmployees();
    }

    void setTaskList(String projectId) {
        ModifiedNetworkGraph modifiedNetworkGraphBuilder =
                new ModifiedNetworkGraphBuilder(taskService.getTasks(projectId))
                        .addVertices()
                        .addEdges()
                        .build();
        employeeDesign = new EmployeeDesign(modifiedNetworkGraphBuilder);
    }

    public List<Project> getProjects() {
        return projectService.getProjects();
    }
}
