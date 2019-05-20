package com.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parser.parser.domain.SorterImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SorterTests {
	private static SorterImpl sorter;
	
	@BeforeClass
	public static void setUp() {
		sorter = new SorterImpl();
	}
	
	@Test
	public void noSortingTest() {
		sorter = new SorterImpl();
		assertThat(sorter.sort("")).isEqualTo("");
	}
	
	@Test
	public void sortingNumberAlgorithmTest() {
		sorter = new SorterImpl();
		assertThat(sorter.sort("9102028143")).isEqualTo("0011223489");
	}
	
	@Test
	public void sortingEnglishAlgorithmTest() {
		sorter = new SorterImpl();
		assertThat(sorter.sort("aboAbBzqaQ")).isEqualTo("AaaBbboQqz");
	}

	@Test
	public void sortingNumberEnglishAlgorithmTest() {
		sorter = new SorterImpl();
		assertThat(sorter.sort("a4boAb1BzqaQ0")).isEqualTo("A0a1a4BbboQqz");
	}
	
	@Test
	public void sortingNumberEnglishSpecialAlgorithmTest() {
		SorterImpl sorter = new SorterImpl();
		assertThat(sorter.sort("<html><body>1stHTML5</body></html>")).isEqualTo("b1b5ddHhhLllMmmoosTtttyy");
	}
	
	@Test
	public void sortingNumberEnglishSpecialAlgorithmTestMoreNumber() {
		SorterImpl sorter = new SorterImpl();
		assertThat(sorter.sort("11293801a")).isEqualTo("a01112389");
	}
}
