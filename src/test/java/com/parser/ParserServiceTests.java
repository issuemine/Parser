package com.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parser.parser.constant.ParserConstants;
import com.parser.parser.domain.ParserType;
import com.parser.parser.service.ParserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserServiceTests {
	@Autowired
	private ParserService parserService;
	
	private final String URL = "https://naver.com";
	
	@Test
	public void allTypeParserServiceTest() throws ArithmeticException, IOException {
		int division = 100;
		Map<String, String> allTypeResultMap = parserService.makeSortedWords(URL, ParserType.ALL_TYPE, division);
		String allTypeQuotientWords = allTypeResultMap.get(ParserConstants.QUOTIENT_WORDS);
		String allTypeRemainderWords = allTypeResultMap.get(ParserConstants.REMAINDER_WORDS);
		assertThat(allTypeQuotientWords.length() % division).isEqualTo(0);
		assertThat(allTypeRemainderWords.length()).isLessThan(division);
	}
	
	@Test
	public void textTypeParserServiceTest() throws ArithmeticException, IOException {
		int division = 100;
		Map<String, String> textTypeResultMap = parserService.makeSortedWords(URL, ParserType.TEXT_TYPE, division);
		String textTypeQuotientWords = textTypeResultMap.get(ParserConstants.QUOTIENT_WORDS);
		String textTypeRemainderWords = textTypeResultMap.get(ParserConstants.REMAINDER_WORDS);
		assertThat(textTypeQuotientWords.length() % division).isEqualTo(0);
		assertThat(textTypeRemainderWords.length()).isLessThan(division);
	}
}
