package com.skyou.web;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/github")
public class EventsController {

	@RequestMapping(value = "/publicEvents", method = {RequestMethod.GET}, produces = {APPLICATION_JSON_UTF8_VALUE})
	public String publicEvents(@RequestParam(required = false, defaultValue = "1") Long pageNumber, @RequestParam(required = false, defaultValue = "10") Long pageSize) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Long> params = ImmutableMap.of("page", pageNumber, "per_page", pageSize);
		String url = "https://api.github.com/events?page={page}&per_page={per_page}";
		ResponseEntity<String> content = restTemplate.exchange(url, HttpMethod.GET, null, String.class, params);
		return content.getBody();
	}
}