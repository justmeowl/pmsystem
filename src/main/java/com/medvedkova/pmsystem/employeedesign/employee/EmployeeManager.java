package com.medvedkova.pmsystem.employeedesign.employee;

import com.medvedkova.pmsystem.employeedesign.graph.ModifiedNetworkGraph;
import com.medvedkova.pmsystem.employeedesign.graph.Vertex;
import com.medvedkova.pmsystem.employeedesign.tact.Tact;
import com.medvedkova.pmsystem.employeedesign.tact.TactManager;

import java.util.*;

class EmployeeManager {

    private final int numberEmployees;
    private int tactSize;
    private final ModifiedNetworkGraph graph;
    private final Map<Employee, TactManager> employees;

    EmployeeManager(int numberEmployees, ModifiedNetworkGraph graph) {
        this.numberEmployees = numberEmployees;
        this.graph = graph;
        this.employees = new LinkedHashMap<>(numberEmployees);
        createEmployees();
        fillTactByVertex(graph.getInputVertex());
    }

    private void createEmployees() {
        for (int i = 1; i <= numberEmployees; i++) {
            employees.put(new Employee(i), new TactManager());
        }
    }

    private void fillTactByVertex(Vertex vertex) {
        if (vertex == null)
            return;
        Tact lastTactOfPreviousVertices = getLastTactOfPreviousVertices(vertex);
        fillTimelinesByEmployees(vertex, getUnBusyEmployee(vertex, lastTactOfPreviousVertices));
        fillTactByVertex(maxPriorityVertex(vertex));
    }

    private Tact getLastTactOfPreviousVertices(Vertex vertex) {
        if (graph.isInputVertex(vertex))
            return Tact.emptyTact;

        Tact lastTactOfPreviousVertices = new Tact();
        Set<Vertex> srcVertices = graph.getVerticesByDst(vertex);
        for (Vertex srcVertex : srcVertices) {
            for (Map.Entry<Employee, TactManager> employee : employees.entrySet()) {
                Tact lastTactByIndex = employee.getValue().getLastTactByVertex(srcVertex);
                boolean isLastTactByIndexExceedsThanLastTact = !lastTactByIndex.isEmpty()
                        && lastTactByIndex.getNumber() > lastTactOfPreviousVertices.getNumber();
                if (isLastTactByIndexExceedsThanLastTact)
                    lastTactOfPreviousVertices = lastTactByIndex;
            }
        }
        return lastTactOfPreviousVertices;
    }

    private UnBusyEmployee getUnBusyEmployee(Vertex vertex, Tact lastTactOfPreviousVertices) {
        boolean isLastTactOfPreviousVerticesExceedsTactSize = lastTactOfPreviousVertices.isEmpty()
                || lastTactOfPreviousVertices.getNumber() == tactSize;
        if (isLastTactOfPreviousVerticesExceedsTactSize) {
            Tact nextTact = new Tact(tactSize + 1,
                    vertex);
            return new UnBusyEmployee(getEmployee(), nextTact);
        }

        for (int indexLastTact = lastTactOfPreviousVertices.getNumber();
             indexLastTact < tactSize;
             indexLastTact++) {
            for (Map.Entry<Employee, TactManager> employee : employees.entrySet()) {
                boolean hasVertex = employee.getValue().getTact(indexLastTact).getVertex() != null;
                if (!hasVertex)
                    return new UnBusyEmployee(employee.getKey(), employee.getValue().getTact(indexLastTact));
            }
        }
        return new UnBusyEmployee(getEmployee(), Tact.emptyTact);
    }

    private void fillTimelinesByEmployees(Vertex vertex, UnBusyEmployee unBusyEmployee) {
        int indexLastTact = unBusyEmployee.getTact().getNumber() - 1;
        for (int i = indexLastTact; i < indexLastTact + vertex.getWeight(); i++) {
            for (Map.Entry<Employee, TactManager> employee : employees.entrySet()) {
                if (i < employee.getValue().getTimeline().size()) {
                    if (employee.getKey().equals(unBusyEmployee.getEmployee()))
                        employee.getValue().setTact(i, vertex);
                } else {
                    if (employee.getKey().equals(unBusyEmployee.getEmployee()))
                        employee.getValue().addTact(vertex);
                    else
                        employee.getValue().addTact(null);

                    if (tactSize <= employee.getValue().getTimeline().size()-1)
                        tactSize++;
                }
            }
        }
    }

    Employee getEmployee() {
        return employees.keySet().stream().findFirst().orElse(null);
    }

    private Vertex maxPriorityVertex(Vertex vertex) {
        return graph.getVertices().stream()
                .filter(v -> v.getPriorityWeight() < vertex.getPriorityWeight())
                .max(Comparator.comparing(Vertex::getPriorityWeight))
                .orElse(null);
    }

    void print() {
        System.out.println("________________________________________________________");
        //print
        for (Employee employee : employees.keySet()) {
            System.out.print(employee.getNumber() + "\t\t\t\t\t\t\t\t\t");
        }
        System.out.println();
        System.out.println("________________________________________________________");
        for (int i = 0; i < tactSize; i++) {
            for (TactManager time : employees.values()) {
                System.out.print(time.getTact(i) + "\t\t\t\t\t");
            }
            System.out.println();
        }

        System.out.println("________________________________________________________");
    }

    public int getTactSize() {
        return tactSize;
    }
}
