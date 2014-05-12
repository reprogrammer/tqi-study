package mst;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.Raw;
import java.lang.SuppressWarnings;

public class Hashtable {
  protected @Nullable HashEntry array[];
  protected int size;

  public Hashtable(int sz) {
    size = sz;
    array = new HashEntry[size];
  }

  private int hashMap(Object key) {
    return ((key.hashCode() >> 3) % size);
  }

  public @Nullable Object get(Object key) {
    int j = hashMap(key);

    HashEntry ent = null;

    for (ent = array[j]; ent != null && ent.key() != key; ent = ent.next());

    if (ent != null) return ent.entry();
    return null;
  }

  public void put(Object key, Object value) {
    int j = hashMap(key);
    HashEntry ent = new HashEntry(key, value, array[j]);
    array[j] = ent;
  }

  @SuppressWarnings({"rawness","nullness"})
  public void remove(@Raw Hashtable this, @Raw Object key) {
    int j = hashMap(key);
    HashEntry ent = array[j];
    if (ent != null && ent.key() == key)
      array[j] = ent.next();
    else {
      HashEntry prev = ent;
      for (ent = ent.next(); ent != null && ent.key() != key; prev = ent, ent = ent.next());
      prev.setNext(ent.next());
    }
  }

}


class HashEntry {
  private Object key;
  private Object entry;
  private @Nullable HashEntry next;

  public HashEntry(Object key, Object entry, @Nullable HashEntry next) {
    this.key = key;
    this.entry = entry;
    this.next = next;
  }

  public Object key() {
    return key;
  }

  public Object entry() {
    return entry;
  }

  public @Nullable HashEntry next() {
    return next;
  }

  @SuppressWarnings({"rawness","nullness"})
  public void setNext(@Raw HashEntry this, @Raw @Nullable HashEntry n) {
    next = n;
  }

}
