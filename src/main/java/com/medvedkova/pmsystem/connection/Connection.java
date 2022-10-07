package com.medvedkova.pmsystem.connection;

import com.medvedkova.pmsystem.task.Task;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class Connection {

    @Id
    @SequenceGenerator(
            name = "connection_sequence",
            sequenceName = "connection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connection_sequence"
    )
    private int id;

    private Integer previousNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn
    private Task task;

    public Connection() {
    }

    public Connection(Integer previousNumber) {
        this.previousNumber = previousNumber;
    }

    public Connection(Integer previousNumber, Task task) {
        this.previousNumber = previousNumber;
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPreviousNumber() {
        return previousNumber;
    }

    public void setPreviousNumber(Integer previousNumber) {
        this.previousNumber = previousNumber;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
