package com.medvedkova.pmsystem.task;

import com.medvedkova.pmsystem.connection.Connection;
import com.medvedkova.pmsystem.task.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Configuration
public class TaskConfiguration {

    @Bean
    public static List<Task> firstTaskList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "1", 1, null));
        taskList.add(new Task(2, "2", 1, null));
        taskList.add(new Task(3, "3", 13, null));
        taskList.add(new Task(4, "4", 18, null));
        taskList.add(new Task(5, "5", 10, null));
        taskList.add(new Task(6, "6", 2, null));
        taskList.add(new Task(7, "7", 1, null));
        taskList.add(new Task(8, "8", 5, null));
        taskList.add(new Task(9, "9", 22, null));
        taskList.add(new Task(10, "10", 2, null));
        taskList.add(new Task(11, "11", 1, null));
        taskList.add(new Task(12, "12", 1, null));

        taskList.get(2-1).addConnection(new Connection(1, taskList.get(2-1)));
        taskList.get(3-1).addConnection(new Connection(2, taskList.get(3-1)));
        taskList.get(5-1).addConnection(new Connection(3, taskList.get(5-1)));
        taskList.get(4-1).addConnection(new Connection(6, taskList.get(4-1)));
        taskList.get(4-1).addConnection(new Connection(5, taskList.get(4-1)));
        taskList.get(6-1).addConnection(new Connection(5, taskList.get(6-1)));
        taskList.get(7-1).addConnection(new Connection(4, taskList.get(7-1)));
        taskList.get(8-1).addConnection(new Connection(5, taskList.get(8-1)));
        taskList.get(9-1).addConnection(new Connection(5, taskList.get(9-1)));
        taskList.get(10-1).addConnection(new Connection(7, taskList.get(10-1)));
        taskList.get(10-1).addConnection(new Connection(9, taskList.get(10-1)));
        taskList.get(11-1).addConnection(new Connection(8, taskList.get(11-1)));
        taskList.get(11-1).addConnection(new Connection(10, taskList.get(11-1)));
        taskList.get(12-1).addConnection(new Connection(11, taskList.get(12-1)));
        return taskList;
    }

    @Bean
    public static List<Task> secondTaskList() {
        Task t1 = new Task(1, "A", 1, null);
        Task t2 = new Task(2, "B", 2, null);
        Task t3 = new Task(3, "C", 3, null);
        Task t4 = new Task(4, "D", 4, null);
        Task t5 = new Task(5, "E", 5, null);
        Task t6 = new Task(6, "Q", 6 , null);

        t1.addConnection(new Connection(6, t1));
        t2.addConnection(new Connection(6, t2));
        t3.addConnection(new Connection(1, t3));
        t3.addConnection(new Connection(2, t3));
        t4.addConnection(new Connection(2, t4));
        t5.addConnection(new Connection(3, t5));
        t5.addConnection(new Connection(4, t5));

        List<Task> tasks = new LinkedList<>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        tasks.add(t4);
        tasks.add(t5);
        tasks.add(t6);
        return tasks;
    }
}
