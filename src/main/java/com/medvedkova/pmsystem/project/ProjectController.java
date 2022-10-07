package com.medvedkova.pmsystem.project;

import com.medvedkova.pmsystem.application.PMSystemController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController
        implements PMSystemController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // TODO: 15.11.2021 set values to properties file
    @GetMapping
    public String projectsPage(Model model) {
        model.addAttribute("modalWindow", "tasks");
        addProjectsAttribute(model);
        return "application-main";
    }

    @GetMapping("/add")
    public String addProjectPage(Model model) {
        model.addAttribute("modalWindow", "add-project");
        addProjectsAttribute(model);
        return "application-main";
    }

    @PostMapping("/add")
    public String addProject(Project project) {
        projectService.addProject(project);
        return "redirect:/project/add";
    }

    @GetMapping("/{projectId}/edit")
    public String editProjectPage(@PathVariable String projectId,
                                  Model model) {
        model.addAttribute("modalWindow", "edit-project");
        addProjectsAttribute(model);
        model.addAttribute("activeProject", projectService.getProject(projectId));
        return "application-main";
    }

    @PostMapping("/{projectId}/edit")
    public String editProject(@PathVariable String projectId, Project project) {
        projectService.editProject(projectId, project);
        return "redirect:/project";
    }

    @GetMapping("/{projectId}/delete")
    public String deleteProject(@PathVariable String projectId) {
        projectService.deleteProject(projectId);
        return "redirect:/project";
    }

    private void addProjectsAttribute(Model model) {
        model.addAttribute("projects", projectService.getProjects());
    }
}
