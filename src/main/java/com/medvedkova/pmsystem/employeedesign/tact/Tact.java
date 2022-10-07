package com.medvedkova.pmsystem.employeedesign.tact;

import com.medvedkova.pmsystem.employeedesign.graph.Vertex;

import java.util.Objects;

public class Tact {

    private int number;
    private Vertex vertex;
    public static final Tact emptyTact = new Tact();

    public Tact(){
    }

    public Tact(int number, Vertex vertex) {
        this.number = number;
        this.vertex = vertex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public boolean isEmpty() {
        return number == 0 && vertex == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tact tact = (Tact) o;
        return number == tact.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return number + "\t" +
                vertex;
    }

}
