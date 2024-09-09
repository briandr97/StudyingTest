package graph

import org.junit.jupiter.api.assertAll
import kotlin.test.Test
import kotlin.test.assertEquals

class GraphTest {
    @Test
    fun `컴포넌트가 하나인 그래프를 dfs로 내림차순 순회했을때 방문한 정점의 순서를 확인한다`() {
        // given
        val expected = listOf(0, 1, 3, 4, 5, 2, 6, 7, 8);
        val g = Graph(9)
        g.addEdge(0, 1)
        g.addEdge(0, 2)
        g.addEdge(1, 3)
        g.addEdge(1, 5)
        g.addEdge(3, 4)
        g.addEdge(4, 5)
        g.addEdge(2, 6)
        g.addEdge(2, 8)
        g.addEdge(6, 7)
        g.addEdge(6, 8)
        g.sortAdjByNatural()

        // when
        val actual = g.run { dfs(); result }

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `컴포넌트가 하나인 그래프를 dfs로 오름차순 순회했을때 방문한 정점의 순서를 확인한다`() {
        // given
        val expected = listOf(0, 2, 8, 6, 7, 1, 5, 4, 3);
        val g = Graph(9)
        g.addEdge(0, 1)
        g.addEdge(0, 2)
        g.addEdge(1, 3)
        g.addEdge(1, 5)
        g.addEdge(3, 4)
        g.addEdge(4, 5)
        g.addEdge(2, 6)
        g.addEdge(2, 8)
        g.addEdge(6, 7)
        g.addEdge(6, 8)
        g.sortAdjByReversed()

        // when
        val actual = g.run { dfs(); result }

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `컴포넌트가 여러개인 그래프를 dfs로 내림차순 순회하고 컴포넌트와 각 컴포넌트의 정점의 개수를 확인한다`() {
        // given
        val expectedComponentCount = 4
        val expectedVertexCountsOfEachComponent = listOf(4, 2, 3, 1);
        val g = UnConnectedGraph(10)
        g.addEdge(0, 1);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(4, 6);
        g.addEdge(5, 7);
        g.addEdge(5, 8);
        g.addEdge(7, 8);
        g.sortAdjByNatural();

        // when
        val actualComponentCount = g.dfsAll()
        val actualVertexCountsOfEachComponent = g.vertexCountsOfEachComponent

        // then
        assertAll(
            { assertEquals(expectedComponentCount, actualComponentCount) },
            { assertEquals(expectedVertexCountsOfEachComponent, actualVertexCountsOfEachComponent) },
        )
    }
}