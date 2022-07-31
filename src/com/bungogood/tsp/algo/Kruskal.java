package com.bungogood.tsp.algo;

import com.bungogood.tsp.model.*;

import java.util.*;

/**
 * https://en.wikipedia.org/wiki/Kruskals_algorithm
 *
 * algorithm Kruskal(G) is
 *     F:= ∅
 *     for each v ∈ G.V do
 *         MAKE-SET(v)
 *     for each (u, v) in G.E ordered by weight(u, v), increasing do
 *         if FIND-SET(u) ≠ FIND-SET(v) then
 *             F:= F ∪ {(u, v)} ∪ {(v, u)}
 *             UNION(FIND-SET(u), FIND-SET(v))
 *     return F
 */

public class Kruskal {
    public static MST solve(Graph graph) {
        MST mst = new MST();

        Queue<Edge> edges = new PriorityQueue<Edge>(graph.getEdges());
        HashMap<Vertex, Vertex> parents = new HashMap<Vertex, Vertex>();

        for (Vertex v : graph.getVertices()) {
            parents.put(v, v);
        }

        while (!edges.isEmpty() && mst.order() != graph.order()) {
            Edge edge = edges.remove();

            if (findRoot(edge.from, parents) != findRoot(edge.to, parents)) {
                reverse(edge.from, parents);
                parents.put(edge.from, edge.to);
                mst.add(edge);
            }
        }

        if (mst.order() != graph.order()) return null;

        return mst;
    }

    private static Vertex findRoot(Vertex vertex, HashMap<Vertex, Vertex> parents) {
        Vertex parent = parents.get(vertex);
        while (parent != vertex) {
            vertex = parent;
            parent = parents.get(vertex);
        }
        return parent;
    }

    private static void reverse(Vertex vertex, HashMap<Vertex, Vertex> parents) {
        Vertex parent = parents.get(vertex);

        if (vertex == parent) return;

        Vertex tmp = parents.get(parent);
        while (tmp != parent) {
            parents.put(parent, vertex);
            vertex = parent;
            parent = tmp;
            tmp = parents.get(parent);
        }
        parents.put(parent, vertex);
    }
}
