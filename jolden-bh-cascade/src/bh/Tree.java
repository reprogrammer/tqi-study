package bh;

import org.checkerframework.checker.nullness.qual.Nullable;


/**
 * A class that represents the root of the data structure used to represent the N-bodies in the
 * Barnes-Hut algorithm.
 **/
public class Tree {
  MathVector rmin;
  double rsize;
  /**
   * A reference to the root node.
   **/
  @Nullable
  Node root;
  /**
   * The complete list of bodies that have been created.
   **/
  private @Nullable Body bodyTab;

  /**
   * Construct the root of the data structure that represents the N-bodies.
   **/
  public Tree() {
    rmin = new MathVector();
    rsize = -2.0 * -2.0;
    root = null;
    bodyTab = null;

    rmin.value(0, -2.0);
    rmin.value(1, -2.0);
    rmin.value(2, -2.0);
  }

  /**
   * Return an enumeration of the bodies.
   * 
   * @return an enumeration of the bodies.
   **/
  public final Enumerator bodies() {
    if (bodyTab == null) {
      return new Enumerator(null);
    } else {
      return bodyTab.elements();
    }
  }

  /**
   * Create the test data used in the benchmark.
   * 
   * @param nbody the number of bodies to create
   **/
  public final void createTestData(int nbody) {
    MathVector cmr = new MathVector();
    MathVector cmv = new MathVector();

    Body head = new Body();
    Body prev = head;

    double rsc = 3.0 * Math.PI / 16.0;
    double vsc = Math.sqrt(1.0 / rsc);
    double seed = 123.0;

    for (int i = 0; i < nbody; i++) {
      Body p = new Body();

      prev.setNext(p);
      prev = p;
      p.mass = 1.0 / (double) nbody;

      seed = BH.myRand(seed);
      double t1 = BH.xRand(0.0, 0.999, seed);
      t1 = Math.pow(t1, (-2.0 / 3.0)) - 1.0;
      double r = 1.0 / Math.sqrt(t1);

      double coeff = 4.0;
      for (int k = 0; k < MathVector.NDIM; k++) {
        seed = BH.myRand(seed);
        r = BH.xRand(0.0, 0.999, seed);
        p.pos.value(k, coeff * r);
      }

      cmr.addition(p.pos);

      double x, y;
      do {
        seed = BH.myRand(seed);
        x = BH.xRand(0.0, 1.0, seed);
        seed = BH.myRand(seed);
        y = BH.xRand(0.0, 0.1, seed);
      } while (y > x * x * Math.pow(1.0 - x * x, 3.5));

      double v = Math.sqrt(2.0) * x / Math.pow(1 + r * r, 0.25);

      double rad = vsc * v;
      double rsq;
      do {
        for (int k = 0; k < MathVector.NDIM; k++) {
          seed = BH.myRand(seed);
          p.vel.value(k, BH.xRand(-1.0, 1.0, seed));
        }
        rsq = p.vel.dotProduct();
      } while (rsq > 1.0);
      double rsc1 = rad / Math.sqrt(rsq);
      p.vel.multScalar(rsc1);
      cmv.addition(p.vel);
    }

    // mark end of list
    prev.setNext(null);
    // toss the dummy node at the beginning and set a reference to the first
    // element
    bodyTab = head.getNext();

    cmr.divScalar((double) nbody);
    cmv.divScalar((double) nbody);

    if (bodyTab != null) {
      for (Enumerator e = bodyTab.elements(); e.hasMoreElements();) {
        Body b = e.nextElement();
        b.pos.subtraction(cmr);
        b.vel.subtraction(cmv);
      }
    }
  }

  /**
   * Advance the N-body system one time-step.
   * 
   * @param nstep the current time step
   **/
  public void stepSystem(int nstep) {
    // free the tree
    root = null;
    makeTree(nstep);
  }

  /**
   * Initialize the tree structure for hack force calculation.
   * 
   * @param nsteps the current time step
   **/
  private void makeTree(int nstep) {
    for (Enumerator e = bodies(); e.hasMoreElements();) {
      Body q = e.nextElement();
      if (q.mass != 0.0) {
        q.expandBox(this, nstep);
        MathVector xqic = intcoord(q.pos);
        assert xqic != null : "@AssumeAssertion(nullness)";
        if (xqic == null) {
          System.out.println("Error: Unexpected body position");
        }
        if (root == null) {
          root = q;
        } else {
          root = root.loadTree(q, xqic, Node.IMAX >> 1, this);
        }
      }
    }
  }

  /**
   * Compute integerized coordinates.
   * 
   * @return the coordinates or null if vp is out of bounds
   **/
  public final @Nullable MathVector intcoord(MathVector vp) {
    MathVector xp = new MathVector();

    double xsc = (vp.value(0) - rmin.value(0)) / rsize;
    if (0.0 <= xsc && xsc < 1.0) {
      xp.value(0, Math.floor(Node.IMAX * xsc));
    } else {
      return null;
    }

    xsc = (vp.value(1) - rmin.value(1)) / rsize;
    if (0.0 <= xsc && xsc < 1.0) {
      xp.value(1, Math.floor(Node.IMAX * xsc));
    } else {
      return null;
    }

    xsc = (vp.value(2) - rmin.value(2)) / rsize;
    if (0.0 <= xsc && xsc < 1.0) {
      xp.value(2, Math.floor(Node.IMAX * xsc));
    } else {
      return null;
    }
    return xp;
  }
}
