package com.medvedkova.pmsystem.employeedesign.graph;

import com.medvedkova.pmsystem.task.Task;

import java.util.List;

public class ModifiedNetworkGraphBuilder extends GraphBuilder {

    public ModifiedNetworkGraphBuilder(List<Task> tasks) {
        super(new ModifiedNetworkGraph(), tasks);
    }

    @Override
    public ModifiedNetworkGraphBuilder addVertices() {
        return (ModifiedNetworkGraphBuilder) super.addVertices();
    }

    @Override
    public ModifiedNetworkGraphBuilder addEdges() {
        return (ModifiedNetworkGraphBuilder) super.addEdges();
    }

    @Override
    public ModifiedNetworkGraph build() {
        return ((ModifiedNetworkGraph) super.build()).build();
    }
}
