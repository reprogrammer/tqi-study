package mst;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A class that represents a vertex in a graph. We maintain a linked list representation of the
 * vertices.
 **/
public class Vertex {
  /**
   * The minimum distance value for the node
   **/
  int mindist;
  /**
   * The next vertex in the graph.
   **/
  @Nullable
  Vertex next;
  /**
   * A hashtable containing all the connected vertices.
   **/
  Hashtable neighbors;

  /**
   * Create a vertex and initialize the fields.
   * 
   * @param n the next element
   **/
  public Vertex(@Nullable Vertex n, int numvert) {
    mindist = 9999999;
    next = n;
    neighbors = new Hashtable(numvert / 4);
  }

  public int mindist() {
    return mindist;
  }

  public void setMindist(int m) {
    mindist = m;
  }

  public @Nullable Vertex next() {
    return next;
  }

  public void setNext(@Nullable Vertex v) {
    next = v;
  }

  public Hashtable neighbors() {
    return neighbors;
  }

}
