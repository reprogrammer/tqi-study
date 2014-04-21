package mst;

import static org.junit.Assert.*;

import org.junit.Test;

public class MSTTest {

	@Test
	public void test(MSTTest this) {
		int vertices = 100;
		Graph graph = new Graph(vertices);
		int dist = MST.computeMST(graph, vertices);
		assertEquals(2166, dist);
	}

}
