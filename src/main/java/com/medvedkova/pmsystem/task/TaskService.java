package com.medvedkova.pmsystem.task;

import com.medvedkova.pmsystem.connection.ConnectionService;
import com.medvedkova.pmsystem.project.ProjectService;
import com.medvedkova.pmsystem.utils.DataUtils;
import com.medvedkova.pmsystem.exception.project.ProjectNotFoundException;
import com.medvedkova.pmsystem.exception.task.TaskEmptyInputException;
import com.medvedkova.pmsystem.exception.task.TaskNotFoundException;
import com.medvedkova.pmsystem.exception.task.TaskNumberNotUniqueException;
import com.medvedkova.pmsystem.project.Project;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TaskService {

    private final ProjectService projectService;
    private final ConnectionService connectionService;
    private final TaskRepository taskRepository;

    public TaskService(ProjectService projectService, ConnectionService connectionService, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
        this.connectionService = connectionService;
    }

    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    public List<Task> getTasks(String projectId) {
        return taskRepository.findAllByProjectId(DataUtils.parseInt(projectId))
                .orElseThrow(ProjectNotFoundException::new);
    }

    public Task getTask(String projectId, String taskNumber) {
        int parsedProjectId = DataUtils.parseInt(projectId);
        int parsedTaskNumber = DataUtils.parseInt(taskNumber);
        return getTask(parsedProjectId, parsedTaskNumber);
    }

    public Task getTask(int projectId, int taskNumber) {
        return taskRepository.findTaskByProjectIdAndNumber(projectId, taskNumber)
                .orElseThrow(() -> new TaskNotFoundException(projectId + "/task/all"));
    }

    public void addTask(String projectId, Task task, String connectionText) {
        Project project = projectService.getProject(projectId);
        //Checked InputData for existence
        final String pagePath = project.getId() + "/task/add";
        if (hasMissedInput(task, connectionText))
            throw new TaskEmptyInputException(pagePath,
                    "Input is required! Please fill out more than one field!");
        //Check Task Number for uniqueness
        if (isTaskNumberNotUnique(project, task.getNumber()))
            throw new TaskNumberNotUniqueException(pagePath);
        task.setProject(project);
        taskRepository.save(task);
    }

    public void editTask(String projectId, String taskNumber, Task newTask, String connectionsText) {
        Project project = projectService.getProject(projectId);
        int parsedTaskNumber = DataUtils.parseInt(taskNumber);
        //Checked InputData for existence
        final String pagePath = project.getId() + "/task/" + taskNumber + "/edit";
        boolean isAllInputsEmpty = newTask.isEmpty() && connectionsText.isEmpty();
        if (isAllInputsEmpty)
            throw new TaskEmptyInputException(pagePath,
                    "Input is required! Please fill out all field!");
        Task oldTask = getTask(project.getId(), parsedTaskNumber);
        //Find inputs that will change the Task field
        boolean isCorrectInputNumber = DataUtils.isCorrectInputData(oldTask.getNumber(), newTask.getNumber());
        boolean isCorrectInputName = DataUtils.isCorrectInputData(oldTask.getName(), newTask.getName());
        boolean isCorrectInputRuntime = DataUtils.isCorrectInputData(oldTask.getRuntime(), newTask.getRuntime());
        boolean isNotEmptyConnectionText = !(connectionsText.isEmpty() || connectionsText.trim().isEmpty());
        if (isCorrectInputNumber) {
            //Check Task Number for uniqueness
            if (isTaskNumberNotUnique(project, parsedTaskNumber))
                throw new TaskNumberNotUniqueException(pagePath);
            oldTask.setNumber(newTask.getNumber());
        }
        if (isCorrectInputName)
            oldTask.setName(newTask.getName());
        if (isCorrectInputRuntime)
            oldTask.setRuntime(newTask.getRuntime());
        if (isNotEmptyConnectionText)
            connectionService.editConnection(oldTask, connectionsText);
        //Save changes
        oldTask.setProject(project);
        taskRepository.save(oldTask);
    }

    public void deleteTask(String projectId, String taskNumber) {
        Task task = getTask(projectId, taskNumber);
        taskRepository.delete(task);
    }

    public boolean hasMissedInput(Task task, String connectionText) {
        return task.getNumber() == null
                || task.getName().trim().isEmpty()
                || task.getRuntime() == null
                || connectionText.trim().isEmpty();
    }

    private boolean isTaskNumberNotUnique(Project project, int number) {
        return taskRepository.existsByProjectAndNumber(project, number);
    }
}
