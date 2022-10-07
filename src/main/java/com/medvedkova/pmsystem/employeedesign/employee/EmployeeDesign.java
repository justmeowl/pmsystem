package com.medvedkova.pmsystem.employeedesign.employee;

import com.medvedkova.pmsystem.employeedesign.graph.ModifiedNetworkGraph;
import com.medvedkova.pmsystem.employeedesign.graph.Vertex;
import com.medvedkova.pmsystem.exception.PmSystemException;

public class EmployeeDesign implements EmployeeDesigner {

    private final ModifiedNetworkGraph graph;

    public EmployeeDesign(ModifiedNetworkGraph graph) {
        this.graph = graph;
    }

    @Override
    public int minNumberEmployeesToCompleteProjectInMinTime() {
        return (int) Math.ceil(sumWeightVertices() / (double) graph.getCriticalPath());
    }

    @Override
    public long minProjectExecutionTimeAtLimitedNumberEmployees(int numberEmployees) {
        if (numberEmployees <= 0)
            throw new PmSystemException("A small Number of Employees!");
        if (numberEmployees == 1)
            return sumWeightVertices();

        EmployeeManager employeeManager = new EmployeeManager(numberEmployees, graph);
        employeeManager.print();
        return employeeManager.getTactSize();
    }

    // TODO: 20.11.2021 add visited Vertices List
    private long sumWeightVertices() {
        long sumWeighVertices = 0;
        for (Vertex vertex : graph.getVertices())
            sumWeighVertices += vertex.getWeight();
        return sumWeighVertices;
    }

    public ModifiedNetworkGraph getGraph() {
        return graph;
    }
}
