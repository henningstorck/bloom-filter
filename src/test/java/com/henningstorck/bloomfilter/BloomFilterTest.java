package com.henningstorck.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BloomFilterTest {
	private BloomFilter<String> bloomFilter;

	@BeforeEach
	void setUp() {
		bloomFilter = new BloomFilter<>(16, 2, (value, i) -> value + i);
		bloomFilter.add("Zeus");
		bloomFilter.add("Hera");
		bloomFilter.add("Artemis");
	}

	@Test
	void testContains() {
		assertTrue(bloomFilter.contains("Zeus"));
		assertFalse(bloomFilter.contains("Hermes"));
	}

	@Test
	void testAdd() {
		bloomFilter.add("Hermes");
		assertTrue(bloomFilter.contains("Zeus"));
		assertTrue(bloomFilter.contains("Hermes"));
	}
}
