package com.medvedkova.pmsystem.employeedesign.tact;

import com.medvedkova.pmsystem.employeedesign.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class TactManager {

    private final List<Tact> timeline;

    public TactManager() {
        this.timeline = new ArrayList<>();
    }

    public Tact getTact(int index) {
        return timeline.get(index);
    }

    public Tact getLastTactByVertex(Vertex vertex) {
        for (int i = timeline.size() - 1; i >= 0; i--) {
            boolean hasVertex = timeline.get(i).getVertex() != null && timeline.get(i).getVertex().equals(vertex);
            if (hasVertex)
                return timeline.get(i);
        }
        return Tact.emptyTact;
    }

    public void setTact(int index, Vertex vertex) {
        getTact(index).setVertex(vertex);
    }

    public void addTact(Vertex vertex) {
        timeline.add(new Tact(timeline.size() + 1, vertex));
    }

    public List<Tact> getTimeline() {
        return timeline;
    }

    @Override
    public String toString() {
        return timeline.toString();
    }
}
