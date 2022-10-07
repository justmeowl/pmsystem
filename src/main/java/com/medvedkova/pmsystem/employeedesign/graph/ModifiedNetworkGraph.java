package com.medvedkova.pmsystem.employeedesign.graph;

import com.medvedkova.pmsystem.connection.Connection;
import com.medvedkova.pmsystem.exception.PmSystemException;
import com.medvedkova.pmsystem.task.Task;

import java.util.Set;

/**
 * A Modified Network Graph is an extended network graph that consists of {@link Vertex}
 * ({@link Task}), edges from {@link Graph}
 * ({@link Connection} between tasks), vertex weights (task execution time/runtime).
 * <p>A Network Graph consists of one input vertex and one output vertex.
 */
public class ModifiedNetworkGraph extends Graph{

    private Vertex inputVertex;
    private Vertex outputVertex;
    private Integer criticalPath;

    ModifiedNetworkGraph() {
        super();
    }

    ModifiedNetworkGraph build() {
        if (!isNetworkGraph())
            throw new PmSystemException("Graph is not network!");
        calculateWeightPriorities(outputVertex);
        setCriticalPath();
        return this;
    }

    private boolean isNetworkGraph() {
        return isOneInputVertex() && isOneOutputVertex();
    }

    private boolean isOneInputVertex() {
        int counter = 0;
        for (Vertex dstVertex : getVertices()) {
            Set<Vertex> srcVertices = getVerticesByDst(dstVertex);
            if (srcVertices.isEmpty()) {
                if (counter == 1)
                    return false;
                counter++;
                inputVertex = dstVertex;
            }
        }
        return true;
    }

    private boolean isOneOutputVertex() {
        try {
            findOutputVertex(inputVertex);
        } catch (PmSystemException e) {
            return false;
        }
        return true;
    }

    private void findOutputVertex(Vertex visitVertex) {
        Set<Vertex> dstVertices = getVerticesBySrc(visitVertex);
        if (dstVertices.isEmpty()) {
            if (outputVertex == null)
                outputVertex = visitVertex;
            if (!visitVertex.equals(outputVertex))
                throw new PmSystemException("More than one Output Vertex!");
        } else
            dstVertices.forEach(this::findOutputVertex);
    }

    private void calculateWeightPriorities(Vertex activeVertex) {
        boolean isOutputVertexActive = activeVertex.equals(outputVertex);
        if (isOutputVertexActive) {
            activeVertex.setPriorityWeight(activeVertex.getWeight());
            for (Vertex srcVertex : getVerticesByDst(activeVertex)) {
                calculateWeightPriorities(srcVertex);
            }
        }

        int maxPriorityDstVertex = maxPriorityDstVertex(activeVertex);

        for (Vertex srcVertex : getVerticesByDst(activeVertex)) {
            srcVertex.setPriorityWeight(
                    maxPriorityDstVertex + srcVertex.getWeight()
            );
            calculateWeightPriorities(srcVertex);
        }
    }

    private int maxPriorityDstVertex(Vertex vertex) {
        int max = 0;
        for (Vertex dstVertex : getVerticesBySrc(vertex)) {
            boolean isOutputVertexDst = dstVertex.equals(outputVertex);
            if (isOutputVertexDst)
                vertex.setPriorityWeight(vertex.getWeight() + outputVertex.getWeight());
            Integer weighPriorities = vertex.getPriorityWeight();
            if (max < weighPriorities)
                max = weighPriorities;
        }
        return max;
    }

    private void setCriticalPath() {
        criticalPath = inputVertex.getPriorityWeight();
    }

    public boolean isInputVertex(Vertex vertex) {
        return vertex.equals(inputVertex);
    }

    public Vertex getInputVertex() {
        return inputVertex;
    }

    public Integer getCriticalPath() {
        return criticalPath;
    }
}
