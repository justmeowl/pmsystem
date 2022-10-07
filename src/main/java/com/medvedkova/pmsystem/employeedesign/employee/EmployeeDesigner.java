package com.medvedkova.pmsystem.employeedesign.employee;

public interface EmployeeDesigner {

    int minNumberEmployeesToCompleteProjectInMinTime();

    long minProjectExecutionTimeAtLimitedNumberEmployees(int numberEmployees);
}
