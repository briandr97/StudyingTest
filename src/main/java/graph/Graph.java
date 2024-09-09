package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Graph {
    private final int vertexCount;
    private List<Boolean> visited = new ArrayList<Boolean>();
    private final List<ArrayList<Integer>> adjacentVertexes;
    private final List<Integer> result;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        adjacentVertexes = IntStream.range(0, vertexCount).mapToObj(idx -> new ArrayList<Integer>()).toList();
        result = new ArrayList<Integer>();
    }

    public List<Integer> getResult() {
        return result;
    }

    public void addEdge(int v1, int v2) {
        adjacentVertexes.get(v1).add(v2);
        adjacentVertexes.get(v2).add(v1);
    }

    public void sortAdjByNatural() {
        adjacentVertexes.forEach(vertexes -> vertexes.sort(Comparator.naturalOrder()));
    }

    public void sortAdjByReversed() {
        adjacentVertexes.forEach(vertexes -> vertexes.sort(Comparator.reverseOrder()));
    }

    public void dfs() {
        visited = new ArrayList<Boolean>(IntStream.range(0, vertexCount).mapToObj(idx -> false).toList());
        dfs(0);
    }

    private void dfs(int startVertex) {
        visited.set(startVertex, true);
        result.add(startVertex);
        // System.out.println("node " + startVertex + " visited");
        adjacentVertexes.get(startVertex).forEach(vertex -> {
            if (!visited.get(vertex)) dfs(vertex);
        });
    }
}
