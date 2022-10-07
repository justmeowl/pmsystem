package com.medvedkova.pmsystem.task;

import com.medvedkova.pmsystem.application.PMSystemController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{projectId}/task")
public class TaskController
        implements PMSystemController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public String tasksPage(@PathVariable String projectId,
                           Model model) {
        model.addAttribute("modalWindow", "tasks");
        addProjectsAttribute(model);
        model.addAttribute("activeProjectId", projectId);
        model.addAttribute("tasks", taskService.getTasks(projectId));
        return "application-main";
    }

    @GetMapping("/add")
    public String addTaskPage(@PathVariable String projectId,
                              Model model) {
        addProjectsAttribute(model);
        model.addAttribute("modalWindow", "add-task");
        model.addAttribute("activeProjectId", projectId);
        return "application-main";
    }

    @PostMapping("/add")
    public String addTask(@PathVariable String projectId,
                          Task task,
                          String connectionsText) {
        taskService.addTask(projectId, task, connectionsText);
        return "redirect:/project/{projectId}/task/add";
    }

    @GetMapping("/{taskNumber}/edit")
    public String editTaskProjectPage(
            @PathVariable String projectId,
            @PathVariable String taskNumber,
            Model model) {
        addProjectsAttribute(model);
        Task task = taskService.getTask(projectId, taskNumber);
        model.addAttribute("activeTask", task);
        model.addAttribute("modalWindow", "edit-task");
        model.addAttribute("activeProjectId", projectId);
        return "application-main";
    }

    @PostMapping("/{taskNumber}/edit")
    public String editTask(
            @PathVariable String projectId,
            @PathVariable String taskNumber,
            Task task,
            String connectionsText) throws RuntimeException{
        taskService.editTask(projectId, taskNumber, task, connectionsText);
        return "redirect:/project/{projectId}/task/all";
    }

    @GetMapping("/{taskNumber}/delete")
    public String deleteTask(@PathVariable String projectId,
                             @PathVariable String taskNumber) {
        taskService.deleteTask(projectId, taskNumber);
        return "redirect:/project/{projectId}/task/all";
    }

    private void addProjectsAttribute(Model model) {
        model.addAttribute("projects", taskService.getProjects());
    }
}
