package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class UnConnectedDfsGraph {
    private final int vertexCount;
    private List<Boolean> visited = new ArrayList<Boolean>();
    private final List<ArrayList<Integer>> adjacentVertexes;
    private List<Integer> vertexCountsOfEachComponent = new ArrayList<>();

    public UnConnectedDfsGraph(int vertexCount) {
        this.vertexCount = vertexCount;
        adjacentVertexes = IntStream.range(0, vertexCount).mapToObj(idx -> new ArrayList<Integer>()).toList();
    }

    public List<Integer> getVertexCountsOfEachComponent() {
        return vertexCountsOfEachComponent;
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

    public int dfsAll() {
        visited = new ArrayList<Boolean>(IntStream.range(0, vertexCount).mapToObj(idx -> false).toList());
        int componentCount = 0;
        for (int i = 0; i < vertexCount; i++) {
            if (visited.get(i)) continue;
            // System.out.println("---new DFS Begins---");
            // System.out.println("size: " + dfs(i) + "\n");
            vertexCountsOfEachComponent.add(dfs(i));
            componentCount++;
        }
        return componentCount;
    }

    private int dfs(int startVertex) {
        int vertexCountInComponent = 1;
        visited.set(startVertex, true);
        System.out.println("node " + startVertex + " visited");
        for (int vertex : adjacentVertexes.get(startVertex)) {
            if (!visited.get(vertex)) vertexCountInComponent += dfs(vertex);
        }
        return vertexCountInComponent;
    }
}
