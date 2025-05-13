package com.henningstorck.bloomfilter;

import java.util.function.BiFunction;

public class BloomFilter<T> {
	private final boolean[] bitArray;
	private final int size;
	private final int rounds;
	private final BiFunction<T, Integer, T> mutate;

	public BloomFilter(int size, int rounds, BiFunction<T, Integer, T> mutate) {
		this.bitArray = new boolean[size];
		this.size = size;
		this.rounds = rounds;
		this.mutate = mutate;
	}

	public void add(T value) {
		for (int i = 0; i < rounds; i++) {
			T mutatedValue = mutate.apply(value, i);
			int hash = hash(mutatedValue);
			bitArray[hash] = true;
		}
	}

	public boolean contains(T value) {
		for (int i = 0; i < rounds; i++) {
			T mutatedValue = mutate.apply(value, i);
			int hash = hash(mutatedValue);
			if (!bitArray[hash]) {
				return false;
			}
		}

		return true;
	}

	private int hash(T value) {
		return Math.abs(value.hashCode() % size);
	}
}
