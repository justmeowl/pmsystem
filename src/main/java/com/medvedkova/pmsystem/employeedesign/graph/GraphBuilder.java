package com.medvedkova.pmsystem.employeedesign.graph;

import com.medvedkova.pmsystem.connection.Connection;

import com.medvedkova.pmsystem.task.Task;

import java.util.List;

public abstract class GraphBuilder {

    private final Graph graph;
    private final List<Task> tasks;

    public GraphBuilder(Graph graph, List<Task> tasks) {
        this.graph = graph;
        this.tasks = tasks;
    }

    GraphBuilder addVertices() {
        tasks.forEach(this::addVertex);
        return this;
    }

    private void addVertex(Task task) {
        graph.addVertex(new Vertex(task));
    }

    GraphBuilder addEdges() {
        tasks.forEach(this::addEdge);
        return this;
    }

    private void addEdge(Task task) {
        Vertex dst = graph.getVertexByNumber(task.getNumber());
        for (Connection connection : task.getConnectionSet()) {
            Vertex src = graph.getVertexByNumber(connection.getPreviousNumber());
            graph.addEdge(src, dst);
        }
    }

    Graph build() {
        return graph;
    }
}
