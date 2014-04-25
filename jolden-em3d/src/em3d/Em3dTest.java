package em3d;

import static org.junit.Assert.*;

import org.junit.Test;

public class Em3dTest {

	@Test
	public void test() {
		int numNodes = 5;
		int numDegree = 2;
		boolean printResult = false;
		int numIter = 6;
		BiGraph graph = BiGraph.create(numNodes, numDegree, printResult);
		for (int i = 0; i < numIter; i++) {
			graph.compute();
		}
		assertEquals(422, graph.toString().length());
	}

}
