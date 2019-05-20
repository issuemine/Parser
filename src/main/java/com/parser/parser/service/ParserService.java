package com.parser.parser.service;

import java.io.IOException;
import java.util.Map;

import com.parser.parser.domain.ParserType;

public interface ParserService {
	public Map<String, String> makeSortedWords(String url, ParserType type, Integer divisionValue) throws ArithmeticException, IOException;
}
