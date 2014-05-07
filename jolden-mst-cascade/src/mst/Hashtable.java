package mst;

import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Hashtable {
	protected HashEntry array[];
	protected int size;

	public Hashtable(int sz) {
		size = sz;
		array = new HashEntry[size];
	}


	private int hashMap(@Nullable Vertex key) {
		if(key!=null)
		return ((key.hashCode() >> 3) % size);
		return 0;
	}


	public @Nullable Object get(Object key) {
		int j = hashMap((Vertex) key);

		HashEntry ent = null;

		for (ent = array[j]; ent != null && ent.key() != key; ent = ent.next());

		if (ent != null) return ent.entry();
		return null;
	}

	public void put( @Nullable Vertex key, Object value) {
		int j = hashMap(key);
		HashEntry ent = new HashEntry(key, value, array[j]);
		array[j] = ent;
	}

	public void remove(Object key) {
		int j = hashMap((Vertex) key);
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
	private @Nullable Vertex key;
	private Object entry;
	private HashEntry next;

	public HashEntry(@Nullable Vertex key, Object entry, HashEntry next) {
		this.key = key;
		this.entry = entry;
		this.next = next;
	}

	public @Nullable Vertex key() {
		return key;
	}

	public Object entry() {
		return entry;
	}

	public HashEntry next() {
		return next;
	}

	public void setNext(HashEntry n) {
		next = n;
	}

}
