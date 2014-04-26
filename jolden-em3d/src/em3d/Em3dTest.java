package em3d;

import static org.junit.Assert.*;

import org.junit.Test;

public class Em3dTest {

  @Test
  public void test() {
    int numNodes = 3;
    int numDegree = 2;
    boolean printResult = false;
    int numIter = 6;
    BiGraph graph = BiGraph.create(numNodes, numDegree, printResult);
    for (int i = 0; i < numIter; i++) {
      graph.compute();
    }
    assertEquals("E: value -16, from_count 2\n" + "E: value -26, from_count 1\n"
        + "E: value -30, from_count 3\n" + "H: value 24, from_count 1\n"
        + "H: value 77, from_count 3\n" + "H: value 36, from_count 2\n", graph.toString());
  }

}
