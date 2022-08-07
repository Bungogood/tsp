package com.bungogood.tsp.model;

import java.util.Iterator;
import java.util.List;

public record Tour(Graph graph, List<Vertex> vertices) implements Iterable<Vertex> {

    private int toIndex(int index) {
        return Math.floorMod(index, vertices.size());
    }

    private Vertex get(int index) {
        return vertices.get(toIndex(index));
    }

    public int cost() {
        Iterator<Vertex> vit = vertices.iterator();

        Vertex start = vit.next();
        Vertex prev = start;
        Vertex cur = null;
        int total = 0;

        while (vit.hasNext()) {
            cur = vit.next();
            total += graph.cost(prev, cur);
            prev = cur;
        }

        return total + graph.cost(cur, start);
    }

    public int size() {
        return vertices.size();
    }

    @Override
    public Iterator<Vertex> iterator() {
        return vertices.iterator();
    }
}
