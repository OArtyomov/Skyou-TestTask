package com.skyou.service;

import com.skyou.config.ApplicationConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.http.HttpMethod.GET;

@Service
@Slf4j
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class GitHubService {

	private ApplicationConfiguration applicationConfiguration;

	private RestTemplate restTemplate;

	public String getEvents(Long pageNumber, Long pageSize) {
		return restTemplate.exchange(applicationConfiguration.getGithubEventsUrl(), GET,
				null, String.class, of("page", pageNumber, "per_page", pageSize)).getBody();
	}
}