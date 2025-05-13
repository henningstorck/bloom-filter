package com.henningstorck.bloomfilter;

public class BloomFilter<T> {
	private final boolean[] bitArray;
	private final int size;

	public BloomFilter(int size) {
		this.bitArray = new boolean[size];
		this.size = size;
	}

	public void add(T value) {
		int hash = hash(value);
		bitArray[hash] = true;
	}

	public boolean contains(T value) {
		int hash = hash(value);
		return bitArray[hash];
	}

	private int hash(T value) {
		return Math.abs(value.hashCode() % size);
	}
}
