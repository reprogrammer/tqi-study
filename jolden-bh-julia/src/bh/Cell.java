package bh;
import org.checkerframework.checker.nullness.qual.Raw;
import java.lang.SuppressWarnings;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A class used to represent internal nodes in the tree
 **/
public final class Cell extends Node {
  // subcells per cell
  public final static int NSUB = 8; // 1 << NDIM

  /**
   * The children of this cell node. Each entry may contain either another cell or a body.
   **/
  @Nullable
  Node[] subp;
  @Nullable
  Cell next;

  public Cell() {
    subp = new Node[NSUB];
    next = null;
  }

  /**
   * Descend Tree and insert particle. We're at a cell so we need to move down the tree.
   * 
   * @param p the body to insert into the tree
   * @param xpic
   * @param l
   * @param tree the root of the tree
   * @return the subtree with the new body inserted
   **/
  public final Node loadTree(Body p, MathVector xpic, int l, Tree tree) {
    // move down one level
    int si = oldSubindex(xpic, l);
    Node rt = subp[si];
    if (rt != null)
      subp[si] = rt.loadTree(p, xpic, l >> 1, tree);
    else
      subp[si] = p;
    return this;
  }

  /**
   * Return a string representation of a cell.
   * 
   * @return a string representation of a cell.
   **/
  @SuppressWarnings({"rawness","nullness"})
  public String asString(@Raw Cell this) {
    return "Cell " + super.asString();
  }

}
