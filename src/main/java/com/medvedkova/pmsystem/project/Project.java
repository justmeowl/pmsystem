package com.medvedkova.pmsystem.project;

import com.medvedkova.pmsystem.task.Task;
import com.medvedkova.pmsystem.user.User;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Project implements Comparable<Project> {

    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )
    private int id;
    private String name;
    private Integer numberEmployees;

    @OneToMany(mappedBy = "project",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Task> tasks;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn
    private User user;

    public Project() {
    }

    public Project(String name, User user) {
        this.name = name;
        this.numberEmployees = 0;
        this.user = user;
        this.tasks = new LinkedList<>();
    }

    public Project(String name, Integer numberEmployees, User user) {
        this(name, user);
        this.numberEmployees = numberEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    boolean isEmpty() {
        return name.trim().isEmpty() && numberEmployees == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id && Objects.equals(name, project.name)
                && Objects.equals(numberEmployees, project.numberEmployees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberEmployees);
    }

    @Override
    public int compareTo(Project project) {
        return Integer.compare(id, project.id);
    }
}




