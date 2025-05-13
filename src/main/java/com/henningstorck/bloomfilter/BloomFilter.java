package com.henningstorck.bloomfilter;

import java.util.function.BiFunction;

public class BloomFilter<T> {
	private final int[] bitArray;
	private final int size;
	private final int rounds;
	private final BiFunction<T, Integer, T> mutate;

	public BloomFilter(int size, int rounds, BiFunction<T, Integer, T> mutate) {
		this.bitArray = new int[size];
		this.size = size;
		this.rounds = rounds;
		this.mutate = mutate;
	}

	public void add(T value) {
		for (int i = 0; i < rounds; i++) {
			T mutatedValue = mutate.apply(value, i);
			int hash = hash(mutatedValue);
			bitArray[hash] += 1;
		}
	}

	public void remove(T value) {
		for (int i = 0; i < rounds; i++) {
			T mutatedValue = mutate.apply(value, i);
			int hash = hash(mutatedValue);
			bitArray[hash] -= 1;
		}
	}

	public boolean contains(T value) {
		for (int i = 0; i < rounds; i++) {
			T mutatedValue = mutate.apply(value, i);
			int hash = hash(mutatedValue);
			if (bitArray[hash] == 0) {
				return false;
			}
		}

		return true;
	}

	private int hash(T value) {
		return Math.abs(value.hashCode() % size);
	}
}
