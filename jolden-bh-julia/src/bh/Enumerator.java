package bh;

class Enumerator {
  private Body current;

  public Enumerator(Body current) {
    this.current = current;
  }

  public boolean hasMoreElements() {
    return (current != null);
  }

  public Body nextElement() {
    Body retval = current;
    current = current.next;
    return retval;
  }
}
