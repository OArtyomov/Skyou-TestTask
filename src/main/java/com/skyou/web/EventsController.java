package com.skyou.web;

import io.swagger.annotations.Api;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EventsController {

	@RequestMapping(value = "/github/publicEvents", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public String publicEvents() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.github.com/events?page=2&per_page=2";
		ResponseEntity<String> content = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return content.getBody();
	}
}