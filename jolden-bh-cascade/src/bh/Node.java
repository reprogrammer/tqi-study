package bh;

import org.checkerframework.checker.nullness.qual.Nullable;

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
  public String asString() {
    return mass + " : " + pos;
  }

}
