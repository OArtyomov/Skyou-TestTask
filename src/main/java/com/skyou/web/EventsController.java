package com.skyou.web;

import com.skyou.service.GitHubService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/github")
@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class EventsController {

	private GitHubService gitHubService;

	@RequestMapping(value = "/publicEvents", method = {RequestMethod.GET}, produces = {APPLICATION_JSON_UTF8_VALUE})
	public String publicEvents(@RequestParam(required = false, defaultValue = "1") Long pageNumber, @RequestParam(required = false, defaultValue = "10") Long pageSize) {
		return gitHubService.getEvents(pageNumber, pageSize);
	}
}