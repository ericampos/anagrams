package com.eric.anagram;

import com.eric.anagram.service.AnagramGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnagramApplicationTests {

	@Autowired
	AnagramGenerator anagramGenerator;

	@Test
	void testValidAnagrams() {
		List<String> result = AnagramGenerator.generateAnagrams("abc");
		List<String> expected = Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba");
		assertTrue(result.containsAll(expected) && expected.containsAll(result), "Anagrams do not match expected output");
	}

	@Test
	void testSingleCharacter() {
		List<String> result = AnagramGenerator.generateAnagrams("a");
		assertEquals(Collections.singletonList("a"), result, "Single character test failed");
	}

	@Test
	void testEmptyInput() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			AnagramGenerator.generateAnagrams("");
		});
		assertEquals("Input must be a non-empty string of letters", exception.getMessage());
	}

	@Test
	void testInvalidInput() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			AnagramGenerator.generateAnagrams("123");
		});
		assertEquals("Input must be a non-empty string of letters", exception.getMessage());
	}

	@Test
	void testDuplicateCharacters() {
		List<String> result = AnagramGenerator.generateAnagrams("aab");
		List<String> expected = Arrays.asList("aab", "aba", "baa");
		assertTrue(result.containsAll(expected) && expected.containsAll(result), "Duplicate character test failed");
	}
}
