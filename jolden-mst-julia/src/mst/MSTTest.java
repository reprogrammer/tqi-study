package mst;

import org.checkerframework.checker.nullness.qual.Raw;
import java.lang.SuppressWarnings;
import static org.junit.Assert.*;

import org.junit.Test;

public class MSTTest {

  @SuppressWarnings({"rawness","nullness"})
  @Test
  public void test(@Raw MSTTest this) {
    int vertices = 100;
    Graph graph = new Graph(vertices);
    int dist = MST.computeMST(graph, vertices);
    assertEquals(2166, dist);
  }

}
