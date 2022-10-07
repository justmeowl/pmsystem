package com.medvedkova.pmsystem.employeedesign.graph;

import com.medvedkova.pmsystem.task.Task;

import java.util.Objects;

public class Vertex {

    private final Task task;
    private final int number;
    private final int weight;
    private Integer priorityWeight;

    public Vertex(Task task) {
        this.task = task;
        this.number = task.getNumber();
        this.weight = task.getRuntime();
    }

    public Task getTask() {
        return task;
    }

    public int getNumber() {
        return number;
    }

    public int getWeight() {
        return weight;
    }

    public Integer getPriorityWeight() {
        return priorityWeight;
    }

    public void setPriorityWeight(Integer priorityWeight) {
        this.priorityWeight = priorityWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return number == vertex.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Vertex " +
                number +
                " (" +
                priorityWeight +
                ")";
    }
}
