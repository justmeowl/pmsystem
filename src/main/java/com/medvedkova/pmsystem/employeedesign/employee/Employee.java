package com.medvedkova.pmsystem.employeedesign.employee;

import com.medvedkova.pmsystem.employeedesign.tact.TactManager;

class Employee {

    private int number;
    private final TactManager tactManager;

    Employee(int number) {
        this.number = number;
        tactManager = new TactManager();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + ":" + tactManager;
    }
}
