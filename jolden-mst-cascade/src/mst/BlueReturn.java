package mst;

class BlueReturn {
  private Vertex vert;
  private int dist;

  public BlueReturn(Vertex vert, int dist) {
    this.vert = vert;
    this.dist = dist;
  }

  public Vertex vert() {
    return vert;
  }

  public void setVert(Vertex v) {
    vert = v;
  }

  public int dist() {
    return dist;
  }

  public void setDist(int d) {
    dist = d;
  }

}
