package com.medvedkova.pmsystem.employeedesign;

import com.medvedkova.pmsystem.employeedesign.employee.EmployeeDesign;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeManagerTest {

    private EmployeeDesign firstEmployeeDesign;
    private EmployeeDesign secondEmployeeDesign;

    @BeforeEach
    void setup() {
        firstEmployeeDesign = new EmployeeDesign(ModifiedNetworkGraphBuilderTest.firstGraph);
        secondEmployeeDesign = new EmployeeDesign(ModifiedNetworkGraphBuilderTest.secondGraph);
    }

    @Test
    void isMinNumberEmployeesToCompleteProjectInMinTime2() {
        int minNumber = firstEmployeeDesign.minNumberEmployeesToCompleteProjectInMinTime();
        assertEquals(2, minNumber);
    }

    @Test
    void isMinNumberEmployeesToCompleteProjectInMinTime1() {
        int minNumber = secondEmployeeDesign.minNumberEmployeesToCompleteProjectInMinTime();
        assertEquals(2, minNumber);
    }

    @Test
    void isMinProjectExecutionTimeAtLimitedNumberEmployees77WhenNumberEmployee1() {
        long minProjectExecutionTime = firstEmployeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(1);
        System.out.println(minProjectExecutionTime);
        assertEquals(77, minProjectExecutionTime);
    }

    @Test
    void isMinProjectExecutionTimeAtLimitedNumberEmployees52WhenNumberEmployee2() {
        long minProjectExecutionTime = firstEmployeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(2);
        System.out.println(minProjectExecutionTime);
        assertEquals(52, minProjectExecutionTime);
    }

    @Test
    void isMinProjectExecutionTimeAtLimitedNumberEmployees51WhenNumberEmployee3() {
        long minProjectExecutionTime = firstEmployeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(3);
        System.out.println(minProjectExecutionTime);
        assertEquals(51, minProjectExecutionTime);
    }

    @Test
    void isMinProjectExecutionTimeAtLimitedNumberEmployees51WhenNumberEmployee4() {
        firstEmployeeDesign.getGraph().getVertices().forEach(System.out::println);
        long minProjectExecutionTime = firstEmployeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(4);
        System.out.println(minProjectExecutionTime);
        assertEquals(51, minProjectExecutionTime);
    }


    @Test
    void isMinProjectExecutionTimeAtLimitedNumberEmployees21WhenNumberEmployee1() {
        secondEmployeeDesign.getGraph().getVertices().forEach(System.out::println);
        long minProjectExecutionTime = secondEmployeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(1);
        System.out.println(minProjectExecutionTime);
        assertEquals(21, minProjectExecutionTime);
    }

    @Test
    void isMinProjectExecutionTimeAtLimitedNumberEmployees17WhenNumberEmployee2() {
        secondEmployeeDesign.getGraph().getVertices().forEach(System.out::println);
        long minProjectExecutionTime = secondEmployeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(2);
        System.out.println(minProjectExecutionTime);
        assertEquals(17, minProjectExecutionTime);
    }

    @Test
    void isMinProjectExecutionTimeAtLimitedNumberEmployees17WhenNumberEmployee3() {
        secondEmployeeDesign.getGraph().getAdjVertices().entrySet().forEach(System.out::println);
        long minProjectExecutionTime = secondEmployeeDesign.minProjectExecutionTimeAtLimitedNumberEmployees(3);
        System.out.println(minProjectExecutionTime);
        assertEquals(17, minProjectExecutionTime);
    }
}