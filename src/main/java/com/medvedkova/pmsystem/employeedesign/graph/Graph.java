package com.medvedkova.pmsystem.employeedesign.graph;

import com.medvedkova.pmsystem.exception.PmSystemException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

abstract class Graph {

    private final Map<Vertex, Set<Vertex>> adjVertices;

    public Graph() {
        adjVertices = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        adjVertices.put(vertex, new HashSet<>());
    }

    public void addEdge(Vertex src, Vertex dst) {
        boolean hasVertices = !adjVertices.containsKey(src) || !adjVertices.containsKey(dst);
        if (hasVertices)
            throw new PmSystemException("Vertex not found!");

        adjVertices.get(src).add(dst);
    }

    public Set<Vertex> getVertices() {
        return adjVertices.keySet();
    }

    public Set<Vertex> getVerticesBySrc(Vertex vertex) {
        return adjVertices.get(vertex);
    }

    public Set<Vertex> getVerticesByDst(Vertex vertex) {
        return adjVertices.entrySet().stream()
                .filter(e -> e.getValue().contains(vertex))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Vertex getVertexByNumber(int number) {
        return getVertices().stream()
                .filter(v -> v.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new PmSystemException("Vertex Not Found!"));
    }

    public Map<Vertex, Set<Vertex>> getAdjVertices() {
        return adjVertices;
    }
}
