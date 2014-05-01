/**
 * 
 * TO PARTICIPANTS: PLEASE DO NOT CHANGE THIS FILE.
 *
 */
package bh;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

class Enumerator {
  private @Nullable Body current;

  public Enumerator(@Nullable Body current) {
    this.current = current;
  }

  @EnsuresNonNullIf(expression = "current", result = true)
  public boolean hasMoreElements() {
    return (current != null);
  }

  @RequiresNonNull("current")
  public Body nextElement() {
    if (current == null) throw new RuntimeException();
    Body retval = current;
    current = current.next;
    return retval;
  }

}
/**
 * 
 * TO PARTICIPANTS: PLEASE DO NOT CHANGE THIS FILE.
 *
 */
