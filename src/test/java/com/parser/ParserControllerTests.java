package com.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.parser.constant.ParserConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserControllerTests {
	@Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    private final String TARGET_URL = "https://naver.com";
    
    private final String TYPE = "TEXT_TYPE";
    
    private final Integer DIVISION = 100;
    
    @Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
    
    @Test
    public void normalTest() throws Exception {
    	String requestUrl = "/parsing?url=" + TARGET_URL + "&type=" + TYPE + "&division=" + DIVISION;
    	MvcResult result = mockMvc.perform(get(requestUrl)).andExpect(status().isOk()).andReturn();
    	MockHttpServletResponse response = result.getResponse();
    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, String> resultMap = mapper.readValue(response.getContentAsString(), HashMap.class);
    	
    	String textTypeQuotientWords = resultMap.get(ParserConstants.QUOTIENT_WORDS);
		String textTypeRemainderWords = resultMap.get(ParserConstants.REMAINDER_WORDS);
		assertThat(textTypeQuotientWords.length() % DIVISION).isEqualTo(0);
		assertThat(textTypeRemainderWords.length()).isLessThan(DIVISION);
    }
    
    @Test
    public void badRequestTest() throws Exception {
    	String requestUrl = "/parsing?url=" + TARGET_URL + "&type=" + TYPE + "&division=" + 0;
    	mockMvc.perform(get(requestUrl)).andExpect(status().isBadRequest()).andReturn();
    }
    
    @Test
    public void badGatewayTest() throws Exception {
    	String requestUrl = "/parsing?url=" + "abc" + "&type=" + TYPE + "&division=" + 100;
    	mockMvc.perform(get(requestUrl)).andExpect(status().isBadGateway()).andReturn();
    }
}
