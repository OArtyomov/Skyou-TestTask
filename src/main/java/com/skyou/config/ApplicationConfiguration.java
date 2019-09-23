package com.skyou.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ApplicationConfiguration {

	@Value("${github.events}")
	private String githubEventsUrl;
}