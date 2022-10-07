package com.medvedkova.pmsystem.employeedesign;

import com.medvedkova.pmsystem.connection.Connection;
import com.medvedkova.pmsystem.employeedesign.graph.ModifiedNetworkGraph;
import com.medvedkova.pmsystem.employeedesign.graph.ModifiedNetworkGraphBuilder;
import com.medvedkova.pmsystem.exception.PmSystemException;
import com.medvedkova.pmsystem.task.Task;
import com.medvedkova.pmsystem.task.TaskLists;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModifiedNetworkGraphBuilderTest {

    static ModifiedNetworkGraph firstGraph = createGraph(TaskLists.createFirstTaskList());
    static ModifiedNetworkGraph secondGraph = createGraph(TaskLists.createSecondTaskList());

    static ModifiedNetworkGraph createGraph(List<Task> taskList) {
        return new ModifiedNetworkGraphBuilder(taskList).addVertices()
                .addEdges()
                .build();
    }

    @Test
    void whenVerticesNotAdded() {
        assertThrows(PmSystemException.class, () -> {
            List<Task> taskList = TaskLists.createFirstTaskList();
            ModifiedNetworkGraph modifiedNetworkGraph = new ModifiedNetworkGraphBuilder(taskList)
                    .addEdges()
                    .build();
        });
    }

    @Test
    void whenTwoInputVertices() {
        Task t1 = new Task(1, "A", 10, null);
        Task t2 = new Task(2, "B", 10, null);
        Task t3 = new Task(3, "C", 10, null);
        Task t4 = new Task(4, "D", 10, null);
        Task t5 = new Task(5, "E", 10, null);
        Task t6 = new Task(6, "Q", 10, null);

        t1.addConnection(new Connection(6));
        t3.addConnection(new Connection(1));
        t3.addConnection(new Connection(2));
        t4.addConnection(new Connection(2));
        t5.addConnection(new Connection(4));
        t5.addConnection(new Connection(3));

        List<Task> tasks = new LinkedList<>();
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);
        tasks.add(t4);
        tasks.add(t5);
        tasks.add(t6);

        assertThrows(PmSystemException.class, () -> {
            ModifiedNetworkGraph modifiedNetworkGraph = new ModifiedNetworkGraphBuilder(tasks).addVertices().addEdges().build();
        });
    }

    @Test
    void whenTwoOutputVertices() {
        List<Task> taskList = TaskLists.createSecondTaskList();
        taskList.get(4)
                .removeConnection(4);

        assertThrows(PmSystemException.class, () -> {
            ModifiedNetworkGraph modifiedNetworkGraph = new ModifiedNetworkGraphBuilder(taskList)
                    .addVertices()
                    .addEdges()
                    .build();
        });
    }

    @Test
    void isCriticalPathForGraph51() {
        assertEquals(51, firstGraph.getCriticalPath());
    }

    private void printGraph() {
        firstGraph.getAdjVertices()
                .forEach((k, v) -> System.out.println(k + "->" + v));
    }
}