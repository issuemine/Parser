package com.parser.parser.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.parser.parser.constant.ParserConstants;
import com.parser.parser.domain.Parser;
import com.parser.parser.domain.ParserType;
import com.parser.parser.domain.SorterImpl;

@Service
public class ParserServiceImpl implements ParserService {
	@Override
	public Map<String, String> makeSortedWords(String url, ParserType type, Integer divisionValue) throws ArithmeticException, IOException {
		Parser parser = new Parser.Builder().url(url)
				.type(type)
				.sorter(new SorterImpl())
				.division(divisionValue)
				.build();
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put(ParserConstants.QUOTIENT_WORDS, parser.getQuotientWords());
		resultMap.put(ParserConstants.REMAINDER_WORDS, parser.getRemainderWords());
		return resultMap;
	}
}
