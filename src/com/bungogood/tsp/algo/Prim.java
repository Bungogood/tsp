package com.bungogood.tsp.algo;

import com.bungogood.tsp.model.*;
import java.util.PriorityQueue;

/**
 * https://en.wikipedia.org/wiki/Prims_algorithm
 *
 * algorithm Prim(G) is
 *     F:= {u}
 *     for each (u, v) in G.E ordered by weight(u, v), increasing do
 *         if FIND-SET(u) ≠ FIND-SET(v) then
 *             F:= F ∪ {(u, v)} ∪ {(v, u)}
 *             UNION(FIND-SET(u), FIND-SET(v))
 *     return F
 */

public class Prim {
    public static MST mst(Graph graph) {
        MST mst = new MST();

        Vertex v = graph.getVertex();

        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(graph.getEdges(v));

        while (!edges.isEmpty() && mst.order() != graph.order()) {
            Edge edge = edges.remove();

            if (!mst.contains(edge.to())) {
                mst.add(edge);
                edges.addAll(graph.getEdges(edge.to()));
            }
        }

        if (mst.order() != graph.order()) return null;

        return mst;
    }
}
