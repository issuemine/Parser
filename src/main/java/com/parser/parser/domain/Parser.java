package com.parser.parser.domain;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.parser.parser.util.WordStrategy;

public class Parser {
	private String url;
	private ParserType type;
	private Sorter sorter;
	private Integer division;
	private String sortedWords;

	private Parser(Builder builder) {
		this.url = builder.url;
		this.type = builder.type;
		this.sorter = builder.sorter;
		this.division = builder.division;
	}

	private String getHTML() throws IOException {
		Document document = null;
		try {
			if (!url.startsWith("http")) {
				url = "http://" + url;
			}
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw new IOException("call : getHTML, cause : connection fauilure");
		}

		if (document != null) {
			if (this.type == ParserType.ALL_TYPE) {
				return document.toString();
			} else if (this.type == ParserType.TEXT_TYPE) {
				return document.text();
			}
		}
		return new String();
	}

	private void getSortedWords() throws IOException {
		if (this.sortedWords == null) {
			this.sortedWords = sorter.sort(this.getHTML());
		}
	}
	
	private String getWords(WordStrategy wordStrategy) throws NullPointerException, IOException {
		getSortedWords();
		try {
			int index = sortedWords.length() / division;
			return wordStrategy.getWords(index, this.division);
		} catch (NullPointerException | ArithmeticException ae) {
			throw new NullPointerException("call : getQuotientWords, cause : divide by zero");
		} 
	}
	
	public String getQuotientWords() throws NullPointerException, IOException {
		WordStrategy quotientStrategy = new WordStrategy() {
			@Override
			public String getWords(int index, int division) {
				if (index == 0) {
					return new String();
				} else {
					return sortedWords.substring(0, (index * division));
				}
			}
		};
		return getWords(quotientStrategy);
	}
	
	public String getRemainderWords() throws NullPointerException, IOException {
		WordStrategy remainderStrategy = new WordStrategy() {
			@Override
			public String getWords(int index, int division) {
				if (index == 0) {
					return sortedWords;
				} else {
					return sortedWords.substring((index * division), sortedWords.length());
				}
			}
		};
		return getWords(remainderStrategy);
	}

	public static class Builder {
		private String url;
		private ParserType type;
		private Sorter sorter;
		private Integer division;

		public Builder url(String url) {
			this.url = url.trim();
			return this;
		}

		public Builder type(ParserType type) {
			this.type = type;
			return this;
		}
		
		public Builder sorter(Sorter sorter) {
			this.sorter = sorter;
			return this;
		}
		
		public Builder division(Integer division) {
			this.division = division;
			return this;
		}

		public Parser build() {
			return new Parser(this);
		}
	}

}
