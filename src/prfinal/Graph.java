/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prfinal;

import java.util.List;

/**
 *
 * @author usuario.general
 */
public class Graph {
    private final List<Vertex> vertexes;
    private final List<Edge> edges;

    public Graph(List<Vertex> vertexes, List<Edge> edges) {
            this.vertexes = vertexes;
            this.edges = edges;
    }

    public List<Vertex> getVertexes() {
            return vertexes;
    }

    public List<Edge> getEdges() {
            return edges;
    }

    
}
