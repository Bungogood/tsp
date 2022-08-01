package com.bungogood.tsp.model;

public record Edge(Vertex from, Vertex to, int distance) implements Comparable<Edge> {

    @Override
    public int compareTo(Edge edge) {
        return this.distance - edge.distance;
    }

    @Override
    public String toString() {
        return from.id() + "->" + to.id() + ":" + distance;
    }
}
