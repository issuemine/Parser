package com.parser.parser.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.parser.parser.constant.ParserConstants;
import com.parser.parser.constant.WordValueMap;

public class SorterImpl implements Sorter {
	public String sort(String text) {
		//문자열을 비교를 위해 character배열로 변경
		Character[] beforeSortedCharArray = toCharacterArray(text);
		
		Arrays.sort(beforeSortedCharArray, new Comparator<Character>() {
			@Override
			public int compare(Character c1, Character c2) {
				Integer c1Value = WordValueMap.WORD_VALUE.get(c1);
				Integer c2Value = WordValueMap.WORD_VALUE.get(c2);
				if (c1Value == null || c2Value == null) {
					return 1;
				}
				return c1Value.compareTo(c2Value);
			}
		});
		
		//문자열을 섞는다.(영문 숫자 순으로)
		beforeSortedCharArray = mixWords(beforeSortedCharArray);
		
		//Character배열을 문자열로 변경
		StringBuilder sortedWordsBuilder = new StringBuilder();
		for (int i = 0; i < beforeSortedCharArray.length; i++) {
			if (beforeSortedCharArray[i] != null) {
				sortedWordsBuilder.append(beforeSortedCharArray[i] + "");
			}
		}
		return sortedWordsBuilder.toString();
	}

	private Character[] toCharacterArray(String text) {
		List<Character> characterList = new ArrayList<>();
		text.chars().forEach((v) -> {
			if ((v >= ParserConstants.NUMBER_ONE_ASCII && v <= ParserConstants.NUMBER_NINE_ASCII) || 
					(v >= ParserConstants.ALPHABET_UPPER_A_ASCII && v <= ParserConstants.ALPHABET_UPPER_Z_ASCII) || 
					(v >= ParserConstants.ALPHABET_LOWER_A_ASCII && v <= ParserConstants.ALPHABET_LOWER_Z_ASCII)) {
				characterList.add(new Character((char) v));
			}
		});
		return characterList.toArray(new Character[characterList.size()]);
	}
	
	private Character[] mixWords(Character[] beforeSortedCharArray) {
		int engIndex = findEngIndex(beforeSortedCharArray);
		if (engIndex == 0) {
			return beforeSortedCharArray;
		}
		
		int wordLength = beforeSortedCharArray.length;
		Character[] shuffledCharArray = new Character[beforeSortedCharArray.length];
		
		int i = 0;
		int j = 0;
		int k = engIndex;
		
		//이미 정렬되어 있으므로 순서만 바꾼다.
		while (i < engIndex && k < wordLength) {
			shuffledCharArray[j++] = beforeSortedCharArray[k++];
			shuffledCharArray[j++] = beforeSortedCharArray[i++];
		}
		
		while (i < engIndex) {
			shuffledCharArray[j++] = beforeSortedCharArray[i++];
		}
		
		while (k < wordLength) {
			shuffledCharArray[j++] = beforeSortedCharArray[k++];
		}
		return shuffledCharArray;
	}
	
	private int findEngIndex(Character[] beforeSortedCharArray) {
		boolean isNumber = true;
		int engIndex = 0;
		for (int i = 0; i < beforeSortedCharArray.length; i++) {
			char c = beforeSortedCharArray[i];
			if (isNumber == true && (c >= ParserConstants.ALPHABET_UPPER_A_ASCII && c <= ParserConstants.ALPHABET_UPPER_Z_ASCII) || 
					(c >= ParserConstants.ALPHABET_LOWER_A_ASCII && c <= ParserConstants.ALPHABET_LOWER_Z_ASCII)) {
				engIndex = i;
				break;
			}
		}
		return engIndex;
	}
}
