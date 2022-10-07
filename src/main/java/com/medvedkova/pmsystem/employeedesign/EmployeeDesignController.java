package com.medvedkova.pmsystem.employeedesign;

import com.medvedkova.pmsystem.application.PMSystemController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{projectId}/employee-design")
public class EmployeeDesignController
        implements PMSystemController {

    private final EmployeeDesignService employeeDesignService;

    public EmployeeDesignController(EmployeeDesignService employeeDesignService) {
        this.employeeDesignService = employeeDesignService;
    }

    @GetMapping
    public String designEmployeePage(@PathVariable String projectId,
                                     Model model) {
        model.addAttribute("modalWindow", "calculate");
        model.addAttribute("projects", employeeDesignService.getProjects());
        employeeDesignService.setTaskList(projectId);
        int numberEmployees = employeeDesignService.getNumberEmployees(projectId);
        model.addAttribute("numberEmployees", numberEmployees);
        model.addAttribute("minNumberEmployees",
                employeeDesignService.minNumberEmployeesToCompleteProjectInMinTime());
        model.addAttribute("minProjectExecutionTime",
                employeeDesignService.minProjectExecutionTimeAtLimitedNumberEmployees(numberEmployees));
        return "application-main";
    }
}
