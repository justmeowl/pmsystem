package com.medvedkova.pmsystem.task;

import com.medvedkova.pmsystem.connection.Connection;
import com.medvedkova.pmsystem.project.Project;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table
public class Task{

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private int id;

    private Integer number;
    private String name;
    private Integer runtime;

    @NotNull
    @OneToMany(mappedBy = "task",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Connection> connections;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn
    private Project project;

    public Task() {
    }

    public Task(Integer number, String name, Integer runtime, Project project) {
        this.number = number;
        this.name = name;
        this.runtime = runtime;
        this.project = project;
        this.connections = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Set<Connection> getConnectionSet() {
        return connections;
    }

    public String getConnections() {
        return connections.stream()
                .map(c -> c.getPreviousNumber().toString())
                .collect(Collectors.joining(", "));
    }

    public void setConnections(Set<Connection> connections) {
        this.connections = connections;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public void removeConnection(int previousNumber) {
        connections.removeIf(c -> c.getPreviousNumber() == previousNumber);
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isEmpty() {
        return number == null
                && name.trim().isEmpty()
                && runtime == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(number, task.number)
                && Objects.equals(name, task.name)
                && Objects.equals(runtime, task.runtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, name, runtime);
    }
}
