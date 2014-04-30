package bh;

import org.checkerframework.checker.nullness.qual.Raw;
import java.lang.SuppressWarnings;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BHTest {

  static @SuppressWarnings({"rawness","nullness"}) double DTIME = 0.0125;
  private static @SuppressWarnings({"rawness","nullness"}) double TSTOP = 2.0;

  @SuppressWarnings({"rawness","nullness"})
  @Test
  public void test(@Raw BHTest this) {
    int nsteps = 10;
    int nbody = 5;
    Tree root = new Tree();
    root.createTestData(nbody);
    double tnow = 0.0;
    int i = 0;
    while ((tnow < TSTOP + 0.1 * DTIME) && (i < nsteps)) {
      root.stepSystem(i++);
      tnow += DTIME;
    }
    StringBuilder sb = new StringBuilder();
    for (Enumerator e = root.bodies(); e.hasMoreElements();) {
      Body b = e.nextElement();
      sb.append(b.pos.asString() + ",");
    }
    assertEquals("-1 -1 0 ,2 0 0 ,0 1 2 ,1 -1 -1 ,-1 1 0 ,", sb.toString());
  }

}
