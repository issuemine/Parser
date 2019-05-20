package com.parser.parser.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class WordValueMap {
	public static Map<Character, Integer> WORD_VALUE = new HashMap<>();
	static {
		int[] priority = {0};
		//문자의 우선순위를 정화기 위한 Map 세팅
		IntStream.range(48, 58).forEach((v) -> WORD_VALUE.put((char) v, priority[0]++));
		"AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz".chars().forEach((v) -> WORD_VALUE.put((char) v, priority[0]++));
	}
}
