package com.skyou.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Data
public class ApplicationConfiguration {

	@Value("${github.events}")
	private String githubEventsUrl;

	@Value("${github.baseUrl}")
	private String githubBaseUrl;
}