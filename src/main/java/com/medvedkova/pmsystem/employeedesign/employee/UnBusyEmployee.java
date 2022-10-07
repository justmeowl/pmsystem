package com.medvedkova.pmsystem.employeedesign.employee;

import com.medvedkova.pmsystem.employeedesign.tact.Tact;

public class UnBusyEmployee {
    private final Employee employee;
    private final Tact tact;

    public UnBusyEmployee(Employee employee, Tact tact) {
        this.employee = employee;
        this.tact = tact;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Tact getTact() {
        return tact;
    }
}
