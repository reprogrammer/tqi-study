package bh;

/**
 * A class that represents the common fields of a cell or body data structure.
 **/
public abstract class Node {
  /**
   * Mass of the node.
   **/
  double mass;
  /**
   * Position of the node
   **/
  MathVector pos;

  // highest bit of int coord
  static final int IMAX = 1073741824;

  // potential softening parameter
  static final double EPS = 0.05;

  /**
   * Construct an empty node
   **/
  protected Node() {
    mass = 0.0;
    pos = new MathVector();
  }

  abstract Node loadTree(Body p, MathVector xpic, int l, Tree root);

  public static final int oldSubindex(MathVector ic, int l) {
    int i = 0;
    for (int k = 0; k < MathVector.NDIM; k++) {
      if (((int) ic.value(k) & l) != 0) i += Cell.NSUB >> (k + 1);
    }
    return i;
  }

  /**
   * Return a string representation of a node.
   * 
   * @return a string representation of a node.
   **/
  public String toString() {
    return mass + " : " + pos;
  }

  /**
   * A class which is used to compute and save information during the gravity computation phse.
   **/
  protected class HG {
    /**
     * Body to skip in force evaluation
     **/
    Body pskip;
    /**
     * Point at which to evaluate field
     **/
    MathVector pos0;
    /**
     * Computed potential at pos0
     **/
    double phi0;
    /**
     * computed acceleration at pos0
     **/
    MathVector acc0;

    /**
     * Create a HG object.
     * 
     * @param b the body object
     * @param p a vector that represents the body
     **/
    HG(Body b, MathVector p) {
      pskip = b;
      pos0 = (MathVector) p.clone();
      phi0 = 0.0;
      acc0 = new MathVector();
    }
  }
}
