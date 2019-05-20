package com.parser.parser.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parser.parser.domain.ParserType;
import com.parser.parser.service.ParserService;

@Controller
public class ParserController {
	private static final Logger logger = LoggerFactory.getLogger(ParserController.class);
	
	@Autowired
	private ParserService parserService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPage() {
		return "main";
	}
	
	@ResponseBody
	@RequestMapping(value = "/parsing", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> make(String url, String type, Integer division) {
		try {
			Map<String, String> result = parserService.makeSortedWords(url, ParserType.valueOf(type), division);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (NullPointerException e) {
			logger.info(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (IOException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
		}
	}
}
